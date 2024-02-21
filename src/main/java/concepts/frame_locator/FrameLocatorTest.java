package concepts.frame_locator;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

/**
 * The code tests Playwright's ability to interact with frames on a webpage, and it launches
 * a Chromium browser, navigates to a specific page, locates a frame with the name attribute
 * set to 'main', finds an <h2> element within the located frame, extracts the text content,
 * and prints it to the console.
 * The browser is launched in non-headless mode for visual inspection, and proper resource
 * cleanup is implemented in the finally block.
 *
 * @author Jagatheshwaran N
 */
public class FrameLocatorTest {

    @Test
    public void testFrameLocator() {

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

            // Navigate to a specific webpage
            page.navigate("https://www.londonfreelance.org/courses/frames/index.html");

            // Locate a frame with the name attribute set to 'main'
            FrameLocator frame = page.frameLocator("frame[name='main']");

            // Extract the text content of an <h2> element within the located frame
            String headerText = frame.locator("h2").textContent();

            // Print the extracted header text to the console
            System.out.println(headerText);
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