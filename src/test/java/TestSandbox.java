
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
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
    log.info("currentTimeStamp : {}",currentTimeStamp);
    if (currentTimeStamp % 2 == 0) {
      assertTrue(true, "time is even");
    } else {
      assertTrue(false, "time is odd");
    }
  }

  @Test
  void testLogLevels() {
    log.info("this is info statement");
    log.debug("this is debug statement");
    log.error("this is error statement");
    log.warn("this is warn statement");
    log.trace("this is trace statement");
  }
}
