package org.keilstein.simplemonorepo.opennotifyadapter.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.keilstein.domain.Geolocation;
import org.keilstein.simplemonorepo.opennotifyadapter.client.model.IssNowResponse;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class IssLocationServiceResultFactoryTest {

  @InjectMocks
  private IssLocationServiceResultFactory tested;

  @Test
  void shouldCreateIssLocationServiceResult() {
    var nowInUnixTime = new Date().getTime() / 1000L;

    var response = IssNowResponse.builder()
        .message("success")
        .issPosition(new IssNowResponse.IssPosition("78.6699", "-22.8804"))
        .timestamp(nowInUnixTime)
        .build();
    var expectedLocation = Geolocation.builder()
        .latitude(new BigDecimal("-22.8804"))
        .longitude(new BigDecimal("78.6699"))
        .build();

    var actual = tested.createIssLocationServiceResult(response);

    assertThat(actual.getLocation()).isEqualTo(expectedLocation);
    assertThat(actual.getPrecision().precision()).isLessThan(5000L);
    assertThat(actual.getPrecision().unit()).isEqualTo("MILLISECONDS");
  }
}