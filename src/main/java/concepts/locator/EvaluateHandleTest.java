package concepts.locator;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

public class EvaluateHandleTest {

    @Test
    public void testLocatorEvaluateHandle() {
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

            // Locate the element with the id "username" on the page
            Locator username = page.locator("#username");

            // Evaluate a JavaScript expression to set the value of the element with id "username" to 'Playwright'
            // The evaluateHandle method returns a JSHandle representing the result of the evaluation
            JSHandle handle = username.evaluateHandle("document.getElementById('username').value='Playwright'");

            // Print the JSON value of the handle to the console
            System.out.println(handle.jsonValue());
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
