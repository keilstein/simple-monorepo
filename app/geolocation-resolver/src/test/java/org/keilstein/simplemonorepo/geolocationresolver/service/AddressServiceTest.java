package org.keilstein.simplemonorepo.geolocationresolver.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.keilstein.simplemonorepo.geolocationresolver.client.BigDataCloudClient;
import org.keilstein.simplemonorepo.geolocationresolver.client.model.BigDataCloudResponse;
import org.keilstein.simplemonorepo.geolocationresolver.mapper.AddressServiceResultFactory;
import org.keilstein.simplemonorepo.geolocationresolver.service.model.AddressServiceResult;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

  @InjectMocks
  private AddressService tested;

  @Mock
  private BigDataCloudClient bigDataCloudClient;
  @Mock
  private AddressServiceResultFactory addressServiceResultFactory;

  @Test
  void shouldGetAddress() {
    var latitude = mock(BigDecimal.class);
    var longitude = mock(BigDecimal.class);
    var response = mock(BigDataCloudResponse.class);
    var result = mock(AddressServiceResult.class);

    when(bigDataCloudClient.getAddress(latitude, longitude)).thenReturn(Mono.just(response));
    when(addressServiceResultFactory.createAddress(response)).thenReturn(result);

    var actual = tested.getAddress(latitude, longitude);

    StepVerifier.create(actual)
        .expectNext(result)
        .verifyComplete();
  }
}