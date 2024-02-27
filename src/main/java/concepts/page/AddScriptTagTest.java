package concepts.page;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

public class AddScriptTagTest {

    @Test
    public void testPageAddScriptTag() {

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

            // Navigate to a local HTML file
            page.navigate("file:///D:/Environment_Collection/Intellij_Env/Playwright_Concepts/support/list.html");

            // Add a script tag to the page with a specific URL
            page.addScriptTag(new Page.AddScriptTagOptions()
                    .setUrl("D:\\Environment_Collection\\Intellij_Env\\Playwright_Concepts\\support\\scripts\\script.js"));

            // Locate the password input element using its ID
            Locator password = page.locator("#password");

            // Print the current input value of the password element
            System.out.println(password.inputValue());
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