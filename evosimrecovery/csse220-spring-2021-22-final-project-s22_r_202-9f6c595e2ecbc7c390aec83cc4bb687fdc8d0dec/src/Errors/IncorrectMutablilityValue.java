 package Errors;

public class IncorrectMutablilityValue extends Error {

	public IncorrectMutablilityValue(double value) {
		super(value);
	}

	public void printError() {
		super.printError();
		System.out.println("between 1 and 0");
	}
}
