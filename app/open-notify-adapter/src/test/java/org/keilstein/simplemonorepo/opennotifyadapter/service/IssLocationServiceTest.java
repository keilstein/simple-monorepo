package org.keilstein.simplemonorepo.opennotifyadapter.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.keilstein.simplemonorepo.opennotifyadapter.client.OpenNotifyClient;
import org.keilstein.simplemonorepo.opennotifyadapter.client.model.IssNowResponse;
import org.keilstein.simplemonorepo.opennotifyadapter.mapper.IssLocationServiceResultFactory;
import org.keilstein.simplemonorepo.opennotifyadapter.service.model.IssLocationServiceResult;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IssLocationServiceTest {

  @InjectMocks
  private IssLocationService tested;

  @Mock
  private OpenNotifyClient openNotifyClient;

  @Mock
  private IssLocationServiceResultFactory issLocationServiceResultFactory;

  @ParameterizedTest
  @ValueSource(booleans = {true, false})
  void shouldGetIssLocation(boolean force) {
    var response = mock(IssNowResponse.class);
    var result = mock(IssLocationServiceResult.class);

    when(openNotifyClient.getIssLocation(force)).thenReturn(Mono.just(response));
    when(issLocationServiceResultFactory.createIssLocationServiceResult(response)).thenReturn(result);

    var actual = tested.getIssLocation(force);

    StepVerifier.create(actual)
        .expectNext(result)
        .verifyComplete();
  }
}