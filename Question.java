public abstract class Question {

    // DATA MEMBERS

    private Subject subject;
    private String question;
    private boolean correct;

    // CONSTRUCTOR
    // Used only to initialize variables for the subclasses

    Question(Subject theSubject, String theQuestion) {
        subject = theSubject;
        question = theQuestion;
    }

    // METHODS

    final Subject getSubject() {
        return subject;
    }

    String getQuestion() {
        return question;
    }

    public abstract void checkAnswer(String userAns);

    public abstract String showForWrongQ();

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean b) {
        correct = b;
    }

    // Used for unit tests
    public boolean getCorrect() {
    	return correct;
    }
  
    @Override
    public String toString() {
        return String.join(" ", subject.toString(), question);
    }
}
