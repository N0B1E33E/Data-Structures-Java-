package hashing;
import java.util.NoSuchElementException;
/**
 * 
 * @author Eric Chen
 *A class that initialize a hash table with basic functions including get(), put() and other functions.
 * @param <T> Generic type that infers the type of the value in the hash table
 */
public class HashTable<T>{

	/**
	 * 
	 * @author Eric Chen
	 *public nested class that initialize a hash entry for 
	 * @param <Q> Generic type that infers the value of the hash entry
	 */
	public class hashEntry<Q>{
		
		private String key;
		private Q value;
		private boolean isDeleted;
		
		/**
		 * constructor that have two input to initialize the entry with given input
		 * @param key key of the input
		 * @param value value of the input
		 */
		private hashEntry(String key, Q value){
			this.key = key;
			this.value = value;
			isDeleted = false;
		}
		
		/**
		 * getter method for key
		 * @return the key of the entry
		 */
		public String getKey(){
			return this.key;
		}
		
		/**
		 * setter method for key
		 * @param key new value of the key
		 * replace the old one
		 */
		private void setKey(String key){
			this.key = key;
		}
		
		/**
		 * getter method for value of the entry
		 * @return the value of the entry
		 */
		public Q getValue(){
			return this.value;
		}
		
		/**
		 * setter method for value
		 * @param value new value assigned to the entry
		 */
		private void setValue(Q value){
			this.value = value;
		}
		
		/**
		 * This method delete this entry, by setting the 
		 * field(isDeleted) to be true
		 */
		private void delete(){
			isDeleted = true;
		}
	}
	
	
	
	protected hashEntry<T>[] table;
	private int size;
	private int elements;
	
	/**
	 * Constructor that has no input,
	 * create a new empty hash table if no input is passed in
	 */
	public HashTable(){
		table = new hashEntry[59];
		this.size = 59;
	}
	/**
	 * Constructor that takes one integer input size
	 * @param size the size of hash table needed to be created
	 */
	public HashTable(int size){
		table = new hashEntry[size];
		this.size = size;
	}
	
	/**
	 * This method takes one String input as the key of the entry in the table and 
	 * return the value of the entry of this key.
	 * Will throw a NoSuchElementException if the key is not in the table.
	 * @param key searching key for the entry
	 * @return the value off the entry found in the table with the given key
	 */
	public T get(String key){
		int position = findKey(key);
		if(position != -1 && table[position].isDeleted == false)
			return table[position].getValue();
		else 
			throw new NoSuchElementException();
	}
	
	
	/**
	 * private helper method, takes one String input as the key of the entry and find(locate)
	 * the key in the table. Return -1 if is not found in the table.
	 * @param key searching key for the entry.
	 * @return the position of the key found.
	 */
	private int findKey(String key){
		int position = Math.abs(key.hashCode()) % size;
		int secondHashCode = 1;
        int iterations = 0;
        while(table[position] != null){
            if(table[position].getKey().equals(key)){
                return position;
            }
            position = (position + secondHashCode * secondHashCode) % size;
            iterations++;
            if(iterations > size) 
            	return -1;
        }
        return -1;
    }
	
	
	/**
	 * This method takes one String input and a T input, using a private helper method of 
	 * quadratic probing to insert the new entry to the table in a proper location.
	 * @param key key of the inserted entry.
	 * @param value Value of the inserted entry.
	 */
	public void put(String key, T value){
		 hashEntry<T> temp = new hashEntry<T>(key, value);
	        int position = Math.abs(key.hashCode())%size;
	        if(table[position] == null){
	            table[position] = temp;
	        }
	        else{
	            position = quadraticProbing(position, key);
	            table[position] = temp;
	        }
	        elements++;
	        reSize();
    }
	
	/**
	 * private helper method that returns nothing.
	 * ReSize the hash table when load factor reaches 0.5 by enlarge the table to
	 * doubling size.
	 */
	 private void reSize(){
	        double loadFactor = (double) elements / size;
	        if(loadFactor > 0.5){
	            hashEntry<T>[] hashTableNew = new hashEntry[2 * size];
	            hashEntry<T>[] temp = table;
	            table = hashTableNew;
	            this.elements = 0;
	            size = size * 2;
	            for(int i = 0; i < temp.length; i++){
	                if(temp[i] != null && !temp[i].isDeleted){
	                    this.put(temp[i].getKey(), temp[i].getValue());
	                }
	            }
	            this.table = hashTableNew;
	        }
	    }
	
	 /**
	  * private helper method that implements a quadratic probing.
	  * @param position position of the current entry
	  * @param key key of the current entry
	  * @return the position where the new entry can be successfully put in the table.
	  */
	 private int quadraticProbing(int position, String key){
	        int positionF = Math.abs(key.hashCode())%size;
	        int secondHashCode = 1;
	        int iterations = 0;
	        while(table[positionF] != null){
	            positionF = (positionF + secondHashCode * secondHashCode) % size;
	            iterations++;
	            if(iterations > size) return -1;
	        }
	        return positionF;
	    }

	 /**
	  * This method takes one String input, remove the entry with the given input
	  * @param key Searching key for the entry needed to be deleted.
	  */
	public void remove(String key){
		int i = this.findKey(key);
		table[i].delete(); 
		this.elements--;
	}
	
	/**
	 * This method takes no input and has a integer return.
	 * Returns the number of elements in the table.
	 * @return The number of elements in the table.
	 */
	public int Size(){
		return this.elements;
	}
	
	/**
	 * private helper method that takes two input.
	 * If the input key is in the table, update the new value to this element.
	 * @param key Searching key of the element.
	 * @param value New value assigned to the element.
	 */
	public void update(String key, T value){
        int i = findKey(key);
        if(i != -1) {
            table[i].setValue(value);
        }
    }
}
