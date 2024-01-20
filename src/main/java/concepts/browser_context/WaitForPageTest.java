package concepts.browser_context;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

/**
 * This code essentially tests the ability of Playwright to wait for the creation
 * of a new page after a specific action (clicking a link) and then retrieves and
 * prints the title of the new page. It showcases proper resource management by
 * closing the browser and Playwright instance in the finally block.
 * <p>
 * BrowserContext.waitForPage() - These methods allow you to execute an action that
 * triggers the creation of a new page within a browser context, and then wait for
 * that page to become fully available before proceeding with further automation
 * steps. This is essential for handling dynamic page navigation and ensuring that
 * actions are performed on the correct page.
 *
 * @author Jagatheshwaran N
 */
public class WaitForPageTest {

    @Test
    public void testBrowserContextWaitForPage() {
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

            // Create a new browser context
            BrowserContext browserContext = browser.newContext();

            // Create a new page within the context
            Page page = browserContext.newPage();

            // Navigate the page to the specified URL
            page.navigate("https://the-internet.herokuapp.com/windows");

            // Wait for a new page to be created after clicking a link
            Page newPage = browserContext.waitForPage(() -> {
                // Click the link that will trigger the new page:
                page.click("xpath=//a[text()='Click Here']");
            });

            // Get the title of the current page.
            String title = newPage.title();

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
