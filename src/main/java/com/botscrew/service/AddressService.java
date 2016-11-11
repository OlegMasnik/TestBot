package com.botscrew.service;

import com.botscrew.data.address.AddressDto;

public interface AddressService {

    String GEOCODE_KEY = "AIzaSyCk4b8JkEGBOtQ7SssXUOK6nUhOX24vKHo";
    String INVALID_ADDRESS = "Address is invalid :(";
    String NOT_USA_ADDRESS = "It isn't in USA.";

    AddressDto getAddresses(final String address);

    String parseAddress(final AddressDto address);
}
