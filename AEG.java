import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.regex.Pattern;

public class AEG {
    private static final Pattern COMPILE = Pattern.compile("\\R");

    public static void main(String[] args) throws IOException {
        // Insert name of file containing user answers here.
        var inputFile = Path.of("somewhat_right.txt");
        if (!Files.exists(inputFile)) throw new NoSuchFileException(inputFile + " does not exist");

        var contents = Files.readString(inputFile);
        String[] splitter = COMPILE.split(contents, 2);
        String name = splitter[0];
        splitter[1] = splitter[1].replace(System.lineSeparator(), "");
        if (splitter[1].chars().filter(c -> c == ';').count() != 24L) {
            System.err.println(inputFile + " must have exactly 24 ';' separating answers");
        } else {
            Exam e = new Exam(name, LocalDate.now().toString());
            e.takeExam(splitter[1].split(";"));
            e.gradeExam();
            e.writeOut();
            e.displayResult(splitter[1].split(";"));
            report();
        }
    }

    // private record User(String name, String date, short score) {}

    public static void report() throws IOException {
        var db = Files.readAllLines(Path.of("db.csv"));
        if (db.isEmpty())
            System.err.println("Try taking the test, buddy. Nothing to pry open with your wily eyes, eh?");
        else {
            final var avrgScorPerSubj = new EnumMap<>(Map.of(Subject.Math, 0.0, Subject.Arts, 0.0,
                    Subject.Geography, 0.0, Subject.History, 0.0, Subject.Science, 0.0));
            for (var l : db) {
                // Scores in order: Math, Science, History, Geography, Arts (each out of 5)
                var tmpStream = Arrays.stream(l.split(",", 3)[2].split(","));
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
            var sb = new StringBuilder("Average score per subject: " + System.lineSeparator());
            for (var s : Subject.values())
                sb.append("%s: %.1f / 5%n".formatted(s.toString(), avrgScorPerSubj.get(s)));
            var f = sb.toString();
            var totalAverage = avrgScorPerSubj.values().parallelStream().mapToDouble(n -> n).sum();
            System.out.printf("Number of test takers: %d\n".formatted(db.size()) +
                            f +
                            "Average overall score: %.1f / 25 (%.1f%%)%n",
                    totalAverage,
                    totalAverage * 4.0);
        }
    }
}
