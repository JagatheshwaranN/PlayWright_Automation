package excercises;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class SeventhScriptOnFrames {

	public static void main(String[] args) {

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();

		page.navigate("http://www.londonfreelance.org/courses/frames/index.html");

		String header;
		header = page.frameLocator("frame[name='main']").locator("h2").textContent();
		System.out.println(header);

		header = page.frame("main").locator("h2").textContent();
		System.out.println(header);

		page.navigate("https://www.formsite.com/templates/registration-form-templates/vehicle-registration-form/");
	
		page.locator("img[title='vehicle-registration-forms-and-templates']").click();
		page.frameLocator("//iframe[@id='frame-one748593425']").locator("#RESULT_TextField-8").fill("Jaga");
		
		browser.close();
		playwright.close();
	}

}
