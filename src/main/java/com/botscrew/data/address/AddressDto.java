package com.botscrew.data.address;

import com.botscrew.data.address.subclasses.AddressResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AddressDto {

    private List<AddressResult> results;
    private String status;
    @JsonProperty("error_message")
    private String errorMessage;
}
