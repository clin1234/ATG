import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShortAnswer extends Question {
    private final Set<String> expectedKeywords;
    private HashSet<String> missingPhrases;

    /**
     * @param subject     Subject
     * @param theQuestion Question
     * @param keywords    Keywords expected in the user's answer for the user's
     *                    answer to be deemed correct
     */
    public ShortAnswer(Subject subject, String theQuestion, String... keywords) {
        super(subject, theQuestion);
        expectedKeywords = Stream.of(keywords).map(String::trim).collect(Collectors.toCollection(HashSet::new));
    }

    @Override
    public final void checkAnswer(String userAns) {
        missingPhrases = new HashSet<>(expectedKeywords.size());
        Stream<String> stringStream = expectedKeywords.parallelStream().filter(p -> !userAns.contains(p));
        stringStream.forEach(missingPhrases::add);

        setCorrect(missingPhrases.isEmpty());
    }

    @Override
    public final String showForWrongQ() {
        return "Your answer needs the following phrases: " + String.join(", ", missingPhrases);
    }

    public final Set<String> getMissingPhrases() {
        return Collections.unmodifiableSet(missingPhrases);
    }
}
