package org.keilstein.simplemonorepo.opennotifyadapter.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * This holds response from Open Notify. For full documentation, see
 * <a href="http://open-notify.org/Open-Notify-API/ISS-Location-Now">International Space Station Current Location</a>
 */
@Value
@Builder
@Jacksonized
@JsonNaming(SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class IssNowResponse {

  String message;
  IssPosition issPosition;
  Long timestamp;

  public record IssPosition(String longitude, String latitude) {
  }
}