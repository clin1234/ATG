import java.time.LocalDate;
import java.util.Scanner;

public class AEG {

	public static void main(String[] args) {
		System.out.print("Enter your first and last name: ");
		String name;
		Exam e;
		try (Scanner s = new Scanner(System.in)) {
			name = s.nextLine();
			e = new Exam(name, LocalDate.now().toString());

			e.takeExam();
			e.gradeExam();
			e.displayResult();
			// Kludge
			return;
		}
	}
}
