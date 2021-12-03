import java.util.Arrays;

public class MultipleChoice extends Question {

    // Data Members

    private String[] responseOptions;
    private String correctAnswer;

    /**
     * @param theSubject       Subject
     * @param theQuestion      Question
     * @param theCorrectAnswer Correct choice
     * @param choices          Response options
     */

    // Constructor
    public MultipleChoice(Subject theSubject, String theQuestion, String theCorrectAnswer, String... choices) {

        // Calling super-class' constructor
        super(theSubject, theQuestion);
        correctAnswer = theCorrectAnswer.toLowerCase();

        // Adding responses options to the array
        responseOptions = Arrays.stream(choices).map(String::toLowerCase).toArray(String[]::new);
    }


    // Methods

    // Printing all response options
    public final void printOptions() {
        // For example purposes I'll be using System.out.println()
        for (int i = 0; i < responseOptions.length; i++)
            System.out.printf("%d. %s%n", i + 1, responseOptions[i]);
    }

    @Override
    public final void checkAnswer(String s) {
        if (s.isEmpty()) setCorrect(false);
        else setCorrect(correctAnswer.equals(responseOptions[Integer.parseInt(s) - 1]));
    }

    @Override
    public final String showForWrongQ() {
        var sb = new StringBuilder(20);
        for (int i = 0; i < responseOptions.length; i++) {
            sb.append("%d. %s".formatted(i + 1, responseOptions[i]));
            if (correctAnswer.equals(responseOptions[i])) sb.append(" <-- Correct answer");
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    public final String getCorrectAnswer() {
        return correctAnswer;
    }

    public final String[] getResponseOptions() {
        return responseOptions;
    }

    public final void setResponseOptions(String... choices) {
        responseOptions = choices;
    }
}
