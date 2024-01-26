package concepts.cdp_session;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.Test;

/**
 * The code demonstrates the use of Playwright and CDP to navigate to a web page, enable
 * performance metrics, retrieve and print those metrics, and handle exceptions while
 * ensuring proper resource cleanup. It serves as a basic example of web automation with
 * a focus on performance monitoring.
 * <p>
 * The CDPSession.send method in Playwright's Java API is used to send a Chrome DevTools
 * Protocol (CDP) command to the browser. Chrome DevTools Protocol is the underlying
 * protocol used by the Chrome browser and other compatible browsers for debugging and
 * interacting with the browser programmatically.
 *
 * @author Jagatheshwaran N
 */
public class SendTest {

    @Test
    public void testCDPSessionSend() {
        // Initialize a Playwright object
        Playwright playwright = null;

        // Declare variable for a browser type
        BrowserType browserType;

        // Declare variable for browser instance
        Browser browser = null;

        try {
            // Create a new Playwright instance
            playwright = Playwright.create();

            // Select the Chromium browser
            browserType = playwright.chromium();

            // Launch a new browser instance in non-headless mode
            browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));

            // Create a new browser context
            BrowserContext context = browser.newContext();

            // Create a new page within the context
            Page page = context.newPage();

            // Create a CDP session for interacting with the browser's DevTools Protocol
            CDPSession cdpSession = context.newCDPSession(page);

            // Enable the Performance domain in CDP
            cdpSession.send("Performance.enable");

            // Navigate to the Playwright website
            page.navigate("https://playwright.dev/");

            // Wait for the page to load fully
            page.waitForLoadState(LoadState.NETWORKIDLE);

            // Retrieve performance metrics using CDP
            JsonObject metrics = cdpSession.send("Performance.getMetrics", null);

            // Print the raw metrics object
            System.out.println(metrics);

            // Extract the array of metrics from the response
            JsonArray metricsArray = metrics.getAsJsonArray("metrics");

            for (JsonElement metricElement : metricsArray) {

                // Get the individual metric object
                JsonObject metricObject = metricElement.getAsJsonObject();

                // Extract the metric name and value
                String name = metricObject.getAsJsonPrimitive("name").getAsString();
                String value = metricObject.getAsJsonPrimitive("value").getAsString();

                // Print the name-value pair
                System.out.println(name + " = " + value);
            }

        } catch (Exception ex) {
            // Print any exceptions that occur
            ex.printStackTrace();
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

