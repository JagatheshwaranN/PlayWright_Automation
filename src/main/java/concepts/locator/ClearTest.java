package concepts.locator;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

public class ClearTest {

    @Test
    public void testLocatorClear() {
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

            // Locate an input element using the test ID "username"
            Locator input = page.getByTestId("username");

            // Fill the located input field with the text "Playwright"
            input.fill("Playwright");

            // Locate the textbox by ARIA role and clear its content
            page.getByRole(AriaRole.TEXTBOX).clear();
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
