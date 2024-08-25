package org.keilstein.simplemonorepo.geolocationresolver.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keilstein.simplemonorepo.geolocationresolver.client.model.BigDataCloudResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import static java.lang.String.format;

@Slf4j
@Component
@RequiredArgsConstructor
public class BigDataCloudClient {

  private static final String BIGDATA_CLOUD_URI = "https://api.bigdatacloud.net/data/reverse-geocode-client";
  private final WebClient webClient;

  public Mono<BigDataCloudResponse> getAddress(BigDecimal latitude, BigDecimal longitude) {
    var uri = format("%s?latitude=%s&longitude=%s&localityLanguage=en", BIGDATA_CLOUD_URI, latitude, longitude);
    return webClient
        .get()
        .uri(uri)
        .retrieve()
        .bodyToMono(BigDataCloudResponse.class)
        .doOnNext(address -> log.info("Successfully retrieved address from BigData Cloud: {}", address))
        .doOnError(throwable -> log.error("Failed to retrieve address from {}", uri, throwable));
  }
}