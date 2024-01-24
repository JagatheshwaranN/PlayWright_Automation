package concepts.browser_context;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

/**
 * The main purpose of this test is to demonstrate how to use Playwright to set up an event
 * listener for failed network requests within a browser context. The code navigates to a URL
 * that returns a 500 Internal Server Error and logs information about the failed request.
 * <p>
 * In Playwright for Java, the BrowserContext.onRequestFailed method allows you to set up a
 * handler function that will be called whenever a network request fails within that browser
 * context.
 *
 * @author Jagatheshwaran N
 */
public class OnRequestFailedTest {

    @Test
    public void testBrowserContextOnRequestFailed() {
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

            // Set up an event listener for intercepting and handling failed network requests
            browserContext.onRequestFailed(request -> {
                // Print information about the failed request, including URL and failure details
                System.err.println("Request failed: " + request.url() + " (" + request.failure() + ")");
            });

            // Create a new page in the browser context
            Page page = browserContext.newPage();

            // Navigate the page to the Amazon India website
            page.navigate("http://httpbin.org/status/500");

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
