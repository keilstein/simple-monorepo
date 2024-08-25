package org.keilstein.simplemonorepo.geolocationresolver.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.keilstein.domain.Address;
import org.keilstein.domain.Geolocation;

import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;

@Value
@Builder
@Jacksonized
@JsonInclude(NON_NULL)
@JsonNaming(SnakeCaseStrategy.class)
public class AddressWebResponse implements Serializable {

  Geolocation location;
  Address address;
}
