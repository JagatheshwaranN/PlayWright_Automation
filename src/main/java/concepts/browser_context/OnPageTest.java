package concepts.browser_context;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

/**
 * The code is to demonstrate how to use Playwright to launch a browser, create a browser
 * context, set up an event listener to be notified when a new page is created, and interact
 * with a page to trigger the creation of a new page. The onPage event handler prints the
 * title of the newly created page, showcasing the ability to perform actions upon page
 * creation within a context.
 *
 * @author Jagatheshwaran N
 */
public class OnPageTest {

    @Test
    public void testBrowserContextOnPage() {
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

            // Set up an event listener handler for the creation of new pages within the browser context
            browserContext.onPage(newPage -> {
                // This code will execute whenever a new page is created
                // Print the title of the newly created page to the console
                System.out.println(newPage.title());
            });

            // Wait for a new page to be created after clicking a link
            browserContext.waitForPage(() -> {
                // Click the link that will trigger the new page:
                page.click("xpath=//a[text()='Click Here']");
            });
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
