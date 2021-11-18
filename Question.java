public abstract class Question {

	// Data Members

	private String subject;
	private String question;
	private String correctAnswer;

	// Constructor
	// Used only to initialize variables for the subclasses

	public Question(String theSubject, String theQuestion, String theCorrectAnswer) {
		subject = theSubject;
		question = theQuestion;
		correctAnswer = theCorrectAnswer.toLowerCase();
	}

	// Methods
	
	public void printOptions() {}
	
	public void setSubject(String theSubject) {
		subject = theSubject;
	}

	public String getSubject() {
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



	public boolean isCorrect() {
		return Exam.getUserAnswer().equals(correctAnswer);
	}
}
