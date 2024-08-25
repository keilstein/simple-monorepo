package org.keilstein.simplemonorepo.geolocationresolver.controller;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keilstein.simplemonorepo.geolocationresolver.controller.model.AddressWebResponse;
import org.keilstein.simplemonorepo.geolocationresolver.mapper.AddressWebResponseFactory;
import org.keilstein.simplemonorepo.geolocationresolver.service.AddressService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1", produces = APPLICATION_JSON_VALUE)
public class AddressController {

  private final AddressService addressService;
  private final AddressWebResponseFactory addressWebResponseFactory;

  @RequestMapping(value = "/address", method = GET)
  public Mono<AddressWebResponse> getAddress(@RequestParam(value = "latitude") @Min(-90) @Max(90) BigDecimal latitude, @RequestParam(value = "longitude") @Min(-180) @Max(180) BigDecimal longitude) {
    log.info("Incoming request to /address");

    return addressService.getAddress(latitude, longitude)
        .map(addressWebResponseFactory::createAddressWebResponse)
        .doOnNext(response -> log.info("Successfully responded to request to /address with {}", response))
        .doOnError(throwable -> log.warn("Failed to respond to request to /address", throwable));
  }
}