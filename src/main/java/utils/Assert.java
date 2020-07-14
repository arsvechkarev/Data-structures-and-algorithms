package utils;

import java.util.function.Supplier;

public class Assert {

  public static void assertThat(boolean condition) {
    assertThat(condition, null);
  }

  public static void assertThat(boolean condition, Supplier<String> lazyMessage) {
    if (!condition) {
      String message = "Assertion failed";
      if (lazyMessage != null) {
        message = lazyMessage.get();
      }
      throw new IllegalArgumentException(message);
    }
  }
}
