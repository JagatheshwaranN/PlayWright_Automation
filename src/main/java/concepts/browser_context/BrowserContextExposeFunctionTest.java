package concepts.browser_context;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

/**
 * The code demonstrates how to expose a Java function to the browser context and
 * interact with it through JavaScript in a Playwright environment. When the button
 * is clicked, the JavaScript function calls the exposed Java function, updating the
 * content of a <div> element with a greeting message.
 * <p>
 * In Playwright for Java, the exposeFunction method of BrowserContext is used to
 * expose a Java method or object to the page's JavaScript context. This allows you
 * to call Java code from the JavaScript running inside the browser.
 *
 * @author Jagatheshwaran N
 */
public class BrowserContextExposeFunctionTest {

    @Test
    public void testBrowserContextExposeFunction() {

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

            // Access the browser context to expose a function within it
            browserContext.exposeFunction(
                    // Define the name of the function that will be accessible from the browser
                    "greetMessage",
                    // Provide the implementation of the function using a lambda expression
                    args -> {
                        // Return the greeting message
                        return "Hi! Welcome to Playwright ExposeFunction.";
                    });

            // Create a new page within the browser context
            Page page = browserContext.newPage();

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
