package hashing;

import static org.junit.Assert.*;
import org.junit.Test;

import java.awt.List;
import java.util.NoSuchElementException;
import org.junit.Before;
public class HashTableTest {
	 private HashTable<Integer> table;
	    @Before
	    public void setUp() throws Exception {
	        table = new HashTable<Integer>();
	    }

	
	  @Test
	    public void testPutAndGet() {
		  String key = "A";
		  	table.put(key, 1);
		  	table.put("B", 2);
		  	table.put("C", 3);
		  	assertEquals(1, (int) table.get("A"));
		  	assertEquals(2, (int) table.get("B"));
	      	assertEquals(3, (int) table.get("C"));
	      	table.put(key, 999);
	      	for(int i = 0; i <= 100; i ++){
	      		table.put(Integer.toString(i), 1);
	      	}
	      	assertEquals(1, (int)table.get("100"));
	    }
	    
	    @Test(expected = NoSuchElementException.class)
	    public void testGetNoSuchElementException() {
	        table.get("null");
	    }
	    
	    
	    @Test(expected = NoSuchElementException.class)
	    public void testRemove() {
	        table.put("A", 1);
	        table.put("B", 2);
	        table.put("C", 3);
	        table.remove("A");
	        assertEquals(2, table.Size());
	        table.get("A");
	        table.get("Eric Chen");
	    }
	    
	    @Test
	    public void testSize(){
	    	assertEquals(0, table.Size());
	    	table.put("A", 1);
	    	assertEquals(1, table.Size());
	    	table.put("B", 2);
	    	assertEquals(2, table.Size());
	    	for(int i = 0; i < 100; i ++){
	    		table.put(Integer.toString(i), i);
	    	}
	    	assertEquals(102, table.Size());
	    }
}
