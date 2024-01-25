package concepts.browser_type;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;

/**
 * The test method demonstrates how to use Playwright to obtain the executable path of
 * the Chromium browser and prints it to the console. It also includes exception handling
 * to address any issues that may arise during the Playwright initialization process.
 *
 * @author Jagatheshwaran N
 */
public class ExecutablePathTest {

    @Test
    public void testBrowserTypeExecutablePath() {
        // Declare a variable to store the BrowserType
        BrowserType browserType;

        // Initialize Playwright
        try (Playwright playwright = Playwright.create()) {

            // Get the Chromium browser type from Playwright
            browserType = playwright.chromium();

            // Get the path to the Chromium browser executable
            String browserExecutablePath = browserType.executablePath();

            // Print the obtained browser executable path
            System.out.println("Chromium executable path: " + browserExecutablePath);
        } catch (Exception e) {
            // Handle any exceptions that might occur during Playwright initialization
            e.printStackTrace();
        }
    }

}
