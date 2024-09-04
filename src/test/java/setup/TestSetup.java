package setup;

import extensions.ReportingExtension;
import extensions.TimingExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(ReportingExtension.class)
@ExtendWith(TimingExtension.class)
public class TestSetup {
}
