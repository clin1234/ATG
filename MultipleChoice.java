

public class MultipleChoice extends Question {

	// Data Members
	
	private String[] responseOptions = new String[4];
	
	// Constructor
	/**
	 * 
	 * @param theSubject Subject
	 * @param theQuestion Question
	 * @param theCorrectAnswer Correct choice
	 * @param chocies Response options
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
