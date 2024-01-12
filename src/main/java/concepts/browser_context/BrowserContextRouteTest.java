package concepts.browser_context;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

/**
 * This code sets up a Playwright environment, launches a browser, creates a
 * specific browser context, intercepts and blocks image requests with specific
 * patterns, navigates to a website, retrieves and prints the page title, and
 * finally closes the browser and Playwright objects. The main focus is on
 * demonstrating the usage of Playwright's routing capabilities within a
 * browser context
 * <p>
 * In the Playwright Java API, the route function is used to intercept network
 * requests made by the browser and provide custom handling for those requests.
 * It allows you to modify or block network requests during the test automation
 * process. This can be useful for various scenarios, such as mocking responses,
 * testing error handling, or simulating different network conditions.
 * <p>
 * In the below code, route method is used to intercept and block requests for
 * PNG and JPG images within the browser context.
 *
 * @author Jagatheshwaran N
 */
public class BrowserContextRouteTest {

    @Test
    public void testBrowserContextRoute() {
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

            // Intercept and block PNG and JPG image requests within the browser context
            browserContext.route(Pattern.compile("(\\.png$)|(\\.jpg$)"), Route::abort);

            // Create a new page in the browser context
            Page page = browserContext.newPage();

            // Navigate the page to the Amazon India website
            page.navigate("https://www.amazon.in/");

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
