import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
 * Notes:
 * 
 * Change correct answer to the letter/number instead of the full correct String
 * 
 * Implement data recording functionality for when the user submits his answers
 * 
 * Have option where user can download a list of all the people who took it and their scores + date taken
 */

public class Exam {

	// Data Members

	private final String userName;
	private final String testDate;
	// private int userScore = 0;
	private final static int MAX_SCORE = 25;
	private final static int QUESTION_WEIGHT = 1;
	private final Question[] questionBank = new Question[25];

	public enum Subject {
		Math, Science, History, Geography, Arts
	}

	public final static EnumMap<Subject, String> subjectNames = new EnumMap<>(Map.of(Subject.Math, "Math", Subject.Arts,
			"Arts", Subject.Geography, "Geography", Subject.History, "History", Subject.Science, "Science"));

	private EnumMap<Subject, Integer> subjectScores = null;

	// Temporary to hold user's answer
	String userAnswer;
	// private final boolean[] correct = new boolean[25];

	// Maybe needed for todo below?
	// private record Pair<F,S>(F first, S second){}

	// Constructor

	public Exam(String theUser, String theTestDate) {

		// Setting exam taker's name and date test was taken
		userName = theUser;
		testDate = theTestDate;
		// TODO: maybe in the future, store Qs in a file, then randomly choose 25?

		// Creating Math Subject Question Objects
		var Math_MCQuestion1 = new MultipleChoice(Subject.Math, "What is 2 + 2?", "4", "1", "2", "3", "4");
		var Math_MCQUestion2 = new MultipleChoice(Subject.Math, "What is 10 * 10?", "100", "0", "100", "1000", "010");
		var Math_ToF = new TrueOrFalse(Subject.Math, "Math is a universal language.", "True", "True", "False");
		var Math_FiB = new FillInTheBlank(Subject.Math, "_____'s rule allows evaluating ____ of ____ forms",
				"L'Hopital", "limits", "indeterminate");
		var Math_SA = new ShortAnswer(Subject.Math,
				"What statement from Cantor states that the set of all real numbers is uncountable?", "Cantor's first");

		// Creating History Subject Question Objects
		var History_MCQuestion1 = new MultipleChoice(Subject.History, "Who said \"I have a dream.\"?",
				"Martin Luther King Jr.", "Gandhi.", "Martin Luther King Jr.", "Nelson Mandela", "Rosa Parks.");
		var History_MCQUestion2 = new MultipleChoice(Subject.History,
				"In which year did the first man walk on the moon?", "1969", "1969", "1960", "1925", "1989");
		var History_ToF = new TrueOrFalse(Subject.History, "California and Texas were once part of Mexico.", "True",
				"True", "False");
		var History_FiB = new FillInTheBlank(Subject.History,
				"In the early 1990s, the two most common families of Unix systems were ___ and ___ ", "System V UNIX",
				"BSD");
		var History_SA = new ShortAnswer(Subject.History,
				"In 1955, when the Warsaw Pact was founded, who were its founding members?", "Albania", "East Germany",
				"Czechoslovakia", "Poland", "Hungary", "Albania", "Bulgaria", "Romania", "Soviet Union");

		// Creating Science Subject Question Objects
		var Science_MCQuestion1 = new MultipleChoice(Subject.Science,
				"Approximately, how many bones does the human body have?", "206", "206", "301", "167", "269");
		var Science_MCQuestion2 = new MultipleChoice(Subject.Science,
				"AU is an abbreviation for which chemical element?", "Gold", "Silver", "Gold", "Bronze", "Mercury");
		var Science_ToF = new TrueOrFalse(Subject.Science, "The average human body has an average of 46 chromosomes.",
				"True", "True", "False");
		var Science_FiB = new FillInTheBlank(Subject.Science,
				"The first 3 elements on the periodic table are _____, ____, and ____", "hydrogen", "helium",
				"lithium");
		var Science_SA = new ShortAnswer(Subject.Science,
				"In words (no variables), describe Newton's 2nd law of motion", "net force", "mass", "acceleration");

		// Creating Arts Subject Question Objects
		var Arts_MCQuestion1 = new MultipleChoice(Subject.Arts, "How many strings does a cello have?", "4", "4", "6",
				"10", "2");
		var Arts_MCQuestion2 = new MultipleChoice(Subject.Arts, "Nike was the goddess of what?", "Victory", "Love",
				"War", "Victory", "Nature");
		var Arts_ToF = new TrueOrFalse(Subject.Arts, "The Monalisa does not have eyebrows.", "True", "True", "False");
		var Arts_FiB = new FillInTheBlank(Subject.Arts, "The three primary colors are ___, ____, and  ____", "red",
				"blue", "yellow");
		var Arts_SA = new ShortAnswer(Subject.Arts, "In what battle and war was Picasso's \"Guernica\" set in?",
				"Bombing of Guernica", "Spanish Civil War");

		// Creating Geography Subject Question Objects
		var Geography_MCQuestion1 = new MultipleChoice(Subject.Geography, "Where is the Capoeira dance from?", "Brazil",
				"Mexico", "Brazil", "South Africa", "Venezuela");
		var Geography_MCQuestion2 = new MultipleChoice(Subject.Geography, "How many continents are there?", "7", "5",
				"6", "7", "8");
		var Geography_ToF = new TrueOrFalse(Subject.Geography,
				"India is the country with the highest population in the world.", "False", "True", "False");
		var Geography_FiB = new FillInTheBlank(Subject.Geography,
				"The two most recently admitted states in the United States are _____ and _____", "Alaska", "Hawaii");
		var Geography_SA = new ShortAnswer(Subject.Geography,
				"Which countries claim to be the legitimate government of China? Write their official names.",
				"People's Republic of China", "Republic of China");

		// Filling Exam with questions from different subjects
		questionBank[0] = Math_MCQuestion1;
		questionBank[1] = Math_MCQUestion2;
		questionBank[2] = Math_ToF;
		questionBank[3] = Math_FiB;
		questionBank[4] = Math_SA;
		questionBank[5] = History_MCQuestion1;
		questionBank[6] = History_MCQUestion2;
		questionBank[7] = History_ToF;
		questionBank[8] = History_FiB;
		questionBank[9] = History_SA;
		questionBank[10] = Science_MCQuestion1;
		questionBank[11] = Science_MCQuestion2;
		questionBank[12] = Science_ToF;
		questionBank[13] = Science_FiB;
		questionBank[14] = Science_SA;
		questionBank[15] = Arts_MCQuestion1;
		questionBank[16] = Arts_MCQuestion2;
		questionBank[17] = Arts_ToF;
		questionBank[18] = Arts_FiB;
		questionBank[19] = Arts_SA;
		questionBank[20] = Geography_MCQuestion1;
		questionBank[21] = Geography_MCQuestion2;
		questionBank[22] = Geography_ToF;
		questionBank[23] = Geography_FiB;
		questionBank[24] = Geography_SA;
	}

