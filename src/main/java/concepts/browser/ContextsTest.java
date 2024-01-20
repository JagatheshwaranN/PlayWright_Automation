package concepts.browser;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;

/**
 * This code demonstrates a test scenario that involves managing browser contexts
 * using Playwright in Java. It initializes, interacts with the browser by creating
 * a new context, and properly closes the browser and Playwright instances to maintain
 * resource efficiency and prevent potential errors.
 *
 * @author Jagatheshwaran N
 */
public class ContextsTest {

    @Test
    public void testBrowserContexts() {
        // Initialize playwright variable to null
        Playwright playwright = null;

        // Declaring variable for a browser type
        BrowserType browserType;

        // Initialize browser variable to null
        Browser browser = null;

        // Initialize browserContext variable to null
        BrowserContext browserContext;

        try {
            // Create a Playwright instance
            playwright = Playwright.create();

            // Select Chromium as the browser type
            browserType = playwright.chromium();

            // Launch a new browser instance
            browser = browserType.launch();

            // Print the initial number of browser contexts
            System.out.println(browser.contexts().size());

            // Create a new browser context within the existing browser
            browserContext = browser.newContext();

            // Get the browser type name of the current context (for debugging)
            browserContext.browser().browserType().name();

            // Print the updated number of browser contexts after creating a new one
            System.out.println(browser.contexts().size());

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
