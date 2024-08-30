
import com.typesafe.config.Config;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mycompany.config.TestEnvFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class TestSandbox {
  final Config CONFIG = TestEnvFactory.getInstance().getConfig();

  @Test
  void assertThatWeCanGetUserConfig() {


    log.info(CONFIG.getString("TEST_ENV"));
    log.info(CONFIG.getString("CREATE_EMPLOYEE_ENDPOINT"));
    log.info(CONFIG.getString("ADMIN_LOGIN"));
    log.info(CONFIG.getString("ADMIN_NAME"));

    log.info(String.valueOf(CONFIG.getBoolean("TOGGLE")));
    log.info(String.valueOf(CONFIG.getInt("NUM_OF_USERS")));
    log.info(String.valueOf(CONFIG.getDouble("PRICE")));
  }

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