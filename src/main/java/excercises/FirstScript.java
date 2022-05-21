package excercises;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class FirstScript {

	public static void main(String ar[]) {

		Playwright playwright = Playwright.create();
		Browser browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
//		LaunchOptions launchOptions = new LaunchOptions();
//		launchOptions.setChannel("firefox");
//		launchOptions.setHeadless(false);
//		Browser browser = playwright.firefox().launch(launchOptions);
		Page page = browser.newPage();
		page.navigate("https://playwright.dev/");
		String pageTitle = page.title();
		System.out.println("Page Title ===> " + pageTitle);
		browser.close();
		playwright.close();
	}

}
