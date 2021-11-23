import java.util.Arrays;

public class FillInTheBlank extends Question {
    private final String[] correctAnswers;
    private int[] incorrectEntries;

    // Data Members

    // Constructor

    public FillInTheBlank(Exam.Subject subj, String quest, String... expectedAnsw) {
        super(subj, quest);
        int count = quest.length() - quest.replace("_", "").length();
        assert expectedAnsw.length == count;
        correctAnswers = Arrays.stream(expectedAnsw).map(String::toLowerCase).toArray(String[]::new);
        incorrectEntries = new int[correctAnswers.length];
        // -1 means the answer at is incorrect
        Arrays.fill(incorrectEntries, -1);
    }

    public void checkAnswer(String p) {
        if (p.equals("")) {
            setCorrect(false);
            return;
        }
        var phrases = Arrays.stream(p.split(","))
                .map(String::toLowerCase).map(String::trim).toArray(String[]::new);

        boolean correct = Arrays.equals(phrases, correctAnswers);
        setCorrect(correct);
        if (!correct) {
            //int mismatch = Arrays.mismatch(correctAnswers, phrases);
            for (int i = 0; i < phrases.length; i++)
                if (correctAnswers[i].equals(phrases[i]))
                    incorrectEntries[i] = i;
        }
    }

    @Override
    public String showForWrongQ() {
        StringBuilder tmp = new StringBuilder(30);
        for (int i = 0; i < correctAnswers.length; i++) {
            if (incorrectEntries[i] == -1)
                tmp.append("Entry %d should have %s,%n".formatted(i, correctAnswers[i]));
            //tmp.append("Entry ").append(incorrectAnswers.indexOf(p)).append(" should have ").append(p).append(", ");
        }
        return tmp.toString();
    }

}
