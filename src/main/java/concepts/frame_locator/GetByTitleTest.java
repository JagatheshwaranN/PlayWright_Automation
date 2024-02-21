package concepts.frame_locator;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

/**
 * The code tests Playwright's ability to locate an element within a frame
 * on a webpage by its title attribute.
 * It launches a Chromium browser, navigates to a specific page with frames,
 * locates the desired frame, finds an element within the frame using getByTitle,
 * extracts the text content, prints it to the console, and performs proper
 * resource cleanup in the finally block.
 *
 * @author Jagatheshwaran N
 */
public class GetByTitleTest {

    @Test
    public void testFrameLocatorGetByTitle() {

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

            // Locate an element within the iframe using its title attribute with the value "teddy"
            Locator headerElement = frame.getByTitle("teddy");

            // Get the text content of the located element
            String headerText = headerElement.textContent();

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