package org.keilstein.commons.testutils.validation;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.Map;

import static java.util.Collections.singletonMap;
import static org.keilstein.commons.testutils.validation.ValidationUtils.validate;

class ValidationUtilsTest {

  private static TestClass.TestClassBuilder createValidTestObjectBuilder() {
    return TestClass.builder()
        .field1(3L)
        .field2("value2")
        .field4(TestClass.InnerTestClass.builder()
            .fieldA(singletonMap("attribute", "value"))
            .build());
  }

  @Test
  void shouldValidate() {
    var tested = createValidTestObjectBuilder().build();

    validate(tested);
  }

  @Test
  void shouldValidateMinConstraint() {
    var tested = createValidTestObjectBuilder()
        .field1(1L)
        .build();

    validate(tested, 1);
  }

  @Test
  void shouldValidateMaxConstraint() {
    var tested = createValidTestObjectBuilder()
        .field1(5L)
        .build();

    validate(tested, 1);
  }

  @Test
  void shouldValidateNotNullConstraint() {
    var tested = createValidTestObjectBuilder()
        .field1(null)
        .build();

    validate(tested, 1);
  }

  @ParameterizedTest
  @NullAndEmptySource
  void shouldValidateNotBlankConstraint(String value2) {
    var tested = createValidTestObjectBuilder()
        .field2(value2)
        .build();

    validate(tested, 1);
  }

  @ParameterizedTest
  @NullAndEmptySource
  void shouldValidateValidConstraint(Map<String, String> valueA) {
    var tested = createValidTestObjectBuilder()
        .field4(TestClass.InnerTestClass.builder()
            .fieldA(valueA)
            .build())
        .build();

    validate(tested, 1);
  }

  @Builder
  private record TestClass(@Min(2) @Max(3) @NotNull Long field1, @NotBlank String field2, String field3,
                           @Valid InnerTestClass field4) {
    @Builder
    private record InnerTestClass(@NotEmpty Map<String, String> fieldA, Long fieldB) {
    }
  }
}