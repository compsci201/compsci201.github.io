import javax.swing.JOptionPane;

import princeton.*;

/**
 * Print statistics on Percolation: prompts the user for N and T, performs T
 * independent experiments on an N-by-N grid, prints out the 95% confidence
 * interval for the percolation threshold, and prints mean and std. deviation
 * of timings
 * 
 * @author Kevin Wayne
 * @author Jeff Forbes
 */

public class PercolationStats {

	public static void main(String[] args) {
		int N, T;
		if (args.length == 2) { // use command-line arguments for
								// testing/grading
			N = Integer.parseInt(args[0]);
			T = Integer.parseInt(args[1]);
		} else {
			String input = JOptionPane.showInputDialog("Enter N and T", "20 100");
			// TODO: parse N and T from input
		}

		// TODO: Perform T experiments for N-by-N grid

		// TODO: print statistics and confidence interval

	}
}
