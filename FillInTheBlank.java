import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class FillInTheBlank extends Question {
	private String[] correctAnswers;
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

		incorrectAnswers = new ArrayList<String>(correctAnswers.length);
		for (int i = 0; i < correctAnswers.length; i++)
			if (!correctAnswers[i].equals(phrases[i]))
				incorrectAnswers.add(i, phrases[i]);
	}

	@Override
	public String showForWrongQ() {
		String tmp = "";
		for (var p : incorrectAnswers.parallelStream().filter(Objects::nonNull).toArray(String[]::new)) {
			tmp += "Entry " + incorrectAnswers.indexOf(p) + " should have " + p + ", ";
		}
		return tmp;
	}

}
