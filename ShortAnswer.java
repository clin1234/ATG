import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShortAnswer extends Question {
    private final HashSet<String> expectedKeywords;
    private HashSet<String> missingPhrases;

    /**
     * @param subject     Subject
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
        Stream<String> stringStream = expectedKeywords.parallelStream().filter(p -> !input.contains(p));
        stringStream.forEach(missingPhrases::add);

        setCorrect(missingPhrases.isEmpty());
    }

    @Override
    public String showForWrongQ() {
        return "Your answer needs the following phrases: " + String.join(", ", missingPhrases);
    }
}
