import static org.junit.Assert.*;

import org.junit.Test;

public class LibraryDatabaseTest {

	@Test
	public void addTest(){
		LibraryDatabase library = new LibraryDatabase(2);
		Book a = new Book("Santi","0000000000001","Cixin Liu");
		Book[] list = {a,null};
		Book c = new Book("c","9999999999999","Dr. Foreback");
		Book[] list2 = {a,c};
		Book b = new Book("b","0000000000002","Eric Chen");
		Book[] list3 = {a,b,c};
		Book d = new Book("d","0000000000000","CWRU");
		library.add(a);
		Book[] list4 = {d,a,b,c};
		assertArrayEquals(library.toArray(),list);//test for size == 0
		library.add(c);
		assertArrayEquals(library.toArray(),list2);//test for adding to the last
		library.add(b);
		assertArrayEquals(library.toArray(),list3);//test for adding to the middle
		library.add(d);
		assertArrayEquals(library.toArray(),list4);// test for adding to the front
	}
	
	
	@Test
	public void removeTest(){
		LibraryDatabase library = new LibraryDatabase(2);
		Book a = new Book("Santi","0000000000001","Cixin Liu");
		Book c = new Book("c","9999999999999","Dr. Foreback");
		Book b = new Book("b","0000000000002","Eric Chen");
		Book d = new Book("d","0000000000000","CWRU");
		library.add(a);
		library.add(c);
		library.add(b);
		library.add(d);
		assertEquals(library.remove("0000000000000"),d);//test for remove from front
		Book[] list = {a,b,c,null};
		assertArrayEquals(library.toArray(),list);
		assertEquals(library.remove("9999999999999"),c);//test for remove from back
		Book[] list2 = {a,b,null,null};
		assertArrayEquals(library.toArray(),list2);
		library.add(d);
		assertEquals(library.remove("0000000000001"),a);//test for remove from middle
		Book[] list3 = {d,b,null,null};
		assertArrayEquals(library.toArray(),list3);
		library.remove(b.getISBN());
		library.remove(d.getISBN());
		Book[] list4 = {null,null,null,null};
		assertArrayEquals(library.toArray(),list4);
		assertEquals(library.remove(d.getISBN()),null);
	}
	
	
	@Test
	public void sizeTest(){
		LibraryDatabase library = new LibraryDatabase(2);
		Book a = new Book("Santi","0000000000001","Cixin Liu");
		Book c = new Book("c","9999999999999","Dr. Foreback");
		Book b = new Book("b","0000000000002","Eric Chen");
		assertEquals(library.size(),0);//test for empty list
		library.add(a);
		assertEquals(library.size(),1);//test for only one element in the list
		library.add(c);
		assertEquals(library.size(),2);//test for multiple elements in the list
		library.add(b);
		assertEquals(library.size(),3);//test for multiple elements in the list
		library.remove(b.getISBN());
		assertEquals(library.size(),2);//test for multiple elements after remove() in the list
		library.remove(c.getISBN());
		assertEquals(library.size(),1);//test for one element after remove() in the list
		library.remove(a.getISBN());
		assertEquals(library.size(),0);//test for empty list after remove() in the list
		
	}
	
