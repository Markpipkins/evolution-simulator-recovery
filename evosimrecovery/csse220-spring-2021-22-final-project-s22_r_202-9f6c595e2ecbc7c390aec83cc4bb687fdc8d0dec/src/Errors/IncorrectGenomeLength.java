package Errors;

public class IncorrectGenomeLength extends Error{

	public IncorrectGenomeLength(double value) {
		super(value);
		// TODO Auto-generated constructor stub
	}
	
	public void printError() {
		super.printError();
		System.out.println("greater than 0");
	}

}
