package concepts.page;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

public class AddInitScriptTest {

    @Test
    public void testPageAddInitScript() {

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

            // Creating a new page within the browser context
            Page page = browserContext.newPage();

            // Adding an initialization script to the page
            // This script will display an alert with the message 'Welcome to Example Site!' when the page loads
            page.addInitScript("window.alert('Welcome to Example Site!')");

            // Pausing the execution for 2000 milliseconds (2 seconds)
            // This is often used to provide time for the alert to be displayed before further actions
            Thread.sleep(2000);

            // Navigate the page to the specified URL.
            page.navigate("http://www.example.com/");

            // Get the title of the current page.
            String title = page.title();

            // Print the title to the console.
            System.out.println(title);
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