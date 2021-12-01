import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.joining;

public class GUI2 {
    private final JTabbedPane tabbedPane = new JTabbedPane();
    private static Exam e;

    private final void addComponentToPane(Container pane) {

        var startCard = new JPanel();
        var instruction = new JLabel("""
                <html>
                AEG for short, the <b>Automated Exam Generator</b> simulates a real online exam of <b>25 questions</b>.<br>
                You will be presented with questions from different subjects (<b>History, Math, Art, Science,<br>
                Geography</b>), and in different types. Select the best suitable answer for each question and<br>
                proceed to the next question. Your results will be saved and displayed at the end. <b>Good luck!</b>
                </html>
                """);

        var startButton = new JButton("Begin Exam");
        var nameBox = new JTextField(12);
        var label = new JLabel("Name:");

        startCard.add(Box.createVerticalStrut(100));
        startCard.add(instruction);
        startCard.add(Box.createHorizontalStrut(1800));
        startCard.add(label, BorderLayout.PAGE_END);
        startCard.add(nameBox, BorderLayout.PAGE_END);
        startCard.add(startButton, BorderLayout.PAGE_END);

        tabbedPane.addTab("Start", startCard);

        pane.add(tabbedPane, BorderLayout.CENTER);

        startButton.addActionListener(event -> {
            if (nameBox.getText().isEmpty())
                JOptionPane.showMessageDialog(startCard, "Please enter a name to proceed with test.");
            else addQs(nameBox.getText());
        });
    }

    /**
     * Process answers embedded within the JPanel attached to all tabs except
     * the first and last ones.
     */
    private void processAnswers() {
        // Skip tabs containing instructional and submission messages
        for (int i = 1; i < (tabbedPane.getTabCount() - 1); i++) {
            // Sanity checks
            if (!Objects.equals(tabbedPane.getTitleAt(i), Integer.toString(i))) throw new AssertionError();

            Question question = e.getQuestionBank()[i - 1];
            //System.out.println(i + " " + question.getQuestion());

            // Hope assumption is true...
            var panel = (JPanel) tabbedPane.getComponentAt(i);
            //System.out.print("Answer: ");
            for (var component : panel.getComponents()) {
                // Skip question text and next and previous buttons
                if ((component instanceof JLabel) || (component instanceof JButton)) continue;
                if (component instanceof JCheckBox c) {
                    var isTrue = c.isSelected();
                    //System.out.println(isTrue);
                    question.checkAnswer(String.valueOf(isTrue));
                    //System.out.println("checkbox");
                } else if (component instanceof JComboBox cb) {
                    /* All because a stupid decision for MultipleChoice's checkAnswer's parameter
                     * to be a string of the index of the choice + 1.
                     * If anyone happens to find this codebase in the future...
                     * well get ready to feel metaphorical rectal discomfort.*/
                    var entryIdx = Integer.toString(
                            Arrays.asList(((MultipleChoice) question)
                                            .getResponseOptions())
                                    .indexOf(Objects.requireNonNull(cb.getSelectedItem()).toString()) + 1);
                    //System.out.println(entryIdx);
                    question.checkAnswer(entryIdx);
                    //System.out.println("combobox");
                } else if (component instanceof JTextArea ta) {
                    var entry = ta.getText();
                    //System.out.println(entry);
                    question.checkAnswer(entry);
                    //System.out.println("textarea");
                }
                // JPanel containing JTextEntries
                else {
                    // Ugly casts necessary
                    JPanel panelInTab = (JPanel) component;
                    var boxes = panelInTab.getComponents();
                    var delineatedString = Arrays.stream(boxes)
                            .filter(c -> c instanceof JTextField)
                            .map(c -> ((JTextComponent) c).getText())
                            .collect(joining(","));
                    //System.out.println(delineatedString);
                    question.checkAnswer(delineatedString);
                    //System.out.println("panel with text entries");
                }
            }
        }
        e.gradeExam();
        //tabbedPane.getTabComponentAt(tabbedPane.getTabCount()-1).setVisible(false);
    }

