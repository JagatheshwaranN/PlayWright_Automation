package concepts.browser_context;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

/**
 * This code serves as a basic test to demonstrate Playwright's functionalities
 * by launching a visible Chromium browser, navigating to a specific page within
 * it, and retrieving and displaying the title of that page. It also includes
 * error handling and resource cleanup to maintain a clean execution environment.
 *
 * @author Jagatheshwaran N
 */
public class BrowserContextNewPageTest {

    @Test
    public void testBrowserContextNewPage() {
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
