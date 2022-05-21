package excercises;

import com.microsoft.playwright.*;

public class FourthScriptOnBrowserContext {

	public static void main(String ar[]) {

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

		BrowserContext browserContext1 = browser.newContext();
		Page page1 = browserContext1.newPage();
		page1.navigate("https://playwright.dev/");
		System.out.println("Page Title ===>> " + page1.title());

		BrowserContext browserContext2 = browser.newContext();
		Page page2 = browserContext2.newPage();
		page2.navigate("https://gorest.co.in/");
		System.out.println("Page Title ===>> " + page2.title());

		page1.close();
		page2.close();

		browserContext1.close();
		browserContext2.close();

	}

}
