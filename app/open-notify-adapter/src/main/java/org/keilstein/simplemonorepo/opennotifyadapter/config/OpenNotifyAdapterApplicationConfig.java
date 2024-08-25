package org.keilstein.simplemonorepo.opennotifyadapter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class OpenNotifyAdapterApplicationConfig {

  @Bean
  WebClient webClient() {
    return WebClient.builder()
        .build();
  }
}