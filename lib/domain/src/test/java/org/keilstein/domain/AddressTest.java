package org.keilstein.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.keilstein.commons.testutils.validation.ValidationUtils.validate;

class AddressTest {

  @ParameterizedTest
  @CsvSource({
      ",,,3",
      "address-line-1,,,2",
      "address-line-1,city,,1",
      "address-line-1,city,U,1",
      "address-line-1,city,country,1",
      "address-line-1,city,U,1",
      "address-line-1,city,US,0",
  })
  void shouldHaveMustHaveFields(String addressLine1, String city, String country, int violations) {
    var address = Address.builder()
        .addressLine1(addressLine1)
        .city(city)
        .country(country)
        .build();

    validate(address, violations);
  }

  @ParameterizedTest
  @CsvSource({
      ",0",
      "test1@gmail.com,0",
      "test1@gmail,0",
      "test1,1",
      "test @gmail.com,1",
      "test1[at]gmail.com,1",
      "test1@,1",
      "@gmail.com,1",
  })
  void shouldHaveValidEmailAddress(String email, int violations) {
    var address = Address.builder()
        .addressLine1("address-line-1")
        .city("city")
        .country("US")
        .email(email)
        .build();

    validate(address, violations);
  }
}