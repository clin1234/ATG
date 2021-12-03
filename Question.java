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

    public abstract void checkAnswer(String userAns);

    public abstract String showForWrongQ();

    public final boolean isCorrect() {
        return correct;
    }

    public final void setCorrect(boolean cor) {
        correct = cor;
    }

    public final void setSubject(Subject sub) {
        subject = sub;
    }

    public final void setQuestion(String q) {
        question = q;
    }

    final Subject getSubject() {
        return subject;
    }

    final String getQuestion() {
        return question;
    }

    // Used for unit tests
    public final boolean getCorrect() {
    	return correct;
    }
}
