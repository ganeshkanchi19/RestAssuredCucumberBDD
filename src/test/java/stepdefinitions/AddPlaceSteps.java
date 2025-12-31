package stepdefinitions;

import static org.testng.Assert.assertEquals;

import base.BaseTest;
import base.TestContext;
import endpoints.Routes;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import pojo.request.AddPlaceRequest;
import pojo.request.DeletePlaceRequest;
import pojo.response.AddPlaceResponse;
import utils.AddPlacePayloadBuilder;
import utils.DeletePlacePayloadBuilder;
import utils.RequestSpecBuilderUtil;
import utils.ResponseSpecBuilderUtil;
import utils.TestDataStore;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class AddPlaceSteps extends BaseTest {

	public AddPlaceSteps(TestContext context) {
		super(context);
	}

	// ===================== ADD PLACE =====================

	@Given("Add place payload")
	public void add_place_payload() {

		AddPlaceRequest payload = AddPlacePayloadBuilder.buildAddPlacePayload();

		context.request = RestAssured.given().spec(RequestSpecBuilderUtil.getRequestSpec())
				.filter(new AllureRestAssured()).body(payload); // 🔥 POJO SERIALIZATION
	}

	@When("User calls Add Place API with POST request")
	public void user_calls_add_place_api_with_post_request() {
		context.response = context.request.when().post(Routes.ADD_PLACE);
	}

	@Then("API call is successful with status code {int}")
	public void api_call_is_successful_with_status_code(Integer statusCode) {
		// Status code validation
		assertEquals(context.response.getStatusCode(), statusCode.intValue());

		// JSON Schema validation (contract check)
		context.response.then().assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/add_place_response_schema.json"));

		// POJO deserialization (business validation)

		AddPlaceResponse responsePojo = context.response.as(AddPlaceResponse.class);

		// Store dynamic data
		TestDataStore.placeId = responsePojo.getPlaceId();

		// Field level assertion
		assertEquals(responsePojo.getStatus(), "OK");
	}

	// ===================== GET PLACE =====================

	@When("User calls Get Place API with GET request")
	public void user_calls_get_place_api_with_get_request() {

		context.response = RestAssured.given().spec(RequestSpecBuilderUtil.getRequestSpec())
				.queryParam("place_id", TestDataStore.placeId).when().get(Routes.GET_PLACE).then()
				.spec(ResponseSpecBuilderUtil.successResponseSpec()).extract().response();
	}

	@Then("Get Place API response should match the added place")
	public void get_place_api_response_should_match_the_added_place() {

		JsonPath js = new JsonPath(context.response.asString());

		assertEquals(js.getString("name"), "Frontline house");
		assertEquals(js.getString("address"), "29, side layout, cohen 09");
		assertEquals(js.getString("language"), "French-IN");
	}

	// ===================== DELETE PLACE =====================

	@When("User calls Delete Place API with POST request")
	public void user_calls_delete_place_api_with_post_request() {

		// Build POJO payload
		DeletePlaceRequest payload = DeletePlacePayloadBuilder.buildDeletePlacePayload(TestDataStore.placeId);

		context.response = RestAssured.given().spec(RequestSpecBuilderUtil.getRequestSpec()).body(payload) // 🔥 POJO
																											// SERIALIZATION
				.when().post(Routes.DELETE_PLACE).then().spec(ResponseSpecBuilderUtil.successResponseSpec()).extract()
				.response();
	}

	@Then("Delete Place API response should be successful")
	public void delete_place_api_response_should_be_successful() {

		// Schema validation
		context.response.then().assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/delete_place_response_schema.json"));
		JsonPath js = new JsonPath(context.response.asString());

		// Field level validation
		assertEquals(js.getString("status"), "OK");
	}

	// ===================== NEGATIVE GET AFTER DELETE =====================

	@Then("Get Place API should return error for deleted place")
	public void get_place_api_should_return_error_for_deleted_place() {

		context.response = RestAssured.given().spec(RequestSpecBuilderUtil.getRequestSpec())
				.queryParam("place_id", TestDataStore.placeId).when().get(Routes.GET_PLACE).then()
				.spec(ResponseSpecBuilderUtil.notFoundResponseSpec())
				.body(matchesJsonSchemaInClasspath("schemas/get_place_error_schema.json")).extract().response();

		String actualMsg = context.response.jsonPath().getString("msg").replaceAll("\\s+", " ").trim();

		assertEquals(actualMsg, "Get operation failed, looks like place_id doesn't exists");
	}
}
