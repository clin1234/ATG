
public class TrueOrFalse extends Question {

	// Data Members
	
	private static final String TRUEOPTION = "True";
	private static final String FALSEOPTION = "False";
	
	// Constructor
	public TrueOrFalse(String s, String q, String ca, String TRUEOPTION, String FALSEOPTION) {
		
		// Calling super-class' constructor
		super(s,q,ca);
	}
	
	public void printOptions() {
		
		// For example purposes I'll be using System.out.println()
		System.out.println("   " + "O " + TRUEOPTION);
		System.out.println("   " + "O " + FALSEOPTION);
		
	}

}


/*
	Notes:

	Maybe add another variable that shows the correct answer to when the
	answer is false
	
	No need to pass in to and fo in the constructor, we can just initialize it in declaration (since they will never change).
*/