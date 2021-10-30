import java.util.Scanner;

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

	private String userName;
	private String testDateAndTime;
	private int userScore = 0;
	private final int MAX_SCORE = 25;
	private final static int QUESTION_WEIGHT = 1;
	private final Question[] questionBank = new Question[25];

	// Constructor

	public Exam(String theUser, String theDate) {

		// Setting user's name and date test was taken to the Exam
		userName = theUser;
		testDateAndTime = theDate;

		// Creating Math Subject Question Objects
		MultipleChoice Math_MCQuestion1 = new MultipleChoice("Math", "What is 2 + 2?", "4", "1", "2", "3", "4");
		MultipleChoice Math_MCQUestion2 = new MultipleChoice("Math", "What is 10 * 10?", "100", "0", "100", "1000",
				"010");
		TrueOrFalse Math_ToF = new TrueOrFalse("Math", "Math is a universal language.", "True", "True", "False");
		FillInTheBlank Math_FiB = new FillInTheBlank("Math", "18 + 3 = ", "21");
		var Math_SA = new ShortAnswer("Math",
				"What statement from Cantor states that the set of all real numbers is uncountable?", "Cantor's",
				"first");

		// Creating History Subject Question Objects
		MultipleChoice History_MCQuestion1 = new MultipleChoice("History", "Who said \"I have a dream.\"?",
				"Martin Luther King Jr.", "Ghandi.", "Martin Luther King Jr.", "Nelson Mandela", "Rosa Parks.");
		MultipleChoice History_MCQUestion2 = new MultipleChoice("History",
				"In which year did the first man walk on the moon?", "1969", "1969", "1960", "1925", "1989");
		TrueOrFalse History_ToF = new TrueOrFalse("History", "California and Texas were once part of Mexico.", "True",
				"True", "False");
		FillInTheBlank History_FiB = new FillInTheBlank("History", "When looking at ancient history, BC stands for ",
				"Before Christ");
		var History_SA = new ShortAnswer("History",
				"In 1955, when the Warsaw Pact was founded, who were its founding members?", "Albania", "East Germany",
				"Czechoslovakia", "Poland", "Hungary", "Albania", "Bulgaria", "Romania", "Soviet Union");

		// Creating Science Subject Question Objects
		MultipleChoice Science_MCQuestion1 = new MultipleChoice("Science",
				"Approximately, how many bones does the human body have?", "206", "206", "301", "167", "269");
		MultipleChoice Science_MCQuestion2 = new MultipleChoice("Science",
				"AU is an abbreviation for which chemical element?", "Gold", "Silver", "Gold", "Bronze", "Mercury");
		TrueOrFalse Science_ToF = new TrueOrFalse("Science", "The average human body has an average of 46 chromosomes.",
				"True", "True", "False");
		FillInTheBlank Science_FiB = new FillInTheBlank("Science",
				"The smallest particle of an element is also known as a/an _____", "atom");
		var Science_SA = new ShortAnswer("Science", "In words (no variables), describe Newton's 2nd law of motion",
				"net force", "mass", "acceleration");

		// Creating Arts Subject Question Objects
		MultipleChoice Arts_MCQuestion1 = new MultipleChoice("Arts", "How many strings does a cello have?", "4", "4",
				"6", "10", "2");
		MultipleChoice Arts_MCQuestion2 = new MultipleChoice("Arts", "Nike was the goddess of what?", "Victory", "Love",
				"War", "Victory", "Nature");
		TrueOrFalse Arts_ToF = new TrueOrFalse("Arts", "The Monalisa does not have eyebrows.", "True", "True", "False");
		FillInTheBlank Arts_FiB = new FillInTheBlank("Arts",
				"____ is the color we get when mixing red and blue together.", "Violet");
		var Arts_SA = new ShortAnswer("Arts", "In what battle and war was Picasso's \"Guernica\" set in?",
				"Bombing of Guernica", "Spanish Civil War");

		// Creating Geography Subject Question Objects
		MultipleChoice Geography_MCQuestion1 = new MultipleChoice("Geography", "Where is the Capoeira dance from?",
				"Brazil", "Mexico", "Brazil", "South Africa", "Venezuela");
		MultipleChoice Geography_MCQuestion2 = new MultipleChoice("Geography", "How many continents are there?", "7",
				"5", "6", "7", "8");
		TrueOrFalse Geography_ToF = new TrueOrFalse("Geography",
				"India is the country with the highest population in the world.", "False", "True", "False");
		FillInTheBlank Geography_FiB = new FillInTheBlank("Geography",
				"The biggest state in the United States is _____", "Alaska");
		var Geography_SA = new ShortAnswer("Geography",
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
	public void takeExam() {

		// Creating Scanner object to read user's input
		try (var scanner = new Scanner(System.in)) {
			for (int i = 0; i < questionBank.length; i++) {

				// Printing the question
				System.out.println((i + 1) + ") " + questionBank[i].getQuestion());

				// Print response options based on the object's type
				questionBank[i].printOptions();

				// Waiting for user's input
				System.out.print("Your Answer: ");
				String userInput = scanner.nextLine();

				// Recording user's answer
				questionBank[i].setUserAnswer(userInput);

				System.out.println();
			}
		}
	}

	// Function to grade the exam
	public void gradeExam() {

		for (var question : questionBank) {
			if (question instanceof ShortAnswer) {
				if (((ShortAnswer) question).isCorrect())
					userScore += QUESTION_WEIGHT;
			}
			// If user's recorded answer is the same as correct one, then...
			else if (question.getUserAnswer().equals(question.getCorrectAnswer())) {
				userScore += QUESTION_WEIGHT;
			}
		}
	}

	// Print user's score on the test
	public void displayResult() {
		// For example purposes I'll be using System.out.println()
		System.out.println("Name: " + userName);
		System.out.println("Date: " + testDateAndTime);
		System.out.println("Score: " + userScore + "/" + MAX_SCORE);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String theUser) {
		userName = theUser;
	}
}
