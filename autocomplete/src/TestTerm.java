import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

public class TestTerm {
	private Random rng = new Random(1234);

	private String[] myNames = { "bhut jolokia", "capsaicin", "carolina reaper", "chipotle", "habanero", "jalapeno",
			"jalapeno membrane" };
	private double[] myWeights = { 855000, 16000000, 2200000, 3500, 100000, 3500, 10000 };

	public Term[] getTerms() {
		Term[] terms = new Term[myNames.length];
		for (int i = 0; i < terms.length; i++)
			terms[i] = new Term(myNames[i], myWeights[i]);
		return terms;
	}

	public int indexOf(Term[] arr, Term item) {
		for (int i = 0; i < arr.length; i++)
			if (arr[i].equals(item))
				return i;
		return -1;
	}

	public void shuffle(Object[] arr) {
		for (int i = 0; i < arr.length; i++) {
			int ind = rng.nextInt(arr.length);
			Object temp = arr[i];
			arr[i] = arr[ind];
			arr[ind] = temp;
		}
	}

	/**
	 * This test checks if Term throws a NullPointerException when constructed
	 * with a null argument
	 */
	@Test(timeout = 10000)
	public void testConstructorException() {
		try {
			Term test = new Term(null, 1);
			fail("No exception thrown for null String");
		} catch (NullPointerException e) {
		} catch (Throwable e) {
			fail("Wrong exception thrown");
		}

		try {
			Term test = new Term("test", -1);
			fail("No exception thrown for invalid weight");
		} catch (IllegalArgumentException e) {
		} catch (Throwable e) {
			fail("Wrong exception thrown");
		}
	}

	/**
	 * Tests that sorting terms without comparator is the same as sorting
	 * lexicographically
	 */
	@Test(timeout = 10000)
	public void testNativeSortingOrder() {
		Term[] terms = getTerms();
		Term[] sorted = terms.clone();
		for (int i = 0; i < 10; i++) {
			shuffle(terms);
			Arrays.sort(terms);
			assertArrayEquals(sorted, terms);
		}
	}

	/**
	 * Tests WeightOrder sorts correctly
	 */
	@Test(timeout = 10000)
	public void testWeightSortingOrder() {
		Term[] terms = getTerms();
		Term[] sorted = { terms[3], terms[5], terms[6], terms[4], terms[0], terms[2], terms[1] };
		for (int i = 0; i < 10; i++) {
			// preserve chipotle and jalapeno's order
			shuffle(terms);
			if (indexOf(terms, sorted[0]) > indexOf(terms, sorted[1])) {
				int temp = indexOf(terms, sorted[0]);
				terms[indexOf(terms, sorted[1])] = sorted[0];
				terms[temp] = sorted[1];
			}
			Arrays.sort(terms, new Term.WeightOrder());
			assertArrayEquals(sorted, terms);
		}
	}

	/**
	 * Tests ReverseWeightSortingOrder
	 */
	@Test(timeout = 10000)
	public void testReverseWeightSortingOrder() {
		Term[] terms = getTerms();
		Term[] sorted = { terms[1], terms[2], terms[0], terms[4], terms[6], terms[3], terms[5] };
		for (int i = 0; i < 10; i++) {
			// preserve chipotle and jalapeno's order
			shuffle(terms);
			if (indexOf(terms, sorted[5]) > indexOf(terms, sorted[6])) {
				int temp = indexOf(terms, sorted[5]);
				terms[indexOf(terms, sorted[6])] = sorted[5];
				terms[temp] = sorted[6];
			}
			Arrays.sort(terms, new Term.ReverseWeightOrder());
			assertArrayEquals(sorted, terms);
		}
	}

	/**
	 * This test checks that toString returns the expected value
	 */
	@Test(timeout = 10000)
	public void testToString() {
		Term[] terms = getTerms();
		for (Term t : terms) {
			assertTrue("weight missing", t.toString().contains(String.format("%.1f", t.getWeight())));
			assertTrue("word missing", t.toString().contains(t.getWord()));
			assertTrue("no tab", t.toString().contains("\t"));
		}
	}
}
