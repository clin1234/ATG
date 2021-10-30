


import java.util.Arrays;
import java.util.HashSet;

public class ShortAnswer extends Question {
    /**
     * 
     * @param theSubject Subject
     * @param theQuestion Question
     * @param keywords Keywords expected in the user's answer for the user's answer
     * to be deemed correct
     */
    public ShortAnswer(String theSubString, String theQuestion, String... keywords) {
        super(theSubString, theQuestion, String.join(",", keywords));
    }

    public boolean isCorrect() {
        var userAns = new HashSet<>(Arrays.asList(getUserAnswer().toLowerCase().split(" |,|:|;|.|?|!")));
        var expectedKw = new HashSet<>(Arrays.asList(super.getCorrectAnswer().toLowerCase().split(",")));
        return userAns.containsAll(expectedKw);
    }

}
