package extensions;

import extensions.report.PublishResults;
import extensions.report.TestRunMetaData;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class ReportingExtension implements AfterEachCallback {
  @Override
  public void afterEach(ExtensionContext context) throws Exception {
    TestRunMetaData testRunMetaData = new TestRunMetaData().setBody(context);
    PublishResults.toElastic(testRunMetaData);
  }
}
