package com.botscrew.service.impl;

import com.botscrew.data.address.AddressDto;
import com.botscrew.service.AddressService;
import com.botscrew.util.MyRestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class GeocodeAddressService implements AddressService {

    @Override
    public AddressDto getAddresses(final String address) {
        if (address == null || "".equals(address))
            return null;
        final String parsedAddress = address.replaceAll("\\W+", ",+");
        System.out.println("*****");
        System.out.println("Parsed address: " + parsedAddress);
        final String uri = "https://maps.googleapis.com/maps/api/geocode/json?address=" + parsedAddress + "&key="
                + GEOCODE_KEY;
        System.out.println(uri);
        try {
            return MyRestTemplate.getRestTemplate().getForObject(new URI(uri), AddressDto.class);
        } catch (RestClientException | URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String parseAddress(AddressDto addressDto) {
        if (addressDto == null || !"OK".equals(addressDto.getStatus())) {
            return INVALID_ADDRESS;
        }
        boolean[] isUSA = new boolean[1];
        addressDto.getResults().get(0).getAddressComponents().forEach(y -> {
            if ("US".equals(y.getShortName())) isUSA[0] = true;
        });
        return isUSA[0] ? addressDto.getResults().get(0).getFormattedAddress() : NOT_USA_ADDRESS;
    }

}
