package concepts.browser_context;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

/**
 * The main purpose of this test is to showcase how to use Playwright to set up an event
 * listener for successfully finished network requests within a browser context. The code
 * navigates to specific URL and logs information about the finished requests,
 * demonstrating the capability to capture network activity details.
 * <p>
 * The onRequestFinished method in Playwright for Java is used to set up an event listener
 * that gets triggered when a network request is successfully finished within a specific
 * BrowserContext. This event provides information about the completed request, allowing
 * you to perform actions or gather data related to the network activity.
 *
 * @author Jagatheshwaran N
 */
public class OnRequestFinishedTest {

    @Test
    public void testBrowserContextOnRequestFinished() {
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

            // Register a handler to be invoked when a request finishes within the browser context
            browserContext.onRequestFinished(request -> {
                // Print the URL of the finished request to the console
                System.out.println("Request URL : " + request.url());

                // Print the HTTP status code of the response to the console
                System.out.println("Request Status : " + request.response().status());
            });

            // Create a new page in the browser context
            Page page = browserContext.newPage();

            // Navigate the page to the Amazon India website
            page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php");

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
