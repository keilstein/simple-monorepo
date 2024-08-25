package org.keilstein.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.keilstein.commons.testutils.validation.ValidationUtils.validate;

class GeolocationTest {

  @ParameterizedTest
  @CsvSource({
      ",,2",
      "-91,-22.4057,1",
      "+91,-22.4057,1",
      "26.7510,-181,1",
      "26.7510,+181,1",
      "-90,-22.4057,0",
      "+90,-22.4057,0",
      "26.7510,-180,0",
      "26.7510,+180,0",
      "26.7510,-22.4057,0",
  })
  void shouldHaveValidGeolocation(BigDecimal latitude, BigDecimal longitude, int violations) {
    var address = Geolocation.builder()
        .latitude(latitude)
        .longitude(longitude)
        .build();

    validate(address, violations);
  }
}