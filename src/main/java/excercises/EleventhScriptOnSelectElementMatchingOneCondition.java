package excercises;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class EleventhScriptOnSelectElementMatchingOneCondition {

	public static void main(String ar[]) {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();
		page.navigate("https://opensource-demo.orangehrmlive.com/");
		// Using CSS selector
		page.locator("input#txtUsername," + "input#username").fill("Admin");
		Locator elements = page.locator("input#txtUsername," + "input#txtPassword");
		System.out.println(elements.count());
		// Using XPATH selector
		page.locator("//input[@id='txtPassword'] | //input[@id='password']").fill("admin123");
		browser.close();
		playwright.close();

	}
}
