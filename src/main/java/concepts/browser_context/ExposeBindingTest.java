package concepts.browser_context;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

/**
 * The code demonstrates a test scenario using Playwright's Java API to interact with
 * a browser page and create a custom binding between Java and JavaScript within the
 * browser context.
 * <p>
 * Exposing Binding: browserContext.exposeBinding() binds a JavaScript function named
 * pageURL to the Java code. This function returns the URL of the current page.
 * The Java lambda expression ((source, args) -> source.page().url()) defines the behavior
 * of the exposed function. It retrieves the URL of the current page using source.page()
 * .url().
 * <p>
 * Page Setup: A new page, page, is created within the browserContext.
 * page.setContent() injects HTML content into the page. It contains a button and a div
 * element. The injected script defines an onClick() function that fetches the URL using
 * window.pageURL() and updates the text content of the div element.
 * <p>
 * Test Scenario:page.getByRole(AriaRole.BUTTON).click() triggers a click on the button,
 * invoking the onClick() function.
 * The onClick() function executes and updates the text content of the div element with
 * the URL obtained from window.pageURL().
 *
 * @author Jagatheshwaran N
 */
public class ExposeBindingTest {

    @Test
    public void testBrowserContextExposeBinding() {

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

            // Expose a Java method 'pageURL' to the browser context that returns the page
            // URL (Expose a binding named "pageURL" to JavaScript)
            browserContext.exposeBinding("pageURL", ((source, args) -> {
                // Return the current page URL when called from JavaScript
                return source.page().url();
            }));

            // Create a new page within the browser context
            Page page = browserContext.newPage();

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
