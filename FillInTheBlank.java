import java.util.Arrays;

public class FillInTheBlank extends Question {
    private final String[] correctAnswers;
    private final int[] incorrectEntries;
    final int numberOfEntries;

    // Data Members

    // Constructor

    public FillInTheBlank(Subject subj, String quest, String... expectedAnsw) {
        super(subj, quest);
        int count = quest.length() - quest.replace("_", "").length();
        assert expectedAnsw.length == count;
        numberOfEntries = expectedAnsw.length;
        correctAnswers = Arrays.stream(expectedAnsw).map(String::toLowerCase).toArray(String[]::new);
        incorrectEntries = new int[correctAnswers.length];
        // -1 means the answer at is incorrect
        Arrays.fill(incorrectEntries, -1);
    }

    @Override
    public void checkAnswer(String p) {
        if (p.isEmpty()) {
            setCorrect(false);
            return;
        }
        var phrases = Arrays.stream(p.split(","))
                .map(String::toLowerCase).map(String::trim).toArray(String[]::new);

        final boolean correct = Arrays.equals(phrases, correctAnswers);
        setCorrect(correct);
        //int mismatch = Arrays.mismatch(correctAnswers, phrases);
        if (!correct) for (int i = 0; i < phrases.length; i++)
            if (correctAnswers[i].equals(phrases[i]))
                incorrectEntries[i] = i;
    }

    @Override
    public final String showForWrongQ() {
        StringBuilder tmp = new StringBuilder(30);
        //tmp.append("Entry ").append(incorrectAnswers.indexOf(p)).append(" should have ").append(p).append(", ");
        for (int i = 0; i < correctAnswers.length; i++)
            if (-1 == incorrectEntries[i])
                tmp.append("Entry %d should have %s,%n".formatted(i, correctAnswers[i]));
        return tmp.toString();
    }

    @Override
    public String toString() {
        return String.join(" ",
                Arrays.toString(correctAnswers), Arrays.toString(incorrectEntries), super.toString());
    }
}
