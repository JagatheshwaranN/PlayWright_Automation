package excercises;

import java.util.HashMap;
import java.util.Map;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Route;
import com.microsoft.playwright.options.LoadState;

public class NetworkMonitor {

	static Playwright playwright;

	public static Page before() {
		playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page;
		page = browserContext.newPage();
		return page;
	}

	public static void getNetworkLogs() {
		Page page = before();
		page.onRequest(request -> System.out
				.println(">>" + request.method() + " " + request.url() + " " + request.isNavigationRequest()));
		page.onResponse(response -> System.out
				.println("<<" + response.status() + " " + response.statusText() + " " + response.securityDetails()));
		page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php");
		page.waitForLoadState(LoadState.NETWORKIDLE);
		page.close();
		playwright.close();
	}

	public static void deleteHeader() {
		Page page = before();
		page.route("**/example.com", route -> {
			Map<String, String> headers = new HashMap<>(route.request().headers());
			headers.remove("cache-control");
			route.resume(new Route.ResumeOptions().setHeaders(headers));
		});
		page.navigate("https://example.com/");
		page.waitForLoadState(LoadState.NETWORKIDLE);
		page.close();
		playwright.close();
	}

	public static void main(String[] args) {

		getNetworkLogs();
		deleteHeader();
	}
}
