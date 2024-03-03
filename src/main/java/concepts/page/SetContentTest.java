package concepts.page;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

public class SetContentTest {

    @Test
    public void testPageSetContent() {

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

            // Navigate to a local HTML file using the file:// protocol
            page.navigate("file:///D:/Environment_Collection/Intellij_Env/Playwright_Concepts/support/content.html");

            // Set the HTML content of the frame to a specific string
            page.setContent("<h1 id='header'>Playwright - Set HTML Content Dynamically</h1>");

            // Use a locator to find an element with the id "header" inside the page
            String pageContent = page.locator("#header").textContent();

            // Print the text content of the found element to the console
            System.out.println(pageContent);
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