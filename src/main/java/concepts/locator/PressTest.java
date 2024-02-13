package concepts.locator;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

public class PressTest {

    @Test
    public void testLocatorPress() {
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

            // Create a locator to find an element with the ID "username" on the current page
            Locator username = page.locator("#username");

            // Simulate pressing the "Backspace" key on the located element
            username.press("Backspace");

            // Simulate pressing the "End" key on the located element
            username.press("End");

            // Simulate pressing the "Backspace" key again on the located element
            username.press("Backspace");

            // Print the input value of the located element to the console
            System.out.println(username.inputValue());
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
