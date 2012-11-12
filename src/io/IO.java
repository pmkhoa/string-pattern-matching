package io;
import java.util.*;
import java.io.*;
public class IO {
	public IO() {}
	public Vector<String> readFile(String filename) throws IOException{
		File inputFile = new File(filename);
		BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
		Vector<String> storedData = new Vector<String>();
		String inputLine;
		while((inputLine = bufferedReader.readLine()) != null) { storedData.add(inputLine);}
		return storedData;
	}
	public String getPattern(Vector<String> storedData) {
		return storedData.firstElement();
	}
	public String getContextToSearch(Vector<String> storedData) {
		String context = "";
		for(int line = 1; line < storedData.size(); line++) { context += storedData.elementAt(line); }
		return context;
	}
	
}
