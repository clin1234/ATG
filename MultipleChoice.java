

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
	public MultipleChoice(String theSubject, String theQuestion, String theCorrectAnswer, String... choices) {
		
		// Calling super-class' constructor
		super(theSubject, theQuestion, theCorrectAnswer);
		
		// Adding responses options to the array
		responseOptions[0] = choices[0]
		responseOptions[1] = choices[1]
		responseOptions[2] = choices[2]
		responseOptions[3] = choices[3]
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
	
	public void setResponseOptions(String... choices) {
		
		responseOptions[0] = choices[0]
		responseOptions[1] = choices[1]
		responseOptions[2] = choices[2]
		responseOptions[3] = choices[3]
	}

}
