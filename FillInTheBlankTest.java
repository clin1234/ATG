import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FillInTheBlankTest {

	@Test
	void testFillInTheBlankConstructor() {
		// Setting up for testing
		FillInTheBlank fib = new FillInTheBlank(Subject.Math, "3 + __ = 5?", "2",
				"3", "2", "1", "5");
						
		// Check that the call worked as it should
		assertTrue(fib instanceof FillInTheBlank);
	}
	
	@Test
	void testCheckAnswerIsEmpty() {
		// Setting up for testing
		FillInTheBlank fib = new FillInTheBlank(Subject.History, "_________ once"
				+ " said \"I have a dream.\"?", "Martin Luther King", "Nelson Mandela", "Ghandi",
				"Martin Luther King", "Barack Obama");
		
		// Call to the method being tested
		fib.checkAnswer("");
		
		// Check that the call worked as it should
		assertTrue(fib.getCorrect() == false);
	}
	
	@Test
	void testCheckAnswerIsWrong() {
		// Setting up for testing
		FillInTheBlank fib = new FillInTheBlank(Subject.Geography, "Capoeira is from ______.",
				"Brazil", "South Africa", "Australia", "Brazil", "Colombia");
		
		// Call to the method being tested
		fib.checkAnswer("South Africa");
		
		// Check that the call worked as it should
		assertTrue(fib.getCorrect() == false);
	}

	@Test
	void testCheckAnswerIsRight() {
		// Setting up for testing
		FillInTheBlank fib = new FillInTheBlank(Subject.History, "California and Texas were"
				+ " once part of _____.",
				"Mexico");
		
		// Call to the method being tested
		fib.checkAnswer("Mexico");
		
		// Check that the call worked as it should
		assertTrue(fib.getCorrect() == true);
	}
}
