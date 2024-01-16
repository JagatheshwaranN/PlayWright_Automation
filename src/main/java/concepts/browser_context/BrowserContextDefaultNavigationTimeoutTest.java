package concepts.browser_context;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

/**
 * This test is to showcase how to customize the default navigation timeout for
 * a browser context in Playwright and then demonstrate a simple page navigation
 * with subsequent title retrieval. This is useful for handling scenarios where
 * you want to control the timeout for page navigation within a specific context.
 * <p>
 * BrowserContext.setDefaultNavigationTimeout(timeout) method is used to set the
 * default navigation timeout for all pages within a specific browser context.
 * The navigation timeout is the maximum amount of time that Playwright will wait
 * for a page to successfully navigate to a new URL.
 *
 * @author Jagatheshwaran N
 */
public class BrowserContextDefaultNavigationTimeoutTest {

    @Test
    public void testBrowserContextDefaultNavigationTimeoutTest() {
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

            // Create a new browser context to manage pages and settings
            BrowserContext browserContext = browser.newContext();

            // Set a default timeout for page navigation's within this context
            browserContext.setDefaultNavigationTimeout(5000);  // Wait up to 5 seconds for navigation's

            // Create a new page within the context
            Page page = browserContext.newPage();

            // Send the page to the Playwright documentation for Java
            page.navigate("https://playwright.dev/java");

            // Get the title of the current page.
            String title = page.title();

            // Print the title to the console.
            System.out.println(title);
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
