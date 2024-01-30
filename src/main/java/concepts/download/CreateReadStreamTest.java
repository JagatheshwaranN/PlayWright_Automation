package concepts.download;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

import java.io.*;

/**
 * The code demonstrates a scenario where a text file is generated on a web page, downloaded,
 * and its contents are printed to the console.
 * Establishes an event listener for download events using page.onDownload().When a file
 * download is triggered, the script captures the download event and creates a read stream
 * from the downloaded file using download.createReadStream().
 *
 * @author Jagatheshwaran N
 */
public class CreateReadStreamTest {

    @Test
    public void testDownloadCreateReadStream() {
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

            // Navigate to the demo page for file download automation
            page.navigate("https://demo.automationtesting.in/FileDownload.html");

            // Locate the text box element
            Locator textBox = page.locator("#textbox");

            // Fill the text box with "Playwright demo" text, forcing input if needed
            textBox.fill("Playwright demo", new Locator.FillOptions().setForce(true));

            // Simulate pressing the Enter key
            page.keyboard().press("Enter");

            // Locate the button that generates the text file
            Locator fileGenerateButton = page.locator("#createTxt");

            // Click the file generation button
            fileGenerateButton.click();

            // Set up a listener for download events
            page.onDownload(download -> {
                // Create a read stream from the downloaded file
                InputStream file = download.createReadStream();

                // Process the input stream (this function needs to be defined)
                processInputStream(file);
            });

            // Click the link to initiate the download
            page.click("#link-to-download");

            // Wait for the download to complete, setting a timeout of 5 seconds
            page.waitForDownload( () -> new Page.WaitForDownloadOptions().setTimeout(5));
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
