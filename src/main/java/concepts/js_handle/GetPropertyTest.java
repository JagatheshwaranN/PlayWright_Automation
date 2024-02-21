package concepts.js_handle;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

public class GetPropertyTest {

    @Test
    public void testJSHandleGetProperty() {

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
            page.navigate("file:///D:/Environment_Collection/Intellij_Env/Playwright_Concepts/support/list.html");

            // Evaluate JavaScript on the page to find an element with the ID 'google'
            // This returns a JSHandle, which is a reference to the DOM element found by `document.getElementById('google')`
            JSHandle jsHandle = page.evaluateHandle("() => document.getElementById('google')");

            // Obtains the "href" property of the element referenced by jsHandle.
            String elementProperty = jsHandle.getProperty("href").toString();

            // Print the string representation of the JSHandle to the console.
            System.out.println(elementProperty);
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
