package org.keilstein.simplemonorepo.geolocationresolver.client;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.keilstein.simplemonorepo.geolocationresolver.client.model.BigDataCloudResponse;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TWO;
import static org.mockito.Answers.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.clearInvocations;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BigDataCloudClientTest {

  @InjectMocks
  private BigDataCloudClient tested;

  @Mock(answer = RETURNS_DEEP_STUBS)
  private WebClient webClient;

  @Test
  void shouldGetAddress() {
    var response = mock(BigDataCloudResponse.class);

    when(
        webClient.get()
            .uri("https://api.bigdatacloud.net/data/reverse-geocode-client?latitude=1&longitude=2&localityLanguage=en")
            .retrieve()
            .bodyToMono(BigDataCloudResponse.class))
        .thenReturn(Mono.just(response));

    clearInvocations(webClient);

    var actual = tested.getAddress(ONE, TWO);

    StepVerifier.create(actual)
        .expectNext(response)
        .verifyComplete();

    verify(webClient, times(1)).get();
  }
}