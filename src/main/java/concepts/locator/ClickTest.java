package concepts.locator;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

public class ClickTest {

    @Test
    public void testLocatorClick() {
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

            // Locate the button by ARIA role and click it
            page.getByRole(AriaRole.BUTTON).click();

            // Locate the button element again (not necessary, but included for demonstration)
            Locator buttonElement = page.locator("#submit");

            // Print the text content of the button element
            System.out.println(buttonElement.textContent());
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
