package excercises;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class IsSeries {

	public static void main(String[] args) {
		
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();
		page.navigate("https://letcode.in/edit");
		
		boolean editable1 = page.locator("#join").isEditable();
		System.out.println(editable1);
		
		boolean editable2 = page.locator("#dontwrite").isEditable();
		System.out.println(editable2);
		
		boolean enabled = page.locator("#fullName").isEnabled();
		System.out.println(enabled);
		
		boolean disabled = page.locator("#noEdit").isDisabled();
		System.out.println(disabled);
		
		boolean visible = page.locator("#getMe").isVisible();
		System.out.println(visible);
		
		page.navigate("https://letcode.in/radio");
		
		boolean checked1 = page.locator("#notfoo").isChecked();
		System.out.println(checked1);
		
		boolean checked2 = page.locator("input[type='checkbox'] >> nth=0").isChecked();
		System.out.println(checked2);
		
		page.close();
		browserContext.close();
		browser.close();
		playwright.close();
		
	}
}
