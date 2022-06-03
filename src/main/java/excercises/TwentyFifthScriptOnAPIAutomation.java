package excercises;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;

public class TwentyFifthScriptOnAPIAutomation {

	Playwright playwright;
	APIRequestContext apiRequestContext;
	String response;
	int responseCode;

	@BeforeClass
	public void setup() {
		playwright = Playwright.create();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		apiRequestContext = playwright.request().newContext(
				new APIRequest.NewContextOptions().setBaseURL("https://reqres.in/").setExtraHTTPHeaders(headers));

	}

	@Test
	public void getCall() {
		responseCode = apiRequestContext.get("api/users/2").status();
		Assert.assertEquals(responseCode, 200);
		response = apiRequestContext.get("api/users/2").text();
		System.out.println("***************************************************");
		System.out.println(response);
		System.out.println("***************************************************");
	}

	@Test
	public void postCall() {
		Map<String, String> payload = new HashMap<String, String>();
		payload.put("name", "James");
		payload.put("job", "CEO");
		response = apiRequestContext.post("api/users", RequestOptions.create().setData(payload)).text();
		System.out.println("***************************************************");
		System.out.println(response);
		System.out.println("***************************************************");
		JsonObject json = new Gson().fromJson(response, JsonObject.class);
		Assert.assertEquals(json.get("name").toString().replaceAll("\"", ""), "James");
		Assert.assertEquals(json.get("job").toString().replaceAll("\"", ""), "CEO");
	}

	@Test
	public void putCall() {
		Map<String, String> payload = new HashMap<String, String>();
		payload.put("name", "Blake");
		payload.put("job", "CFO");
		response = apiRequestContext.put("api/users/2", RequestOptions.create().setData(payload)).text();
		System.out.println("***************************************************");
		System.out.println(response);
		System.out.println("***************************************************");
		JsonObject json = new Gson().fromJson(response, JsonObject.class);
		Assert.assertEquals(json.get("name").toString().replaceAll("\"", ""), "Blake");
		Assert.assertEquals(json.get("job").toString().replaceAll("\"", ""), "CFO");
	}

	@Test
	public void deleteCall() {
		responseCode = apiRequestContext.delete("api/users/2").status();
		Assert.assertEquals(responseCode, 204);
	}

	public void tearDown() {
		apiRequestContext.dispose();
		playwright.close();
	}

}
