package concepts.page;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

public class BringToFrontTest {

    @Test
    public void testPageBringToFront() {

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
            Page page1 = browserContext.newPage();

            // Create another new page in the same browser context
            Page page2 = browserContext.newPage();

            // Navigate the first page to the specified URL
            page1.navigate("https://playwright.dev/");

            // Navigate the second page to a different URL
            page2.navigate("https://www.google.com/");

            // Bring the first page to the front (make it the active page)
            page1.bringToFront();

            // Retrieve the title of the active page (first page)
            String pageTitle = page1.title();

            // Print the title of the active page to the console
            System.out.println(pageTitle);
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