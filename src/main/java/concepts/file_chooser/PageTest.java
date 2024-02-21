package concepts.file_chooser;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

/**
 * The code tests Playwright's ability to interact with a file chooser element
 * on a webpage, waits for the file chooser dialog to appear, and retrieves the
 * title of the associated page.
 * The browser is launched in non-headless mode for visual inspection, and proper
 * resource cleanup is implemented in the finally block.
 *
 * @author Jagatheshwaran N
 */
public class PageTest {

    @Test
    public void testFileChooserPage() {

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
            BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions().setHasTouch(true));

            // Create a new page within the context
            Page page = browserContext.newPage();

            // Navigate to the file upload demo page
            page.navigate("https://davidwalsh.name/demo/multiple-file-upload.php");

            // Wait for a file chooser dialog to appear after clicking on an element with id "filesToUpload"
            FileChooser fileChooser = page.waitForFileChooser(() -> page.locator("#filesToUpload").click());

            // Get the title of the page associated with the file chooser
            String fileUploadPageTitle = fileChooser.page().title();

            // Print the title to the console
            System.out.println(fileUploadPageTitle);
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