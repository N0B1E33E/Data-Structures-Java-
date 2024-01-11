package hashing;
import org.junit.Test;

import static org.junit.Assert.*;


public class WordStatTest {
	/**
	 * The sample text I have used in this class is, 
	 * This is an testLine with WORD that represent normailizing. the the the Some   word wi!th punct.ations betweens.
	 * As you can see, there are 3 'the' and 2 'word' in the sample text. 
	 * There are 2 'the the', and 1 'it was', as shown in testWordPairCount(). 
	 * 
	 */
	@Test
	public void testWordCount() throws Exception {
		WordStat demoWordStat = new WordStat(new String[] {"This", "is", "an", "testLine", "with", "WORD", "that", "represent", "normailizing. ", "the", "the", "the" ,"Some ", " word", "wi!th", "punct.ations", "between."});
		Integer countOfThe = demoWordStat.wordCount("the");
		// there should be 3 'the' in the sample line. 
		assertTrue(countOfThe.equals(3));
		// there should be 0 'aaaa' in the sample text. 
		Integer countOfUnexist = demoWordStat.wordCount("aaaa");
		assertTrue(countOfUnexist.equals(0));
	}

	@Test
	public void testWordRank() throws Exception {
		WordStat demoWordStat = new WordStat(new String[] {"This", "is", "an", "testLine", "with", "WORD", "that", "represent", "normailizing. ", "the", "the", "the" ,"Some ", " word", "wi!th", "punct.ations", "between."});		
		// there are 3 'the'. As the first place of the word freqency
		Integer countOfThe = demoWordStat.wordRank("the");
		assertTrue(countOfThe.equals(1));
		// there are 2 'word'. As the second place of the word freqency
		Integer countOfWord = demoWordStat.wordRank("word");
		assertTrue(countOfWord.equals(2));
		// test for unexisted word
		Integer countOfAAAA = demoWordStat.wordRank("aaaa");
		assertTrue(countOfAAAA.equals(0));
	}

	@Test
	public void testMostCommonWords() throws Exception {
		WordStat demoWordStat = new WordStat(new String[] {"This", "is", "an", "testLine","with", "WORD", "that", "represent", "normailizing. ", "the", "the", "the" ,"Some ", " word", "wi!th", "punct.ations", "between."});		
		// most common word should be "the"
		String[] MostCommonWord1 = demoWordStat.mostCommonWords(1);
		assertEquals("the", MostCommonWord1[0]);
		// most two common words should be "the" and "word".
		String[] MostCommonWord2 = demoWordStat.mostCommonWords(2);
		assertEquals("the", MostCommonWord1[0]);
		assertEquals("word", MostCommonWord2[1]);
		// unreasonable input should get an empty object to avoid NullPointerException.
		String[] MostCommonWord0 = demoWordStat.mostCommonWords(-1);
		assertEquals(null, MostCommonWord0[0]);
	}

	@Test
	public void testLeastCommonWords() throws Exception {
		WordStat demoWordStat = new WordStat(new String[] {"This", "is", "an", "testLine","with", "WORD", "that", "represent", "normailizing. ", "the", "the", "the" ,"Some ", " word", "wi!th", "punct.ations", "between."});		
		// the least Common Word should be "th" 
		String[] leastCommonWord1 = demoWordStat.leastCommonWords(1);
		assertEquals("th", leastCommonWord1[0]);
		// the second least common word should be "some".
		String[] leastCommonWord2 = demoWordStat.leastCommonWords(2);
		assertEquals("some", leastCommonWord2[1]);
		// unreasonable input should get an empty object to avoid NullPointerException
		String[] leastCommonWord0 = demoWordStat.leastCommonWords(-1);
		assertEquals(null, leastCommonWord0[0]);
	}


}