package org.keilstein.simplemonorepo.geolocationresolver.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.keilstein.domain.Address;
import org.keilstein.domain.Geolocation;
import org.keilstein.simplemonorepo.geolocationresolver.service.model.AddressServiceResult;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class AddressWebResponseFactoryTest {

  @InjectMocks
  private AddressWebResponseFactory tested;

  @Test
  void shouldCreateAddressWebResponse() {
    var location = mock(Geolocation.class);
    var address = mock(Address.class);
    var result = AddressServiceResult.builder()
        .location(location)
        .address(address)
        .build();

    var actual = tested.createAddressWebResponse(result);

    assertThat(actual.getLocation()).isEqualTo(location);
    assertThat(actual.getAddress()).isEqualTo(address);
  }
}