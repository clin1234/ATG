import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;

public class AEG {

	/*
	 * public static void main(String[] args) {
	 * System.out.print("Enter your first and last name: "); String name; Exam e;
	 * try (Scanner s = new Scanner(System.in)) { name = s.nextLine(); e = new
	 * Exam(name, LocalDate.now().toString());
	 * 
	 * e.takeExam(); e.gradeExam(); e.displayResult(); // Kludge return; } }
	 */

	public static void main(String[] args) throws IOException {
		// TODO: hardcode file name here
		var inputFile = Path.of(args[args.length - 1]);
		if (Files.notExists(inputFile))
			throw new NoSuchFileException(inputFile + " does not exist.");

		var contents = Files.readString(inputFile);
		String[] splitter = contents.split("\\R", 2);
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
		if (db.size() == 0) {
			System.err.println("Try taking the test, buddy. Nothing to pry open with your wily eyes, eh?");
			return;
		}
		var avrgScorPerSubj = new EnumMap<Exam.Subject, Float>(Map.of(Exam.Subject.Math, 0f, Exam.Subject.Arts, 0f,
				Exam.Subject.Geography, 0f, Exam.Subject.History, 0f, Exam.Subject.Science, 0f));
		for (var l : db) {
			// Scores in order: Math, Science, History, Geography, Arts (each out of 5)
			var ints = Arrays.stream(l.split(",", 3)[1].split(",")).mapToInt(Integer::parseInt).toArray();
			avrgScorPerSubj.put(Exam.Subject.Math, avrgScorPerSubj.get(Exam.Subject.Math) + ints[0]);
			avrgScorPerSubj.put(Exam.Subject.Arts, avrgScorPerSubj.get(Exam.Subject.Arts) + ints[4]);
			avrgScorPerSubj.put(Exam.Subject.Geography, avrgScorPerSubj.get(Exam.Subject.Geography) + ints[3]);
			avrgScorPerSubj.put(Exam.Subject.History, avrgScorPerSubj.get(Exam.Subject.History) + ints[2]);
			avrgScorPerSubj.put(Exam.Subject.Science, avrgScorPerSubj.get(Exam.Subject.Science) + ints[1]);
		}
		
		for (var subj : avrgScorPerSubj.keySet()) {
			float avg = avrgScorPerSubj.get(subj)/5;
			avrgScorPerSubj.put(subj, avg);
		}
			

		var sb = new StringBuilder("Average score per subject: "+System.lineSeparator());
		for (var s : Exam.Subject.values())
			sb.append("%s: %d / 5%n".formatted(Exam.subjectNames.get(s), avrgScorPerSubj.get(s)));
		var f = sb.toString();
		/*
		 * int[] list = new int[db.size()]; for (var i = 0; i < db.size(); i++) { var a
		 * = db.get(i).split(","); // var u = new User(a[0], a[1],
		 * Short.parseShort(a[2])); list[i] = Short.parseShort(a[2]); }
		 */
		var totalAverage = avrgScorPerSubj.values().parallelStream()
				.collect(Collectors.summingDouble(n->n)) / (5*db.size());
		System.out.printf("""
				Number of test takers: %d
				""".formatted(db.size())+
				f+
				"""
				Average overall score: %.1f / 25 (%.1f%%)
				%n""", totalAverage, totalAverage * 4f);
	}
}
