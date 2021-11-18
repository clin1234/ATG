import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.regex.Pattern;

public class AEG {
    private static final Pattern COMPILE = Pattern.compile("\\R");

    /*
     * public static void main(String[] args) {
     * System.out.print("Enter your first and last name: "); String name; Exam e;
     * try (Scanner s = new Scanner(System.in)) { name = s.nextLine(); e = new
     * Exam(name, LocalDate.now().toString());
     *
     * e.takeExam(); e.gradeExam(); e.displayResult(); // Kludge return; } }
     */

    public static void main(String[] args) throws IOException {
        // Insert name of file containing user answers here.
        var inputFile = Path.of("actually_right1.txt");
        assert !Files.notExists(inputFile) : inputFile + " does not exist.";

        var contents = Files.readString(inputFile);
        String[] splitter = COMPILE.split(contents, 2);
        String name = splitter[0];
        splitter[1] = splitter[1].replace(System.lineSeparator(), "");
        assert splitter[1].chars().filter(c -> c == ';').count() == 24;
        Exam e = new Exam(name, LocalDate.now().toString());
        e.takeExam(splitter[1].split(";"));
        e.gradeExam();
        e.writeOut();
        e.displayResult(splitter[1].split(";"));
        report();
    }

    // private record User(String name, String date, short score) {}

    public static void report() throws IOException {
        var db = Files.readAllLines(Path.of("db.csv"));
        if (db.isEmpty())
            System.err.println("Try taking the test, buddy. Nothing to pry open with your wily eyes, eh?");
        else {
            final var avrgScorPerSubj = new EnumMap<>(Map.of(Exam.Subject.Math, 0.0, Exam.Subject.Arts, 0.0,
                    Exam.Subject.Geography, 0.0, Exam.Subject.History, 0.0, Exam.Subject.Science, 0.0));
            for (var l : db) {
                // Scores in order: Math, Science, History, Geography, Arts (each out of 5)
                var tmpStream = Arrays.stream(l.split(",", 3)[2].split(","));
                var doubleStream = tmpStream.mapToDouble(Double::parseDouble);
                final var userScores = doubleStream.toArray();
                avrgScorPerSubj.put(Exam.Subject.Math, avrgScorPerSubj.get(Exam.Subject.Math) + userScores[0]);
                avrgScorPerSubj.put(Exam.Subject.Arts, avrgScorPerSubj.get(Exam.Subject.Arts) + userScores[4]);
                avrgScorPerSubj.put(Exam.Subject.Geography, avrgScorPerSubj.get(Exam.Subject.Geography) + userScores[3]);
                avrgScorPerSubj.put(Exam.Subject.History, avrgScorPerSubj.get(Exam.Subject.History) + userScores[2]);
                avrgScorPerSubj.put(Exam.Subject.Science, avrgScorPerSubj.get(Exam.Subject.Science) + userScores[1]);
            }
            for (final var entry : avrgScorPerSubj.entrySet()) {
                double avg = entry.getValue() / db.size();
                avrgScorPerSubj.put(entry.getKey(), avg);
            }
            var sb = new StringBuilder("Average score per subject: " + System.lineSeparator());
            for (var s : Exam.Subject.values())
                sb.append("%s: %.1f / 5%n".formatted(Exam.subjectNames.get(s), avrgScorPerSubj.get(s)));
            var f = sb.toString();/*
             * int[] list = new int[db.size()]; for (var i = 0; i < db.size(); i++) { var a
             * = db.get(i).split(","); // var u = new User(a[0], a[1],
             * Short.parseShort(a[2])); list[i] = Short.parseShort(a[2]); }
             */
            var totalAverage = avrgScorPerSubj.values().parallelStream().mapToDouble(n -> n).sum();
            System.out.printf("""
                    Number of test takers: %d
                    """.formatted(db.size()) +
                    f +
                    """
                            Average overall score: %.1f / 25 (%.1f%%)
                            %n""", totalAverage, totalAverage * 4.0);
        }
    }
}
