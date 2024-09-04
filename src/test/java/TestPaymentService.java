import annotations.SmokeTest;
import com.typesafe.config.Config;
import config.TestEnvFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Tag;
import setup.TestSetup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("payment-service")
@Slf4j
public class TestPaymentService extends TestSetup {

  @SmokeTest
  void assertThatTrueIsTrue() {
    assertTrue(true, "true is true");
  }

  @SmokeTest
  void assertThatTestForChosenEnvRuns() {
    Config CONFIG = TestEnvFactory.getInstance().getConfig();
    String expectedEnv = CONFIG.getString("TEST_ENV");
    log.info("expectedEnv is : {}",expectedEnv);
    assertEquals(expectedEnv, CONFIG.getString("TEST_ENV"),"TEST_ENV");
  }
}