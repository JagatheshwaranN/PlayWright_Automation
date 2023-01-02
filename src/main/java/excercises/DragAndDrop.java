package excercises;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class DragAndDrop {

	public static void main (String[] args) {
		
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext1 = browser.newContext();
		BrowserContext browserContext2 = browser.newContext();
		Page page1 = browserContext1.newPage();
		Page page2 = browserContext2.newPage();
		
		page1.navigate("https://letcode.in/dropable");
		
		// Handling drag and drop in normal page
		Locator source1 = page1.locator("#draggable");
		Locator destination1 = page1.locator("#droppable");
		
		source1.hover();
		page1.mouse().down();
		destination1.hover();
		page1.mouse().down();
		
		// Another way to drag and drop
		// source1.dragTo(destination1);
		
		page2.navigate("https://jqueryui.com/droppable/");
		
		// Handling drag and drop in fame
		FrameLocator frame = page2.frameLocator(".demo-frame"); 
		Locator source2 = frame.locator("#draggable");
		Locator destination2 = frame.locator("#droppable");
		
		source2.hover();
		page2.mouse().down();
		destination2.hover();
		page2.mouse().down();
		
		// Another way to drag and drop
		// source2.dragTo(destination2);
		
		// BoundingBox - Not Working 
//		page1.mouse().move(source1.boundingBox().x/2, source1.boundingBox().y/2);
//		page1.mouse().down();
//		page1.mouse().move(destination1.boundingBox().x/2, destination1.boundingBox().y/2);
//		page1.mouse().down();
		
		page2.close();
		page1.close();
		browserContext2.close();
		browserContext1.close();
		browser.close();
		playwright.close();
		
	}
}
