package org.keilstein.simplemonorepo.geolocationresolver.service.model;

import lombok.Builder;
import lombok.Value;
import org.keilstein.domain.Address;
import org.keilstein.domain.Geolocation;

@Value
@Builder
public class AddressServiceResult {

  Geolocation location;
  Address address;
}
