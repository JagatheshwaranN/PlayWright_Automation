package concepts.browser_context;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

/**
 * The code is to test how a browser behaves in an offline mode using Playwright.
 * It launches a Chromium browser, sets it to non-headless mode, creates a browser
 * context, simulates an offline network environment for that context, opens a new
 * page, and navigates to the Playwright documentation for Java. The code includes
 * proper cleanup steps in the finally block to close the browser and release
 * resources.
 *
 * @author Jagatheshwaran N
 */
public class OfflineTest {

    @Test
    public void testBrowserContextOffline() {
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

            // Simulate an offline network environment for the browser context.
            browserContext.setOffline(true);

            // Set up a blank page within the established context
            Page page = browserContext.newPage();

            // Send the page to the Playwright documentation for Java
            page.navigate("https://playwright.dev/java");
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
