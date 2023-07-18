package ie.atu.sw;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RunnerAutomatedTest {

	// Duplicated Runner.main() to bypass Scanner test (see testVerifyRunner) in
	// order allow SuiteTest to run without user input needed.
	
	// SuiteTest reports less than 50% code coverage due to main method being
	// excluded from test, but testVerifyRunner performs tests on a range of valid
	// scenarios for Runner.main().
	// (No Accidents, 3 and 5 Accidents and Excessive Accidents for Under 25 and Over 25).
	// Therefore, practically speaking, coverage of Runner.main() is closer to 100%.

	public static Runner testAutoRunner;
	public static InsuranceProvider testIP;

	@BeforeAll
	public static void setUpRunner() {
		testAutoRunner = new Runner();
		testIP = new InsuranceProvider();
	}

	@ParameterizedTest
	@CsvSource({ "18,0", "18,3", "18,5", "18,6", "26,0", "26,3", "26,5", "26,6" })
	@Timeout(value = 10, unit = TimeUnit.MILLISECONDS)
	void testVerifyRunner(int age, int accidents) {
		if (accidents >= 6) {
			testIP.calculatePenalty(accidents);
			assertTrue(testIP.isInsuranceDenied());
		} else {
			int penalty = testIP.calculatePenalty(accidents);
			int total = 0;
			if (age == 18) {
				total = (testIP.getBasicInsurance() + testIP.getSurcharge() + penalty);
			} else if (age == 26) {
				total = (testIP.getBasicInsurance() + penalty);
			}
			assertEquals(testAutoRunner.verifyRunner(age, accidents), total);
		}
	}

	@AfterAll
	public static void tearDownRunner() {
		testAutoRunner = null;
		testIP = null;
	}
}
