package hashing;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

public class TokenizerTest {

	@Test
	public void wordListTest() throws Exception {
		String[] testLine = {"This", "is", "an", "testLine", "that", "represent", "normailizing. ","Some ", " word", "wi!th", "punct.ations", "between."};
		String[] equalLine = {"this","is", "an", "testline", "that", "represent", "normailizing", "some", "word", "wi", "th", "punct","ations", "between"};
		Tokenizer testToken = new Tokenizer(testLine);
		ArrayList<String> temp = testToken.wordList();
		int iterations = 0;
		// here is a simple test representing the normalizing. 
		for (String string : temp) {
			assertEquals(string, equalLine[iterations]);
			iterations++;
		}
		
		Tokenizer testToken2 = new Tokenizer("String.txt");
		
		String[] equalLine2 = {"in", "computing", "a", "hash", "table", "also", "known", "as", 
								"hash", "map", "is", "a", "data", "structure", "that", "implements",
								"an", "associative", "array", "or", "dictionary"};
		ArrayList<String> temp2 = testToken2.wordList();
		int iterations2 = 0;
		for (String string : temp2){
			assertEquals(string, equalLine2[iterations2]);
			iterations2++;
		}
	}
}