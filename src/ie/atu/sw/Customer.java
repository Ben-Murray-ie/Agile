package ie.atu.sw;

public class Customer {
	private int age;
	private int accidents;

	// Constructors for Customer

	public Customer(int age, int accidents) {
		this.age = age;
		this.accidents = accidents;
	}

	public Customer() {
	}

	// Accessor methods for Customer variables with exception handling.

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		if (age <= 17) {
			throw new IllegalArgumentException("Cannot be insured under 17 years of age.");
		} else {
			this.age = age;
		}
	}

	public int getAccidents() {
		return accidents;
	}

	public void setAccidents(int accidents) {
		if (accidents < 0) {
			throw new IllegalArgumentException("Cannot have negative number of accidents.");
		} else {
			this.accidents = accidents;
		}
	}
}
