


public abstract class Question {
	
	// Data Members
	
	private String subject;
	private String question;
	private String userAnswer;
	private String correctAnswer;
	
	// Constructor 
	// Used only to initialize variables for the subclasses

	/**
	@param	s	Subject
	@param	q	Question
	@param	ca	Correct Answer
	*/
	public Question(String s, String q, String ca) {
		subject = s;
		question = q;
		correctAnswer = ca;	
	}
	
	// Methods
	
	public abstract void printOptions();
	
	public void setSubject(String s) {
		subject = s;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setQuestion(String q) {
		question = q ;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public void setCorrectAnswer(String ca) {
		correctAnswer = ca;
	}
	
	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public String getUserAnswer() {
		return userAnswer;
	}

	public void setUserAnswer(String ui) {
		userAnswer = ui;
	}

}
