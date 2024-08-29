import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSandbox {
  /**
   * a very basic test
   */
  @Test
  void assertThatTrueIsTrue() throws InterruptedException {
    assertTrue(true, "true is true");
    Thread.sleep(2000);
  }

  @Test
  void assertThatDayIsADay() throws InterruptedException {
    assertEquals("day", "day","day is a day");
    Thread.sleep(2000);
  }
}
