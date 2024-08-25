package org.keilstein.simplemonorepo.opennotifyadapter.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.keilstein.domain.Geolocation;
import org.keilstein.simplemonorepo.opennotifyadapter.domain.Precision;

import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Value
@Builder
@Jacksonized
@JsonInclude(NON_NULL)
@JsonNaming(SnakeCaseStrategy.class)
public class IssLocationWebResponse implements Serializable {

  Geolocation location;
  Precision precision;
}