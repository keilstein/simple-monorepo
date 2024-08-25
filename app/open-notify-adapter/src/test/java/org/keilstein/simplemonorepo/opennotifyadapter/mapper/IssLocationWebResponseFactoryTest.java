package org.keilstein.simplemonorepo.opennotifyadapter.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.keilstein.domain.Geolocation;
import org.keilstein.simplemonorepo.opennotifyadapter.controller.model.IssLocationWebResponse;
import org.keilstein.simplemonorepo.opennotifyadapter.domain.Precision;
import org.keilstein.simplemonorepo.opennotifyadapter.service.model.IssLocationServiceResult;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class IssLocationWebResponseFactoryTest {

  @InjectMocks
  private IssLocationWebResponseFactory tested;

  @Test
  void shouldCreateIssLocationWebResponse() {
    var location = mock(Geolocation.class);
    var precision = mock(Precision.class);

    var result = IssLocationServiceResult.builder()
        .location(location)
        .precision(precision)
        .build();
    var expected = IssLocationWebResponse.builder()
        .location(location)
        .precision(precision)
        .build();

    var actual = tested.createIssLocationWebResponse(result);

    assertThat(actual).isEqualTo(expected);
  }
}