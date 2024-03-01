package concepts.page;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitUntilState;
import org.testng.annotations.Test;

public class GoBackTest {

    @Test
    public void testPageGoBack() {

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

            // Initialize title variable
            String title;

            // Navigate to the first web page (Playwright website)
            page.navigate("https://playwright.dev/");

            // Get the title of the current page
            title = page.title();

            // Print the title of the first page
            System.out.println("Page Title: " + title);

            // Navigate to the second web page (Selenium website)
            page.navigate("https://www.selenium.dev/");

            // Get the title of the current page
            title = page.title();

            // Print the title of the second page
            System.out.println("Page Title: " + title);

            // Go back to the previous page with network idle state as a condition
            page.goBack(new Page.GoBackOptions().setWaitUntil(WaitUntilState.NETWORKIDLE));

            // Get the title of the current page (after going back)
            title = page.title();

            // Print the title of the page after going back
            System.out.println("Page Title: " + title);
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