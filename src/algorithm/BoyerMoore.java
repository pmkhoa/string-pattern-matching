package algorithm;

import java.util.LinkedList;

public class BoyerMoore{
	private int comparisons;
	private int alphabet;
	public BoyerMoore() {comparisons = 0; alphabet = 256;}
	
	public int[] BoyerMooreShiftTable(String pattern) {
		int[] shiftTable = new int[alphabet]; int patternLength = pattern.length();
		for(int aChar = 0; aChar < alphabet; aChar++) shiftTable[aChar] = -1;
		for(int patternIndex = 0; patternIndex < patternLength; patternIndex++) 
			shiftTable[pattern.charAt(patternIndex)] = patternIndex;
		return shiftTable;
	}
	public LinkedList<Object> searchForPattern(String pattern, String context){
		LinkedList<Object> foundPatternList = new LinkedList<Object>();
		int patternLength = pattern.length(); int contextLength = context.length(); int skip; 
		int[] shiftTable = this.BoyerMooreShiftTable(pattern);
		for(int contextIndex = 0; contextIndex < contextLength - patternLength; contextIndex += skip) {
			skip = 0;
			for(int patternIndex = patternLength - 1; patternIndex >= 0; patternIndex--)
				if(!this.compareTwoChars(pattern.charAt(patternIndex), context.charAt(patternIndex+contextIndex))) {
					skip = this.getMaxIntegers(1, (patternIndex - shiftTable[context.charAt(contextIndex+patternIndex)])); break;}
			if(skip == 0) {foundPatternList.add(contextIndex); skip = skip + patternLength -1;}	
		}
		return foundPatternList;
	}
	public int getMaxIntegers(int aNumber, int anotherNumber) {
		if(aNumber >= anotherNumber) return aNumber;
		else return anotherNumber;
	}
	public boolean compareTwoChars(char aChar, char anotherChar) {
		comparisons++;
		return aChar == anotherChar;
	}
	public void printFoundPattern(LinkedList<Object> foundPatternList) {
		int listSize = foundPatternList.size();
		System.out.println("Number of comparisons (Boyer Moore): "+comparisons);
		System.out.print("Found pattern in text at: ");
		for(int index = 0; index < listSize; index++) 
			System.out.print(foundPatternList.get(index)+" ");
		System.out.println();
	}
	public void foundPatternInContext(String pattern, String context) {
		LinkedList<Object> foundPatternList = this.searchForPattern(pattern, context);
		if(foundPatternList.isEmpty()) System.out.println("Number of comparisons (Boyer Moore): "+comparisons + ". No pattern found in text.");
		else this.printFoundPattern(foundPatternList);
	}
}
