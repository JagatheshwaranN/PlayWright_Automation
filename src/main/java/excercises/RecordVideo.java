package excercises;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.nio.file.Paths;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class RecordVideo {

	public static void main(String[] args) {
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext(new Browser.NewContextOptions().setRecordVideoDir(Paths.get("videos/")).setRecordVideoSize(1366, 625));
			Page page = context.newPage();
			page.navigate("https://www.google.com/");
			page.locator("[aria-label=\"Search\"]").click();
			page.locator("[aria-label=\"Search\"]").fill("playwright");
			page.waitForNavigation(() -> {
				page.locator("[aria-label=\"Search\"]").press("Enter");
			});
			page.locator("text=Playwright: Fast and reliable end-to-end testing for modern ...").click();
			assertThat(page).hasURL("https://playwright.dev/");
			context.close();
			page.close();
			playwright.close();
		}
	}
}
