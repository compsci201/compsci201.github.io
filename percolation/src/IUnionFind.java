/**
 * Interface supported by different implementations of UnionFind algorithms.
 * 
 * @author Kevin Wayne
 * @author Owen Astrachan
 * @author Jeff Forbes
 * @date March 2011
 */

public interface IUnionFind {

	/**
	 * Instantiate N isolated components in [0,N-1]
	 */
	public void initialize(int n);

	/**
	 * Returns number of components (disjoint sets)
	 */
	public int components();

	/**
	 * Returns id of component corresponding to element x
	 */
	public int find(int x);

	/**
	 * Returns true iff p and q are in the same set, false otherwise
	 */
	public boolean connected(int p, int q);

	/**
	 * Replace sets containing p and q with their union i.e., merge sets
	 * containing p and q
	 */
	public void union(int p, int q);
}
