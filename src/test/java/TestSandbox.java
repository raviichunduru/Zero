import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSandbox {
  /**
   * a very basic test
   */
  @Test
  void assertThatTrueIsTrue() {
    assertTrue(true, "true is true");
  }

  @Tag("failing")
  @Test
  void assertThatDayIsADay() {
    assertEquals("day", "night", "day is a day");
  }

  @Tag("flaky")
  @Test
  void createFlakyTest() {
    long currentTimeStamp = System.currentTimeMillis();
    // remove this with logging statement
    System.out.println(currentTimeStamp);
    if (currentTimeStamp % 2 == 0) {
      assertTrue(true, "time is even");
    } else {
      assertTrue(false, "time is odd");
    }
  }
}
