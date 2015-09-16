import static org.junit.Assert.*;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

public class MarkovModelTest {
	// TODO: change to MapMarkovModel
	private MarkovModel myMark;

	@Before
	public void setUp() {
		myMark = new MarkovModel();
	}

	/**
	 * This test checks if MapMarkovModel makes a correct " Ngram using a simple
	 * source
	 */
	@Test(timeout = 10000)
	public void testMapMakeNgram() {

		myMark.initialize(new Scanner("aabbaabbaabbaabbaabbaabbaabbaabbaabbaabba"
				+ "abbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabbaabb"));
		myMark.process("2");
		String output = "";
		while (output.length() < 8) {
			output = myMark.makeNGram(2, 100);
		}
		assertFalse("This test checks if MarkovModel makes a correct " +
					"Ngram using a simple source",
					output.contains("aaa"));
		assertFalse("This test checks if MarkovModel makes a correct " +
					"Ngram using a simple source",
					output.contains("bbb"));
		assertFalse("This test checks if MarkovModel makes a correct " + 
					"Ngram using a simple source",
					output.contains("aba"));
		assertFalse("This test checks if MarkovModel makes a correct " + 
					"Ngram using a simple source",
					output.contains("bab"));
		assertTrue("This test checks if MarkovModel makes a correct " + 
				   "Ngram using a simple source",
				   output.contains("aab"));
		assertTrue("This test checks if MarkovModel makes a correct " + 
				   "Ngram using a simple source",
				   output.contains("baa"));
		assertTrue("This test checks if MarkovModel makes a correct " + 
				   "Ngram using a simple source",
				   output.contains("abb"));
		assertTrue("This test checks if MarkovModel makes a correct " + "Ngram using a simple source",
					output.contains("bba"));

	}

	/**
	 * This test checks if MarkovModel makes a correct " Ngram when the source
	 * contains only one distinct letter
	 */
	@Test(timeout = 10000)
	public void testMapAllRepeats() {
		myMark.initialize(new Scanner("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
		myMark.process("1");
		String output = "";
		while (output.length() < 8) {
			output = myMark.makeNGram(1, 
					 "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa".length());
		}
		assertEquals("This test checks if MarkovModel makes a correct " + 
					 "Ngram when the source contains only one distinct letter",
					 output, "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa".substring(0, output.length()));
	}

	/**
	 * This test checks if MarkovModel makes a correct Ngram when the source
	 * contains no repeat letters
	 */
	@Test(timeout = 10000)
	public void testMapNoRepeats() {
		myMark.initialize(new Scanner("qwertyuiopasdfghjklzxcvbnm"));
		myMark.process("1");
		String output = myMark.makeNGram(1, 100);
		assertTrue(
				"This test checks if MarkovModel makes a correct " + 
				"Ngram when the source contains no repeat letters",
				"qwertyuiopasdfghjklzxcvbnm".contains(output));
	}

}
