package utils;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecBuilderUtil {
	public static ResponseSpecification successResponseSpec() {
		return new ResponseSpecBuilder().expectStatusCode(200)
				.expectHeader("Content-Type", "application/json;charset=UTF-8").build();

	}

	public static ResponseSpecification notFoundResponseSpec() {
		return new ResponseSpecBuilder().expectStatusCode(404).build();
	}
}
