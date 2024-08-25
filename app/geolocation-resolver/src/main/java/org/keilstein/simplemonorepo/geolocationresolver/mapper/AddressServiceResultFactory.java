package org.keilstein.simplemonorepo.geolocationresolver.mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keilstein.domain.Address;
import org.keilstein.domain.Geolocation;
import org.keilstein.simplemonorepo.geolocationresolver.client.model.BigDataCloudResponse;
import org.keilstein.simplemonorepo.geolocationresolver.service.model.AddressServiceResult;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AddressServiceResultFactory {

  public AddressServiceResult createAddress(BigDataCloudResponse response) {
    var location = Geolocation.builder()
        .latitude(response.getLatitude())
        .longitude(response.getLongitude())
        .build();
    var address = Address.builder()
        .city(response.getCity())
        .postCode(response.getPostcode())
        .country(response.getCountryCode())
        .build();

    return AddressServiceResult.builder()
        .location(location)
        .address(address)
        .build();
  }
}
