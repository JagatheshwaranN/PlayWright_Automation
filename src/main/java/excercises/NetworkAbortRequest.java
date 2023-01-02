package excercises;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class NetworkAbortRequest {

	static Playwright playwright;
	
	public static Page before() {
		playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page;
		page = browserContext.newPage();
		return page;
	}

	public static void abortWebSiteImage() {
		Page page = before();
		page.route("**/*", route -> {
			if (route.request().resourceType().equals("image"))
				route.abort();
			else
				route.resume();
		});
		page.navigate("https://unsplash.com/t/people");
		page.waitForTimeout(5000);
		page.close();
		playwright.close();
	}

	public static void abortWebSiteAdv() {
		Page page = before();
		page.route("**/*", route -> {
			if (route.request().url().startsWith("https://googleads.g.doubleclick.net/pagead/ads"))
				route.abort();
			else
				route.resume();
		});
		page.navigate("https://letcode.in/test");
		page.waitForTimeout(5000);
		page.close();
		playwright.close();
	}

	public static void main(String[] args) {

		abortWebSiteAdv();
		abortWebSiteImage();

	}

}
