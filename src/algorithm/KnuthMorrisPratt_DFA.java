package algorithm;

import java.util.LinkedList;

public class KnuthMorrisPratt_DFA {
	private int alphabet;
	private int comparisons;
	
	public KnuthMorrisPratt_DFA() {alphabet = 256; comparisons = 0;}
	public int[][] DFA(String pattern) {
		int patternLength = pattern.length();
		int[][] dfa = new int[alphabet][patternLength];
		dfa[pattern.charAt(0)][0] = 1;
		for (int X = 0, j = 1; j < patternLength; j++) { 
           for (int character = 0; character < alphabet; character++) 
              dfa[character][j] = dfa[character][X];
           dfa[pattern.charAt(j)][j] = j+1;
           X = dfa[pattern.charAt(j)][X];
        }
		return dfa;
	}
	public LinkedList<Object> searchPatternInContext(String pattern, String context) {
		int[][] dfa = this.DFA(pattern);
		int contextIndex, patternIndex, patternLength = pattern.length(), contextLength = context.length();
		LinkedList<Object> foundPatternList = new LinkedList<Object>();
		for(contextIndex = 0, patternIndex = 0; contextIndex < contextLength & patternIndex < patternLength; contextIndex++) { 
			patternIndex = dfa[context.charAt(contextIndex)][patternIndex];
			if(patternIndex == patternLength) { foundPatternList.add((contextIndex - patternLength + 1)); patternIndex = 0;}
			comparisons++;
		}
		return foundPatternList;
	}
	public void printFoundPattern(LinkedList<Object> foundPatternList) {
		int listSize = foundPatternList.size();
		System.out.println("Number of comparisons (KMP_DFA): "+comparisons);
		System.out.print("Found pattern in text at: ");
		for(int index = 0; index < listSize; index++) 
			System.out.print(foundPatternList.get(index)+" ");
		System.out.println();
	}
	public void foundPatternInContext(String pattern, String context) {
		LinkedList<Object> foundPatternList = this.searchPatternInContext(pattern, context);
		if(foundPatternList.isEmpty()) System.out.println("Number of comparisons (KMP_DFA): "+comparisons + ". No pattern found in text.");
		else this.printFoundPattern(foundPatternList);
	}
}
