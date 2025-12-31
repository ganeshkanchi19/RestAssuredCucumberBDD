package base;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

public class BaseTest {
	protected TestContext context;

	public BaseTest(TestContext context) {
		this.context = context;
	}

	protected void setup() {
		RestAssured.baseURI = "https://rahulshettyacademy.com";

		// Initialize request specification with AllureRestAssured filter
		context.request = RestAssured.given().filter(new AllureRestAssured()) // Allure auto request/response
				.header("X-Allure-Test", "ACTIVE") // ✅ header AFTER init
				.log().all(); // Console logging

	}

}
