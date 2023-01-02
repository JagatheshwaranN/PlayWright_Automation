package excercises;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class BrowserContextSwitch {

	public static void main(String[] args) throws InterruptedException {

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext1 = browser.newContext();
		Page page1 = browserContext1.newPage();
		page1.navigate(
				"D:\\Environment_Collection\\Eclipse_Env\\Workspace\\PlayWrightTestAutomation\\windowHandle\\Login.html");
		System.out.println("Page Title ===>> " + page1.title());
		Page page2 = page1.waitForPopup(() -> {
			page1.click("a:has-text('Sign Up')");
		});
		System.out.println(page2.url());
		Thread.sleep(3000);
		page2.close();
		page1.close();
		browserContext1.close();
	}
}
