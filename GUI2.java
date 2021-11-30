import java.awt.*;
import java.time.LocalDate;
import javax.swing.*;

public class GUI2 {
    JTabbedPane tabbedPane = new JTabbedPane();
    Exam e;

    public void addComponentToPane(Container pane) {

        var startCard = new JPanel();
        var instruction = new JLabel("""
                <html>
                AEG for short, the Automated Exam Generator simulates a real online exam of 25 questions.<br>
                You will be presented with questions from different subjects<br>
                (History, Math, Art, Science, Geography), and in different types.<br>
                Select the best suitable answer for each question and proceed to the next question.<br>
                Your results will be saved and displayed at the end. Good luck!<br>
                </html>
                """);
        var startButton = new JButton("Start");
        var nameBox = new JTextField(12);
        var label = new JLabel("Name:");

        startCard.add(instruction);
        startCard.add(label);
        startCard.add(nameBox, BorderLayout.PAGE_END);
        startCard.add(startButton, BorderLayout.PAGE_END);

        tabbedPane.addTab("Start", startCard);

        pane.add(tabbedPane, BorderLayout.CENTER);

        startButton.addActionListener(e -> {
            if (nameBox.getText().isEmpty())
                JOptionPane.showMessageDialog(startCard, "Enter a name");
            else addQs(nameBox.getText());
        });
    }

    /**
     * Process answers embedded within the JPanel attached to all tabs except
     * the first and last ones.
     */
    void processAnswers() {
        // Skip tabs containing instructional and submission messages
        for (int i = 1; i < tabbedPane.getTabCount()-1; i++) {
            // Hope assumption is true...
            var panel = (JPanel) tabbedPane.getComponentAt(i);
            for (var component : panel.getComponents()) {
                // Skip question text
                if (component instanceof JLabel) continue;
                if (component instanceof JCheckBox c) {
                    var isTrue = c.isSelected();

                }
                else if (component instanceof JComboBox cb) {
                    var entry = cb.getSelectedItem().toString();
                }
                else if (component instanceof JTextField tf) {
                    var entry = tf.getText();

                }
            }
        }
        e.gradeExam();
    }

    void addQs(String name) {
        e = new Exam(name, LocalDate.now().toString());
        var p = e.getQuestionBank();
        for (int i = 1; i <= p.length; i++) {
            JPanel tmpPanel = new JPanel();
            var question = p[i-1];
            tmpPanel.add(new JLabel(question.getQuestion()));
            //JComponent answerComp;
            if (question instanceof MultipleChoice q)
                tmpPanel.add(new JComboBox<>(q.getResponseOptions()));
            else if (question instanceof  TrueOrFalse)
                tmpPanel.add(new JCheckBox("True?"));
            else if (question instanceof  FillInTheBlank q) {
                var size = q.numberOfEntries;
                for (int j = 0 ; j < size; j++)
                    tmpPanel.add(new JTextField(10));
            }
            else tmpPanel.add(new JTextArea());
            tabbedPane.addTab(Integer.toString(i), tmpPanel);
        }

        var submissionCard = new JPanel();
        submissionCard.add(new JLabel("Are you sure you want to submit?"));
        JButton submit = new JButton("Submit");
        submit.addActionListener(e -> processAnswers());
        submissionCard.add(submit);
        tabbedPane.addTab("Submission", submissionCard);
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("AEG");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        var demo = new GUI2();
        demo.addComponentToPane(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUI2::createAndShowGUI);
    }
}