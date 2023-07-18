package ie.atu.sw;

import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SelectClasses;

@Suite
@SelectClasses({ CustomerTest.class, InsuranceProviderTest.class, RunnerAutomatedTest.class })
class SuiteTest {
}
