package excercises;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class NthElementSelector {

	public static void main(String ar[]) {

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();
		page.navigate("https://www.bigbasket.com/");
		Locator firstElement = page.locator("div.container.footer-links li a >> nth=0");
		String firstEle = firstElement.textContent();
		System.out.println(firstEle);
		Locator lastElement = page.locator("div.container.footer-links li a >> nth=-1");
		String lastEle = lastElement.textContent();
		System.out.println(lastEle);
		browser.close();
		playwright.close();

	}

}
