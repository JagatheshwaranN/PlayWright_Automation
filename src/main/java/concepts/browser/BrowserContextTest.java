package concepts.browser;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

/**
 * This code serves as a test demonstrating Playwright's functionality to create
 * an isolated browser context within a Chromium browser, create a new page within
 * that context, navigate to a specific URL, and retrieve/display the title of the
 * page. It includes error handling and resource cleanup to maintain a clean
 * execution environment.
 *
 * @author Jagatheshwaran N
 */
public class BrowserContextTest {

    @Test
    public void testBrowserContext() {
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

            // Create a new isolated browser context.
            BrowserContext browserContext = browser.newContext();

            // Create a new page within the context.
            Page page = browserContext.newPage();

            // Navigate the page to the specified URL.
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
