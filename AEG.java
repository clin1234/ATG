import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.Scanner;

public class AEG {

	/*
	 * public static void main(String[] args) {
	 * System.out.print("Enter your first and last name: "); String name; Exam e;
	 * try (Scanner s = new Scanner(System.in)) { name = s.nextLine(); e = new
	 * Exam(name, LocalDate.now().toString());
	 * 
	 * e.takeExam(); e.gradeExam(); e.displayResult(); // Kludge return; } }
	 */
	private static Scanner inputter;

	public static void main(String[] args) throws IOException {
		/*
		 * Two properties: -Duser=<name> skips the prompt for name, and -Dstatsonly only
		 * displays statistics about the test, then exits.
		 */
		String name = System.getProperty("user", null);
		var statsOnly = System.getProperty("statsonly", null);
		if (statsOnly != null) {
			report();
			System.exit(0);
		}
		inputter = new Scanner(System.in);
		if (name == null) {
			System.out.print("Enter your first and last name: ");
			name = inputter.nextLine();
		}
		// while (true) {
		System.out.println("Do you want to take the test? Type \"yes\" to do so.");
		while (inputter.hasNextLine()) {
			var res = inputter.nextLine();
			if (res.equals("yes")) {
				Exam e = new Exam(name, LocalDate.now().toString());
				e.takeExam();
	
				e.writeOut();
				e.displayResult();
				report();
			} else {
				inputter.close();
				return;
			}
		}
		// }
	}

	// private record User(String name, String date, short score) {}

	public static void report() throws IOException {
		var db = Files.readAllLines(Path.of("db.csv"));
		if (db.size() == 0) {
			System.err.println("Try taking the test, buddy. Nothing to pry open with your wily eyes, eh?");
			return;
		}
		var list = new ArrayDeque<Short>(db.size());
		for (var v : db) {
			var a = v.split(",");
			// var u = new User(a[0], a[1], Short.parseShort(a[2]));
			list.add(Short.parseShort(a[2]));
		}
		double as = list.parallelStream().mapToInt(n -> n).average().getAsDouble();
		System.out.println("""
				Number of test takers: %d
				Average score: %.1f / 25 (%.1f%%)
				""".formatted(list.size(), as, as * 4d));
	}
}
