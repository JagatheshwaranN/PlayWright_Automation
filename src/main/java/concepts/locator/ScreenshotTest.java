package concepts.locator;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.ScreenshotAnimations;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class ScreenshotTest {

    @Test
    public void testLocatorScreenshot() {
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

            // Navigate to the demo page
            page.navigate("file:///D:/Environment_Collection/Intellij_Env/Playwright_Concepts/support/list.html");

            // Get a link element by its role using the AriaRole.LINK constant
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions()
                            // Set the name attribute to filter links with the specific name "GoogleLink"
                            .setName("GoogleLink"))
                    // Take a screenshot of the found link element with specified options
                    .screenshot(new Locator.ScreenshotOptions()
                            // Disable animations to capture a static screenshot
                            .setAnimations(ScreenshotAnimations.DISABLED)
                            // Set the file path for saving the screenshot to "support/screens/screenshot.png"
                            .setPath(Paths.get("support/screens/screenshot.png")));
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
