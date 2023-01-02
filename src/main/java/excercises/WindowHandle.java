package excercises;


import java.util.List;

import org.testng.Assert;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class WindowHandle {

	public static void main (String[] args) {
		
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();
		page.navigate("https://letcode.in/windows");
		
		// Single window handling
		Page page1 = browserContext.waitForPage(()->{
			page.click("#home");
			});	
		page1.waitForLoadState();
		Assert.assertEquals(page1.url().contains("test"), true);
		
		page1.waitForNavigation(() ->{
			page1.click("'Log in'");	
		});
		Assert.assertEquals(page1.url().contains("signin"), true);
		
		page1.close();
		page.bringToFront();
		//page.click("a>img[alt='letcode']");
		
		// Multiple windows handling
		Page page2 = browserContext.waitForPage(()->{
			page.click("#multi");
			});	
		page2.waitForLoadState();
		
		List<Page> pages = page2.context().pages();
		System.out.println("Number of opened tab/window : "+pages.size());
		pages.stream().forEach(ele -> System.out.println(ele.url()));
		
		pages.get(1).bringToFront();		
		pages.get(1).onDialog(dialog -> {
			String text = dialog.message();
			System.out.println(text);
			dialog.accept();
		});
		pages.get(1).click("#accept");
		pages.get(0).bringToFront();
		
		page.close();
		browserContext.close();
		browser.close();
		playwright.close();
	}
}
