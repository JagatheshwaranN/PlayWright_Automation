package concepts.browser;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;

/**
 * The code uses Java with the Playwright library to start a browser, print its
 * type and name, and handle potential errors by closing the browser properly.
 * It's essentially a basic test method for initializing and interacting with a
 * browser using Playwright in Java, ensuring proper cleanup even if there's an
 * error during setup.
 *
 * @author Jagatheshwaran N
 */
public class BrowserTypeTest {

    @Test
    public void testBrowserType() {
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

            // Print the browser type object (for debugging)
            System.out.println(browser.browserType());

            // Print the browser type name
            System.out.println(browser.browserType().name());
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
