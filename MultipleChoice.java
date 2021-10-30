

public class MultipleChoice extends Question {

	// Data Members
	
	private String[] responseOptions = new String[4];
	
	// Constructor
	/**
	 * 
	 * @param s Subject
	 * @param q Question
	 * @param ca Correct choice
	 * @param r1 Choice 1
	 * @param r2 Choice 2
	 * @param r3 Choice 3
	 * @param r4 Choice 4
	 */
	public MultipleChoice(String s, String q, String ca, String r1, String r2, String r3, String r4) {
		
		// Calling super-class' constructor
		super(s,q,ca);
		
		// Adding responses options to the array
		responseOptions[0] = r1;
		responseOptions[1] = r2;
		responseOptions[2] = r3;
		responseOptions[3] = r4;
	}
	
	// Methods
	
	// Printing all response options 
	public void printOptions() {
		for (int i = 0; i < responseOptions.length; i++) {
			
			// For example purposes I'll be using System.out.println()
			System.out.println("   " + (i + 1) + ". " + responseOptions[i]);
		}
	}
	
	public String[] getResponseOptions() {
		return responseOptions;
	}
	
	public void setResponseOptions(String r1, String r2, String r3, String r4) {
		
		responseOptions[0] = r1;
		responseOptions[1] = r2;
		responseOptions[2] = r3;
		responseOptions[3] = r4;
	}

}
