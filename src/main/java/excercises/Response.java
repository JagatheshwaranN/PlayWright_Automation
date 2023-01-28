package excercises;

import java.util.List;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.HttpHeader;

public class Response {

	public static void main(String[] args) {

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();

		page.onResponse(response -> {
			System.out.println("Response All Headers : " + response.allHeaders());
			System.out.println("*********************************************");
			System.out.println("Response Header Value : " + response.headerValue("expires"));
			System.out.println("*********************************************");
			System.out.println("Response Header Values : " + response.headerValues("content-length"));
			System.out.println("*********************************************");
			System.out.println("Response Headers : " + response.headers());
			System.out.println("*********************************************");
			List<HttpHeader> arr = response.headersArray();
			for (HttpHeader a : arr) {
				System.out.println("Response Header Array : " + a.name + " " + a.value);
			}
			System.out.println("*********************************************");
			System.out.println("Response Body : " + response.body().length);
			System.out.println("*********************************************");
			System.out.println("Response Frame : " + response.frame());
			System.out.println("*********************************************");
			System.out.println("Response Worker : " + response.fromServiceWorker());
			System.out.println("*********************************************");
			System.out.println("Response Status : " + response.ok());
		});

		page.navigate("https://www.google.com/");
		page.close();
		browserContext.close();
		browser.close();
		playwright.close();
	}
}
