package concepts.locator;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

public class OrTest {

    @Test
    public void testLocatorOr() {
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

            // Define a locator for the username input field using the CSS selector "#username"
            Locator username = page.locator("#username")

                    // Use the "or" method to create a compound locator that combines the previous locator with another locator
                    .or(page.locator("input[title='name']"));

            // Print a message indicating whether the username input field is visible or not
            System.out.println("UserName input field is visible? : " + username.isVisible());
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
