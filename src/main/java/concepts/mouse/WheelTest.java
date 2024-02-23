package concepts.mouse;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.BoundingBox;
import org.testng.annotations.Test;

public class WheelTest {

    @Test
    public void testMouseWheel() {

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

            // Navigate to the Playwright for Java documentation page
            page.navigate("https://playwright.dev/java/");

            // Scroll down on the page by simulating a mouse wheel event with a positive deltaY
            page.mouse().wheel(0, 500); // Positive deltaY for scrolling down

            // Scroll up on the page by simulating a mouse wheel event with a negative deltaY
            page.mouse().wheel(0, -500); // Negative deltaY for scrolling up

            // Locate the element with the text content 'GitHub' on the page
            BoundingBox box = page.locator("'GitHub'").boundingBox();

            // Scroll to the vertical position (y-coordinate) of the located element
            page.mouse().wheel(0, box.y);
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
