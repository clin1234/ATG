import java.util.Arrays;

public class MultipleChoice extends Question {

    // Data Members

    private String[] responseOptions;

    public final String getCorrectAnswer() {
        return correctAnswer;
    }

    private String correctAnswer;

    // Constructor

    /**
     * @param theSubject       Subject
     * @param theQuestion      Question
     * @param theCorrectAnswer Correct choice
     * @param choices          Response options
     */
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
        var sb = new StringBuilder(20);
        for (int i = 0; i < responseOptions.length; i++) {
            String tmp = "%d. %s".formatted(i + 1, responseOptions[i]);
            if (correctAnswer.equals(responseOptions[i])) tmp += " <-- Correct answer";
            sb.append(tmp + System.lineSeparator());
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return String.join(" ",  Arrays.toString(responseOptions), correctAnswer, super.toString());
    }
}
