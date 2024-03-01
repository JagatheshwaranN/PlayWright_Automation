package concepts.page;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

public class IsClosedTest {

    @Test
    public void testPageIsClosed() {

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

            // Navigate to the first web page (Playwright website)
            page.navigate("https://playwright.dev/");

            // Get the title of the current page
            String title = page.title();

            // Print the title of the first page
            System.out.println("Page Title: " + title);

            // Check if the page is closed
            boolean pageCloseStatus = page.isClosed();

            // Print whether the page is closed or not
            System.out.println("Is Page Closed?: " + pageCloseStatus);
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