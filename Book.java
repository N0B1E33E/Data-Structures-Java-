
public class Book {


	private String title;
	private String isbn;
	private String author;
	
	/**
	 * constructor that initials the basic information of the book
	 * including title, isbn and author
	 * the isbn argument must be 13 numeric characters
	 * @param title the name of the book
	 * @param isbn International Standard Book Number, used to located and sort the books
	 * throws an Illegal Argument Exception if ISBN is not 13 numeric characters.
	 * @param author writer of the book
	 */
	public Book(String title, String isbn, String author){
		this.title = title;
		//error in length or content of isbn
		if(isbn.length() != 13 || !isbn.matches("[0-9]+")){
			throw new IllegalArgumentException();
		}
		else{
			this.isbn = isbn;
		}
		this.author = author;
	}
	
	
	/**
	 * this method takes no input
	 * returns the title of the book created as a string
	 * @return return the title of the book
	 */
	public String getTitle(){
		return this.title;
	}
	
	/**
	 * this method takes no input
	 * return the numeric tag of the book as a string
	 * @return return the International Standard Book Number
	 */
	public String getISBN(){
		return this.isbn;
	}
	
	/**
	 * this method takes no input
	 * returns the name of the author as a string
	 * @return return the name of the author
	 */
	public String getAuthor(){
		return this.author;
	}
	
}
