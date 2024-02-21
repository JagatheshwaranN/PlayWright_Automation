package concepts.js_handle;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

public class EvaluateTest {

    @Test
    public void testJSHandleEvaluate() {

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
            page.navigate("file:///D:/Environment_Collection/Intellij_Env/Playwright_Concepts/support/list.html");

            // Evaluate JavaScript on the page to find an element with the ID 'google'
            // This returns a JSHandle, which is a reference to the DOM element found by `document.getElementById('google')`
            JSHandle jsHandle = page.evaluateHandle("() => document.getElementById('google')");

            // Evaluate a JavaScript function in the context of the element referenced by jsHandle
            // This function retrieves the innerText of the element, allowing you to access the text content of the element
            Object elementText = jsHandle.evaluate("element => element.innerText");

            // Print the text content of the element to the console
            System.out.println(elementText);
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
