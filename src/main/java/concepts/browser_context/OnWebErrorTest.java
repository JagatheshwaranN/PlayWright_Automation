package concepts.browser_context;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

/**
 * The test demonstrates the setup of a Playwright-driven Chromium browser, handles web page
 * errors by registering a web error handler, performs interactions on a web page that may
 * trigger errors, and provides a mechanism to catch and log any exceptions that occur during
 * the test execution. The emphasis is on capturing and handling web errors for effective
 * debugging and troubleshooting.
 *
 * @author Jagatheshwaran N
 */
public class OnWebErrorTest {

    @Test
    public void testBrowserContextOnWebError() {
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

            // Register a web error handler for the browser context
            browserContext.onWebError(webError -> {

                // Log the error message to the console for debugging
                System.out.println(webError.error());
            });

            // Create a new page in the browser context
            Page page = browserContext.newPage();

            // Navigate to the specified URL
            page.navigate("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");

            // Click the button with the ID "jsException", potentially triggering a JavaScript error
            page.click("xpath=//button[@id='jsException']");
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
