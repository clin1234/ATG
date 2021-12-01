import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ShortAnswerTest {

	@Test
	void testShortAnswerConstructor() {
		// Setting up for testing
		ShortAnswer sa = new ShortAnswer(Subject.Science, "In words, explain"
				+ " Newton's 2nd Law of Motion.", "net force", "mass", "acceleration");
	
		// Check that the call worked as it should
		assertTrue(sa instanceof ShortAnswer);
	}
	
	@Test
	void testCheckAnswerIsEmpty() {
		// Setting up for testing
		ShortAnswer sa = new ShortAnswer(Subject.Arts, "In what battle and war was"
				+ " Picasso's \"Guernica\" set in?", "Bombing of Guernica", "Spanish"
						+ " Civil War");
	
		// Call to the method being tested
		sa.checkAnswer("");
		
		// Check that the call worked as it should
		assertTrue(sa.getCorrect() == false);
	}
	
	@Test
	void testCheckAnswerIsWrong() {
		// Setting up for testing
		ShortAnswer sa = new ShortAnswer(Subject.Science, "In words, explain"
				+ " Newton's 2nd Law of Motion.", "net force", "mass", "acceleration");
	
		// Call to the method being tested
		sa.checkAnswer("Not any of the correct words");
		
		// Check that the call worked as it should
		assertTrue(sa.getCorrect() == false);
	}
	
	@Test
	void testCheckAnswerIsRight() {
		// Setting up for testing
		ShortAnswer sa = new ShortAnswer(Subject.Geography, "Which countires claim to"
				+ " be the legitimate government of China?", "People's Republic of"
						+ " China", "Republic of China");
	
		// Call to the method being tested
		sa.checkAnswer("People's Republic of China, as well as the Republic of China");
		
		// Check that the call worked as it should
		assertTrue(sa.getCorrect() == true);
	}
}
