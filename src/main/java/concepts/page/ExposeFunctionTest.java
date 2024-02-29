package concepts.page;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

public class ExposeFunctionTest {

    @Test
    public void testPageExposeFunction() {

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

            // Access the page to expose a function within it
            page.exposeFunction(
                    // Define the name of the function that will be accessible from the browser
                    "greetMessage",
                    // Provide the implementation of the function using a lambda expression
                    args -> {
                        // Return the greeting message
                        return "Hi! Welcome to Playwright ExposeFunction.";
                    });

            // Set the page content, including JavaScript code
            page.setContent("""
                        <script>
                        async function onClick() {
                            // Call the exposed "greetMessage" function and update the div content
                            document.querySelector('div').textContent = await window.greetMessage();
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
