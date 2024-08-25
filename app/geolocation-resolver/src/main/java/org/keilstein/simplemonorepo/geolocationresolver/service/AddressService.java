package org.keilstein.simplemonorepo.geolocationresolver.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keilstein.simplemonorepo.geolocationresolver.client.BigDataCloudClient;
import org.keilstein.simplemonorepo.geolocationresolver.mapper.AddressServiceResultFactory;
import org.keilstein.simplemonorepo.geolocationresolver.service.model.AddressServiceResult;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Slf4j
@Component
@RequiredArgsConstructor
public class AddressService {

  private final BigDataCloudClient bigDataCloudClient;
  private final AddressServiceResultFactory addressServiceResultFactory;

  public Mono<AddressServiceResult> getAddress(BigDecimal latitude, BigDecimal longitude) {
    return bigDataCloudClient.getAddress(latitude, longitude)
        .map(addressServiceResultFactory::createAddress);
  }
}
