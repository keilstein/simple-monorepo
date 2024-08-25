package org.keilstein.commons.testutils.validation;

import lombok.experimental.UtilityClass;

import static jakarta.validation.Validation.buildDefaultValidatorFactory;
import static org.assertj.core.api.Assertions.assertThat;

@UtilityClass
public class ValidationUtils {

  public void validate(Object object, int violations) {
    var constraintViolations = buildDefaultValidatorFactory().getValidator().validate(object);

    assertThat(constraintViolations.size()).isEqualTo(violations);
  }

  public void validate(Object object) {
    validate(object, 0);
  }
}