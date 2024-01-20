package concepts.browser_context;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.Geolocation;
import org.testng.annotations.Test;

import java.util.List;

/**
 * This test is to showcase how to grant geolocation permissions to a specific
 * website, set a custom geolocation for a browser context, and then navigate
 * to that website to retrieve location information. This is useful for testing
 * location-based functionality in web applications.
 *
 * @author Jagatheshwaran N
 */
public class GeoLocationTest {

    @Test
    public void testBrowserContextGeoLocation() {
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

            // Create a new browser context for managing pages and settings
            BrowserContext browserContext = browser.newContext();

            // Grant geolocation permission to the specific website
            browserContext.grantPermissions(
                    List.of("geolocation"),  // List of permissions to grant
                    new BrowserContext.GrantPermissionsOptions().setOrigin("https://my-location.org/")  // Specify the website
            );

            // Set the context's geolocation to a specific location
            browserContext.setGeolocation(new Geolocation(36.778259, -119.417931));  // Override default coordinates

            // Create a new page within the context
            Page page = browserContext.newPage();

            // Navigate to the website that uses geolocation
            page.navigate("https://my-location.org/");

            // Retrieve the text content of the element containing the address
            String location = page.locator("xpath=//div[@id='address']").textContent();  // Extract address from page

            // Print the extracted location to the console
            System.out.println(location);
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
