package org.keilstein.simplemonorepo.geolocationresolver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GeolocationResolverApplicationConfig {

  @Bean
  WebClient webClient() {
    return WebClient.builder()
        .build();
  }
}