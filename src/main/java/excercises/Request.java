package excercises;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.LoadState;

public class Request {

	public static void main(String[] args) {

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();
	//	page.onRequest(request -> System.out.println(">>" + request.headers()));
		System.out.println("**************");
		page.onRequest(request -> System.out.println(request.headerValue("content-type")));
		page.onRequest(request -> System.out.println(request.headerValue("charset")));
		page.navigate("https://www.google.com/");
		page.waitForLoadState(LoadState.NETWORKIDLE);
		
	
	}

}
