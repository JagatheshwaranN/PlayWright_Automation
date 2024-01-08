package concepts.browser_context;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.Cookie;
import org.testng.annotations.Test;

import java.util.List;

/**
 * This Java code demonstrates the usage of Playwright to create a browser context,
 * add a cookie to it, navigate to a web page, and retrieve and print cookies from
 * that context.
 *
 * @author Jagatheshwaran N
 */
public class BrowserContextAddCookieTest {

    @Test
    public void testBrowserContextAddCookie() {
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

            // Retrieve all cookies currently stored in the browser context
            List<Cookie> cookies = browserContext.cookies();  // Get cookies from the browser

            // Iterate through each cookie and print its name and value
            for (Cookie cookie1 : cookies) {
                System.out.println("Cookie Name  : " + cookie1.name);  // Print the cookie's name
                System.out.println("Cookie Value : " + cookie1.value);  // Print the cookie's value
            }

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

