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

      String testEnvDirPath = String.format("src/main/resources/%s", testEnv);
      File testEnvDir = new File(testEnvDirPath);

      for (File file : testEnvDir.listFiles()) {
        Config childConfig = ConfigFactory.load(String.format("%s/%s", testEnv, file.getName()));
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
