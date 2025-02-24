package concepts.frame_locator;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

/**
 * The code tests Playwright's ability to interact with iframes, and it launches a Chromium
 * browser, navigates to a page with an iframe, locates the iframe using an XPath expression,
 * selects the first frame, and finds an element within the iframe with a specified ARIA role.
 * The text content of the located heading element is then printed to the console.
 * The browser is launched in non-headless mode for visual inspection, and proper resource
 * cleanup is implemented in the finally block.
 *
 * @author Jagatheshwaran N
 */
public class FirstTest {

    @Test
    public void testFrameLocatorFirst() {

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
            page.navigate("file:///D:/Environment_Collection/Intellij_Env/Playwright_Concepts/support/iframes.html");

            // Locate the iframe using an XPath expression targeting an iframe with the name attribute 'frame'
            FrameLocator frame = page.frameLocator("//iframe[@name='frame']");

            // Locate the first frame and find an element by its ARIA role as a heading
            Locator headerElement = frame.first().getByRole(AriaRole.HEADING);

            // Print the text content of the located heading element
            System.out.println(headerElement.textContent());
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