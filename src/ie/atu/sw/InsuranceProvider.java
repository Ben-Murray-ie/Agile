package ie.atu.sw;

public class InsuranceProvider {
	private int basicInsurance = 500;
	private int surcharge = 100;
	private int penalty;
	private int total;
	private boolean insuranceDenied;
	private int[] penaltyValues = { 0, 50, 125, 225, 375, 575 };

	// Accessor methods for InsuranceProvider variables.

	public int getBasicInsurance() {
		return basicInsurance;
	}

	public void setBasicInsurance(int basicInsurance) {
		this.basicInsurance = basicInsurance;
	}

	public int getSurcharge() {
		return surcharge;
	}

	public void setSurcharge(int surcharge) {
		this.surcharge = surcharge;
	}

	public int getPenalty() {
		return penalty;
	}

	public void setPenalty(int penalty) {
		this.penalty = penalty;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public boolean isInsuranceDenied() {
		return insuranceDenied;
	}

	public void setInsuranceDenied(boolean insuranceDenied) {
		this.insuranceDenied = insuranceDenied;
	}

	public int[] getPenaltyValues() {
		return penaltyValues;
	}

	public int getPenaltyValuesByIndex(int index) {
		return penaltyValues[index];
	}

	public void setPenaltyValues(int[] penaltyValues) {
		this.penaltyValues = penaltyValues;
	}

	// Constructors for InsuranceProvider
	
	public InsuranceProvider(int basicInsurance, int surcharge) {
		this.basicInsurance = basicInsurance;
		this.surcharge = surcharge;
	}

	public InsuranceProvider() {

	}

	// InsuranceProvider methods for calculating penalties and quotes.

	public int printPenaltyValue(int index) {
		if (index > this.penaltyValues.length) {
			throw new ArrayIndexOutOfBoundsException("Requested penalty value not available.");
		} else {
			int penaltyValueAtIndex = this.penaltyValues[index];
			System.out.println(penaltyValueAtIndex);
			return penaltyValueAtIndex;
		}
	}

	public int quoteUnder25() {
		this.total = this.basicInsurance + this.surcharge + this.penalty;
		return total;
	}

	public int quoteOver25() {
		this.total = this.basicInsurance + this.penalty;
		return total;
	}

	public int calculatePenalty(int accidents) {
		if (accidents >= 6) {
			System.out.println("No insurance");
			setInsuranceDenied(true);
			return -1;
		} else {
			switch (accidents) {
			case 0:
				setPenalty(this.getPenaltyValuesByIndex(0));
				System.out.println("No surcharge.");
				break;
			case 1:
				setPenalty(this.getPenaltyValuesByIndex(1));
				System.out.println("Additional surcharge for accident: " + penalty);
				break;
			case 2:
				setPenalty(this.getPenaltyValuesByIndex(2));
				System.out.println("Additional surcharge for accident: " + penalty);
				break;
			case 3:
				setPenalty(this.getPenaltyValuesByIndex(3));
				System.out.println("Additional surcharge for accident: " + penalty);
				break;
			case 4:
				setPenalty(this.getPenaltyValuesByIndex(4));
				System.out.println("Additional surcharge for accident: " + penalty);
				break;
			case 5:
				setPenalty(this.getPenaltyValuesByIndex(5));
				System.out.println("Additional surcharge for accident: " + penalty);
				break;
			}
		}
		return getPenalty();
	}
}