    private void addQs(String name) {
        e = new Exam(name, LocalDate.now().toString());
        var p = e.getQuestionBank();
        for (int i = 1; i <= p.length; i++) {
            JPanel tmpPanel = new JPanel();
            JButton nextQuestion = new JButton("Next");
            JButton previousQuestion = new JButton("Previous");

            var question = p[i - 1];
            tmpPanel.add(new JLabel("Q: " + question.getQuestion()));

            if (question instanceof MultipleChoice q)
                tmpPanel.add(new JComboBox<>(q.getResponseOptions()));
            else if (question instanceof TrueOrFalse) {
                tmpPanel.add(new JCheckBox("Is the statement true?"));
                /*
                var trueOption = new JRadioButton("True");
                var falseOption = new JRadioButton("False");
                var group = new ButtonGroup();
                group.add(trueOption);
                group.add(falseOption);
                tmpPanel.add(trueOption);
                tmpPanel.add(falseOption);
                */
            } else if (question instanceof FillInTheBlank q) {
                var groupOfEntries = new JPanel(new GridBagLayout());
                var size = q.numberOfEntries;
                for (int j = 0; j < size; j++)
                    groupOfEntries.add(new JTextField(10));
                tmpPanel.add(groupOfEntries, new GridBagConstraints());
            } else if (question instanceof ShortAnswer) tmpPanel.add(new JTextArea(2, 10));

            tabbedPane.addTab(Integer.toString(i), tmpPanel);
            tabbedPane.setSelectedIndex(1);
            tabbedPane.setEnabledAt(0, false);

            previousQuestion.addActionListener(event -> {
                if (tabbedPane.getSelectedIndex() > 1)
                    tabbedPane.setSelectedIndex(tabbedPane.getSelectedIndex() - 1);
            });

            nextQuestion.addActionListener(event -> {
                tabbedPane.setSelectedIndex(tabbedPane.getSelectedIndex() + 1);
            });

            //tmpPanel.add(Box.createHorizontalStrut(800));
            tmpPanel.add(previousQuestion);
            tmpPanel.add(nextQuestion);

        }

        var submissionCard = new JPanel();
        submissionCard.add(new JLabel("Are you sure you want to submit?"));
        JButton submit = new JButton("Submit");
        submit.addActionListener(event -> {
            processAnswers();
            try {
                e.writeOut();
                showForWrongQs();
                displayGraph();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        submissionCard.add(submit);
        tabbedPane.addTab("Submission", submissionCard);
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Automated Exam Generator");
        frame.setMinimumSize(new Dimension(700, 400));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        var demo = new GUI2();
        demo.addComponentToPane(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private final void showForWrongQs() {
        for (int i = 1; i < (tabbedPane.getTabCount() - 1); i++) {
            // Sanity checks
            if (!Objects.equals(tabbedPane.getTitleAt(i), Integer.toString(i))) throw new AssertionError();

            Question question = e.getQuestionBank()[i - 1];
            //System.out.println(i + " " + question.getQuestion());

            // Hope assumption is true...
            var panel = (JPanel) tabbedPane.getComponentAt(i);
            //System.out.print("Answer: ");
            for (var component : panel.getComponents()) {
                // Skip question text
                if (component instanceof JLabel) continue;
                boolean isSet = false;
                if ((component instanceof JCheckBox c) && (question instanceof TrueOrFalse t) && !t.isCorrect()) {
                    c.setForeground(Color.RED);
                    //System.out.println("checkbox");
                } else if ((component instanceof JComboBox cb) && (question instanceof MultipleChoice m)
                        && !m.isCorrect()) {
                    var p = new JLabel(m.getCorrectAnswer() + ", not "
                            + cb.getSelectedItem().toString());
                    panel.add(p);
                } else if ((component instanceof JTextArea ta) &&
                        (question instanceof ShortAnswer sa) &&
                        !sa.isCorrect()) {
                    var missing = sa.getMissingPhrases();
                    ta.setText("Your response was missing: " + missing.toString());
                }
                // JPanel containing JTextEntries
                else if ((question instanceof FillInTheBlank f) && !f.isCorrect()) {
                    // Ugly casts necessary
                    if (component instanceof JPanel panelInTab) {
                        var boxes = Arrays.stream(panelInTab.getComponents())
                                .filter(element -> element instanceof JTextField)
                                .toArray(JTextField[]::new);
                        for (int j = 0; j < boxes.length; j++)
                            if (!boxes[j].getText().equals(f.getCorrectAnswers()[j])) {
                                var entry = (JTextField) panelInTab.getComponent(j);
                                entry.setForeground(Color.RED);
                                entry.setText(f.getCorrectAnswers()[j]);
                            }
                        //System.out.println("panel with text entries");
                    }
                }
            }
        }
    }

    private static void displayGraph() throws IOException {
        var db = Files.readAllLines(Path.of("db.csv"));
        if (db.isEmpty())
            System.err.println("Try taking the test, buddy. Nothing to pry open with your wily eyes, eh?");
        else {
            final var avrgScorPerSubj = new EnumMap<>(Map.of(Subject.Math, 0.0, Subject.Arts, 0.0,
                    Subject.Geography, 0.0, Subject.History, 0.0, Subject.Science, 0.0));
            for (var line : db) {
                // Scores in order: Math, Science, History, Geography, Arts (each out of 5)
                var tmpStream = Arrays.stream(line.split(",", 3)[2].split(","));
                var doubleStream = tmpStream.mapToDouble(Double::parseDouble);
                final var userScores = doubleStream.toArray();
                avrgScorPerSubj.put(Subject.Math, avrgScorPerSubj.get(Subject.Math) + userScores[0]);
                avrgScorPerSubj.put(Subject.Arts, avrgScorPerSubj.get(Subject.Arts) + userScores[4]);
                avrgScorPerSubj.put(Subject.Geography, avrgScorPerSubj.get(Subject.Geography) + userScores[3]);
                avrgScorPerSubj.put(Subject.History, avrgScorPerSubj.get(Subject.History) + userScores[2]);
                avrgScorPerSubj.put(Subject.Science, avrgScorPerSubj.get(Subject.Science) + userScores[1]);
            }
            for (final var entry : avrgScorPerSubj.entrySet()) {
                double avg = entry.getValue() / db.size();
                avrgScorPerSubj.put(entry.getKey(), avg);
            }

            var ds = new DefaultCategoryDataset();
            for (var kv : e.getSubjectScores().entrySet())
                ds.addValue(kv.getValue(), kv.getKey(), e.getUserName());
            ds.addValue(e.getSubjectScores().values().stream().mapToInt(e -> e).sum(),
                    "Total",
                    "Total score of " + e.getUserName());
            for (var kv : avrgScorPerSubj.entrySet())
                ds.addValue(kv.getValue(), kv.getKey(), "Averages of all test takers");
            ds.addValue(avrgScorPerSubj.values().stream().mapToDouble(e -> e).sum(),
                    "Total",
                    "Average score of this test");

            var c = ChartFactory.createStackedBarChart("Stats", "Score", "", ds);
            c.getCategoryPlot().getRangeAxis().setRange(0, 25);
            c.getCategoryPlot().getRangeAxis().setStandardTickUnits(NumberAxis.createIntegerTickUnits());
            var frame = new ChartFrame("Past Test Statistics", c);
            frame.setSize(500, 400);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUI2::createAndShowGUI);
    }
}