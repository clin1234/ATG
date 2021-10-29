
public class TrueOrFalse extends Question {

	// Data Members
	
	private String trueOption;
	private String falseOption;
	
	// Constructor
	public TrueOrFalse(String s, String q, String ca, String to, String fo) {
		
		// Calling super-class' constructor
		super(s,q,ca);
		
		// Initializing true and false response options
		trueOption = to;
		falseOption = fo;
	}
	
	public void printOptions() {
		
		// For example purposes I'll be using System.out.println()
		System.out.println("   " + "A. " + trueOption);
		System.out.println("   " + "B. " + falseOption);
		
	}

}


/*
	Notes:

	Maybe add another variable that shows the correct answer to when the
	answer is false
	
	No need to pass in to and fo in the constructor, we can just initialize it in declaration (since they will never change).
*/