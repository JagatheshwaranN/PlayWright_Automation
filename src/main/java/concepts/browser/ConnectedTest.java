package concepts.browser;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;

/**
 * This code serves as a basic test to check whether a browser launched using
 * Playwright can establish a connection successfully. It's structured to handle
 * potential errors and properly manage resources to ensure a clean execution
 * environment.
 *
 * @author Jagatheshwaran N
 */
public class ConnectedTest {

    @Test
    public void testBrowserConnected() {
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

            // Launch a new browser instance
            browser = browserType.launch();

            // Check the browser's connection status and print it to the console.
            System.out.println(browser.isConnected());
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
