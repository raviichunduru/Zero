package extensions;

import com.typesafe.config.Config;
import config.TestEnvFactory;
import extensions.report.PublishResults;
import extensions.report.TestRunMetaData;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

@Slf4j
public class ReportingExtension implements AfterEachCallback {

  private static final Config CONFIG = TestEnvFactory.getInstance().getConfig();
  private static final Boolean PUBLISH_RESULTS_TO_ELASTIC =
      CONFIG.getBoolean("PUBLISH_RESULTS_TO_ELASTIC");

  @Override
  public void afterEach(ExtensionContext context) throws Exception {
    if (PUBLISH_RESULTS_TO_ELASTIC) {
      log.info("publishing results to elastic");
      TestRunMetaData testRunMetaData = new TestRunMetaData().setBody(context);
      PublishResults.toElastic(testRunMetaData);
    } else {
      log.info("Skipping publishing results to console since PUBLISH_RESULTS_TO_ELASTIC=false");
    }
  }
}
