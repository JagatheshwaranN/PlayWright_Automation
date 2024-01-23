package concepts.browser_context;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

/**
 * The code showcases a scenario where network responses within a specific browser context are
 * monitored using the onResponse handler. This handler extracts and prints information from
 * responses with URLs containing ".jpg," demonstrating a practical use case for handling
 * specific types of network responses during automated browser testing.
 * <p>
 * In the Playwright Java API, BrowserContext.onResponse(handler) is a method used to register
 * a handler function that will be called whenever a network response is received within the
 * context of a specific browser context.
 *
 * @author Jagatheshwaran N
 */
public class OnResponseTest {

    @Test
    public void testBrowserContextOnResponse() {
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

            // Register a listener to handle response events within the browser context
            browserContext.onResponse(response -> {

                // Check if the response URL contains the ".jpg" extension
                if (response.url().contains(".jpg")) {

                    // Extract the response status code
                    String status = String.valueOf(response.status());

                    // Retrieve the value of the "Content-Type" header
                    String headerValue = response.headerValue("Content-Type");

                    // Print the response status and content type to the console
                    System.out.println("Response Status: " + status);
                    System.out.println("Response Content Type: " + headerValue);
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
