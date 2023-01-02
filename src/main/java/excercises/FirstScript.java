package excercises;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class FirstScript {
	
	
	public static void launchType1() {
		
		Playwright playwright = Playwright.create();
		LaunchOptions launchOptions = new LaunchOptions();
		launchOptions.setChannel("chrome");
		launchOptions.setHeadless(false);
		Browser browser = playwright.chromium().launch(launchOptions);
		Page page = browser.newPage();
		page.navigate("https://playwright.dev/");
		page.waitForTimeout(3000);
		page.close();
		browser.close();
		playwright.close();
	}
	
	public static void launchType2() { 
		
		Playwright playwright = Playwright.create();
		BrowserType browserType = playwright.chromium();
		LaunchOptions launchOptions = new BrowserType.LaunchOptions().setHeadless(false);
		Browser browser = browserType.launch(launchOptions);
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();
		page.navigate("https://playwright.dev/");
		page.waitForTimeout(3000);
		page.close();
		browserContext.close();
		browser.close();
		playwright.close();
	}
	
	public static void launchType3() { 
	
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();
		page.navigate("https://playwright.dev/");
		page.waitForTimeout(3000);
		page.close();
		browserContext.close();
		browser.close();
		playwright.close();	
	}

	public static void main(String ar[]) {
		launchType1();
		launchType2();
		launchType3();	
	}

}