	@Test
	public void randomSelectionTest(){
		LibraryDatabase library = new LibraryDatabase(2);
		Book a = new Book("Santi","0000000000001","Cixin Liu");
		assertEquals(library.randomSelection(),null);//test for empty library database case
		library.add(a);
		assertEquals(library.randomSelection(),a);//test for normal case
		
	}
	
	
	@Test
	public void findByTitleTest(){
		LibraryDatabase library = new LibraryDatabase(2);
		Book a = new Book("Santi","0000000000001","Cixin Liu");
		Book c = new Book("c","9999999999999","Dr. Foreback");
		Book b = new Book("b","0000000000002","Eric Chen");
		library.add(a);
		library.add(c);
		library.add(b);
		assertEquals(library.findByTitle("c"),c);//test for existed case
		assertEquals(library.findByTitle("d"),null);//test for book that do not in the list
		assertEquals(library.findByTitle(a.getTitle()),a);//test for existed case
		assertEquals(library.findByTitle(b.getTitle()),b);//test for existed case
	}
	
	
	@Test
	public void findByISBNTest(){
		LibraryDatabase library = new LibraryDatabase(2);
		Book a = new Book("Santi","0000000000001","Cixin Liu");
		Book c = new Book("c","9999999999999","Dr. Foreback");
		Book b = new Book("b","0000000000002","Eric Chen");
		Book d = new Book("d","0000000000000","CWRU");
		library.add(a);
		library.add(c);
		library.add(b);
		library.add(d);
		assertEquals(library.findByISBN(a.getISBN()),a);//test for books that exist in the database
		assertEquals(library.findByISBN(c.getISBN()),c);//test for books that exist in the database
		assertEquals(library.findByISBN(b.getISBN()),b);//test for books that exist in the database
		assertEquals(library.findByISBN("0000000000000"),d);//test for books that exist in the database
		assertEquals(library.findByISBN("0000000000004"),null);//test for null case(book does not exist in the database
	}
	
	@Test
	public void getAllByAuthorTest(){
		LibraryDatabase library = new LibraryDatabase(2);
		Book a = new Book("Santi","0000000000001","Cixin Liu");
		Book c = new Book("c","9999999999999","Dr. Foreback");
		Book b = new Book("b","0000000000002","Eric Chen");
		Book d = new Book("d","0000000000000","Eric Chen");
		library.add(a);
		library.add(b);
		library.add(c);
		library.add(d);
		Book[] list1 = {a};
		Book[] list2 = {c};
		Book[] list3 = {d,b};
		Book[] list4 = {};
		assertArrayEquals(library.getAllByAuthor("Cixin Liu"),list1);//normal test case
		assertArrayEquals(library.getAllByAuthor("Dr. Foreback"),list2);//normal test case
		assertArrayEquals(library.getAllByAuthor("Eric Chen"),list3);// test case for more than one book of the same author
		assertArrayEquals(library.getAllByAuthor("Zeyu Chen"),list4);//test case for no book of the input author
	}
	
	@Test
	public void removeDuplicatesTest(){
		LibraryDatabase library = new LibraryDatabase(2);
		Book a = new Book("Santi","0000000000001","Cixin Liu");
		Book c = new Book("c","9999999999999","Dr. Foreback");
		Book b = new Book("b","0000000000002","Eric Chen");
		Book d = new Book("d","0000000000000","Eric Chen");
		library.add(a);
		library.add(b);
		library.add(b);
		library.add(c);
		library.add(d);
		library.add(a);
		library.add(d);
		Book[] list = {d,a,b,c};
		library.removeDuplicates();
		assertArrayEquals(library.toArray(),list);//test for removing books in the list
		library.removeDuplicates();
		assertArrayEquals(library.toArray(),list);//test for no repeating books in the database
	}
	
	@Test
	public void toArrayTest(){
		LibraryDatabase library = new LibraryDatabase(2);
		Book a = new Book("Santi","0000000000001","Cixin Liu");
		Book[] list = {a,null};
		Book c = new Book("c","9999999999999","Dr. Foreback");
		Book[] list2 = {a,c};
		Book b = new Book("b","0000000000002","Eric Chen");
		Book[] list3 = {a,b,c};
		Book d = new Book("d","0000000000000","CWRU");
		library.add(a);
		Book[] list4 = {d,a,b,c};
		assertArrayEquals(library.toArray(),list);//test for adding to an empty database
		library.add(c);
		assertArrayEquals(library.toArray(),list2);//test for adding to the back
		library.add(b);
		assertArrayEquals(library.toArray(),list3);//test for adding to the middle
		library.add(d);
		assertArrayEquals(library.toArray(),list4);//test for adding to the front
	}
}
