public abstract class Question {

	// Data Members

	private Exam.Subject subject;
	private String question;
	private String correctAnswer;
	private boolean correct;

	// Constructor
	// Used only to initialize variables for the subclasses

	public Question(Exam.Subject theSubject, String theQuestion) {
		subject = theSubject;
		question = theQuestion;
		//correctAnswer = theCorrectAnswer.toLowerCase();
	}

	// Methods

	public void printOptions() {
	}

	public void setSubject(Exam.Subject theSubject) {
		subject = theSubject;
	}

	public Exam.Subject getSubject() {
		return subject;
	}

	public void setQuestion(String theQuestion) {
		question = theQuestion;
	}

	public String getQuestion() {
		return question;
	}

	public void setCorrectAnswer(String theCorrectAnswer) {
		correctAnswer = theCorrectAnswer;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void checkAnswer(String userAns) {
		setCorrect(userAns.equals(correctAnswer));
	}

	public abstract String showForWrongQ();

	public void setCorrect(boolean b) {
		correct = b;
	}

	public boolean isCorrect() {
		return correct;
	}
}
