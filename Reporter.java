import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

/**
 * Only contains {@link #displayGraph(Exam) displayGraph}, a static method
 * used to display statistics about the user and the exam as a whole.
 */
public class Reporter {
    public static void displayGraph(Exam exam) throws IOException {
        var db = Files.readAllLines(Path.of("db.csv"));
        if (db.isEmpty())
            System.err.println("Try taking the test, buddy. Nothing to pry open with your wily eyes, eh?");
        else {
            final var avrScorePerSubj = new EnumMap<>(Map.of(Subject.Math, 0.0, Subject.Arts, 0.0,
                    Subject.Geography, 0.0, Subject.History, 0.0, Subject.Science, 0.0));
            for (var line : db) {
                // Scores in order: Math, Science, History, Geography, Arts (each out of 5)
                var tmpStream = Arrays.stream(line.split(",", 3)[2].split(","));
                var doubleStream = tmpStream.mapToDouble(Double::parseDouble);
                final var userScores = doubleStream.toArray();
                // Store running score sums per subject
                avrScorePerSubj.put(Subject.Math, avrScorePerSubj.get(Subject.Math) + userScores[0]);
                avrScorePerSubj.put(Subject.Arts, avrScorePerSubj.get(Subject.Arts) + userScores[4]);
                avrScorePerSubj.put(Subject.Geography, avrScorePerSubj.get(Subject.Geography) + userScores[3]);
                avrScorePerSubj.put(Subject.History, avrScorePerSubj.get(Subject.History) + userScores[2]);
                avrScorePerSubj.put(Subject.Science, avrScorePerSubj.get(Subject.Science) + userScores[1]);
            }
            // Calculate average score per subject
            for (final var entry : avrScorePerSubj.entrySet()) {
                double avg = entry.getValue() / db.size();
                avrScorePerSubj.put(entry.getKey(), avg);
            }

            var dataset = new DefaultCategoryDataset();
            // Add test taker's per subject score to dataset
            for (var kv : exam.getSubjectScores().entrySet())
                dataset.addValue(kv.getValue(), kv.getKey(), exam.getUserName());
            // Add test taker's total score to dataset
            dataset.addValue(Integer.valueOf(exam.getSubjectScores()
                            .values().stream().mapToInt(e -> e).sum()),
                    "Total",
                    "Total score of " + exam.getUserName());
            // Add average per subject score from all takers to dataset
            for (var kv : avrScorePerSubj.entrySet())
                dataset.addValue(kv.getValue(), kv.getKey(), "Averages of all test takers");
            // Add average total exam score to dataset
            dataset.addValue(avrScorePerSubj.values().stream().mapToDouble(e -> e).sum(),
                    "Total",
                    "Average score of this test");

            var chart = ChartFactory.createStackedBarChart(
                    "Stats", "Score", "", dataset);
            chart.getCategoryPlot().getRangeAxis().setRange(0.0, 25.0);
            chart.getCategoryPlot().getRangeAxis().setStandardTickUnits(NumberAxis.createIntegerTickUnits());
            var frame = new ChartFrame("Past Test Statistics", chart);
            frame.setSize(500, 400);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }
}
