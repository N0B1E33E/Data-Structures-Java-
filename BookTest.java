import static org.junit.Assert.*;

import org.junit.Test;

public class BookTest {
	
	
	@Test
	public void getTitleTest(){
		Book ThreeBody = new Book("Santi", "0000000000001","Liu Cixin");
		Book DNE = new Book("", "0000000000002","");
		Book a = new Book("123","0000000000003","");
		assertEquals(ThreeBody.getTitle(),"Santi");//check for non-null condition
		assertEquals(DNE.getTitle(),"");//check for empty string condition
		assertEquals(a.getTitle(),"123");//check for numeric string condition
	}
	

	@Test
	public void getISBNTest(){
		boolean exceptionExist = false;
		Book ThreeBody = new Book("Santi", "0000000000001", "Cixin Liu");
		assertEquals(ThreeBody.getISBN(),"0000000000001"); //check for normal condition
		try{
			new Book("", "", "");//check for null ISBN
		} catch(IllegalArgumentException e){
			exceptionExist = true;
		}
		assertTrue(exceptionExist);
		exceptionExist = false;
		try{
			new Book("","aaaaaaaaaaaaaa","");//check for alphabetical string condition 
		} catch(IllegalArgumentException e){
			exceptionExist = true;
		}
		assertTrue(exceptionExist);
	}
	
	
	@Test
	public void getAuthorTest(){
		Book ThreeBody = new Book("Santi", "0000000000001","Liu Cixin");
		Book DNE = new Book("", "0000000000002","");
		Book a = new Book("123","0000000000003","321");
		assertEquals(ThreeBody.getAuthor(),"Liu Cixin");//check for normal condition
		assertEquals(DNE.getAuthor(),"");//check for empty author condition
		assertEquals(a.getAuthor(),"321");//check for numeric condition
	}
	
	

}
