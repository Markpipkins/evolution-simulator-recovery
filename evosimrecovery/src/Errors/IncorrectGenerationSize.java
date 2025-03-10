package Errors;

public class IncorrectGenerationSize extends Error {

	public IncorrectGenerationSize(double value) {
		super(value);
	}

	public void printError() {
		super.printError();
		System.out.println("greater than 0");
	}
}