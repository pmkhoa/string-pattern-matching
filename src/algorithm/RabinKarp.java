package algorithm;

import java.util.LinkedList;

public class RabinKarp {
	private long primeNumber;
	private int alphabet;
	private int comparisons;
	public RabinKarp() {primeNumber = 15485867; alphabet = 256; comparisons = 0;}
	
	public long computeSpecialValue(int patternLength) {
		return (long) (Math.pow(alphabet, patternLength -1) % primeNumber); // alphabet ^ (patternLength - 1) % primeNumber.
	}
	public long computeHashValueForPattern(String pattern, int patternLength) {
		long hash = 0;
		for(int index = 0; index < patternLength; index++) {
			hash = (hash * alphabet + pattern.charAt(index)) % primeNumber;
			comparisons++;
		}
		return hash;
	}
	public LinkedList<Object> searchForPattern(String pattern, String context){
		LinkedList<Object> foundPatternList = new LinkedList<Object>();
		int patternLength = pattern.length(); int contextLength = context.length();
		long specialValue = this.computeSpecialValue(patternLength);
		long patternHash = this.computeHashValueForPattern(pattern, patternLength);
		long contextHash = this.computeHashValueForPattern(context, patternLength);
		if (patternHash == contextHash) foundPatternList.add(1);
		for(int foundPatternAt = patternLength; foundPatternAt < (contextLength); foundPatternAt++) {
			contextHash = ((contextHash + context.charAt(foundPatternAt-patternLength)*
					(primeNumber - specialValue))*alphabet + context.charAt(foundPatternAt))%primeNumber;
			if (patternHash == contextHash) 
				if(this.isStringInContext(pattern, context, foundPatternAt - patternLength + 1)) 
					foundPatternList.add(foundPatternAt - patternLength + 1);
			comparisons++;
		}
		return foundPatternList;
	}
	public boolean isStringInContext(String pattern,String context, int foundPatternAt) {
		int patternSize = pattern.length();
		String compareString = "";
		while(patternSize > 0) { compareString += context.charAt(foundPatternAt); foundPatternAt++; patternSize--;}
		return this.compareTwoStrings(pattern, compareString);
	}
	public boolean compareTwoStrings(String aString, String anotherString) {
		int aStringSize = aString.length(); int anotherStringSize = anotherString.length();
		if(aStringSize != anotherStringSize) return false;
		for(int index = 0; index < aStringSize; index++) 
			if(aString.charAt(index) != anotherString.charAt(index)) {comparisons++; return false;}
			comparisons++;
		return true;
	}
	public void printFoundPattern(LinkedList<Object> foundPatternList) {
		int listSize = foundPatternList.size();
		System.out.println("Number of comparisons (Rabin Karp): "+comparisons);
		System.out.print("Found pattern in text at: ");
		for(int index = 0; index < listSize; index++) 
			System.out.print(foundPatternList.get(index)+" ");
		System.out.println();
	}
	public void foundPatternInContext(String pattern, String context) {
		LinkedList<Object> foundPatternList = this.searchForPattern(pattern, context);
		if(foundPatternList.isEmpty()) System.out.println("Number of comparisons (Rabin Karp): "+comparisons + ". No pattern found in text.");
		else this.printFoundPattern(foundPatternList);
	}
	
}
