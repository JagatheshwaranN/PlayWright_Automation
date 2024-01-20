package concepts.browser_context;

import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

/**
 * The script demonstrates how to intercept and block specific network
 * requests, navigate to a web page, manipulate page elements, and perform
 * assertions in a browser automation test using Playwright. The test
 * focuses on handling image requests and ensuring the visibility of a
 * specific image after un-routing the intercepted requests. Proper exception
 * handling and resource cleanup are included in the script.
 *
 * @author Jagatheshwaran N
 */
public class UnRouteTest {

    @Test
    public void testBrowserContextUnRoute() {
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

            // Create a new page within the browser context
            Page page = browserContext.newPage();

            // Navigate the page to the specified URL
            page.navigate("https://www.amazon.in/");

            // Get the title of the current page
            String title = page.title();

            // Print the title to the console
            System.out.println(title);

            // Unroute network requests for PNG and JPG images in the current browser context
            browserContext.unroute(Pattern.compile("(\\.png$)|(\\.jpg$)"));

            // Navigate to the same URL after unrouting image requests
            page.navigate("https://www.amazon.in/");

            // Locate the first image with alt attribute set to 'Footwear'
            Locator imageLocator = page.locator("xpath=(//img[@alt='Footwear'])[1]");

            // Assert that the located image is visible on the page
            Assert.assertTrue(imageLocator.isVisible());
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
