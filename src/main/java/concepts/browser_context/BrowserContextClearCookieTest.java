package concepts.browser_context;


import com.microsoft.playwright.*;
import com.microsoft.playwright.options.Cookie;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * This Java code snippet demonstrates the use of Playwright to create a browser
 * context, add a cookie, navigate to a webpage, clear cookies from that context,
 * and perform assertions on cookie removal.
 *
 * @author Jagatheshwaran N
 */
public class BrowserContextClearCookieTest {

    @Test
    public void testBrowserContextClearCookie() {
        // Initialize playwright variable to null
        Playwright playwright = null;

        // Declaring variable for a browser type
        BrowserType browserType;

        // Initialize browser variable to null
        Browser browser = null;

        try {
            // Create a Playwright instance
            playwright = Playwright.create();

            // Select Chromium as the browser type
            browserType = playwright.chromium();

            // Launch a browser in non-headless mode (visible window).
            browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));

            // Create a new isolated browser context.
            BrowserContext browserContext = browser.newContext();

            // Create a new cookie object with the name "test" and value "auto"
            Cookie cookie = new Cookie("test", "auto");

            // Set the domain for the cookie to "example.com"
            cookie.setDomain("example.com");

            // Set the path for the cookie to "/", meaning it will be sent to all pages on the domain
            cookie.setPath("/");

            // Add the cookie to the browser context
            browserContext.addCookies(List.of(cookie));

            // Create a new page within the context.
            Page page = browserContext.newPage();

            // Navigate the page to the specified URL.
            page.navigate("http://www.example.com/");

            // Get the title of the current page.
            String title = page.title();

            // Print the title to the console.
            System.out.println(title);

            // Remove all cookies from the current browser context
            browserContext.clearCookies();

            // Verify that no cookies remain after clearing
            List<Cookie> cookies = browserContext.cookies();  // Retrieve cookies to check

            // Assert that the cookie list is empty
            Assert.assertEquals(cookies.size(), 0);
        } catch (Exception e) {
            // Print the exception stack trace for debugging
            e.printStackTrace();
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

