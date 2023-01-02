package excercises;

import java.util.List;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class VisibleElement {

	public static void main(String[] args) {

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();
		page.navigate("https://www.flipkart.com/");

		List<String> linksText = page.locator("a:visible").allTextContents();
		for (String text : linksText) {
			System.out.println(text);
		}

		int linksCount = page.locator("a>>visible=true").count();
		System.out.println(linksCount);

		int imagesCount = page.locator("xpath=//img>>visible=true").count();
		System.out.println(imagesCount);

		browser.close();
		playwright.close();
	}

}
