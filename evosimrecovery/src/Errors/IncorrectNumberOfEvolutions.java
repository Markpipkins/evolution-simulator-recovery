package Errors;

public class IncorrectNumberOfEvolutions extends Error {

	public IncorrectNumberOfEvolutions(double value) {
		super(value);
	}

	public void printError() {
		super.printError();
		System.out.println("greater than 0");
	}
}
