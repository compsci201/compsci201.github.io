import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class AutocompletorBenchmark {

	public static Random myRandom = new Random(1234);

	public static Autocompletor getInstance(String[] words, double[] weights){
		return new BruteAutocomplete(words, weights);
	}

	public static long countNodes(Node root){
		long result = 1;
		for(Node child: root.children.values())
			result += countNodes(child);
		return result;
	}

	public static Scanner openFileFromDialog() {
		Scanner scan = null;
		System.out.println("Opening file dialog.");
		JFileChooser openChooser = new JFileChooser(System.getProperties()
				.getProperty("user.dir"));
		int retval = openChooser.showOpenDialog(null);
		if (retval == JFileChooser.APPROVE_OPTION) {
			File file = openChooser.getSelectedFile();
			try {
				scan = new Scanner(file);
				System.out.println("Opening - " + file.getName() + ".");
			} catch (FileNotFoundException e) {
				System.out.println("Could not open selected file.");
				e.printStackTrace();
			}
		} else
			System.out.println("File open canceled.");
		return scan;
	}

	public static void main(String[] args){
		Scanner input = openFileFromDialog();
		In in = new In(input);
		int N = Integer.parseInt(in.readLine());
		String[] terms = new String[N];
		double[] weights = new double[N];
		for (int i = 0; i < N; i++) {
			String line = in.readLine();
			int tab = line.indexOf('\t');
			weights[i] = Double.parseDouble(line.substring(0, tab).trim());
			terms[i] = line.substring(tab + 1).toLowerCase();
		}
		Autocompletor auto = getInstance(terms, weights);
		System.out.println("Benchmarking "+auto.getClass().getName()+"...");
		System.out.println("Found "+N+" words");
		long startTime = System.nanoTime();
		auto = getInstance(terms, weights);
		System.out.println("Time to initialize - "+
				(System.nanoTime()-startTime)/1E9);
		if (auto instanceof TrieAutocomplete)
			System.out.println("Created "+countNodes(((TrieAutocomplete) auto)
					.myRoot)+" nodes");
		String randomWord = "";
		while (randomWord.length() <= 2)
			randomWord = terms[myRandom.nextInt(terms.length)];
		String randomPrefix1 = randomWord.substring(0, 1);
		String randomPrefix2 = randomWord.substring(0, 2);
		String[] queries = {"", randomWord, randomPrefix1, randomPrefix2,
				"notarealword"};
		int trial;
		for(String query: queries){
			startTime = System.nanoTime();
			for(trial = 0; trial < 1000; trial++){
				auto.topMatch(query);
				if (System.nanoTime() - startTime > 5E9)
					break;
			}
			System.out.println("Time for topMatch(\""+query+"\") - "+
					(System.nanoTime()-startTime)/(1E9*trial));
		}
		for(String query: queries){
			for(int k = 1; k <= 7; k+=3){
				startTime = System.nanoTime();
				for(trial = 0; trial < 1000; trial++){
					auto.topKMatches(query, 3);
					if (System.nanoTime() - startTime > 5E9)
						break;
				}
				System.out.println("Time for topKMatches(\""+query+"\", "+k+")"
						+ " - "+" "+(System.nanoTime()-startTime)/(1E9*trial));
			}
		}
	}
}
