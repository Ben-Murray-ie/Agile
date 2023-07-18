package ie.atu.sw;

import java.util.Scanner;

public class Runner {
	
	//Insurance Program - Assignment 2 - Agile Module
	
	// Ben Murray - G00275740

	public static void main(String[] args) {

		// Default main method, includes Scanner for accepting user input.

		Scanner s = new Scanner(System.in);
		InsuranceProvider ip = new InsuranceProvider();
		Customer c = new Customer();

		System.out.println("Enter your Age: ");
		c.setAge(s.nextInt());

		System.out.print("\nHow many accidents did you have? \n");
		c.setAccidents(s.nextInt());

		s.close();

		ip.calculatePenalty(c.getAccidents());
		if (c.getAge() < 25) {
			ip.quoteUnder25();
		} else {
			ip.quoteOver25();
		}
		if (c.getAccidents() <= 5) {
			System.out.println("Total amount to pay: " + ip.getTotal());
		}
	}

	// Added method for testing purposes. Bypasses Scanner so SuiteTest can be run
	// in a single operation, without user input.

	public int verifyRunner(int age, int accidents) {
		InsuranceProvider ip = new InsuranceProvider();
		Customer c = new Customer();

		c.setAge(age);
		c.setAccidents(accidents);

		ip.calculatePenalty(c.getAccidents());
		if (c.getAge() < 25) {
			ip.quoteUnder25();
		} else {
			ip.quoteOver25();
		}
		if (c.getAccidents() <= 5) {
			System.out.println("Total amount to pay: " + ip.getTotal());
		}
		return ip.getTotal();
	}
}
