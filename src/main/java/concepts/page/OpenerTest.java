package concepts.page;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

public class OpenerTest {

    @Test
    public void testPageOpener() {

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

            // Create a new page within the context
            Page page = browserContext.newPage();

            // Navigate to the specified URL (https://playwright.dev/java/)
            page.navigate("https://playwright.dev/java/");

            // Get a reference to the page that opened the current page (opener page)
            Page openerPage = page.opener();

            // Check if there is an opener page
            if (openerPage != null) {
                // Print the URL of the opener page if it exists
                System.out.println("Opener page URL: " + openerPage.url());
            } else {
                // Print a message indicating that there is no opener page (main page)
                System.out.println("No opener page (main page)");
            }
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