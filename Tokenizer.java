package hashing;

import java.util.ArrayList;
import java.io.*;
/**
 * Tokenizer Class the accept either a file or an array of strings as input.
 * Normalize all the word in the input.
 * @author Eric Chen
 */
public class Tokenizer {
	
	private ArrayList<String> wordList = new ArrayList<String>();
	private BufferedReader br;
	private ArrayList<String> tempArrayList;
	private String tempSt = new String("");
	
	/**
	 * constructor that takes one String input standing for the path of the file.
	 * @param file the file that contains string needed to be normalized.
	 * @throws IOException if the file is not found in the given path.
	 */
	public Tokenizer(String file) throws IOException{
		br = new BufferedReader(new FileReader(file));
		while(br.readLine() != null){
			tempArrayList = this.normalize();
			wordList.addAll(tempArrayList);
		}
		
		
	}
	
	/**
	 * Constructor that takes an array of string input, and normalize it.
	 * @param text String array input to be normalized.
	 */
	public Tokenizer(String[] text){
		ArrayList<String> actualList = new ArrayList<String>();
		for(int i = 0; i < text.length; i++){
			tempSt = tempSt + " " + text[i];
		}
		String[] splitSt = tempSt.split("[^a-zA-Z0-9]");
		for(int i = 0; i < splitSt.length; i++){
			 if(!splitSt[i].equals("")){
	                actualList.add(splitSt[i].toLowerCase());
	            }
		}
		wordList.addAll(actualList);
	}
	
	
	/**
	 * A method that returns an array list of the normalized file or array of strings.
	 * @return the normalized word list.
	 */
	public ArrayList<String> wordList(){
		return this.wordList;
	}
	
	/**
	 * private helper method that helps normalize the string.
	 * @return the normalized word list.
	 * @throws IOException if the file is not found.
	 */
	private ArrayList<String> normalize() throws IOException {
	    String line = br.readLine();
	    if (line != null) {
	        tempSt = line;
	    }
	    
	    String[] tempList = tempSt.split("[^A-Za-z0-9]");
	    ArrayList<String> actualList = new ArrayList<String>();
	    
	    for (int i = 0; i < tempList.length; i++) {
	        if (!tempList[i].equals("")) {
	            actualList.add(tempList[i].toLowerCase());
	        }
	    }
	    
	    return actualList;
	}
}
