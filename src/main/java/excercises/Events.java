package excercises;

import java.util.function.Consumer;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Request;

public class Events {
	
	static Playwright playwright;

	public static Page before() {

		playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page;
		page = browserContext.newPage();
		return page;
	}
	
	public static void waitRequest() {
		
		Page page = before();
		Request request = page.waitForRequest("**/login*", ()->{
			page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		});
		System.out.println(request.url());
		page.close();
		playwright.close();
	}
	
	public static void addRemoveListener() {
		
		Page page = before();
		page.onRequest(request -> System.out.println("Request Sent >>> "+request.url()));
		Consumer<Request> listener = request -> System.out.println("Request finished >>> "+request.url());
		page.onRequestFinished(listener);
		page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		page.offRequestFinished(listener);
		page.navigate("https://www.openstreetmap.org/");
		page.close();
		playwright.close();
	}

	public static void main (String[] args) {
		
		waitRequest();
		addRemoveListener();
	}
}
