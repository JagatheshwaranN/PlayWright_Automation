package concepts.locator;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.Test;

public class GetByAltTextTest {

    @Test
    public void testLocatorGetByAltText() {
        // Initialize playwright variable to null
        Playwright playwright = null;

        // Declaring variable for a browser type
        BrowserType browserType;

        // Initialize browser variable to null
        Browser browser = null;

        try {
            // Create Playwright instance
            playwright = Playwright.create();

            // Use Chromium as the browser type
            browserType = playwright.chromium();

            // Launch the browser
            browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));

            // Create a new isolated browser context
            BrowserContext browserContext = browser.newContext();

            // Create a new page within the context
            Page page = browserContext.newPage();

            // Navigate to the specified URL
            page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

            // Wait for the page to reach the network idle state
            page.waitForLoadState(LoadState.NETWORKIDLE);

            // Check the visibility of the OrangeHRM logo by its alt text
            boolean orangeHRMLogo = page.getByAltText("company-branding").isVisible();

            // Print the result of the visibility check
            System.out.println("OrangeHRM Logo Display : " + orangeHRMLogo);
        } catch (Exception ex) {
            // Print the exception stack trace for debugging
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
