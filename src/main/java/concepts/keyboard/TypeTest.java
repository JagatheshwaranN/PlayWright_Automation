package concepts.keyboard;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

/**
 * The code tests the Playwright library's capability to simulate typing text with a
 * specified delay between key presses.
 * It launches a Chromium browser, navigates to a page, focuses on an input field,
 * and types "Hello" into the input field with a delay of 200 milliseconds between
 * key presses.
 * The browser is launched in non-headless mode for visual inspection, and proper
 * resource cleanup is implemented in the finally block.
 *
 * @author Jagatheshwaran N
 */
public class TypeTest {

    @Test
    public void testKeyboardType(){
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

            // Store the locator (e.g., CSS selector, XPath) for the input field as String
            String inputLocator = "//input[@title='pass']";

            // Focus on the input field
            page.focus(inputLocator);

            // Simulate typing "Hello" with a delay of 200 milliseconds between key presses
            page.keyboard().type("Hello", new Keyboard.TypeOptions().setDelay(200));
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
