package concepts.locator;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

public class HoverTest {

    @Test
    public void testLocatorHover() {
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

            // Use getByRole to find a link element with the role "link" and the name "PlaywrightLink"
            Locator playwrightLink = page.getByRole(AriaRole.LINK,
                    new Page.GetByRoleOptions().setName("PlaywrightLink").setExact(true));

            // Hover over the found link element
            playwrightLink.hover();
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
