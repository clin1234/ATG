import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;

class ExamTest {

	@Test
	void testExam() {
		// Call to the method being tested
		Exam e = new Exam("Jack Black", "2021-04-28");
		// Assertion
		assertTrue(e instanceof Exam);
	}

	@Test
	void testGetMAX_SCORE() {
		// Setting up for testing
		Exam e = new Exam("Erin Mack", "2021-11-09");
		final int expected = 25;
		// Call to the method being tested
		final int actual = Exam.getMAX_SCORE();
		// Assertion
		assertEquals(expected, actual);
	}

	@Test
	void testGetQUESTION_WEIGHT() {
		// Setting up for testing
		Exam e = new Exam("Archie Hen", "2021-08-12");
		final int expected = 1;
		// Call to the method being tested
		final int actual = Exam.getQUESTION_WEIGHT();
		// Assertion
		assertEquals(expected, actual);
	}


	@Test
	void testGetUserName() {
		// Setting up for testing
		Exam e = new Exam("Charlie Lin", "2021-09-20");
		// Call to the method being tested
		String actual = e.getUserName();
		// Assertion
		assertEquals("Charlie Lin", actual);
	}

	@Test
	void testGetTestDate() {
		// Setting up for testing
		Exam e = new Exam("Matt Westphalen", "2021-11-17");
		// Call to the method being tested
		String actual = e.getTestDate();
		// Assertion
		assertEquals("2021-11-17", actual);
	}

	@Test
	void testGetQuestionBank() {
		Exam e = new Exam("Til Jiu", "2021-02-03");
		assertNotNull(e.getQuestionBank());
		assertTrue(Arrays.stream(e.getQuestionBank()).allMatch(q -> q != null));
		assertEquals(e.getQuestionBank().length, 25);
	}

	@Test
	void testGetSubjectScores() {
		Exam e = new Exam("Kilo Wet", "2021-04-20");
		var map = e.getSubjectScores();
		assertEquals(5, map.size());
	}


	@Test
	void testTakeExam() {
		Exam e = new Exam("Kilo Jiu", "2021-10-22");
		String[] answers = """
				1;2;true;L'Hopital, limits, indeterminate;Cantor's first;
				2;1;true;System V Unix, BSD;Albania, East Germany,
				Czechoslovakia, Poland, Hungary, Bulgaria, Romania, and the Soviet Union;
				1;2;true;hydrogen, helium, lithium;net force = mass times acceleration;
				1;3;true;red, blue, yellow;Bombing of Guernica, Spanish Civil War;
				2;3;false;alaska, hawaii;People's Republic of China and the Republic of China
				""".replace("\n", "").split(";");
		assertEquals(25, answers.length);
		e.takeExam(answers);
	}

	@Test
	void testWriteOut() throws IOException {
		Exam e = new Exam("Kio J", "2021-11-22");
		String[] answers = """
				1;2;true;L'Hopital, limits, indeterminate;Cantor's first;
				2;1;true;System V Unix, BSD;Albania, East Germany,
				Czechoslovakia, Poland, Hungary, Bulgaria, Romania, and the Soviet Union;
				1;2;true;hydrogen, helium, lithium;net force = mass times acceleration;
				1;3;true;red, blue, yellow;Bombing of Guernica, Spanish Civil War;
				2;3;false;alaska, hawaii;People's Republic of China and the Republic of China
				""".replace("\n", "").split(";");
		e.takeExam(answers);
		e.gradeExam();
		int oldSize = Files.readAllLines(Path.of("db.csv")).size();
		e.writeOut();
		int newSize = Files.readAllLines(Path.of("db.csv")).size();
		assertEquals(oldSize+1, newSize);
	}
}
