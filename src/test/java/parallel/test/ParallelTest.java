package parallel.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitUntilState;

@Execution(ExecutionMode.CONCURRENT)
public class ParallelTest extends TestRunner {
	
	@Test
	public void launchPlaywrightSite() {
		page.navigate("https://playwright.dev/java/",new Page.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE));
		assertEquals("https://playwright.dev/java/", page.url());
		page.locator(".highlight_gXVj").isVisible();
		page.waitForTimeout(5000);
	}

	@Test
	public void launchGoogleSite() {
		page.navigate("https://www.google.com/",new Page.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE));
		assertEquals("https://www.google.com/", page.url());
		page.locator(".gLFyf").type("playwright");
		page.waitForTimeout(5000);

	}

}
