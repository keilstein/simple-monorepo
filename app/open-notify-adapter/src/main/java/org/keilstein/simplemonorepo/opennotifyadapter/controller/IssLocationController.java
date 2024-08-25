package org.keilstein.simplemonorepo.opennotifyadapter.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keilstein.simplemonorepo.opennotifyadapter.controller.model.IssLocationWebResponse;
import org.keilstein.simplemonorepo.opennotifyadapter.mapper.IssLocationWebResponseFactory;
import org.keilstein.simplemonorepo.opennotifyadapter.service.IssLocationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1", produces = APPLICATION_JSON_VALUE)
public class IssLocationController {

  private final IssLocationService issLocationService;
  private final IssLocationWebResponseFactory issLocationWebResponseFactory;

  @RequestMapping(value = "/iss-location", method = GET)
  public Mono<IssLocationWebResponse> getIssLocation(@RequestParam(value = "force", required = false) boolean force) {
    log.info("Incoming request to /iss-location");

    return issLocationService.getIssLocation(force)
        .map(issLocationWebResponseFactory::createIssLocationWebResponse)
        .doOnNext(response -> log.info("Successfully responded to request to /iss-location with {}", response))
        .doOnError(throwable -> log.warn("Failed to respond to request to /iss-location", throwable));
  }
}