package org.keilstein.simplemonorepo.geolocationresolver.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.keilstein.simplemonorepo.geolocationresolver.client.model.BigDataCloudResponse;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class AddressServiceResultFactoryTest {

  @InjectMocks
  private AddressServiceResultFactory tested;

  @Test
  void shouldCreateAddress() {
    var response = BigDataCloudResponse.builder()
        .latitude(new BigDecimal("78.6699"))
        .longitude(new BigDecimal("-22.8804"))
        .city("Daneborg")
        .postcode("some-postcode")
        .countryCode("GL")
        .build();

    var actual = tested.createAddress(response);

    assertThat(actual.getLocation().getLatitude()).isEqualTo(new BigDecimal("78.6699"));
    assertThat(actual.getLocation().getLongitude()).isEqualTo(new BigDecimal("-22.8804"));
    assertThat(actual.getAddress().getPostCode()).isEqualTo("some-postcode");
    assertThat(actual.getAddress().getCity()).isEqualTo("Daneborg");
    assertThat(actual.getAddress().getCountry()).isEqualTo("GL");
  }
}