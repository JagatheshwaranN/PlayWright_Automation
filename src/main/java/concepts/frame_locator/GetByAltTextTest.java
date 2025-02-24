package concepts.frame_locator;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

/**
 * The code tests Playwright's ability to interact with iframes and locate an image
 * within the iframe by its alt attribute value.
 * It launches a Chromium browser, navigates to a specific page with an iframe, locates
 * the iframe, finds an image within the iframe with the alt attribute value "TeddyBears",
 * checks its visibility, and prints the result to the console.
 * The browser is launched in non-headless mode for visual inspection, and proper resource
 * cleanup is implemented in the finally block.
 *
 * @author Jagatheshwaran N
 */
public class GetByAltTextTest {

    @Test
    public void testFrameLocatorGetByAltText() {

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

            // Navigate to a local HTML file using the file:// protocol
            page.navigate("file:///D:/Environment_Collection/Intellij_Env/Playwright_Concepts/support/iframe.html");

            // Locate the iframe with the ID "myIframe"
            FrameLocator frame = page.frameLocator("#myIframe");

            // Locate an image within the iframe using its alt attribute value "TeddyBears"
            Locator teddyImage = frame.getByAltText("TeddyBears");

            // Print whether the Teddy image is visible on the page
            System.out.println("Teddy Image Displayed? : " + teddyImage.isVisible());
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