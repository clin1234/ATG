import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.stream.IntStream;

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
		/*
		 * Two properties: -Duser=<name> skips the prompt for name, and -Dstatsonly only
		 * displays statistics about the test, then exits.
		 */
		//String name = System.getProperty("user", null);
		var statsOnly = System.getProperty("statsonly", null);
		if (statsOnly != null) {
			report();
			System.exit(0);
		}

		var inputFile = Path.of(args[args.length - 1]);
		if (Files.notExists(inputFile))
			throw new NoSuchFileException(inputFile.toString()+ " does not exist.");
		
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
		int[] list = new int[db.size()];
		for (var i = 0; i < db.size(); i++) {
			var a = db.get(i).split(",");
			// var u = new User(a[0], a[1], Short.parseShort(a[2]));
			list[i] = Short.parseShort(a[2]);
		}
		double as = IntStream.of(list).average().getAsDouble();
		System.out.println("""
				Number of test takers: %d
				Average score: %.1f / 25 (%.1f%%)
				""".formatted(list.length, as, as * 4d));
	}
}
