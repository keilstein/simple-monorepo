package org.keilstein.simplemonorepo.geolocationresolver.mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keilstein.simplemonorepo.geolocationresolver.controller.model.AddressWebResponse;
import org.keilstein.simplemonorepo.geolocationresolver.service.model.AddressServiceResult;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AddressWebResponseFactory {

  public AddressWebResponse createAddressWebResponse(AddressServiceResult result) {
    return AddressWebResponse.builder()
        .location(result.getLocation())
        .address(result.getAddress())
        .build();
  }
}
