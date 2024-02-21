package concepts.js_handle;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

public class JsonValueTest {

    @Test
    public void testJSHandleAsJsonValue() {

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
            browser = browserType.launch();

            // Create a browser context with touch support
            BrowserContext browserContext = browser.newContext();

            // Create a new page within the context
            Page page = browserContext.newPage();

            // Navigate to a local HTML file using the file:// protocol
            page.navigate("file:///D:/Environment_Collection/Intellij_Env/Playwright_Concepts/support/jshandle.html");

            // Evaluate JavaScript to get a handle to the myObject.value property
            JSHandle valueHandle = page.evaluateHandle("() => window.myObject.value");

            // Get the value from the handle
            Object value = valueHandle.jsonValue();

            // Print the value
            System.out.println("Current value: " + value);
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
