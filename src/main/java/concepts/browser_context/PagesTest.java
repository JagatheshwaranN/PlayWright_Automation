package concepts.browser_context;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

import java.util.List;

/**
 * The code serves as a basic example of working with multiple pages within
 * a Playwright browser context, including page creation, navigation, and
 * retrieval of page information. It also demonstrates proper resource cleanup
 * using finally to close the browser and Playwright instance.
 * <p>
 * BrowserContext.pages() is a method used to retrieve a list of all pages
 * associated with the browser context.
 *
 * @author Jagatheshwaran N
 */
public class PagesTest {

    @Test
    public void testBrowserContextPages() {
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

            // Create a new browser context for managing pages
            BrowserContext browserContext = browser.newContext();

            // Create two separate pages within the context
            Page page1 = browserContext.newPage();  // Create the first page
            Page page2 = browserContext.newPage();  // Create the second page

            // Navigate page1 to Playwright Java docs
            page1.navigate("https://playwright.dev/java");

            // Navigate page2 to Google
            page2.navigate("https://www.google.com/");

            // Retrieve a list of all open pages within the context
            List<Page> pages = browserContext.pages();

            // Print the total number of pages in the context
            System.out.println("Number of pages in the context: " + pages.size());
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
