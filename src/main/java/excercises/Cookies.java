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

	public static void main(String[] args) {

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();
		page.navigate("https://www.amazon.in/");

		// Get Cookies
		List<Cookie> cookiesList = browserContext.cookies("https://www.amazon.in/");
		for (Cookie c : cookiesList) {
			System.out.println(c.name + " | " + c.value + " | " + c.domain + " | " + c.path + " | " + c.url + " | "
					+ c.expires + " | " + c.httpOnly + " | " + c.sameSite + " | " + c.secure);
		}

		// Set Cookie
		Cookie c1 = new Cookie("playwright", "test");
		c1.setHttpOnly(false);
		c1.setSecure(true);
		c1.setDomain(".amazon.in");
		c1.setUrl(null);
		browserContext.addCookies(Arrays.asList(c1));

		// Get Cookie
		Cookie c2 = cookiesList.get(cookiesList.size() - 1);
		System.out.println(c2.name + " | " + c2.value + " | " + c2.httpOnly + " | " + c2.secure);

	}
}
