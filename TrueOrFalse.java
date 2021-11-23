public class TrueOrFalse extends Question {

    // Data Members

    private static final String TRUEOPTION = "True";
    private static final String FALSEOPTION = "False";
    private String correctAnswer;

    // Constructor
    public TrueOrFalse(Exam.Subject theSubject, String theQuestion, String theCorrectAnswer) {

        // Calling super-class' constructor
        super(theSubject, theQuestion);
        correctAnswer = theCorrectAnswer.toLowerCase();
    }

    @Override
    public void printOptions() {

        // For example purposes I'll be using System.out.println()
        System.out.println("A. " + TRUEOPTION);
        System.out.println("B. " + FALSEOPTION);
    }

    public void checkAnswer(String userAns) {
        setCorrect(userAns.toLowerCase().equals(correctAnswer));
    }

    @Override
    public String showForWrongQ() {
        return correctAnswer;
    }
}

/*
 * Notes:
 *
 * Maybe add another variable that shows the correct answer to when the answer
 * is false
 *
 * No need to pass in to and fo in the constructor, we can just initialize it in
 * declaration (since they will never change).
 */