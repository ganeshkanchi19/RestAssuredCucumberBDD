package utils;

import java.util.Arrays;
import pojo.request.AddPlaceRequest;
import pojo.request.Location;

public class AddPlacePayloadBuilder {

	public static AddPlaceRequest buildAddPlacePayload() {
		Location location = new Location();
		location.setLat(-38.383494);
		location.setLng(33.427362);

		AddPlaceRequest req = new AddPlaceRequest();
		
		req.setLocation(location);
		req.setAccuracy(50);
		req.setName("Frontline house");
		req.setPhone_number("(+91) 983 893 3937");
		req.setAddress("29, side layout, cohen 09");
		req.setTypes(Arrays.asList("shoe park", "shop"));
		req.setWebsite("http://google.com");
		req.setLanguage("French-IN");
		return req;
	}

}
