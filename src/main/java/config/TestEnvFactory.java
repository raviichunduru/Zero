package config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import lombok.extern.slf4j.Slf4j;
import java.io.File;

@Slf4j
public class TestEnvFactory {

  private static final TestEnvFactory UNIQUE_INSTANCE = new TestEnvFactory();
  private Config config;

  private TestEnvFactory() {
    // private constructor to restrict any one to create instance of this class
    config=setConfig();

  }

  public static TestEnvFactory getInstance() {
    return  UNIQUE_INSTANCE;
  }

  public Config getConfig() {
    return config;
  }

  private Config setConfig() {
    log.info("Call setConfig only once for the whole test run!");

    try {

      // Standard config load behavior (loads common config from application.conf file)
      // https://github.com/lightbend/config#standard-behavior
      config = ConfigFactory.load();

      TestEnv testEnv = config.getEnum(TestEnv.class, "TEST_ENV");

      String envPath = String.format("src/main/resources/%s", testEnv.getValue());
      log.info("envPath : {}",envPath);

      File testEnvDir = new File(envPath);

      for (File file : testEnvDir.listFiles()) {
        String envFilePath = String.format("%s/%s", testEnv.getValue(), file.getName());
        log.info("envFilePath : {}",envFilePath);
        Config childConfig = ConfigFactory.load(envFilePath);
        config = config.withFallback(childConfig);
      }
      return config;
    }
    catch(Exception exception) {
      exception.printStackTrace();
      throw new IllegalStateException("couldn't parse config");
    }
  }
}