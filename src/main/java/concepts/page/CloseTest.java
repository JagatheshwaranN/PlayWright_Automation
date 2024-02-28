package concepts.page;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

public class CloseTest {

    @Test
    public void testPageClose() {

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

            // Create a browser context with touch support
            BrowserContext browserContext = browser.newContext();

            // Create a new page in the browser context
            Page page = browserContext.newPage();

            // Navigate to the specified file URL using Playwright
            page.navigate("https://playwright.dev/");

            // Close the page with specific options
            page.close(
                    new Page.CloseOptions()
                            .setRunBeforeUnload(false) // Specify not to run 'beforeunload' handlers
                            .setReason("Demo purpose") // Provide a custom reason for closing (for demo purpose)
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