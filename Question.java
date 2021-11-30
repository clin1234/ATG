public abstract class Question {

    // Data Members

    private Subject subject;
    private String question;
    private boolean correct;

    // Constructor
    // Used only to initialize variables for the subclasses

    Question(Subject theSubject, String theQuestion) {
        subject = theSubject;
        question = theQuestion;
    }

    // Methods

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

    @Override
    public String toString() {
        return String.join(" ", subject.toString(), question);
    }
}
