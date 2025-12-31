package utils;

import io.qameta.allure.Step;

public class AllureSteps {

	@Step("Preparing payload: {payloadName}")
	public static void preparePayload(String payloadName) {
	}

	@Step("Calling {apiName} API with {method} request")
	public static void callapi(String apiName, String method) {

	}

	@Step("Validating status code is {statusCode}")
	public static void validateStatusCode(int statusCode) {

	}

	@Step("Validating response body: {responseName}")
	public static void validateResponseBody(String responseName) {
	}

	@Step("Extracted value - {key}: {value}")
	public static void captureValue(String key, String value) {
	}

	@Step("Validating field {fieldName} with value {value}")
	public static void validateField(String fieldName, String value) {
	}

	@Step("Validating error message: {errorMessage}")
	public static void validateErrorMessage(String errorMessage) {
	}
}
