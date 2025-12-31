package utils;

import pojo.request.DeletePlaceRequest;

public class DeletePlacePayloadBuilder {
	private DeletePlacePayloadBuilder() {
		// prevent object creation
	}

	public static DeletePlaceRequest buildDeletePlacePayload(String placeId) {

		DeletePlaceRequest request = new DeletePlaceRequest();
		request.setPlace_id(placeId);

		return request;
	}
}
