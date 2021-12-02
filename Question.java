public abstract class Question {

    // DATA MEMBERS

    public void setSubject(Subject sub) {
        subject = sub;
    }

    private Subject subject;

    public void setQuestion(String question) {
        this.question = question;
    }

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

    final String getQuestion() {
        return question;
    }

    public abstract void checkAnswer(String userAns);

    public abstract String showForWrongQ();

    public final boolean isCorrect() {
        return correct;
    }

    public final void setCorrect(boolean cor) {
        correct = cor;
    }

    // Used for unit tests
    public final boolean getCorrect() {
    	return correct;
    }
}
