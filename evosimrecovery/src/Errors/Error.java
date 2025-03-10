package Errors;

public class Error extends Exception {
	private double value;
	
	public Error(double value) {
		this.value = value;		
	}
	
	public void printError() {
		System.err.print("Error. " + this.value + " is not a value ");
	}

}
