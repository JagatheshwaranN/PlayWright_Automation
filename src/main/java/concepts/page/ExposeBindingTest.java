package concepts.page;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

public class ExposeBindingTest {

    @Test
    public void testPageExposeBinding() {

        // Initialize playwright variable to null
        Playwright playwright = null;

        // Declaring variable for a browser type
        BrowserType browserType;

        // Initialize browser variable to null
        Browser browser = null;

        try {
            // Create a Playwright instance
            playwright = Playwright.create();

            // Select Chromium as the browser type
            browserType = playwright.chromium();

            // Launch a browser in non-headless mode (visible window).
            browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));

            // Create a new isolated browser context.
            BrowserContext browserContext = browser.newContext();

            // Create a new page within the browser context
            Page page = browserContext.newPage();

            // Expose a Java method 'pageURL' to the page that returns the page
            // URL (Expose a binding named "pageURL" to JavaScript)
            page.exposeBinding("pageURL", ((source, args) -> {
                // Return the current page URL when called from JavaScript
                return source.page().url();
            }));

            // Set the page content, including JavaScript code
            page.setContent("""
                        <script>
                        async function onClick() {
                            // Call the exposed "pageURL" binding and update the div content
                            document.querySelector('div').textContent = await window.pageURL();
                        }
                        </script>
                        <button onclick="onClick()">Click me</button>
                        <div></div>
                    """);

            // Click the button on the page, triggering the JavaScript function
            page.getByRole(AriaRole.BUTTON).click();
        } catch (Exception e) {
            // Print the exception stack trace for debugging
            e.printStackTrace();
        } finally {
            // Close the browser if it's running
            if (browser != null) {
                browser.close();
            }

            // Close the Playwright object to release resources
            if (playwright != null) {
                playwright.close();
            }
        }
    }

}
