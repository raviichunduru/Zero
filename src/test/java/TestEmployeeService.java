import annotations.SmokeTest;
import com.typesafe.config.Config;
import config.TestEnvFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Tag;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("employee-service")
@Slf4j
public class TestEmployeeService {
  @SmokeTest
  void assertThatTestForChosenEnvRuns() {
    Config CONFIG = TestEnvFactory.getInstance().getConfig();
    String expectedEnv = CONFIG.getString("TEST_ENV");
    log.info("expectedEnv is : {}",expectedEnv);
    assertEquals(expectedEnv, CONFIG.getString("TEST_ENV"),"TEST_ENV");
  }
}