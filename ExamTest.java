import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

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

	/*
	@Test
	void testTakeExam() {
		fail("Not yet implemented");
		// Setting up for testing

		// Call to the method being tested

		// Assertion
	}

	@Test
	void testIsValidInputStringMultipleChoice() {
		fail("Not yet implemented");
		// Setting up for testing

		// Call to the method being tested

		// Assertion
	}

	@Test
	void testIsValidInputStringTrueOrFalse() {
		fail("Not yet implemented");
		// Setting up for testing

		// Call to the method being tested

		// Assertion
	}

	@Test
	void testGradeExam() {
		fail("Not yet implemented");
		// Setting up for testing

		// Call to the method being tested

		// Assertion
	}

	@Test
	void testDisplayResult() {
		fail("Not yet implemented");
		// Setting up for testing

		// Call to the method being tested

		// Assertion
	}

	@Test
	void testWriteOut() {
		fail("Not yet implemented");
		// Setting up for testing

		// Call to the method being tested

		// Assertion
	}

	@Test
	void testPrintExamResult() {
		fail("Not yet implemented");
		// Setting up for testing

		// Call to the method being tested

		// Assertion

	}*/

}
