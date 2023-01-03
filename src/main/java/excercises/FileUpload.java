package excercises;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.FileChooser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.FilePayload;

public class FileUpload {

	static Playwright playwright;

	public static Page before() {

		playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page;
		page = browserContext.newPage();
		return page;
	}

	public static void fileChooser() throws InterruptedException {
		Page page = before();
		page.navigate("https://davidwalsh.name/demo/multiple-file-upload.php");
		FileChooser fileChooser = page.waitForFileChooser(() -> page.locator("#filesToUpload").click());
		fileChooser.setFiles(Paths.get("orangeHRMlogin.json"));
		Thread.sleep(2000);
		fileChooser.setFiles(new Path[0]);
		fileChooser.setFiles(new Path[] { Paths.get("orangeHRMlogin.json"), Paths.get("trace-view.zip") });
		Thread.sleep(2000);
		fileChooser.setFiles(new Path[0]);
		fileChooser.setFiles(new FilePayload("demo.txt", "text/plain",
				"This is playwright filechooser demo".getBytes(StandardCharsets.UTF_8)));
		System.out.println(fileChooser.element());
		System.out.println(fileChooser.isMultiple());
		System.out.println(fileChooser.page().content());
		page.close();
		playwright.close();
	}

	public static void setInputFiles() throws InterruptedException {
		Page page = before();
		page.navigate("https://davidwalsh.name/demo/multiple-file-upload.php");
		page.setInputFiles("input#filesToUpload", Paths.get("orangeHRMlogin.json"));
		Thread.sleep(2000);
		page.setInputFiles("input#filesToUpload", new Path[0]);
		page.setInputFiles("input#filesToUpload",
				new Path[] { Paths.get("orangeHRMlogin.json"), Paths.get("trace-view.zip") });
		Thread.sleep(2000);
		page.setInputFiles("input#filesToUpload", new Path[0]);
		page.setInputFiles("input#filesToUpload",
				new FilePayload("demo.txt", "text/plain", "This is playwright demo".getBytes(StandardCharsets.UTF_8)));
		page.close();
		playwright.close();
	}

	public static void main(String[] args) throws InterruptedException {

		fileChooser();
		setInputFiles();

	}

}
