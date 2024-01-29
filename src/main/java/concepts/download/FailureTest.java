package concepts.download;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FailureTest {

    @Test
    public void testDownloadDelete() {
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

            // Create a new isolated browser context
            BrowserContext browserContext = browser.newContext();

            // Create a new page within the context
            Page page = browserContext.newPage();

            page.navigate("https://file-examples.com/index.php/sample-documents-download/sample-doc-download/");
            Download downloadFile = page.waitForDownload(() -> {
                page.click("(//a[contains(@href,'file-sample_1MB.doc')])[1]");
            });

            boolean flag = downloadFile.failure() != null;
           if(flag){
               System.out.println("No interruption in file download");
           }else{
               System.out.println("File download got interrupted");
           }
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

    // This function takes an InputStream as input, representing the downloaded file.
    private static void processInputStream(InputStream stream) {
        // Wrap the InputStream in a BufferedReader for efficient line-by-line reading.
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));

        // Read each line of the file and print it to the console.
        // The "lines()" method splits the file into individual lines.
        // The "forEach" method iterates over each line and calls the provided lambda function.
        // The lambda function simply prints the line using System.out.println().
        bufferedReader.lines().forEach(System.out::println);

        // Close the BufferedReader to release resources.
        try {
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
