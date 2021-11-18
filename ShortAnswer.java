import java.util.ArrayDeque;
import java.util.stream.Stream;

public class ShortAnswer extends Question {
    private ArrayDeque<String> missingPhrases;

    /**
     * 
     * @param theSubject  Subject
     * @param theQuestion Question
     * @param keywords    Keywords expected in the user's answer for the user's
     *                    answer to be deemed correct
     */
    public ShortAnswer(String theSubString, String theQuestion, String... keywords) {
        super(theSubString, theQuestion, String.join(", ", keywords));
    }

    public boolean isCorrect() {
        if (Exam.getUserAnswer() == null)
            return false;
        var userAnswer = Exam.getUserAnswer();
        var expectedKeywords = Stream.of(super.getCorrectAnswer().split(", ")).map(String::trim).toArray(String[]::new);
        missingPhrases = new ArrayDeque<>(expectedKeywords.length);
        for (var phrase : expectedKeywords)
            if (!userAnswer.contains(phrase))
                missingPhrases.add(phrase);

        return missingPhrases.size() == 0;
    }

    @Override
    public String getCorrectAnswer() {
        return "Your answer needs the following phrases: " + String.join(", ", missingPhrases);
    }

}
