/**
 * This interface encapsulates what a class used in Percolation Threshold
 * simulations should support. By varying the implementations you can experiment
 * with different approaches in analyzing not only what the Percolation
 * Threshold constants are for different grids, but how efficiently these
 * constants can be determined via simulation.
 * 
 * @author Owen Astrachan
 * @author Jeff Forbes
 * @date March, 2008
 * @date September, 2008
 * @date March, 2011
 */

public interface IPercolate {

	// Possible states for a grid cell are BLOCKED, OPEN, and FULL
	public static final int BLOCKED = 0;
	public static final int OPEN = 1;
	public static final int FULL = 2;

	/**
	 * Open site (row i, col j) if it is not already open. By convention, (0, 0)
	 * is the upper-left site
	 * 
	 * The method modifies internal state so that determining if percolation
	 * occurs could change after taking a step in the simulation.
	 * 
	 * @param i
	 *            row index in range [0,N-1]
	 * @param j
	 *            column index in range [0,N-1]
	 */
	public abstract void open(int i, int j);

	/**
	 * Returns true if and only if site (row i, col j) is OPEN
	 * 
	 * @param i
	 *            row index in range [0,N-1]
	 * @param j
	 *            column index in range [0,N-1]
	 */
	public abstract boolean isOpen(int i, int j);

	/**
	 * Returns true if and only if site (row i, col j) is FULL
	 * 
	 * @param i
	 *            row index in range [0,N-1]
	 * @param j
	 *            column index in range [0,N-1]
	 */
	public boolean isFull(int i, int j);

	/**
	 * Returns true if the simulated percolation actually percolates. What it
	 * means to percolate could depend on the system being simulated, but
	 * returning true typically means there's a connected path from
	 * top-to-bottom.
	 * 
	 * @return true iff the simulated system percolates
	 */
	public abstract boolean percolates();
}