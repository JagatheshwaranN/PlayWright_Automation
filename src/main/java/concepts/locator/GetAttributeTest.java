package concepts.locator;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

public class GetAttributeTest {

    @Test
    public void testGetAttribute() {
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

            // Navigate to the specified URL
            page.navigate("file:///D:/Environment_Collection/Intellij_Env/Playwright_Concepts/support/list.html");

            // Retrieve the value of the "data-testid" attribute for the element with ID "username"
            String attributeValue1 = page.locator("#username").getAttribute("data-testid");

            // Retrieve the value of the "title" attribute for the element with ID "username"
            String attributeValue2 = page.locator("#username").getAttribute("title");

            // Print the value of the "data-testid" attribute
            System.out.println(attributeValue1);

            // Print the value of the "title" attribute
            System.out.println(attributeValue2);
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
