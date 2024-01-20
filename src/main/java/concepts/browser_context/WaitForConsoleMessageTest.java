package concepts.browser_context;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

/**
 * The code demonstrates a basic Playwright script for browser automation,
 * focusing on navigating to a page, interacting with an element, and capturing
 * and printing console messages. Exception handling ensures graceful termination,
 * and cleanup is performed to close the browser and release resources.
 * <p>
 * BrowserContext.waitForConsoleMessage() - Performs action and waits for a
 * ConsoleMessage to be logged by in the pages in the context. Pauses test
 * execution until a specific message is logged to the browser console within
 * the browser context.
 *
 * @author Jagatheshwaran N
 */
public class WaitForConsoleMessageTest {

    @Test
    public void testBrowserContextWaitForConsoleMessage() {
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

            // Wait for a console message to be logged after clicking an element:
            ConsoleMessage consoleMessage = browserContext.waitForConsoleMessage(() -> {
                // Perform the action that triggers the console message
                page.click("#console");
            });

            // Print the captured console message to the system output
            System.out.println("Console Message : " + consoleMessage.text());
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
