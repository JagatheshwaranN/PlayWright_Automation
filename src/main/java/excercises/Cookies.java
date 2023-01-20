package excercises;

import java.util.Arrays;
import java.util.List;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.Cookie;

public class Cookies {

	 static List<Cookie> cookieList;
	
	public static void main(String[] args) {

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();
		page.navigate("https://www.amazon.in/");

		// Get Cookies
		cookieList = browserContext.cookies("https://www.amazon.in/");
		for (Cookie c : cookieList) {
			System.out.println(c.name + " | " + c.value + " | " + c.domain + " | " + c.path + " | " + c.url + " | "
					+ c.expires + " | " + c.httpOnly + " | " + c.sameSite + " | " + c.secure);
		}
		
		//clear Cookies
		browserContext.clearCookies();
		List<Cookie> cookiesList1 = browserContext.cookies("https://www.amazon.in/");
		System.out.println(cookiesList1);
		
		// Set Cookie
		Cookie c1 = new Cookie("playwright", "test");
		c1.setHttpOnly(false);
		c1.setSecure(true);
		c1.setUrl(null);
		c1.setDomain(".amazon.in");
		c1.setPath("/");
		browserContext.addCookies(Arrays.asList(c1));

		// Get Cookie
		cookieList = browserContext.cookies("https://www.amazon.in/");
		for (Cookie c : cookieList) {
			System.out.println(c.name + " | " + c.value + " | " + c.domain + " | " + c.path + " | " + c.url + " | "
					+ c.expires + " | " + c.httpOnly + " | " + c.sameSite + " | " + c.secure);
		}
	}
}
