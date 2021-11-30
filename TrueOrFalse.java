public class TrueOrFalse extends Question {

    // Data Members

    private boolean trueRight;

    /**
     *
     * @param theSubject Subject
     * @param theQuestion Question
     * @param trueIsCorrect Whether the true option is the correct answer
     */
    public TrueOrFalse(Subject theSubject, String theQuestion, boolean trueIsCorrect) {

        super(theSubject, theQuestion);
        trueRight = trueIsCorrect;
    }

    @Override
    public void checkAnswer(String userAns) {
        setCorrect(Boolean.parseBoolean(userAns) == trueRight);
    }

    @Override
    public String showForWrongQ() {
        return Boolean.toString(trueRight);
    }

    @Override
    public String toString() {
        return String.join(" ", trueRight + super.toString());
    }
}