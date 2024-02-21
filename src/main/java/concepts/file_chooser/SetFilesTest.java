package concepts.file_chooser;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.FilePayload;
import org.testng.annotations.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

/**
 * The code tests Playwright's ability to interact with a file chooser element
 * on a webpage and sets files using various methods.
 * There might be an issue with the fileChooser.setFiles(filePayload) part, and
 * you may need to investigate further or modify the payload creation to resolve
 * the issue.
 * The browser is launched in non-headless mode for visual inspection, and proper
 * resource cleanup is implemented in the finally block.
 *
 * @author Jagatheshwaran N
 */
public class SetFilesTest {

    @Test
    public void testFileChooserSetFiles() {
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

            // Define file paths
            Path file1 = Paths.get("src/main/java/concepts/locator/files/orangeHRMlogin.json");
            Path file2 = Paths.get("src/main/java/concepts/locator/files/nopcommercelogin.json");

            // Wait for the file chooser dialog to appear after clicking on the element with id "filesToUpload"
            FileChooser fileChooser = page.waitForFileChooser(() -> page.locator("#filesToUpload").click());

            // Set the files in the file chooser dialog with a single file specified by the Path object file1
            fileChooser.setFiles(file1);

            // Set the files in the file chooser dialog with an empty array of Path
            // Note: This might not be necessary, consider whether you need to set an empty array
            fileChooser.setFiles(new Path[0]);

            // Set the files in the file chooser dialog with an array of Paths containing file1 and file2
            fileChooser.setFiles(new Path[]{file1, file2});

            // Set the files in the file chooser dialog with an empty array of Path
            // Note: This might not be necessary, consider whether you need to set an empty array
            fileChooser.setFiles(new Path[0]);

            // Content to be written to the file
            String content = "Playwright SetInputFiles Demo.";

            // Convert content to bytes using UTF-8 encoding
            byte[] contentBytes = content.getBytes(StandardCharsets.UTF_8);

            // Encode content bytes to Base64
            String base64Content = Base64.getEncoder().encodeToString(contentBytes);

            // Create a FilePayload object with file information
            FilePayload filePayload = new FilePayload("demo.txt", "text/plain", base64Content.getBytes(StandardCharsets.UTF_8));

            /*
             The below code is not working with proper inputs
             ================================================
             stack='Error: Exactly one of payloads, localPaths and streams must be provided
             */

            // Set the files using FilePayload
            fileChooser.setFiles(filePayload);
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
