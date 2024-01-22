package concepts.browser_context;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

/**
 * The code sets up a browser automation test using Playwright, interacts with a webpage,
 * and demonstrates the use of the onConsoleMessage event listener to capture and handle
 * console messages within a browser context. The console.html file likely contains
 * elements that trigger console messages when interacted with, and the test captures and
 * prints those messages for verification or debugging purposes.
 *
 * @author Jagatheshwaran N
 */
public class OnConsoleMessageTest {

    @Test
    public void testBrowserContextOnConsoleMessage() {
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

            // Create a separate browsing session within the browser
            BrowserContext browserContext = browser.newContext();

            // Set up a blank page within the established context
            Page page = browserContext.newPage();

            // Navigate to the specified HTML file
            page.navigate("file:///D:/Environment_Collection/Intellij_Env/Playwright_Concepts/support/console.html");

            // Registering a callback for handling console messages in a browser context
            browserContext.onConsoleMessage(message -> {

                // Iterating through the arguments of the console message
                for (int i = 0; i < message.args().size(); i++) {

                    // Printing the JSON value of each argument to the system console
                    System.out.println(message.args().get(i).jsonValue());
                }
            });

            // Perform the action that triggers the console message
            page.click("#console");
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
