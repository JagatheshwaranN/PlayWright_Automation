package concepts.locator;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.FilePayload;
import org.testng.annotations.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class SetUpInputFilesTest {

    @Test
    public void testLocatorSetUpInputFiles() {
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

            // Set input files for file upload input field
            // Method 1: Set a single file
            // Set input files for the file upload input field
            // Using setInputFiles method to upload a single file (file1) for the input field with ID 'filesToUpload'
            page.locator("input#filesToUpload").setInputFiles(file1);

            // Set an empty array to clear previously set files
            page.locator("input#filesToUpload").setInputFiles(new Path[0]);

            // Method 2: Set multiple files
            // Set input files for the file upload input field
            // Using setInputFiles method to upload multiple files (file1 and file2) for the input field with ID 'filesToUpload'
            page.locator("input#filesToUpload").setInputFiles(new Path[]{file1, file2});

            // Set an empty array to clear previously set files
            page.locator("input#filesToUpload").setInputFiles(new Path[0]);

            // Method 3: Set file using FilePayload
            // Create content for the file
            String content = "Playwright SetInputFiles Demo.";

            // Convert content to bytes using UTF-8 encoding
            byte[] contentBytes = content.getBytes(StandardCharsets.UTF_8);

            // Encode content bytes to Base64
            String base64Content = Base64.getEncoder().encodeToString(contentBytes);

            // Create a FilePayload object with file information
            FilePayload filePayload = new FilePayload("demo.txt", "text/plain", base64Content.getBytes());

            // Set input files for the file upload input field
            // Using setInputFiles method to upload a file (represented by FilePayload) for the input field with ID 'filesToUpload'
            page.locator("input#filesToUpload").setInputFiles(filePayload);
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
