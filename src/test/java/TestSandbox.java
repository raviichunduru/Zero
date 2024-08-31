
import annotations.FlakyTest;
import annotations.SmokeTest;
import com.typesafe.config.Config;
import config.TestEnvFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import annotations.FailingTest;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class TestSandbox {
  @Test
  void assertThatWeCanGetUserConfig() {
    final Config CONFIG = TestEnvFactory.getInstance().getConfig();
    assertAll("Config Test",
      ()->assertEquals("STAGING",CONFIG.getString("TEST_ENV"),"TEST_ENV"),
      ()->assertEquals("/employee/create",CONFIG.getString("CREATE_EMPLOYEE_ENDPOINT"),"CREATE_EMPLOYEE_ENDPOINT"),
      ()->assertEquals("staging-admin_login",CONFIG.getString("ADMIN_LOGIN"),"ADMIN_LOGIN"),
      ()->assertEquals("staging-admin-name",CONFIG.getString("ADMIN_NAME"),"ADMIN_NAME"),
      ()->assertEquals(false,CONFIG.getBoolean("TOGGLE"),"TOGGLE"),
      ()->assertEquals(10,CONFIG.getInt("NUM_OF_USERS"),"NUM_OF_USERS"),
      ()->assertEquals(123.456,CONFIG.getDouble("PRICE"),"PRICE")
    );
  }

  @SmokeTest
  void assertThatTrueIsTrue() {
    assertTrue(true, "true is true");
  }

  @FailingTest
  void assertThatDayIsADay() {
    assertEquals("day", "night", "day is a day");
  }

  @FlakyTest
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