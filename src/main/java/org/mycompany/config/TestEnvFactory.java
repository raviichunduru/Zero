package org.mycompany.config;

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
    try {
      log.info("setConfig called");

      // Standard config load behaviour : https://github.com/lightbend/config?tab=readme-ov-file#standard-behavior

      config = ConfigFactory.load();

      TestEnv testEnv = config.getEnum(TestEnv.class, "TEST_ENV");
      String testEnvName = testEnv.toString().toLowerCase();

      String envPath = String.format("src/main/resources/%s", testEnvName);
      log.info("envPath : {}",envPath);

      File testEnvDir = new File(envPath);

      for (File file : testEnvDir.listFiles()) {
        String envFilePath = String.format("%s/%s", testEnvName, file.getName());
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
