import java.util.Scanner;

public class AEG {

	public static void main(String[] args) {
		System.out.print("Enter your first and last name: ");
		String name;
		try (Scanner s = new Scanner(System.in)) {
			name = s.nextLine();
		}
		Exam e = new Exam(name);
		
		e.takeExam();
		e.gradeExam();
		e.displayResult();

	}

}
