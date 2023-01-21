package excercises;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class Offline {
	
	public static void main (String[] args) {
		
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions().setOffline(true));
//		BrowserContext browserContext = browser.newContext(new BrowserType.LaunchOptions()
//				  .setIgnoreDefaultArgs(Arrays.asList("--mute-audio")));
		Page page = browserContext.newPage();
		page.navigate("https://www.google.com/");
		page.close();
		browserContext.close();
		playwright.close();	
	}
}
