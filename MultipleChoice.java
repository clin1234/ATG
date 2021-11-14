
public class MultipleChoice extends Question {

	// Data Members

	private String[] responseOptions;

	// Constructor
	/**
	 * 
	 * @param theSubject       Subject
	 * @param theQuestion      Question
	 * @param theCorrectAnswer Correct choice
	 * @param chocies          Response options
	 */
	public MultipleChoice(Exam.Subject theSubject, String theQuestion, String theCorrectAnswer, String... choices) {

		// Calling super-class' constructor
		super(theSubject, theQuestion);
		setCorrectAnswer(theCorrectAnswer);

		// Adding responses options to the array
		responseOptions = choices;
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
		responseOptions = choices;
	}

	@Override
	public void checkAnswer(String s) {
		setCorrect(getCorrectAnswer().equals(responseOptions[Short.parseShort(s) - 1].toLowerCase()));
	}

	@Override
	public String showForWrongQ() {
		var s = getCorrectAnswer();
		return s + ". " + responseOptions[Short.parseShort(s) - 1];
	}
}
