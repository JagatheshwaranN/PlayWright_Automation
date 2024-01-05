package concepts.browser;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ChromeBrowserTest {

    // Declaration of Playwright, Browser, and Page instances
    Playwright playwright;

    Browser browser;

    Page page;

    // Setup method to initialize test environment
    @BeforeTest
    public void setup() {
        try {
            // Create a Playwright object to manage browser interactions
            playwright = Playwright.create();

            // Create a new browser instance using Playwright's Chromium browser
            browser = playwright.chromium().launch(
                    // Configure launch options for the browser
                    new BrowserType.LaunchOptions()
                            // Set headless mode as false to make the browser visible
                            .setHeadless(false)
                            // Specify the browser channel to use "chrome" (stable)
                            .setChannel("chrome")
            );

            // Create a new page within the browser
            page = browser.newPage();
        } catch (Exception e) {
            // Handle any exceptions that might occur during setup and printing error message
            System.out.println("Error during setup: " + e.getMessage());
        }
    }

    // Test method to check the title of the Google webpage
    @Test
    public void testChromeBrowser() {
        try {
            // Set the expected title of the page
            String expectedTitle = "Google";

            // Navigate to the Google webpage
            page.navigate("https://www.google.com/");

            // Get the actual title of the loaded page
            String actualTitle = page.title();

            // Assert that the actual title matches the expected title
            Assert.assertEquals(actualTitle, expectedTitle, "Page title mismatch");
        } catch (Exception e) {
            // Handle any exceptions that might occur during the test and printing error message
            System.out.println("Error during test: " + e.getMessage());
        }
    }

    // Teardown method to clean up after the test execution
    @AfterTest
    public void tearDown() {
        try {
            // Close the page if it's open
            if (page != null) {
                page.close();
            }

            // Close the browser if it's running
            if (browser != null) {
                browser.close();
            }

            // Close the Playwright object to release resources
            if (playwright != null) {
                playwright.close();
            }
        } catch (Exception e) {
            // Handle any exceptions that might occur during teardown and printing error message
            System.out.println("Error during teardown: " + e.getMessage());
        }
    }

}
