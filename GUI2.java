import java.awt.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.swing.*;

public class GUI2 {
    final JTabbedPane tabbedPane = new JTabbedPane();
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
        startCard.add(label, BorderLayout.SOUTH);
        startCard.add(nameBox, BorderLayout.SOUTH);
        startCard.add(startButton, BorderLayout.SOUTH);

        tabbedPane.addTab("Start", startCard);

        pane.add(tabbedPane, BorderLayout.CENTER);

        startButton.addActionListener(event -> {
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
        for (int i = 1; i < tabbedPane.getTabCount() - 1; i++) {
            // Sanity checks
            if (!Objects.equals(tabbedPane.getTitleAt(i), Integer.toString(i))) throw new AssertionError();

            Question question = e.getQuestionBank()[i - 1];
            System.out.println(i + " " + question.getQuestion());

            // Hope assumption is true...
            var panel = (JPanel) tabbedPane.getComponentAt(i);
            System.out.print("Answer: ");
            for (var component : panel.getComponents()) {
                // Skip question text
                if (component instanceof JLabel) continue;
                if (component instanceof JCheckBox c) {
                    var isTrue = c.isSelected();
                    System.out.println(isTrue);
                    question.checkAnswer(String.valueOf(isTrue));
                    System.out.println("checkbox");
                } else if (component instanceof JComboBox cb) {
                    /* All because a stupid decision for MultipleChoice's checkAnswer's parameter
                     * to be a string of the index of the choice + 1.
                     * If anyone happens to find this codebase in the future...
                     * well get ready to feel metaphorical rectal discomfort.*/
                    var entryIdx = Integer.toString(
                            Arrays.asList(((MultipleChoice) question)
                                            .getResponseOptions())
                                    .indexOf(Objects.requireNonNull(cb.getSelectedItem()).toString()) + 1);
                    System.out.println(entryIdx);
                    question.checkAnswer(entryIdx);
                    System.out.println("combobox");
                } else if (component instanceof JTextArea ta) {
                    var entry = ta.getText();
                    System.out.println(entry);
                    question.checkAnswer(entry);
                    System.out.println("textarea");
                }
                // JPanel containing JTextEntries
                else {
                    // Ugly casts necessary
                    JPanel panelInTab = (JPanel) component;
                    var boxes = panelInTab.getComponents();
                    var delineatedString = Arrays.stream(boxes)
                            .filter(c -> c instanceof JTextField)
                            .map(c -> ((JTextField) c).getText())
                            .collect(Collectors.joining(","));
                    System.out.println(delineatedString);
                    question.checkAnswer(delineatedString);
                    System.out.println("panel with text entries");
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
            var question = p[i - 1];
            tmpPanel.add(new JLabel(question.getQuestion()));
            //JComponent answerComp;
            if (question instanceof MultipleChoice q)
                tmpPanel.add(new JComboBox<>(q.getResponseOptions()));
            else if (question instanceof TrueOrFalse)
                tmpPanel.add(new JCheckBox("True?"));
            else if (question instanceof FillInTheBlank q) {
                var groupOfEntries = new JPanel(new GridBagLayout());
                var size = q.numberOfEntries;
                for (int j = 0; j < size; j++)
                    groupOfEntries.add(new JTextField(10));
                tmpPanel.add(groupOfEntries, new GridBagConstraints());
            } else if (question instanceof ShortAnswer) tmpPanel.add(new JTextArea(2, 10));
            tabbedPane.addTab(Integer.toString(i), tmpPanel);
        }

        var submissionCard = new JPanel();
        submissionCard.add(new JLabel("Are you sure you want to submit?"));
        JButton submit = new JButton("Submit");
        submit.addActionListener(event -> processAnswers());
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
        frame.setSize(550, 200);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUI2::createAndShowGUI);
    }
}