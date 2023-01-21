package excercises;

import java.nio.file.Paths;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType.LaunchPersistentContextOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class LaunchPersistentContext {

	public static void main(String[] args) {

		// Not working - Have to find the solution to make it work
		Playwright playwright = Playwright.create();
		BrowserContext browserContext = playwright.chromium().launchPersistentContext(
				Paths.get("nopcommercelogin.json"), new LaunchPersistentContextOptions().setHeadless(false));
		Page page = browserContext.newPage();
		page.navigate("https://admin-demo.nopcommerce.com/admin/");
	}
}
