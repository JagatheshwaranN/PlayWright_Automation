package concepts.locator;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

public class FrameLocatorTest {

    @Test
    public void testLocatorFrameLocator() {
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

            // Navigate to the specified URL
            page.navigate("http://www.londonfreelance.org/courses/frames/index.html");

            // Locate the frame with the name 'main' and then locating the h2 element within that frame
            String header = page.frameLocator("frame[name='main']").locator("h2").textContent();

            // Print the text content of the h2 element
            System.out.println(header);
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
