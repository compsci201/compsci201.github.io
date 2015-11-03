import java.util.PriorityQueue;

/**
 * Implements Autocompletor by scanning through the entire array of terms for every topKMatches or
 * topMatch query.
 */
public class BruteAutocomplete implements Autocompletor {

  Term[] myTerms;

  public BruteAutocomplete(String[] terms, double[] weights) {
		if (terms == null || weights == null)
			throw new NullPointerException("One or more arguments null");
    myTerms = new Term[terms.length];
    for (int i = 0; i < terms.length; i++) {
      myTerms[i] = new Term(terms[i], weights[i]);
    }
  }

  @Override
  public String[] topKMatches(String prefix, int k) {
    // maintain pq of size k
    PriorityQueue<Term> pq = new PriorityQueue<Term>(k, new Term.WeightOrder());
    for (Term t : myTerms) {
      if(!t.getWord().startsWith(prefix)) continue;
      if (pq.size() < k) {
        pq.add(t);
      } else if (pq.peek().getWeight() < t.getWeight()) {
        pq.remove();
        pq.add(t);
      }
    }
    int numResults = Math.min(k, pq.size());
    String[] ret = new String[numResults];
    for (int i = 0; i < numResults; i++) {
      ret[numResults - 1 - i] = pq.remove().getWord();
    }
    return ret;
  }

  @Override
  public String topMatch(String prefix) {
    String maxTerm = "";
    double maxWeight = -1;
    for (Term t : myTerms) {
      if (t.getWeight() > maxWeight && t.getWord().startsWith(prefix)) {
        maxTerm = t.getWord();
      }
    }
    return maxTerm;
  }

}
