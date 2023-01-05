package excercises;

import java.util.List;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Frame;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class Frames {

	public static void main(String[] args) {

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();

		page.navigate("http://www.londonfreelance.org/courses/frames/index.html");

		// Handling Single Frame
		String header;
		header = page.frameLocator("frame[name='main']").locator("h2").textContent();
		System.out.println(header);

		header = page.frame("main").locator("h2").textContent();
		System.out.println(header);

		FrameLocator frameLoc = page.frameLocator("frame[name='main']");
		header = frameLoc.getByText("Title bar (top.html)").textContent();
		System.out.println(header);

		Locator loc = page.locator("frame[name='main']");
		FrameLocator frameLocator = loc.frameLocator(":scope");
		header = frameLocator.getByText("Title bar (top.html)").textContent();
		System.out.println(header);

		page.navigate("https://www.formsite.com/templates/registration-form-templates/vehicle-registration-form/");

		page.locator("img[title='vehicle-registration-forms-and-templates']").click();
		page.frameLocator("//iframe[@id='frame-one748593425']").locator("#RESULT_TextField-8").fill("Jaga");

		// Handling Multiple Frames
		page.navigate("https://letcode.in/frame");

		Frame parent = page.frame("firstFr");
		parent.fill("input[name='fname']", "Playwright");

		List<Frame> childs = parent.childFrames();
		System.out.println(childs.size());
		childs.get(1).fill("input[name='email']", "Automation");

		childs.get(0).parentFrame().fill("input[name='lname']", "Testing");

		browser.close();
		playwright.close();
	}

}
