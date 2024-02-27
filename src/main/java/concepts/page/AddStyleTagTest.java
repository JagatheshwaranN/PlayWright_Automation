package concepts.page;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

public class AddStyleTagTest {

    @Test
    public void testPageAddStyleTag() {

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

            // Add a style tag to the page with a specific URL
            page.addStyleTag(new Page.AddStyleTagOptions()
                    .setUrl("D:\\Environment_Collection\\Intellij_Env\\Playwright_Concepts\\support\\scripts\\style.css"));

            // Locate the password input element using its ID
            Locator password = page.locator("#password");

            // Use evaluate method to execute JavaScript in the browser context
            String backgroundColor = (String) password.evaluate("ele => window.getComputedStyle(ele).backgroundColor");

            // Print the obtained background color to the console
            System.out.println("Background Color: " + backgroundColor);
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