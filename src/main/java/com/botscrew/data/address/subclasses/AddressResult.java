package com.botscrew.data.address.subclasses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AddressResult {

    @JsonProperty("address_components")
	private List<AddressComponents> addressComponents;
    @JsonProperty("formatted_address")
	private String formattedAddress;
	private Geometry geometry;
    @JsonProperty("place_id")
	private String placeId;
	private List<String> types;

}
