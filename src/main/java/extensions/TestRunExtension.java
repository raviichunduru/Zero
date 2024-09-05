package extensions;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public class TestRunExtension implements BeforeAllCallback, ExtensionContext.Store.CloseableResource {

  AtomicBoolean isTestRunStarted = new AtomicBoolean(false);

  @Override
  public void beforeAll(ExtensionContext context) throws Exception {
    try {
      if (isTestRunStarted.compareAndSet(false,true)) {
        log.info("Run this section only once at the beginning of whole test run ");
        context.getRoot().getStore(ExtensionContext.Namespace.GLOBAL).put("TestRunExtension", this);

        // Add your database connection pool setup here.
        // DBConnectionPool.getInstance().setup();


      }
    }
    catch (Exception e){
      log.error("Exception in TestRunExtension : {}", e);
      log.error("⚠ Terminating Test run, since tests depend on the setup done in TestRunExtension class");
      System.exit(1);
    }
  }

  @Override
  public void close() throws Throwable {
    log.info("Run this section only once at the end of whole test run ");

    // Close your database connection pool here.
    // DBConnectionPool.getInstance().close();
  }
}
