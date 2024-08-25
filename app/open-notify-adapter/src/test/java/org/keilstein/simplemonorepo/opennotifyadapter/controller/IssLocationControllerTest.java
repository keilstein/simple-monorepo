package org.keilstein.simplemonorepo.opennotifyadapter.controller;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.keilstein.domain.Geolocation;
import org.keilstein.simplemonorepo.opennotifyadapter.client.OpenNotifyClient;
import org.keilstein.simplemonorepo.opennotifyadapter.client.model.IssNowResponse;
import org.keilstein.simplemonorepo.opennotifyadapter.domain.Precision;
import org.keilstein.simplemonorepo.opennotifyadapter.mapper.IssLocationServiceResultFactory;
import org.keilstein.simplemonorepo.opennotifyadapter.mapper.IssLocationWebResponseFactory;
import org.keilstein.simplemonorepo.opennotifyadapter.service.IssLocationService;
import org.keilstein.simplemonorepo.opennotifyadapter.service.model.IssLocationServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import static java.lang.String.format;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = IssLocationController.class)
@Import({IssLocationService.class, IssLocationWebResponseFactory.class})
class IssLocationControllerTest {

  @Autowired
  private WebTestClient webTestClient;

  @MockBean
  private OpenNotifyClient openNotifyClient;

  @MockBean
  private IssLocationServiceResultFactory issLocationServiceResultFactory;

  @ParameterizedTest
  @EmptySource
  @CsvSource({
      "?force=true",
      "?force=false",
  })
  void shouldGetIssLocation(String parameters) {
    var response = mock(IssNowResponse.class);
    var result = IssLocationServiceResult.builder()
        .location(Geolocation.builder()
            .latitude(new BigDecimal("44.7833"))
            .longitude(new BigDecimal("120.2801"))
            .build())
        .precision(new Precision(153L, "MILLISECONDS"))
        .build();

    when(openNotifyClient.getIssLocation(anyBoolean())).thenReturn(Mono.just(response));
    when(issLocationServiceResultFactory.createIssLocationServiceResult(response)).thenReturn(result);

    webTestClient
        .get()
        .uri(format("/v1/iss-location%s", parameters))
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody()
        .jsonPath("$.location.latitude").isEqualTo(44.7833)
        .jsonPath("$.location.longitude").isEqualTo(120.2801)
        .jsonPath("$.precision.precision").isEqualTo(153)
        .jsonPath("$.precision.unit").isEqualTo("MILLISECONDS");
  }
}