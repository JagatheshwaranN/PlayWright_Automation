package concepts.browser_context;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * The code demonstrates how to expose a Java function for SHA-256 encryption to a
 * browser context using Playwright. When the button is clicked, the SHA-256 hash
 * of the string 'PLAYWRIGHT' is calculated in Java and displayed on the webpage.
 * <p>
 * Exposing an SHA-256 Encryption Function: A new browser context is created
 * (browserContext), and the exposeFunction method is used to expose a Java function
 * for SHA-256 encryption.The exposed function is named "sha256Encrypt," and its
 * implementation takes a string argument, computes the SHA-256 hash of the content,
 * and returns the hash encoded as a Base64 string.
 * <p>
 * JavaScript Interaction: The HTML content includes a button with an onclick event
 * that triggers the onClick function when clicked. This function calls the exposed
 * Java function (sha256Encrypt) with the argument 'PLAYWRIGHT' and updates the
 * content of a <div> element with the result.
 *
 * @author Jagatheshwaran N
 */
public class BrowserContextExposeFunctionType2Test {

    @Test
    public void testBrowserContextExposeFunction() {

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

            // Access the browser context to expose a function for SHA-256 encryption
            browserContext.exposeFunction(
                    // Name the function to be accessible from the browser
                    "sha256Encrypt",
                    // Provide the function's implementation
                    (args) -> {
                        // Extract the content to be encrypted from the first argument
                        String content = (String) args[0];

                        // Create a MessageDigest instance for SHA-256 hashing
                        MessageDigest messageDigest;
                        try {
                            messageDigest = MessageDigest.getInstance("SHA-256");
                        } catch (NoSuchAlgorithmException e) {
                            // Handle any errors with the algorithm
                            throw new RuntimeException(e);
                        }

                        // Calculate the SHA-256 hash of the content
                        byte[] token = messageDigest.digest(content.getBytes(StandardCharsets.UTF_8));

                        // Encode the hash as a Base64 string for convenient use in JavaScript
                        return Base64.getEncoder().encodeToString(token);
                    });

            // Create a new page within the browser context
            Page page = browserContext.newPage();

            // Set the page content, including JavaScript code
            page.setContent("""
                        <script>
                        async function onClick() {
                            // Call the exposed "sha256" binding and update the div content
                            document.querySelector('div').textContent = await window.sha256Encrypt('PLAYWRIGHT');
                        }
                        </script>
                        <button onclick="onClick()">Click me</button>
                        <div></div>
                    """);

            // Click the button on the page, triggering the JavaScript function
            page.getByRole(AriaRole.BUTTON).click();
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