	// Methods

	// Function to print questions and read user's input
	public void takeExam(String... ans) {
		assert ans.length == questionBank.length;
		for (int i = 0; i < questionBank.length; i++) questionBank[i].checkAnswer(ans[i]);
	}

	public boolean isValidInput(String s, MultipleChoice m) {
		try {
			int choice = Integer.parseInt(s);
			return List.of(1, 2, 3, 4).contains(choice);
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public boolean isValidInput(String s, TrueOrFalse q) {
		if (s == null)
			return false;
		return List.of("false", "true").contains(s.toLowerCase());
	}

	// Function to grade the exam
	public void gradeExam() {
		/*
		 * Keep only correctly-answered questions, count number of questions answered
		 * correctly per subject, and store in EnumMap, with keys being the subjects and
		 * values being their per subject score
		 */
		subjectScores = Arrays.stream(questionBank).filter(Question::isCorrect)
				.collect(Collectors.groupingBy(Question::getSubject, () -> new EnumMap<>(Subject.class),
						Collectors.summingInt(q -> QUESTION_WEIGHT * 1)));

		// The power of Streams...........
		// var correct =
		// List.of(questionBank).parallelStream().filter(Question::isCorrect).count();
		// userScore += QUESTION_WEIGHT * correct;
	}

	// Print user's score on the test
	public void displayResult(String... ans) {
		if (List.of(questionBank).parallelStream().anyMatch(q -> !q.isCorrect())) {
			System.out.println("Questions you answered incorrectly.");
			for (short i = 0; i < questionBank.length; i++) {
				var q = questionBank[i];
				if (!q.isCorrect()) {
					System.out.println((i + 1) + ") " + q.getQuestion());
					System.out.println("Your answer: " + ans[i]);
					System.out.println("Correct answer: " + q.showForWrongQ());
				}
			}
		}
		System.out.println();
		System.out.println(printExamResult());
	}

	public void writeOut() throws IOException {
		var p = subjectScores.values().parallelStream().map(String::valueOf).collect(Collectors.joining(","));
		try (var pw = new PrintWriter(new FileWriter("db.csv", true))) {
			pw.println("%s,%s,%s".formatted(userName, testDate, p));
		}
	}

	public String printExamResult() {
		var sb = new StringBuilder(30);
		for (var s : Subject.values())
			sb.append("%s: %d / 5%n".formatted(subjectNames.get(s), subjectScores.get(s)));
		var f = sb.toString();

		final var sum = subjectScores.values().stream().mapToLong(n -> n).sum();
		return """
				Name of test taker: %s
				Date: %s
				""".formatted(userName, testDate) + f + """
				Total: %d / %d
				""".formatted(sum, MAX_SCORE);
	}

	public String getUserName() {
		return userName;
	}

	public String getTestDate() {
		return testDate;
	}

	public static int getMAX_SCORE() {
		return MAX_SCORE;
	}

	public static int getQUESTION_WEIGHT() {
		return QUESTION_WEIGHT;
	}

}
