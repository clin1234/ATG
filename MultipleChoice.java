import java.util.Arrays;
import java.util.Locale;

public class MultipleChoice extends Question {

	// Data Members

	private String[] responseOptions;
	private String correctAnswer;

	// Constructor
	/**
	 * 
	 * @param theSubject       Subject
	 * @param theQuestion      Question
	 * @param theCorrectAnswer Correct choice
	 * @param choices          Response options
	 */
	public MultipleChoice(Exam.Subject theSubject, String theQuestion, String theCorrectAnswer, String... choices) {

		// Calling super-class' constructor
		super(theSubject, theQuestion);
		correctAnswer = theCorrectAnswer.toLowerCase();

		// Adding responses options to the array
		responseOptions = Arrays.stream(choices).map(String::toLowerCase).toArray(String[]::new);
	}

	// Methods

	// Printing all response options
	@Override
	public void printOptions() {
		// For example purposes I'll be using System.out.println()
		for (int i = 0; i < responseOptions.length; i++)
			System.out.println("   " + (i + 1) + ". " + responseOptions[i]);
	}

	public String[] getResponseOptions() {
		return responseOptions;
	}

	public void setResponseOptions(String... choices) {
		responseOptions = choices;
	}

	@Override
	public void checkAnswer(String s) {
		setCorrect(correctAnswer.equals(responseOptions[Short.parseShort(s) - 1]));
	}

	@Override
	public String showForWrongQ() {
		return (Arrays.asList(responseOptions).indexOf(correctAnswer)+1) + ". " + correctAnswer;
	}
}
