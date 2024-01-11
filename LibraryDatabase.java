/**
 * initialize a library database for books created in the Book class
 * 
 */

/**
 * this is a class that represent a basic library database
 * have basic functions(add, remove)
 * can get access to the book regarding to author, ISBN or title
 * can remove duplicate books
 * @author Eric Chen
 *
 */
public class LibraryDatabase {

	/**
	 * @param size indicates the number of books stored in the library database
	 * initialized to be 0(empty database)
	 * @param list the list stores all the book(serves as the database) 
	 * initialized to be empty(param capacity needed)
	 */
	private int size = 0;
	Book [] list;
	/**
	 * constructor that takes one input(capacity) that specifies the maximum number of books that can be stored in the database
	 * @param capacity indicate the largest number of books that can stored in this database
	 * throw IllegalArgumentException if input is negative
	 * assign the capacity to the list
	 */
	
	public LibraryDatabase(int capacity){
		if(capacity < 0){
			throw new IllegalArgumentException();
		}
		else{
			list = new Book[capacity];
		}
	}
	

	/**
	 * takes one input, find the proper location for the book to be either added or removed
	 * @param book input book with given ISBN
	 * using sorted ISBN to find location of the book
	 * @return ptr pointer point to location of this book
	 */
	private int locate(Book book){
		int ptr = 0;
		for(int i = 0; i < list.length - 1; i ++){
			//find matched ISBN of the book
			if(Long.parseLong(book.getISBN()) > 
			Long.parseLong(list[ptr].getISBN())){
				ptr ++;
			}
		}
		return ptr;
	}
	/**
	 * this method has no return values
	 * using a private helper method to find the place where the input book should be inserted
	 * add the input book to the database
	 * @param book book needed to be added to the database
	 * temp temporary list that have one more space as capacity
	 * when there is no space for new books, resize the database, then add the book
	 */
	public void add(Book book){
		//size reaches maximum
		//need to resize the list
		if(this.size() == list.length){
			Book[] temp = this.copyOf(list,list.length + 1);
			list = temp;
		}
		//empty database
		if(this.size() == 0){
			list[0] = book;
		}else{
			//shift books after the insert position one backward
			for(int i = list.length - 1; i > this.locate(book); i--){
				list[i] = list[i - 1];
		}
			//insert the book
		list[this.locate(book)] = book;
		}
		size++;
	}
	
	/**
	 * this method takes one string input(isbn) which stands for the tag of the book
	 * remove the book with the input value from the database and return the book removed
	 * if there is no book has the same isbn as the input value, return null
	 * @param isbn input String that stands for the isbn of a book
	 * @return bookRemoved the book removed from the library database, return null if the book is not in the database
	 */
	public Book remove(String isbn){
		Integer ptr = null;
		Book bookRemoved = null;
		//iterate the list
		for(int i = 0; i < this.size(); i++){
			//find matched ISBN
			if(Long.parseLong(list[i].getISBN()) == Long.parseLong(isbn)){
				//reference of position of the removed book
				ptr = i;
				//store the return value
				bookRemoved = list[i];
			}
		}
		//the book does not exist in the database
		if(ptr == null)
			return null;
		//the book exist
		else{
			//shift all element to the left of the book removed one forward
			for(int i = ptr; i < this.size() - 1; i++){
				list[i] = list[i + 1];
			}
			//emptied the last element in the database
			list[this.size() - 1] = null;
			size--;
			return bookRemoved;
		}
	}
	
	/**
	 * this method provides the current number of books stored in the library database
	 * @return the number of books in the library database
	 */
	public int size(){
		return this.size;
	}
	
	/**
	 * return a random book the library database
	 * return null if the library database is empty
	 * @return return a random book from the list
	 * return null if the library database is empty
	 */
	public Book randomSelection(){
		if(this.size() == 0)
			return null;
		else
			//randomize using Math.random()
			return list[(int) (Math.random() * this.size())];
	}
	
