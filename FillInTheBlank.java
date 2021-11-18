import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class FillInTheBlank extends Question {
    private final String[] correctAnswers;
    private ArrayList<String> incorrectAnswers;

    // Data Members

    // Constructor

    public FillInTheBlank(Exam.Subject subj, String quest, String... expectedAnsw) {
        super(subj, quest);
        int count = quest.length() - quest.replace("_", "").length();
        assert expectedAnsw.length == count;
        correctAnswers = Arrays.stream(expectedAnsw).map(String::toLowerCase).toArray(String[]::new);
        //incorrectAnswers = new ArrayList<>();
    }

    public void checkAnswer(String p) {
        var phrases = Arrays.stream(p.split(",")).map(String::toLowerCase).map(String::trim).toArray(String[]::new);
        assert phrases.length == correctAnswers.length;

        boolean correct = Arrays.equals(phrases, correctAnswers);
        setCorrect(correct);
        if (!correct) {
            incorrectAnswers = new ArrayList<>(correctAnswers.length);
            for (int i = 0; i < correctAnswers.length; i++)
                if (!correctAnswers[i].equals(phrases[i]))
                    incorrectAnswers.add(i, phrases[i]);
        }
    }

    @Override
    public String showForWrongQ() {
        StringBuilder tmp = new StringBuilder(30);
        for (var p : incorrectAnswers.parallelStream().filter(Objects::nonNull).toList())
            tmp.append("Entry ").append(incorrectAnswers.indexOf(p)).append(" should have ").append(p).append(", ");
        return tmp.toString();
    }

}
