package excercises;

import java.util.List;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.HttpHeader;
import com.microsoft.playwright.options.LoadState;

public class Request {

	public static void main(String[] args) {

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();
		page.onRequest(request -> System.out.println(">>" + request.headers()));
//		page.onRequest(request -> System.out.println("Content : " + request.headerValue("content-type")));
//		page.onRequest(request -> System.out.println("User Agent : " + request.headerValue("user-agent")));
		System.out.println("***************");
		page.onRequest(request -> {
			List<HttpHeader> arr = request.headersArray();		
			for(HttpHeader a : arr) {
				System.out.println(a);
			}
		});
		page.onRequestFailed(request -> {
			System.out.println(request.url() + " " + request.failure());
		});
		page.navigate("https://www.google.com/");
		page.waitForLoadState(LoadState.NETWORKIDLE);

	}

}
