package utils;

import io.qameta.allure.Attachment;

public class AllureAttachments {

	@Attachment(value = "Request Payload", type = "application/json")
	public static String attachRequest(String requestBody) {
		return requestBody;

	}

	@Attachment(value = "Response Body", type = "application/json")
	public static String attachResponse(String responseBody) {
		return responseBody;
	}

}
