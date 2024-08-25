package org.keilstein.simplemonorepo.geolocationresolver.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.LowerCamelCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

/**
 * This holds response from BigData Cloud. For full documentation, see
 * <a href="https://www.bigdatacloud.com/reverse-geocoding">BigData Cloud Reverse Geocoding</a>
 */
@Value
@Builder
@Jacksonized
@JsonNaming(LowerCamelCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BigDataCloudResponse {

  BigDecimal latitude;
  String lookupSource;
  BigDecimal longitude;
  String localityLanguageRequested;
  String continent;
  String continentCode;
  String countryName;
  String countryCode;
  String principalSubdivision;
  String principalSubdivisionCode;
  String city;
  String locality;
  String postcode;
  String plusCode;
}