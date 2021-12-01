import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TrueOrFalseTest {

	@Test
	void testShowForQrongQFalse() {
		TrueOrFalse a = new TrueOrFalse(Subject.Math, "Does 0 + 0 = 100?", false);
		assertEquals("Your answer should be false", a.showForWrongQ());
	}
	
	@Test
	void testShowForQrongQTrue() {
		TrueOrFalse a = new TrueOrFalse(Subject.Arts, "Is the sky blue?", true);
		assertEquals("Your answer should be true", a.showForWrongQ());
	}
	
	@Test
	void testCheckAnswerFalse() {
		TrueOrFalse a = new TrueOrFalse(Subject.History, "Was Obama the first President?", false);
		a.checkAnswer("FALSE");
		assertTrue(a.isCorrect());
	}
	
	@Test
	void testCheckAnswerTrue() {
		TrueOrFalse a = new TrueOrFalse(Subject.Geography, "Is the US in North America?", true);
		a.checkAnswer("TrUe");
		assertTrue(a.isCorrect());
	}
	
	@Test
	void testPrintOptions() {
		TrueOrFalse a = new TrueOrFalse(Subject.Math, "Does 2 + 2 = 4", true);
		assertNotNull(a);
	}
	
	

}
