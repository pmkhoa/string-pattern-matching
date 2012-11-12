package algorithm;

import java.util.LinkedList;

public class NaiveSearch {
	int comparisons = 0;
	public NaiveSearch() {}
	
	public LinkedList<Object> searchForPattern(String pattern, String context) {
		LinkedList<Object> foundPatternList = new LinkedList<Object>();
		int patternLength = pattern.length(); int contextLength = context.length();
		for(int contextIndex = 0; contextIndex <= contextLength - patternLength; contextIndex++) {
			int match = 0;
			for(int patternIndex = 0; patternIndex < patternLength; patternIndex++) {
				if(pattern.charAt(patternIndex) == context.charAt(patternIndex+contextIndex)) {comparisons++; match++;}
				else {comparisons++; break;}	
			}
			if(match == patternLength) foundPatternList.add(contextIndex);
			comparisons++;	
		}
		return foundPatternList;
	}
	public void printFoundPattern(LinkedList<Object> foundPatternList) {
		int listSize = foundPatternList.size();
		System.out.println("Number of comparisons (Naive Search): "+comparisons);
		System.out.print("Found pattern in text at: ");
		for(int index = 0; index < listSize; index++) 
			System.out.print(foundPatternList.get(index)+" ");
		System.out.println();
	}
	public void foundPatternInContext(String pattern, String context) {
		LinkedList<Object> foundPatternList = this.searchForPattern(pattern, context);
		if(foundPatternList.isEmpty()) System.out.println("Number of comparisons (Naive Search): "+comparisons + ". No pattern found in text.");
		else this.printFoundPattern(foundPatternList);
	}	
}
