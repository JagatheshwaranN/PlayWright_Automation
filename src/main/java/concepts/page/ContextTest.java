package concepts.page;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

public class ContextTest {

    @Test
    public void testPageContext() {

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

            // Create a new page associated with the current context
            Page contextAssociatedPage = page.context().newPage();

            // Navigate to the specified file URL using the newly created page
            contextAssociatedPage.navigate("file:///D:/Environment_Collection/Intellij_Env/Playwright_Concepts/support/list.html");

            // Retrieve the title of the page
            String pageTitle = contextAssociatedPage.title();

            // Print the page title to the console
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