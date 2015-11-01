import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class AutocompletorBenchmark {

	public static Random myRandom = new Random(1234);

	public static Autocompletor getInstance(String[] words, double[] weights) {
		return new BruteAutocomplete(words, weights);
	}

	public static long countNodes(Node root) {
		long result = 1;
		for (Node child : root.children.values())
			result += countNodes(child);
		return result;
	}

	public static File openFileFromDialog() {
		File file = null;
		System.out.println("Opening file dialog.");
		JFileChooser openChooser = new JFileChooser(System.getProperties().getProperty("user.dir"));
		int retval = openChooser.showOpenDialog(null);
		if (retval == JFileChooser.APPROVE_OPTION) {
			file = openChooser.getSelectedFile();
			if (file.canRead()) {
				System.out.println("Opening - " + file.getName() + ".");
			} else {
				System.out.println("Could not open selected file.");
			}
		} else
			System.out.println("File open canceled.");
		return file;
	}

	public static void main(String[] args) {
		File file = openFileFromDialog();
		if (file == null)
			System.exit(0); // prevent further execution which may throw null pointer exception
		Scanner in = null;
		try {
			in = new Scanner(new BufferedInputStream(new FileInputStream(file)));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		int N = 0;
		String[] terms = null;
		double[] weights = null;
		try {
			N = Integer.parseInt(in.nextLine());
			terms = new String[N];
			weights = new double[N];

			for (int i = 0; i < N; i++) {
				String line = in.nextLine();
				int tab = line.indexOf('\t');
				weights[i] = Double.parseDouble(line.substring(0, tab).trim());
				terms[i] = line.substring(tab + 1).toLowerCase();
			}
		} catch (Exception e) { //could be any parsing related exception
			System.err.println("File is malformatted");
			System.exit(0);
		}
		long startTime = System.nanoTime();
		Autocompletor auto = getInstance(terms, weights);
		System.out.println("Benchmarking " + auto.getClass().getName() + "...");
		System.out.println("Found " + N + " words");
		System.out.println("Time to initialize - " + (System.nanoTime() - startTime) / 1E9);
		if (auto instanceof TrieAutocomplete)
			System.out.println("Created " + countNodes(((TrieAutocomplete) auto).myRoot) + " nodes");
		String randomWord = "";
		while (randomWord.length() <= 2)
			randomWord = terms[myRandom.nextInt(terms.length)];
		String randomPrefix1 = randomWord.substring(0, 1);
		String randomPrefix2 = randomWord.substring(0, 2);
		String[] queries = { "", randomWord, randomPrefix1, randomPrefix2, "notarealword" };
		int trial;
		for (String query : queries) {
			startTime = System.nanoTime();
			for (trial = 0; trial < 1000; trial++) {
				auto.topMatch(query);
				if (System.nanoTime() - startTime > 5E9)
					break;
			}
			System.out.println(
					"Time for topMatch(\"" + query + "\") - " + (System.nanoTime() - startTime) / (1E9 * trial));
		}
		for (String query : queries) {
			for (int k = 1; k <= 7; k += 3) {
				startTime = System.nanoTime();
				for (trial = 0; trial < 1000; trial++) {
					auto.topKMatches(query, 3);
					if (System.nanoTime() - startTime > 5E9)
						break;
				}
				System.out.println("Time for topKMatches(\"" + query + "\", " + k + ")" + " - " + " "
						+ (System.nanoTime() - startTime) / (1E9 * trial));
			}
		}
	}
}
