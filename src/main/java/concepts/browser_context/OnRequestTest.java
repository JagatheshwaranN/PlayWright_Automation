package concepts.browser_context;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

/**
 * The code demonstrates how to use Playwright to intercept network requests, providing a
 * mechanism to customize behavior based on the type of requests made during automated
 * browser testing. The specific example in the code focuses on capturing and printing the
 * status codes of image requests while interacting with a webpage.
 * <p>
 * In Playwright's Java API, the BrowserContext.onRequest method is used to set up a request
 * handler for intercepting and modifying network requests within a specific browser context.
 *
 * @author Jagatheshwaran N
 */
public class OnRequestTest {

    @Test
    public void testBrowserContextOnRequest() {
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

            // Set up an event listener for intercepting network requests within the browser context
            browserContext.onRequest(request -> {

                // Extract the URL of the current request
                String url = request.url();

                // Check if the URL contains ".jpg" (indicating an image request)
                if (url.contains(".jpg")) {

                    // Retrieve the status code of the response associated with the request
                    String status = String.valueOf(request.response().status());

                    // Print the status of the image request to the system console
                    System.out.println("Image Status : " + status);
                }
            });

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
