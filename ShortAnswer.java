import java.util.Arrays;
import java.util.HashSet;

public class ShortAnswer extends Question {

    public ShortAnswer(String s, String q, String... keywords) {
        super(s, q, String.join(",", keywords));
    }

    public boolean isCorrect() {
        var userAns = new HashSet<>(Arrays.asList(getUserAnswer().toLowerCase().split(" |,|:|;|.|\\?|!")));
        var expectedKw = new HashSet<>(Arrays.asList(getCorrectAnswer().toLowerCase().split(",")));
        return userAns.containsAll(expectedKw);
    }

    @Override
    public void printOptions() {
        // Thanks

    }

}
