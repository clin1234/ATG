import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShortAnswer extends Question {
    private HashSet<String> missingPhrases;
    private final HashSet<String> expectedKeywords;

    /**
     * 
     * @param subject  Subject
     * @param theQuestion Question
     * @param keywords    Keywords expected in the user's answer for the user's
     *                    answer to be deemed correct
     */
    public ShortAnswer(Exam.Subject subject, String theQuestion, String... keywords) {
        super(subject, theQuestion);
        expectedKeywords = Stream.of(keywords).map(String::trim).collect(Collectors.toCollection(HashSet::new));
    }

    @Override
    public void checkAnswer(String input) {
        missingPhrases = new HashSet<>(expectedKeywords.size());
        expectedKeywords.parallelStream().filter(p -> !input.contains(p)).peek(missingPhrases::add);

        setCorrect(missingPhrases.isEmpty());
    }

    @Override
    public String showForWrongQ() {
        return "Your answer needs the following phrases: " + String.join(", ", missingPhrases);
    }
}
