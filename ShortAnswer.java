import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class ShortAnswer extends Question {
    private HashSet<String> userAns;
    private HashSet<String> expectedKw;

    /**
     * 
     * @param theSubject  Subject
     * @param theQuestion Question
     * @param keywords    Keywords expected in the user's answer for the user's
     *                    answer to be deemed correct
     */
    public ShortAnswer(String theSubString, String theQuestion, String... keywords) {
        super(theSubString, theQuestion, String.join(",", keywords));
    }

    @Override
    public boolean isCorrect() {
        userAns = new HashSet<>(Stream.of(getUserAnswer().toLowerCase().split(",|:|;|.")).map(String::trim).toList());
        expectedKw = new HashSet<>(Arrays.asList(getCorrectAnswer().toLowerCase().split(",")));
        return userAns.containsAll(expectedKw);
    }

    @Override
    public String getCorrectAnswer() {
        var tmpCopy = Set.copyOf(expectedKw);
        expectedKw.removeAll(userAns);
        return "Your answer is missing the following words: " + String.join(",", tmpCopy);
    }

}
