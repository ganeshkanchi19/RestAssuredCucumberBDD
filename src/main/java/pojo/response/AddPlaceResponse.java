package pojo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddPlaceResponse {
	@JsonProperty("status")
	private String status;

	@JsonProperty("place_id")
	private String placeId;

	@JsonProperty("scope")
	private String scope;

	public String getStatus() {
		return status;
	}

	public String getPlaceId() {
		return placeId;
	}

	public String getScope() {
		return scope;
	}
}
