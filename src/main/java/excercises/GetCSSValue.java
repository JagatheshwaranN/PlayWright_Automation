package excercises;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class GetCSSValue {
	
	public static void main (String[] args) {
		
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();
		page.navigate("https://letcode.in/buttons");
		Object cssValue = page.locator("#color").evaluate("element => window.getComputedStyle(element).getPropertyValue('background-color')");
		System.out.println(cssValue.toString());
//		Approach 2
//		System.out.println(page.locator("#color").evaluate("element => getComputedStyle(element)['background-color']"));
		page.close();
		browserContext.close();
		browser.close();
		playwright.close();
		
	}

}
