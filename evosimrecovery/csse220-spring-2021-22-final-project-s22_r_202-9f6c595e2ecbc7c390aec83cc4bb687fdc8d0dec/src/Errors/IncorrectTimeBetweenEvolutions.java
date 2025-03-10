package Errors;

public class IncorrectTimeBetweenEvolutions extends Error {

	public IncorrectTimeBetweenEvolutions(double value) {
		super(value);
		// TODO Auto-generated constructor stub
	}
	
	public void printError() {
		super.printError();
		System.out.println("greater than 0");
	}

}
