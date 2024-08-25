package org.keilstein.simplemonorepo.opennotifyadapter.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keilstein.simplemonorepo.opennotifyadapter.client.OpenNotifyClient;
import org.keilstein.simplemonorepo.opennotifyadapter.mapper.IssLocationServiceResultFactory;
import org.keilstein.simplemonorepo.opennotifyadapter.service.model.IssLocationServiceResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class IssLocationService {

  private final OpenNotifyClient openNotifyClient;
  private final IssLocationServiceResultFactory issLocationServiceResultFactory;

  public Mono<IssLocationServiceResult> getIssLocation(boolean force) {
    return openNotifyClient.getIssLocation(force)
        .map(issLocationServiceResultFactory::createIssLocationServiceResult);
  }

  @Scheduled(fixedDelay = 5000)
  private void scheduleFixedDelayTask() {
    getIssLocation(true).subscribe();
  }
}