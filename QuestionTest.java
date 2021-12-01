import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class QuestionTest {

	@Test
	void testQuestion() {
		Question a = new TrueOrFalse (Subject.Math, "Is 8 / 2 = 4?", true);
		assertTrue(a instanceof Question);
	}
	
	@Test
	void testGetSubject() {
		Question a = new MultipleChoice (Subject.History, "Which President was Washington?", "1", "1", "2", "3", "4");
		assertEquals(Subject.History, a.getSubject());
	}
	
	@Test
	void testSetSubject() {
		Question a = new TrueOrFalse (Subject.Arts, "Is the Mona Lisa a woman?", true);
		a.setSubject(Subject.Geography);
		assertEquals(Subject.Geography, a.getSubject());
	}
	
	@Test
	void testGetQuestion() {
		Question a = new MultipleChoice (Subject.Science, "What fruit is associated with Isaac Newton?", "apple", "orange", "banana", "pineapple", "apple");
		assertEquals("What fruit is associated with Isaac Newton?", a.getQuestion());
	}
	
	@Test
	void testSetQuestion() {
		Question a = new TrueOrFalse (Subject.Math, "Is 1/2 equivalent to 0.6", false);
		a.setQuestion("Is 1/2 equivalent to 0.7?");
		assertEquals("Is 1/2 equivalent to 0.7?", a.getQuestion());
	}

}
