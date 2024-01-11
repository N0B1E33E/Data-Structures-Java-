package hashing;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import hashing.HashTable.hashEntry;
public class WordStat {

	private HashTable<Integer> frequencyTable = new HashTable<>();
	private List<String> wordList;
	private Object[] rankEntries;
	
	/**
	 * constructor that accept a String input as a file path of file needed to be read and processed
	 * @param file file path that needed to be read
	 * @throws IOException
	 */
	public WordStat(String file) throws IOException{
		Tokenizer fileTokenizer = new Tokenizer(file);
		wordList = fileTokenizer.wordList();
		Collections.sort(wordList);
		for(String i : wordList)
			frequencyTable.update(i, frequencyTable.get(i) + 1);
		  // Add a filter before actually sort the whole array. 
        ArrayList<hashEntry> temp = new ArrayList<>();
        for(int i = 0; i < frequencyTable.table.length; i++){
            if(frequencyTable.table[i] != null)
            	temp.add(frequencyTable.table[i]);
        }
        
        Object[] tempArray = temp.toArray();
        Arrays.sort(tempArray);
        rankEntries = tempArray;
        temp.clear();
	}
	
	/**
	 * constructor that accept an array of string input that needed to be processed
	 * @param text input String array needed to be processed
	 */
	public WordStat(String [] text){
		Tokenizer textTokenizer = new Tokenizer(text);
		wordList = textTokenizer.wordList();
		Collections.sort(wordList);
		System.out.println(wordList);
		for(String word : wordList){
			System.out.println(word);
			//put this String in the hash table if not is the table previously
			if(frequencyTable.get(word) == -1){
				frequencyTable.put(word, 0);
            } else {
            	//add 1 to the value(frequency) after the first time
            	frequencyTable.update(word, frequencyTable.get(word) + 1);
            }
		}
		
		// Add a filter before actually sort the whole array. 
        ArrayList<hashEntry> temp = new ArrayList<>();
        for(int i = 0; i < frequencyTable.table.length; i++){
            if(frequencyTable.table[i] != null) temp.add(frequencyTable.table[i]);
        }
        
        Object[] tempArray = temp.toArray();
        Arrays.sort(tempArray);
        rankEntries = tempArray;
        temp.clear();
	}
	
	/**
	 * private constructor that can accept an array as input
	 * @param array extension of the other two constructors that can accept an array as input
	 * @throws FileNotFoundException
	 */
	private WordStat(List<String> array){
        this.wordList = array;
        this.frequencyTable = new HashTable();
        for(String i : wordList){
            frequencyTable.update(i, frequencyTable.get(i) + 1);
        }
        ArrayList<hashEntry> temp = new ArrayList<>();
        for(int i = 0; i < frequencyTable.table.length; i++){
            if(frequencyTable.table[i] != null) temp.add(frequencyTable.table[i]);
        }
        Object[] tempArray = temp.toArray();
        Arrays.sort(tempArray);
        rankEntries = tempArray;
    }
	
	
	/**
	 * This method takes a string input, count the number of the input word
	 * in the input file or string array.
	 * @param word searching key of given word.
	 * @return 0 if the word is not in the string. Otherwise return the number of existence.
	 */
	public int wordCount(String word) {
	    if (frequencyTable.get(word) == 0) {
	        return 0;
	    } else {
	        return frequencyTable.get(word) + 1;
	    }
	}
	
	/**
	 * This method takes a string input, 
	 * @param word
	 * @return
	 */
    public int wordRank(String word){
        for(int i = 0; i < rankEntries.length; i++){
            if(((hashEntry) rankEntries[i]).getKey().equals(word))
            	return rankEntries.length - i;
        }
        return 0;
    }
    
    public String[] mostCommonWords(int k){
        if(k < 1)
        	return new String[1];
        String[] kMostCommonWords = new String[k];
        for(int i = 0; i < k; i++){
            kMostCommonWords[i] = ((hashEntry) rankEntries[rankEntries.length - i - 1]).getKey();
        }
        return kMostCommonWords;
    }
    
    public String[] leastCommonWords(int k){
        if(k < 1)
        	return new String[1];
        String[] kLeastCommonWords = new String[k];
        for(int i = 0; i < k; i++){
            kLeastCommonWords[i] = ((hashEntry) rankEntries[i]).getKey();
        }
        return kLeastCommonWords;
    }
    
}
