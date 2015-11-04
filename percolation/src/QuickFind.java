/**
 * Represents a union-find data structure using quick find.
 * <p>
 * Initializing a data structure with <em>N</em> objects takes linear time.
 * Afterwards, <em>find</em>, <em>connected</em>, and <em>count</em> takes O(1)
 * time but <em>union</em> takes O(N) time.
 * <p>
 * For additional documentation, see
 * <a href="http://algs4.cs.princeton.edu/15uf">Section 1.5</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 * 
 * @author jforbes
 *
 */

public class QuickFind implements IUnionFind {
	private int[] myID;
	private int myComponents;

	/**
	 * Default constructor
	 */
	public QuickFind() {
		myID = null;
		myComponents = 0;
	}

	/**
	 * Constructor that creates N isolated components
	 */
	public QuickFind(int N) {
		initialize(N);
	}

	// instantiate N isolated components 0 through N-1
	public void initialize(int n) {
		myComponents = n;
		myID = new int[n];
		for (int i = 0; i < n; i++) {
			myID[i] = i;
		}
	}

	// return number of connected components
	public int components() {
		return myComponents;
	}

	// return id of component corresponding to element x
	public int find(int x) {
		return myID[x];
	}

	// are elements p and q in the same component?
	public boolean connected(int p, int q) {
		return myID[p] == myID[q];
	}

	// merge components containing p and q
	public void union(int p, int q) {
		if (connected(p, q))
			return;
		int pid = myID[p];
		for (int i = 0; i < myID.length; i++)
			if (myID[i] == pid)
				myID[i] = myID[q];
		myComponents -= 1;
	}
}
