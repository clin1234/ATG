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
		correctAnswers = expectedAnsw;
	}

	public void checkAnswer(String... phrases) {
		assert phrases.length == correctAnswers.length;
		setCorrect(Arrays.equals(phrases, correctAnswers));

		incorrectAnswers = new ArrayList<>(correctAnswers.length);
		for (int i = 0; i < correctAnswers.length; i++)
			if (!correctAnswers[i].equals(phrases[i]))
				incorrectAnswers.add(i, phrases[i]);
	}

	@Override
	public String showForWrongQ() {
		StringBuilder tmp = new StringBuilder();
		for (var p : incorrectAnswers.parallelStream().filter(Objects::nonNull).toArray(String[]::new)) {
			tmp.append("Entry ").append(incorrectAnswers.indexOf(p)).append(" should have ").append(p).append(", ");
		}
		return tmp.toString();
	}

}
