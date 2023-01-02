package excercises;

import java.nio.file.Paths;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class AutomaticLogin {

	public static void main(String[] args) throws InterruptedException {

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();
		page.navigate("https://admin-demo.nopcommerce.com/login");
		page.fill("#Email", "admin@yourstore.com");
		page.fill("#Password", "admin");
		page.click(".button-1.login-button");
		Thread.sleep(5000);
		browserContext.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("nopcommercelogin.json")));
		browser.close();
		playwright.close();
	}

}
