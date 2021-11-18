public abstract class Question {

	// Data Members

	private Exam.Subject subject;
	private String question;
	private boolean correct;

	// Constructor
	// Used only to initialize variables for the subclasses

	public Question(Exam.Subject theSubject, String theQuestion) {
		subject = theSubject;
		question = theQuestion;
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

	public abstract void checkAnswer(String userAns);

	public abstract String showForWrongQ();

	public void setCorrect(boolean b) {
		correct = b;
	}

	public boolean isCorrect() {
		return correct;
	}
}
