package excercises;

import java.util.List;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.HttpHeader;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.Timing;

public class Request {

	public static void main(String[] args) {

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();
		System.out.println("***************");
		page.onRequest(request -> System.out.println(">>" + request.headers()));
		page.onRequest(request -> System.out.println("Content : " + request.headerValue("content-type")));
		page.onRequest(request -> System.out.println("User Agent : " + request.headerValue("user-agent")));
		System.out.println("***************");
		page.onRequest(request -> {
			List<HttpHeader> arr = request.headersArray();
			for (HttpHeader a : arr) {
				System.out.println("Header Array Data : " + a.name + " " + a.value);
			}
		});
		System.out.println("***************");
		page.onRequestFailed(request -> {
			System.out.println(request.url() + " " + request.failure());
		});
		System.out.println("***************");
		page.onRequest(request -> System.out.println("Request Method : " + request.method()));
		System.out.println("***************");
		page.onRequest(request -> {
			System.out.println("Request Post Data : " + request.postData());
			System.out.println("Request Post Data Buffer : " + request.postDataBuffer());
			System.out.println("Request Data Type : " + request.resourceType());
			System.out.println("Response Data : " + request.response().status());
			System.out.println("Request BSize : " + request.sizes().requestBodySize);
			System.out.println("Request HSize : " + request.sizes().requestHeadersSize);
			System.out.println("Response BSize : " + request.sizes().responseBodySize);
			System.out.println("Response HSize : " + request.sizes().responseHeadersSize);
		});

		page.onRequestFinished(request -> {
			Timing time = request.timing();
			System.out.println("Request Url : " + request.url());
			System.out.println("Time : " + (time.responseEnd - time.responseStart));
			System.out.println("Time : " + (time.connectEnd - time.connectStart));
		});
		System.out.println("***************");
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
		Response response1 = page.navigate("https://google.com");
		System.out.println("Redirect : " + response1.request().redirectedFrom().url());
		page.navigate("https://www.google.com/");
		page.waitForLoadState(LoadState.NETWORKIDLE);
		page.close();
		browserContext.close();
		playwright.close();
	}
}
