package algorithm;
import java.io.IOException;
import java.util.Vector;

import io.*;

public class Main {
	public Main() {};
	
	public static void main(String args[]) throws IOException {
		IO InputOutput = new IO();
		NaiveSearch naive = new NaiveSearch();
		//RabinKarp RK = new RabinKarp();
		String filename = "/Users/khoapham/Dropbox/Developer/String Matching/TestCase/testcase_3.txt";
		Vector<String> storedData = InputOutput.readFile(filename);
		String pattern = InputOutput.getPattern(storedData);
		String context = InputOutput.getContextToSearch(storedData);
		System.out.println("Pattern: " + pattern);
		System.out.println("Context: " + context);
		naive.foundPatternInContext(pattern, context);
		//long[] patternInIntegerArray = RK.converseStringToIntArray(pattern);
		//long[] contextInIntegerArray = RK.converseStringToIntArray(context);
		RabinKarp RK = new RabinKarp();
		RK.foundPatternInContext(pattern, context);
		KnuthMorrisPratt_DFA KMP_DFA = new KnuthMorrisPratt_DFA();
		KMP_DFA.foundPatternInContext(pattern, context);
		KnuthMorrisPratt KMP = new KnuthMorrisPratt();
		KMP.foundPatternInContext(pattern, context);
		//int[] prefixFunction = KMP.computePrefix("ababac");
		BoyerMoore BM = new BoyerMoore();
		BM.foundPatternInContext(pattern, context);
		System.out.println();
	}
}
