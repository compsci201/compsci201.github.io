import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.*;

/**
 * Class for running JUNit tests with different implementations of IDnaStrand.
 * To use a different implementation alter the method <code>getNewStrand</code>
 * since that method is called by all JUnit tests to create the IDnaStrand
 * objects being tested.
 * 
 * @author ola
 * @date January 2008, modifed and commented in September 2008
 * @date January 2009, added splice testing
 * @version 3.0, October 2015, added nodeList
 */

public class TestStrand {
	private static String[] strs = { "aggtccg", "aaagggtttcccaaagggtttccc", "a", "g", "",
			"aggtccgttccggttaaggagagagagagagttt" };

	/**
	 * Return a strand to test by other JUnit tests
	 * 
	 * @param s
	 *            is the string modeled by an IDnaStrand implementation
	 * @return an IDnaStrand object for testing in this JUnit testing class.
	 */
	public LinkStrand getNewStrand(String s) {
		return new LinkStrand(s);
	}

	/**
	 * This test checks if .size() returns the correct value for basic cases"
	 */
	@Test(timeout = 10000)
	public void testSize() {
		for (String s : strs) {
			IDnaStrand str = getNewStrand(s);
			assertEquals("This test checks if .size() returns the correct value"
					+ " for basic cases. The test case failed on was " + s, s.length(), str.size());
		}
	}

	/**
	 * This test checks if toString works correctly for basic cases.
	 */
	@Test(timeout = 10000)
	public void testToString() {
		for (String s : strs) {
			IDnaStrand str = getNewStrand(s);
			assertEquals("This test checks if toString works correctly for "
					+ "basic cases. The test case failed on was " + s, s, str.toString());
			assertEquals("This test checks if toString works correctly for "
					+ "basic cases. The test case failed on was " + s, s.length(), str.size());
		}
	}

	/** This test checks if initializeFrom works correctly for basic cases */
	@Test(timeout = 10000)
	public void testInitialize() {
		for (String s : strs) {
			LinkStrand str = getNewStrand("");
			str.initializeFrom(s);
			assertEquals("This test checks if initializeFrom works correctly"
					+ " for basic cases. The test case failed on was " + s, s.length(), str.size());
			assertEquals("This test checks if initializeFrom works correctly"
					+ " for basic cases. The test case failed on was " + s, s, str.toString());
		}
	}

	/**
	 * This test checks if reverse works correctly for "simple cases
	 */
	@Test(timeout = 10000)
	public void testReverse() {
		for (String s : strs) {
			IDnaStrand strand = getNewStrand(s);
			IDnaStrand rev = strand.reverse();
			String rs = new StringBuilder(s).reverse().toString();
			assertEquals("This test checks if reverse works correctly for "
					+ "simple cases. The test case failed on was " + s, rev.toString(), rs);
		}
	}

	@Test(timeout = 10000)
	/** This test checks if append works correctly for simple cases */
	public void testAppend() {
		String app = "gggcccaaatttgggcccaaattt";
		for (String s : strs) {
			IDnaStrand str = getNewStrand(s);
			str.append(app);
			assertEquals(
					"This test checks if append works correctly for "
							+ "simple cases. The test case failed on was appending " + app + " to " + s,
					s + app, str.toString());
			assertEquals(
					"This test checks if append works correctly for "
							+ "simple cases. The test case failed on was appending " + app + " to " + s,
					s.length() + app.length(), str.size());
		}
	}

	/**
	 * This test checks if cutAndSplice works correctly for simple cases
	 */
	@Test(timeout = 10000)
	public void testSplice() {
		String r = "gat";
		String sp = "xxyyzz";
		String[] strands = { "ttgatcc", "tcgatgatgattc", "tcgatctgatttccgatcc", "gat", "gatctgatctgat", "gtacc",
				"gatgatgat" };
		String[] recombs = { "ttxxyyzzcc", "tcxxyyzzxxyyzzxxyyzztc", "tcxxyyzzctxxyyzzttccxxyyzzcc", "xxyyzz",
				"xxyyzzctxxyyzzctxxyyzz", "", "xxyyzzxxyyzzxxyyzz" };

		for (int k = 0; k < strands.length; k++) {
			IDnaStrand str = getNewStrand(strands[k]);
			String bef = str.toString();
			IDnaStrand rec = str.cutAndSplice(r, sp);
			assertEquals(
					"This test checks if cutAndSplice works correctly for "
							+ "simple cases. The test case failed on was splicing " + sp + " into " + strands[k],
					recombs[k], rec.toString());
			assertEquals(
					"This test checks if cutAndSplice works correctly for "
							+ "simple cases. The test case failed on was splicing " + sp + " into " + strands[k],
					bef, str.toString());
		}
	}

	/**
	 * This test checks if nodeList returns the correct value
	 */
	@Test(timeout = 10000)
	public void testNodeList() {
		ArrayList<String> sol = new ArrayList<String>();
		sol.add("actg");
		LinkStrand sub = new LinkStrand("actg");
		for (String s : strs) {
			sol.add(s);
			sub.append(s);
			assertEquals("This test checks if nodeList returns the correct " + "value", sol, sub.nodeList());
		}
	}
}
