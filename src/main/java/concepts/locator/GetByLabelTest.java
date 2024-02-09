package concepts.locator;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

public class GetByLabelTest {

    @Test
    public void testLocatorGetByLabel() {
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

            // Locate the input field by its label with the text "UserName"
            Locator userName = page.getByLabel("UserName");

            // Fill the input field with the value "John"
            userName.fill("John");

            // Locate the input field by its aria-label with the text "password"
            Locator password = page.getByLabel("password");

            // Fill the input field with the value "Secret"
            password.fill("Secret");
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
