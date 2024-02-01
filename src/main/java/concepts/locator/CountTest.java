package concepts.locator;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

public class CountTest {

    @Test
    public void testLocatorCount() {
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

            // Navigate to a local HTML file with a list of items
            page.navigate("file:///D:/Environment_Collection/Intellij_Env/Playwright_Concepts/support/list.html");

            // Count the number of list items on the page using getByRole with ARIA role LISTITEM
            int listItemCount = page.getByRole(AriaRole.LISTITEM).count();

            // Print the count of list items
            System.out.println("Number of list items: " + listItemCount);
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
