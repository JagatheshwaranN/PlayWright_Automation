package concepts.cdp_session;

import com.google.gson.JsonObject;
import com.microsoft.playwright.*;
import org.testng.annotations.Test;

/**
 * Not Implemented
 *
 * @author Jagatheshwaran N
 */
public class OffTest {

    @Test
    public void testCDPSessionOff() {
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

            cdpSession.off("Network.responseReceived", event -> {
                JsonObject response = event.getAsJsonObject("response");
                String url = response.getAsJsonPrimitive("url").getAsString();
                int status = response.getAsJsonPrimitive("status").getAsInt();

                System.out.println("Response received - URL: " + url + ", Status: " + status);
            });

            // Navigate to the Playwright website
            page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php");
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

