import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MultipleChoiceTest {

	@Test
	public void testMC() {
		MultipleChoice a = new MultipleChoice(Subject.Math, "What is 1 + 1?","2", "1", "2", "3", "4");
		assertNotNull(a);
	}

	@Test
	public void testGetResponseOptions() {
		MultipleChoice a = new MultipleChoice(Subject.Arts, "How old was Van Gogh", "50", "10", "20", "50", "40");
		String[] expected = {"10", "20", "50", "40"};
		assertArrayEquals(expected, a.getResponseOptions());
	}
	
	@Test
	public void testSetResponseOptions() {
		MultipleChoice a = new MultipleChoice(Subject.Science, "How long are snakes in feet?", "1", "2", "3", "4", "1");
		a.setResponseOptions("5", "10", "15", "20");
		String[] expected = {"5", "10", "15", "20"};
		assertArrayEquals(expected, a.getResponseOptions());
	}
	

	@Test
	public void testShowForWrongQ() {
		MultipleChoice a = new MultipleChoice(Subject.Geography, "How many States are in the USA",
				"50", "53", "50", "51", "52");
		// Needed, because the string returned by showForWrongQ contains system-specific line separators
		final String lineSep = System.lineSeparator();
		String expected = "1. 53%s2. 50 <-- Correct answer%s3. 51%s4. 52%s"
				.formatted(lineSep, lineSep, lineSep, lineSep);
		assertEquals(expected, a.showForWrongQ());
	}
}
