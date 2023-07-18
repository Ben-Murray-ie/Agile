package ie.atu.sw;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Random;

class CustomerTest {
	Random rng = new Random();
	static int randomAge;
	static int randomAccidents;
	static Customer testCustomer;

	// Verify Accessor Methods for Customer.

	@BeforeAll
	static void setUpCustomer() {
		testCustomer = new Customer();
	}

	@BeforeEach
	void setupVariables() {
		randomAge = (rng.nextInt(99) + 18);
		randomAccidents = rng.nextInt(10);
		testCustomer.setAge(randomAge);
		testCustomer.setAccidents(randomAccidents);
	}

	@Test
	void testGetAge() {
		assertEquals(testCustomer.getAge(), randomAge);
	}

	@Test
	void testSetAge() {
		testCustomer.setAge(randomAge);
		assertEquals(testCustomer.getAge(), randomAge);
	}

	@Test
	void testSetAgeUnder17() {
		assertThrows(IllegalArgumentException.class, () -> {testCustomer.setAge(16);});
	}

	@Test
	void testGetAccidents() {
		assertEquals(testCustomer.getAccidents(), randomAccidents);
	}

	@Test
	void testSetAccidents() {
		testCustomer.setAccidents(randomAccidents);
		assertEquals(testCustomer.getAccidents(), randomAccidents);
	}

	@Test
	void testSetAccidentsNegative() {
		assertThrows(IllegalArgumentException.class, () -> {testCustomer.setAccidents(-1);});
	}

	// Verify Constructors for Customer
	
	@Test
	void testCustomerConstructor() {
		Customer testCC = new Customer();
		assertNotNull(testCC);
	}

	@Test
	void testCustomerConstructorParams() {
		Customer testCCP = new Customer(26, 1);
		assertNotNull(testCCP);
	}

	@AfterEach
	void tearDownVariables() {
		testCustomer.setAge(18);
		testCustomer.setAccidents(0);
	}

	@AfterAll
	static void tearDownCustomer() {
		testCustomer = null;
	}
}
