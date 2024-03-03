package concepts.page;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.ScreenshotAnimations;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class ScreenshotTest {

    @Test
    public void testPageScreenshot() {
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

            // Take a screenshot with the specified options
            page.screenshot(
                    // Create a new ScreenshotOptions object to customize the screenshot behavior
                    new Page.ScreenshotOptions()
                            // Disable animations to capture a static snapshot
                            .setAnimations(ScreenshotAnimations.DISABLED)
                            // Set the path where the screenshot will be saved
                            .setPath(Paths.get("support/screens/page-screenshot.png"))
            );

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
