package org.keilstein.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;
import lombok.experimental.NonFinal;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Value
@NonFinal
@Jacksonized
@SuperBuilder
@JsonInclude(NON_NULL)
@JsonNaming(SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {

  String title;
  String firstName;
  String lastName;

  @NotBlank
  String addressLine1;
  String addressLine2;
  String postCode;
  @NotBlank
  String city;
  String region;

  @NotBlank
  @Size(min = 2, max = 2)
  String country;

  @Email
  String email;
  String phone;
}