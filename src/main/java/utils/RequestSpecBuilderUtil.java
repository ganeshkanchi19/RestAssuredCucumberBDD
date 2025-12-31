package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;

public class RequestSpecBuilderUtil {

	private static RequestSpecification requestSpec;

	private RequestSpecBuilderUtil() {
	}

	public static RequestSpecification getRequestSpec() {
		if (requestSpec == null) {
			requestSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
					.addQueryParam("key", "qaclick123").addHeader("Content-Type", "application/json")
					.addHeader("X-Allure-Test", "ACTIVE") // ✅ moved here
					.log(LogDetail.URI).log(LogDetail.METHOD).log(LogDetail.HEADERS).log(LogDetail.BODY).build();
		}
		return requestSpec;
	}
}
