package org.keilstein.simplemonorepo.opennotifyadapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class OpenNotifyAdapterApplication {

  public static void main(String[] args) {
    SpringApplication.run(OpenNotifyAdapterApplication.class, args);
  }
}