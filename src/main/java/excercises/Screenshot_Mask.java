package excercises;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.ScreenshotCaret;

public class Screenshot_Mask {

	public static void main(String[] args) {

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium()
				.launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(75));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();
		page.navigate("https://playwright.dev/");
		
		//Element Screenshot
		page.locator(".navbar__inner").screenshot(new Locator.ScreenshotOptions().setPath(Paths.get("element.png")));
		
		//Visible DOM Screenshot
		page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("visiblepage.png")));
		
		//Fullpage Screenshot
		page.screenshot(new Page.ScreenshotOptions().setFullPage(true).setPath(Paths.get("fullpage.png")));
		
		//Screenshot with Mask of Elements
		Locator githubButton = page.locator(".gh-btn");
		Locator githubCount = page.locator(".gh-count");
		
		List<Locator> locators = new ArrayList<Locator>();
		locators.add(githubButton);
		locators.add(githubCount);
		
		page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("maskpage.png")).setMask(locators));
		
		page.locator(".DocSearch.DocSearch-Button").click();
		
		//Screenshot with Caret
		page.screenshot(new Page.ScreenshotOptions().setCaret(ScreenshotCaret.INITIAL).setPath(Paths.get("Caret.png")));
			
		page.close();
		browserContext.close();
		browser.close();
		playwright.close();
	}
}
