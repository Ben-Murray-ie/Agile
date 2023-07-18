package ie.atu.sw;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.concurrent.TimeUnit;

class InsuranceProviderTest {
	static InsuranceProvider ipTest;

	// Tests for Class functionality.

	@BeforeAll
	public static void setUpInsuraceProvider() {
		ipTest = new InsuranceProvider();
	}

	@Test
	@Timeout(value = 10, unit = TimeUnit.MILLISECONDS)
	void testCalculatePenaltyNoAccidents() {
		assertEquals(ipTest.calculatePenalty(0), 0);
	}

	@ParameterizedTest
	@CsvSource({ "1,50", "2,125", "3,225", "4,375", "5,575" })
	@Timeout(value = 10, unit = TimeUnit.MILLISECONDS)
	void testCalculatePenalty(int test, int penalty) {
		assertEquals(ipTest.calculatePenalty(test), penalty);
	}

	@Test
	@Timeout(value = 10, unit = TimeUnit.MILLISECONDS)
	void testCalculatePenaltyExcessiveAccidents() {
		int testIterator = 6;
		while (testIterator < 10) {
			assertEquals(ipTest.calculatePenalty(6), -1);
			testIterator++;
		}
	}

	@ParameterizedTest
	@ValueSource(ints = { 0, 50, 125, 225, 375, 575 })
	@Timeout(value = 10, unit = TimeUnit.MILLISECONDS)
	void testQuoteUnder25(int test) {
		ipTest = new InsuranceProvider(500, 100);
		ipTest.setPenalty(test);
		assertEquals(ipTest.quoteUnder25(), 600 + test);
	}

	@ParameterizedTest
	@ValueSource(ints = { 0, 50, 125, 225, 375, 575 })
	@Timeout(value = 10, unit = TimeUnit.MILLISECONDS)
	void testQuoteOver25(int test) {
		ipTest = new InsuranceProvider(500, 0);
		ipTest.setPenalty(test);
		assertEquals(ipTest.quoteOver25(), 500 + test);
	}

	@ParameterizedTest
	@ValueSource(ints = { 0, 2, 4, 6, 8 })
	void testPrintPenaltyValueOutOfBounds(int index) {
		if (index >= 6) {
			assertThrows(ArrayIndexOutOfBoundsException.class, () -> {ipTest.printPenaltyValue(index);});
		} else {
			int verifyPenaltyValue = ipTest.printPenaltyValue(index);
			assertEquals(verifyPenaltyValue, ipTest.getPenaltyValuesByIndex(index));
		}

	}

	// Verify Accessor Methods for InsuranceProvider.

	@BeforeEach
	void setupVariables() {
		ipTest.setBasicInsurance(500);
		ipTest.setSurcharge(100);
		ipTest.setPenalty(0);
		ipTest.setTotal(500);
	}

	@Test
	void testGetBasicInsurance() {
		assertEquals(ipTest.getBasicInsurance(), 500);
	}

	@Test
	void testGetSurcharge() {
		assertEquals(ipTest.getSurcharge(), 100);
	}

	@Test
	void testGetPenalty() {
		assertEquals(ipTest.getPenalty(), 0);
	}

	@Test
	void testGetTotal() {
		assertEquals(ipTest.getTotal(), 500);
	}

	@Test
	void testSetBasicInsurance() {
		ipTest.setBasicInsurance(1000);
		assertEquals(ipTest.getBasicInsurance(), 1000);
	}

	@Test
	void testSetSurcharge() {
		ipTest.setSurcharge(1000);
		assertEquals(ipTest.getSurcharge(), 1000);
	}

	@Test
	void testSetPenalty() {
		ipTest.setPenalty(1000);
		assertEquals(ipTest.getPenalty(), 1000);
	}

	@Test
	void testSetTotal() {
		ipTest.setTotal(1000);
		assertEquals(ipTest.getTotal(), 1000);
	}
	
	@Test
	void testIsInsuranceDenied() {
		ipTest.setInsuranceDenied(true);
		assertTrue(ipTest.isInsuranceDenied());
	}
	
	@Test
	void testGetPenaltyValues() {
		int[] testArray = { 0, 50, 125, 225, 375, 575 };
		assertArrayEquals(ipTest.getPenaltyValues(), testArray);
	}
	
	@Test
	void testSetPenaltyValues() {
		int[] testArray = { 0, 50, 125, 225, 375, 575 };
		ipTest.setPenaltyValues(testArray);
		assertArrayEquals(ipTest.getPenaltyValues(), testArray);
	}

	@AfterEach
	void tearDownVariables() {
		ipTest.setBasicInsurance(0);
		ipTest.setSurcharge(0);
		ipTest.setPenalty(0);
		ipTest.setTotal(0);
	}

	@AfterAll
	static void tearDownInsuranceProvider() {
		ipTest = null;
	}

}
