package excercises;

import com.microsoft.playwright.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import java.nio.file.*;

public class TraceViewer {
	public static void main(String[] args) {
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();

			// Start tracing before creating / navigating a page.
			context.tracing()
					.start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true));

			// Open new page
			Page page = context.newPage();

			// Go to https://opensource-demo.orangehrmlive.com/
			page.navigate("https://opensource-demo.orangehrmlive.com/");

			// Click #divUsername >> text=Username
			page.locator("#divUsername >> text=Username").click();

			// Fill input[name="txtUsername"]
			page.locator("input[name=\"txtUsername\"]").fill("Admin");

			// Click input[name="txtPassword"]
			page.locator("input[name=\"txtPassword\"]").click();

			// Fill input[name="txtPassword"]
			page.locator("input[name=\"txtPassword\"]").fill("admin123");

			// Click input:has-text("LOGIN")
			page.locator("input:has-text(\"LOGIN\")").click();
			assertThat(page).hasURL("https://opensource-demo.orangehrmlive.com/index.php/dashboard");

			// Click text=Welcome Paul
			page.locator("text=Welcome Paul").click();

			// Go to https://opensource-demo.orangehrmlive.com/index.php/auth/login
			page.navigate("https://opensource-demo.orangehrmlive.com/index.php/auth/login");

			// Stop tracing and export it into a zip archive.
			context.tracing().stop(new Tracing.StopOptions().setPath(Paths.get("trace-view.zip")));
		}
	}
}