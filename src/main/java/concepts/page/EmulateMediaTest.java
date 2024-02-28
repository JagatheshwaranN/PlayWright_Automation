package concepts.page;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.ColorScheme;
import com.microsoft.playwright.options.Media;
import org.testng.annotations.Test;

public class EmulateMediaTest {

    @Test
    public void testPageEmulateMedia() {

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

            // Create a new page in the browser context
            Page page = browserContext.newPage();

            // Navigate to the specified file URL using Playwright
            page.navigate("file:///D:/Environment_Collection/Intellij_Env/Playwright_Concepts/support/list.html");

            // Emulate the page as if it's being printed
            page.emulateMedia(new Page.EmulateMediaOptions().setMedia(Media.PRINT));

            // Check if the page is currently emulated as 'print'
            boolean printStatus = (boolean) page.evaluate("() => matchMedia('print').matches");
            System.out.println("Print Status: " + printStatus);

            // Check if the page is currently emulated as 'screen'
            boolean screenStatus = (boolean) page.evaluate("() => matchMedia('screen').matches");
            System.out.println("Screen Status: " + screenStatus);

            // Emulate the page with a dark color scheme
            page.emulateMedia(new Page.EmulateMediaOptions().setColorScheme(ColorScheme.DARK));

            // Check if the page is currently emulated with a dark color scheme
            boolean darkTheme = (boolean) page.evaluate("() => matchMedia('(prefers-color-scheme: dark)').matches");
            System.out.println("Dark Theme Status: " + darkTheme);

            // Check if the page is currently emulated with a light color scheme
            boolean lightTheme = (boolean) page.evaluate("() => matchMedia('(prefers-color-scheme: light)').matches");
            System.out.println("Light Theme Status: " + lightTheme);

            // Check if the page is currently emulated with no color scheme preference
            boolean noTheme = (boolean) page.evaluate("() => matchMedia('(prefers-color-scheme: no-preference)').matches");
            System.out.println("No Preference Theme Status: " + noTheme);
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