	/**
	 * this method takes one String input(title) that indicates the name of the book needed to be found
	 * return the book if matched title found in the database
	 * return null otherwise
	 * @param title String input that indicates the name of the book needed to be found
	 * @return the book found in the library database
	 * return null if the title doesn't match any titles of the books in the library database
	 */
	public Book findByTitle(String title){
		// iterate the whole list to find a book that has matched title
		for(int i = 0; i < this.size(); i ++){
			//find matched titles
			if(list[i].getTitle().equals(title))
				return list[i];
		}
		return null;
	}
	
	/**
	 * use a recursive private helper method to use binary search to find the book faster with O(logN)
	 * return null if there is no book has a matched ISBN
	 * @param isbn input String variable that indicates the tag of the book needed to be found in the library database
	 * @return the book found in the database
	 * return null if there is no book has a matched ISBN
	 */
	public Book findByISBN(String isbn){
		return this.binarySearch(isbn,0,this.size -1);
	}
	
	/**
	 * helper method that uses binary search to help better search for a book in a sorted library database with O(logN)
	 * @param isbn input String variable, indicates the tag of the book needed to be found in the library database
	 * @param front defines as the first book in the library database
	 * @param back defines as the last book in the library database 
	 * @return if isbn matches, return the book with the matched isbn
	 * otherwise recursively decreasing the range of the library database(binary search)
	 */
	private Book binarySearch(String isbn, int front, int back){
		if(front > back)
			return null;
		else{
			int mid = (front + back)/2;
			//the book is in the middle
			if(Long.parseLong(list[mid].getISBN()) ==
					Long.parseLong(isbn))
				return list[mid];
			//the book is to the right of middle
			else if(Long.parseLong(list[mid].getISBN()) < 
					Long.parseLong(isbn))
				return binarySearch(isbn, mid + 1, back);
			//the book is to the left of middle
			else
				return binarySearch(isbn, front, mid - 1);
		}
	}
	
	
	/**
	 * this method takes one string input and returns an array
	 * iterate the all library database to find all books with the same author
	 * use a pointer(ptr) to indicates the position of a book that is added next in the list of bibliography
	 * @param author String input that indicates the writer of books we want to find in the library database
	 * @return returns all books by the same author in the library database
	 */
	public Book[] getAllByAuthor(String author){
		int ptr = 0;//pointer that helps locate where to add the next book of this author to in the new array
		Book[] bibliography = new Book[this.size()];
		for(int i = 0; i < this.size(); i ++){//traverse the whole database
			//check if the author matches
			if(list[i].getAuthor().equals(author)){
				bibliography[ptr] = list[i];
				ptr++;
			}
		}
		return this.copyOf(bibliography, ptr);//resize the array so that there is no null existed in the array
	}
	
	/**
	 * this method takes no input and returns nothing
	 * remove all the books with the same ISBN but the one added the earliest
	 */
	public void removeDuplicates(){
		int ptr = 1;
		Book[] temp = new Book[this.size()];
		temp[0] = list[0];
		for(int i = 1; i < this.size(); i++){//iterate the library database
			if(!list[i].getISBN().equals(list[i-1].getISBN())){//add to temp[] if the two books are different
				temp[ptr] = list[i];
				ptr++;//point to the next position added in the temp[]
			}
		}
		this.size = ptr;
		list = this.copyOf(temp, ptr);
	}
	
	/**
	 * takes no input and returns an array of type Book[] that stands for the entire library database
	 * @return the array that represent the library database
	 */
	public Book[] toArray(){
		return list;
	}
	
	/**
	 * this private method takes one Book[] input and a int input to copy the array input with the int input length
	 * @param arr input array that indicates the array that needed to be copied
	 * @param length new length of the array copied
	 * @return the new array with input length of the input array
	 */
	private Book[] copyOf(Book[] arr, int length){
		Book[] temp = new Book[length];
		//iterate and copy all element within certain length to temp[]
		for(int i = 0; i < length; i++){
			if(i < arr.length)
				temp[i] = arr[i];
			else
				temp[i] = null;
		}
		return temp;
	}
}
