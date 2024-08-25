package org.keilstein.simplemonorepo.opennotifyadapter.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keilstein.simplemonorepo.opennotifyadapter.client.model.IssNowResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class OpenNotifyClient {

  private static final String OPEN_NOTIFY_URI = "http://api.open-notify.org/iss-now.json";
  private static IssNowResponse CACHED = null;
  private final WebClient webClient;

  public Mono<IssNowResponse> getIssLocation(boolean force) {
    if (!force && CACHED != null) {
      return Mono.just(CACHED)
          .doOnNext(response -> log.info("Using cached ISS location {}", response));
    }

    return webClient
        .get()
        .uri(OPEN_NOTIFY_URI)
        .retrieve()
        .bodyToMono(IssNowResponse.class)
        .doOnNext(response -> CACHED = response)
        .doOnNext(location -> log.info("Successfully retrieved ISS location from Open Notify: {}", location))
        .doOnError(throwable -> log.error("Failed to retrieve ISS location from {}", OPEN_NOTIFY_URI, throwable));
  }
}