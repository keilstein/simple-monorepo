package org.keilstein.simplemonorepo.geolocationresolver.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.keilstein.domain.Address;
import org.keilstein.domain.Geolocation;
import org.keilstein.simplemonorepo.geolocationresolver.client.BigDataCloudClient;
import org.keilstein.simplemonorepo.geolocationresolver.client.model.BigDataCloudResponse;
import org.keilstein.simplemonorepo.geolocationresolver.mapper.AddressServiceResultFactory;
import org.keilstein.simplemonorepo.geolocationresolver.mapper.AddressWebResponseFactory;
import org.keilstein.simplemonorepo.geolocationresolver.service.AddressService;
import org.keilstein.simplemonorepo.geolocationresolver.service.model.AddressServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = AddressController.class)
@Import({AddressService.class, AddressWebResponseFactory.class})
class AddressControllerTest {

  @Autowired
  private WebTestClient webTestClient;

  @MockBean
  private BigDataCloudClient bigDataCloudClient;

  @MockBean
  private AddressServiceResultFactory addressServiceResultFactory;

  @Test
  void shouldGetAddress() {
    var latitude = new BigDecimal("44.7833");
    var longitude = new BigDecimal("-120.2801");
    var response = mock(BigDataCloudResponse.class);
    var result = AddressServiceResult.builder()
        .location(Geolocation.builder()
            .latitude(latitude)
            .longitude(longitude)
            .build())
        .address(Address.builder()
            .postCode("97750")
            .city("Fossil")
            .country("US")
            .build())
        .build();

    when(bigDataCloudClient.getAddress(latitude, longitude)).thenReturn(Mono.just(response));
    when(addressServiceResultFactory.createAddress(response)).thenReturn(result);

    webTestClient
        .get()
        .uri("/v1/address?latitude=44.7833&longitude=-120.2801")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody()
        .jsonPath("$.location.latitude").isEqualTo(44.7833)
        .jsonPath("$.location.longitude").isEqualTo(-120.2801)
        .jsonPath("$.address.post_code").isEqualTo("97750")
        .jsonPath("$.address.city").isEqualTo("Fossil")
        .jsonPath("$.address.country").isEqualTo("US");
  }
}