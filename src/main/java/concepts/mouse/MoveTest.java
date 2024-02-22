package concepts.mouse;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.BoundingBox;
import org.testng.annotations.Test;

public class MoveTest {

    @Test
    public void testMouseMove() {

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

            // Navigate to the Selenium test page demonstrating mouse interactions
            page.navigate("https://www.selenium.dev/selenium/web/mouse_interaction.html");

            // Locate the element with the ID "hover" on the page and get its bounding box
            BoundingBox hoverElement = page.locator("#hover").boundingBox();

            // Move the mouse to the center of the bounding box of the "hover" element
            page.mouse().move(
                    hoverElement.x + hoverElement.width / 2,
                    hoverElement.y + hoverElement.height / 2
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
