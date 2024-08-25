package org.keilstein.simplemonorepo.opennotifyadapter.mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keilstein.domain.Geolocation;
import org.keilstein.simplemonorepo.opennotifyadapter.client.model.IssNowResponse;
import org.keilstein.simplemonorepo.opennotifyadapter.client.model.IssNowResponse.IssPosition;
import org.keilstein.simplemonorepo.opennotifyadapter.domain.Precision;
import org.keilstein.simplemonorepo.opennotifyadapter.service.model.IssLocationServiceResult;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Slf4j
@Component
@RequiredArgsConstructor
public class IssLocationServiceResultFactory {

  private static Precision calculatePrecisionInMillis(Long timestamp) {
    var dateFromUnixTime = new Date(timestamp * 1000);
    var precision = new Date().getTime() - dateFromUnixTime.getTime();

    return new Precision(precision, MILLISECONDS.name());
  }

  private static Geolocation createGeolocation(IssPosition issPosition) {
    return Geolocation.builder()
        .latitude(new BigDecimal(issPosition.latitude()))
        .longitude(new BigDecimal(issPosition.longitude()))
        .build();
  }


  public IssLocationServiceResult createIssLocationServiceResult(IssNowResponse response) {
    var location = createGeolocation(response.getIssPosition());
    var precision = calculatePrecisionInMillis(response.getTimestamp());

    return IssLocationServiceResult.builder()
        .location(location)
        .precision(precision)
        .build();
  }
}