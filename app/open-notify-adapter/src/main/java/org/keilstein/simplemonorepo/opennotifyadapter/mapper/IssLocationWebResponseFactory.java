package org.keilstein.simplemonorepo.opennotifyadapter.mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keilstein.simplemonorepo.opennotifyadapter.controller.model.IssLocationWebResponse;
import org.keilstein.simplemonorepo.opennotifyadapter.service.model.IssLocationServiceResult;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class IssLocationWebResponseFactory {

  public IssLocationWebResponse createIssLocationWebResponse(IssLocationServiceResult result) {
    return IssLocationWebResponse.builder()
        .location(result.getLocation())
        .precision(result.getPrecision())
        .build();
  }
}