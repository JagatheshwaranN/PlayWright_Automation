package concepts.page;

import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

public class UnRouteTest {

    @Test
    public void testPageUnRoute() {
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

            // Create a new page within the browser context
            Page page = browserContext.newPage();

            // Intercept and block PNG and JPG image requests within the browser page
            page.route(Pattern.compile("(\\.png$)|(\\.jpg$)"), Route::abort);

            // Navigate the page to the specified URL
            page.navigate("https://www.amazon.in/");

            // Get the title of the current page
            String title = page.title();

            // Print the title to the console
            System.out.println(title);

            // Unroute network requests for PNG and JPG images in the current browser page
            page.unroute(Pattern.compile("(\\.png$)|(\\.jpg$)"));

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
