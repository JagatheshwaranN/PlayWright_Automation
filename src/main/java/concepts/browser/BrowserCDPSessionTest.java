package concepts.browser;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

/**
 * This code serves as a demonstration of using Playwright to establish a CDP
 * session within a Chromium browser and then detach the session. In a practical
 * scenario, this could be expanded upon to interact with browser internals
 * through the Chrome DevTools Protocol. The code includes error handling and
 * resource cleanup to maintain a clean execution environment.
 *
 * @author Jagatheshwaran N
 */
public class BrowserCDPSessionTest {

    @Test
    public void testBrowserCDPSession() {
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

            // Create a new CDP session for interacting with browser internals.
            CDPSession session = browser.newBrowserCDPSession();

            // Detach the session from the browser, ending its lifecycle.
            session.detach();
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
