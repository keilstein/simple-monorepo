package org.keilstein.simplemonorepo.opennotifyadapter.service.model;

import lombok.Builder;
import lombok.Value;
import org.keilstein.domain.Geolocation;
import org.keilstein.simplemonorepo.opennotifyadapter.domain.Precision;

@Value
@Builder
public class IssLocationServiceResult {

  Geolocation location;
  Precision precision;
}