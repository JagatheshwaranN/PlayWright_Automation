package excercises;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.BoundingBox;

public class MouseWheel {

	static Playwright playwright;

	public static Page before() {

		playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page;
		page = browserContext.newPage();
		return page;
	}

	public static void scrollIntoView() {
		Page page = before();
		page.navigate("https://playwright.dev/java/");
		page.locator("'GitHub'").scrollIntoViewIfNeeded();
		page.waitForTimeout(3000);
		page.close();
		playwright.close();
	}

	public static void mouseWheel() {
		Page page = before();
		page.navigate("https://playwright.dev/java/");
		BoundingBox box = page.locator("'GitHub'").boundingBox();
		page.mouse().wheel(0, box.y);
		page.waitForTimeout(3000);
		page.close();
		playwright.close();
	}

	public static void main(String[] args) {

		scrollIntoView();
		mouseWheel();
	}

}
