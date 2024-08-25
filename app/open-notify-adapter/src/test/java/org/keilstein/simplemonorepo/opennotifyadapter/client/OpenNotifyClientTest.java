package org.keilstein.simplemonorepo.opennotifyadapter.client;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.keilstein.simplemonorepo.opennotifyadapter.client.model.IssNowResponse;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Answers.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.clearInvocations;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OpenNotifyClientTest {

  @InjectMocks
  private OpenNotifyClient tested;

  @Mock(answer = RETURNS_DEEP_STUBS)
  private WebClient webClient;

  private static IssNowResponse mockResponseFromServer(WebClient webClient) {
    var response = mock(IssNowResponse.class);

    when(
        webClient.get()
            .uri("http://api.open-notify.org/iss-now.json")
            .retrieve()
            .bodyToMono(IssNowResponse.class))
        .thenReturn(Mono.just(response));

    clearInvocations(webClient);

    return response;
  }

  @Test
  void shouldGetFromServer() {
    var expected = mockResponseFromServer(webClient);
    var actual = tested.getIssLocation(true);

    StepVerifier.create(actual)
        .expectNext(expected)
        .verifyComplete();

    verify(webClient, times(1)).get();
  }

  @Test
  void shouldGetFromCache() {
    var expected = mockResponseFromServer(webClient);
    var actualFromServer = tested.getIssLocation(true);

    StepVerifier.create(actualFromServer)
        .expectNext(expected)
        .verifyComplete();

    var actualFromCache = tested.getIssLocation(false);

    StepVerifier.create(actualFromCache)
        .expectNext(expected)
        .verifyComplete();

    verify(webClient, times(1)).get();
  }
}