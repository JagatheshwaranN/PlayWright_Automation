package concepts.locator;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.BoundingBox;
import org.testng.annotations.Test;

public class BoundingBoxTest {

    @Test
    public void testLocatorBoundingBox() {
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

            // Navigate to a local HTML file
            page.navigate("file:///D:/Environment_Collection/Intellij_Env/Playwright_Concepts/support/list.html");

            BoundingBox button = page.locator("#submit").boundingBox();

            page.mouse().click(button.x + button.width / 2, button.y + button.height / 2);

            Locator buttonElement = page.locator("#submit");

            System.out.println(buttonElement.textContent());

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
