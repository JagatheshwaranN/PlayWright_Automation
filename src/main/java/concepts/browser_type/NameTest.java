package concepts.browser_type;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;

/**
 * The test method demonstrates how to use Playwright to obtain the name of the Chromium
 * browser type and prints it to the console. This type of test might be used to verify
 * the correctness of the Playwright setup and to check the availability of specific
 * browser types during test execution.
 *
 * @author Jagatheshwaran N
 */
public class NameTest {

    @Test
    public void testBrowserTypeName() {
        // Declare a variable to store the BrowserType
        BrowserType browserType;

        try {
            // Create a Playwright instance
            try (Playwright playwright = Playwright.create()) {
                // Get the Chromium browser type from Playwright
                browserType = playwright.chromium();

                // Get the name of the browser type (e.g., "chromium")
                String name = browserType.name();

                // Print the obtained browser type name to the console
                System.out.println("Browser Type Name: " + name);
            }
        } catch (Exception e) {
            // Handle any exceptions that might occur during Playwright initialization
            e.printStackTrace();
        }
    }

}
