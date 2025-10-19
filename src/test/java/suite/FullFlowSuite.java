package suite;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import tests.RegistrationTest;
import tests.NewApplicationTest;

@Suite
@SelectClasses({
        RegistrationTest.class,
        NewApplicationTest.class
})
public class FullFlowSuite {
}
