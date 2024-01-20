package concepts.browser;

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
public class NewPageTest {

    @Test
    public void testBrowserNewPage() {
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

            // Create a new page with a base URL set to https://playwright.dev/.
            Page page = browser.newPage(new Browser.NewPageOptions().setBaseURL("https://playwright.dev/"));

            // Navigate to the "java" page relative to the base URL.
            page.navigate("java");

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
