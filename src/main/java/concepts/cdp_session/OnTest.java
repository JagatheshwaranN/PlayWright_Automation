package concepts.cdp_session;

import com.google.gson.JsonObject;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.Test;

/**
 * Not Implemented
 *
 * @author Jagatheshwaran N
 */
public class OnTest {

    @Test
    public void testCDPSessionOn() {
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
//            CDPSession cdpSession = context.newCDPSession(page);

//            cdpSession.on("Network.requestWillBeSent", event -> {
//                System.out.println("Request URL" +event.getAsJsonObject().getAsJsonPrimitive("request").getAsJsonObject().getAsJsonPrimitive("url").getAsString());
//            });

//            cdpSession.on("Network.responseReceived", event -> {
//                JsonObject response = event.getAsJsonObject("response");
//                String url = response.getAsJsonPrimitive("url").getAsString();
//                int status = response.getAsJsonPrimitive("status").getAsInt();
//
//                System.out.println("Response received - URL: " + url + ", Status: " + status);
//            });
//
//            cdpSession.on("Network.requestWillBeSent", event -> {
//                System.out.println("Request will be sent: " + event);
//            });

            // Navigate to Amazon.in
            page.navigate("https://www.amazon.in/");

            // Wait for the page to load completely
            page.waitForLoadState(LoadState.LOAD);

            // Click on the shopping cart icon
            page.click(".nav-cart-icon.nav-sprite");

            // Attach an event handler after the navigation and click actions
            CDPSession cdpSession = page.context().newCDPSession(page);
            cdpSession.on("Network.requestWillBeSent", event -> {
                System.out.println("Request will be sent: " + event);
            });

            // Wait for some time or use proper synchronization mechanisms
            // You might need to wait for specific network events or other conditions

            // Detach the event handler (optional)
            //cdpSession.off("Network.requestWillBeSent", event);

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

