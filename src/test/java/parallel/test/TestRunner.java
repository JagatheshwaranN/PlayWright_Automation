package parallel.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.TestInstance;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestRunner {

	public static Playwright playwright;
	Browser browser;
	BrowserContext context;
	Page page;

	@AfterAll
	public static void closeBrowser() {
		playwright.close();
	}

	@BeforeEach
	void createContextAndPage() {
		playwright = Playwright.create();
		browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		context = browser.newContext();
		page = context.newPage();
	}

	@AfterEach
	void closeContext() {
		page.close();
		context.close();
		browser.close();
	}

}
