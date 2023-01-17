package excercises;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class Browsers {

	public static void main(String[] args) {

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		System.out.println("Browser Connect Status : " + browser.isConnected());
		System.out.println("Browser Version : " + browser.version());
		System.out.println("Browser Context Count : " + browser.contexts().size());
		BrowserContext browsercontext1 = browser.newContext();
		Page page1 = browsercontext1.newPage();
		page1.navigate("https://playwright.dev/");
		System.out.println(browser.contexts().size());
		BrowserContext browsercontext2 = browser.newContext();
		Page page2 = browsercontext2.newPage();
		page2.navigate("https://www.google.com/");
		System.out.println(browser.contexts().size());

		browsercontext1.close();
		browsercontext2.close();
		browser.close();

	}
}
