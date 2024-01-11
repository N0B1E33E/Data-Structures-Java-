package extraCredit;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class PriorityQueue<K extends Comparable<? super K>,V> {
	 private List<Pair<K, V>> heap;

	 /**
	  * private nested class initializing Pair with two generic input
	  * @author Eric Chen
	  *
	  * @param <K> Generic Type inferring key.
	  * @param <V> Generic Type inferring value;
	  */
	    private static class Pair<K, V>{
	        private K key;
	        private V value;

	        /**
	         * constructor of the private nested class
	         * @param key
	         * @param value
	         */
	        private Pair(K key, V value){
	            this.key = key;
	            this.value = value;
	        }
	        
	        /**
	         * getter method for key
	         * @return key of this pair.
	         */
	        public K getKey(){
	            return key;
	        }
	        
	        /**
	         * getter method for value
	         * @return the value of the pair
	         */
	        public V getValue(){
	            return value;
	        }

	        /**
	         * setter method for key
	         * @param key new key needed to be assigned to the pair
	         */
	        public void setKey(K key){
	            this.key = key;
	        }
	    }
	    
	    /**
	     * Constructor that takes no input and initialized a heap of an ArrayList type
	     */
	    public PriorityQueue(){
	        heap = new ArrayList<>();
	    }

	    /**
	     * Constructor that takes two input and initialize a heap of an arrayList type
	     * @param key key of the new pair
	     * @param value value of the new pair
	     */
	    public PriorityQueue(K[] key, V[] value){
	        if(key.length != value.length){
	            throw new IllegalArgumentException("elements' lengths don't match");
	        }else{
	            heap = new ArrayList<>();
	            for(int i = 0; i < key.length; i++){
	                this.add(key[i], value[i]);
	            }
	        }
	    }

	    /**
	     * adder method that takes two input and add a new pair of input key and value
	     * to the heap ArrayList.
	     * @param key new key needed to be assigned to the new pair 
	     * @param value new value of the new pair needed to be added to the heap
	     */
	    public void add(K key, V value){
	        Pair<K, V> entry = new Pair<>(key, value);
	        heap.add(entry);

	        int index = heap.size() - 1;
	        int parent = (index - 1) / 2;

	        while (index > 0 && heap.get(index).key.compareTo(heap.get(parent).key) > 0) {
	            swap(index, parent);
	            index = parent;
	            parent = (index - 1) / 2;
	        }
	    }
	    
	    /**
	     * This method takes two integer input and swap these the two pair of the input value
	     * @param i value of key of one pair
	     * @param j value of key of the other pair
	     */
	    private void swap(int i, int j){
	        Pair<K, V> temp = heap.get(i);
	        heap.set(i, heap.get(j));
	        heap.set(j, temp);
	    }
	    
	    /**
	     * This method takes no input and return nothing.
	     * Print out the heap on the screen.
	     */
	    public void printString(){
	        for(int i = 0; i < heap.size() ; i++){
	            System.out.println(heap.get(i).getKey()+" "+heap.get(i).getValue());
	        }
	    }
	    
	    /**
	     * This method find a pair with the input value and then assign the input key
	     * to this pair.
	     * @param key new key assigned to the pair
	     * @param value searching key of the pair according to value
	     */
	    public void update(K key, V value){
	        for(int i = 0; i < heap.size() ; i++){
	            if(heap.get(i).getValue() == value){
	                int parentIndex = (i - 1) / 2;
	                heap.get(i).setKey(key);
	                while (i > 0 && heap.get(i).key.compareTo(heap.get(parentIndex).key) > 0) {
	                    swap(i, parentIndex);
	                    i = parentIndex;
	                    parentIndex = (i - 1) / 2;
	                }
	                break;
	            }
	        }
	        throw new NoSuchElementException("value not found");
	    }
	    
	    /**
	     * This method takes no input and return the max value in the heap.
	     * @return the max value in the heap.
	     */
	    public V peek(){
	        return heap.get(0).getValue();
	    }

	    /**
	     * This method takes an integer input and return the first k largest value in the heap.
	     * @param k first k number of values needed to be returned
	     * @return the first k maximum value in the heap
	     */
	    public V[] peek(int k){
	        ArrayList<V> list = new ArrayList<V>();
	        for(int i = 0; i < k ; i++){
	            list.add(heap.get(i).getValue());
	        }
	        return (V[]) list.toArray();
	    }
	    
	    /**
	     * Remove the root of the heap and return the value of the root.
	     * @return the value of the root removed
	     */
	    public V poll(){
	        if (heap.isEmpty()){
	            return null;
	        }

	        Pair<K, V> max = heap.get(0);
	        heap.set(0, heap.get(heap.size() - 1));
	        heap.remove(heap.size() - 1);

	        int index = 0;

	        while(true){
	            int left = 2 * index + 1;
	            int right = 2 * index + 2;

	            if (left >= heap.size()) {
	                break;
	            }

	            int maxChild = left;

	            if(right < heap.size() && heap.get(right).key.compareTo(heap.get(left).key) > 0) {
	                maxChild = right;
	            }

	            if(heap.get(index).key.compareTo(heap.get(maxChild).key) < 0) {
	                swap(index, maxChild);
	                index = maxChild;
	            } else {
	                break;
	            }
	        }

	        return max.value;
	    }
	    
	    /**
	     * This method takes one V input as value, find the pair with input value and then
	     * remove the pair.
	     * @param value searching key according to the value input.
	     * @return the key of the pair with input value.
	     */
	    public K poll(V value) {
	        for (int i = 0; i < heap.size(); i++) {
	            Pair<K, V> pair = heap.get(i);
	            if (pair.value.equals(value)) {
	                K key = pair.key;
	                heap.set(i, heap.get(heap.size() - 1));
	                heap.remove(heap.size() - 1);
	                for (int j = (heap.size() - 2) / 2; j >= 0; j--) {
	                    heapifyDown(j);
	                }
	                return key;
	            }
	        }
	        throw new NoSuchElementException();
	    }
	    
	    /**
	     * This is a private helper method that is a implementation of heapify algorithm
	     * which is basically one heap sort method. It compares the left and right children
	     * of the parent and swap the parent with the child that is the greatest and the child
	     * must be greater than the parent.
	     * @param i index of the parent.
	     */
	    private void heapifyDown(int i) {
	        while (2 * i + 1 < heap.size()) {
	            int j = 2 * i + 1;
	            if (j + 1 < heap.size() && heap.get(j + 1).key.compareTo(heap.get(j).key) > 0) {
	                j++;
	            }
	            if (heap.get(i).key.compareTo(heap.get(j).key) < 0) {
	                swap(i, j);
	                i = j;
	            } else {
	                break;
	            }
	        }
	    }

	    /**
	     * This method have an integer return which returns the size of the heap.
	     * @return size of the heap.
	     */
	    public int size(){
	        return heap.size();
	    }
	}