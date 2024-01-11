import static org.junit.Assert.*;

import org.junit.Test;

public class StringManipulationTest {
	
	@Test
	public void toPostfixTest(){
		assertEquals(StringManipulation.toPostfix("1+2"),"1 2 +");//test for addition
		assertEquals(StringManipulation.toPostfix("1+2*3-4/4"),"1 2 3 * + 4 4 /");//test for composite operation
		assertEquals(StringManipulation.toPostfix("12+34"),"12 34 +");//test for over 1-digit operand
		assertEquals(StringManipulation.toPostfix("(1+2)*3"),"1 2 + 3 *");//test for parenthesis in the beginning
		assertEquals(StringManipulation.toPostfix("1*(3+4)"),"1 3 4 + *");//test for parenthesis in the middle
		assertEquals(StringManipulation.toPostfix("(1+2)*(4-3)"),"1 2 + 4 3 - *");//test for more than 1 pair of parenthesis
		assertEquals(StringManipulation.toPostfix("(12+34)*(56+78)"),"12 34 + 56 78 + *");//test for composite expressions of more than 1 pair of parenthesis
	}
	
	@Test
	public void resultTest(){
		assertEquals(StringManipulation.result("1 1 +"),2);//test for addition of two numbers
		assertEquals(StringManipulation.result("11 1 -"),10);//test for subtraction of two numbers
		assertEquals(StringManipulation.result("2 3 *"),6);//test for multiplication of two numbers
		assertEquals(StringManipulation.result("6 3 /"),2);//test for division of two numbers
		assertEquals(StringManipulation.result("1 2 + 4 *"),12);//test for calculation of more than 2 numbers
		assertEquals(StringManipulation.result("3 20 + 6 -"),17);
		assertEquals(StringManipulation.result("12 34 + 56 78 + * 15 /)"),(12+34)*(56+78)/15);//test for calculations of numbers over 10
	}
	
	@Test
	public void smallestNumberTest(){
		assertEquals(StringManipulation.smallestNumber("700004", 1),"4");//test for having leading 0 after deletion
		assertEquals(StringManipulation.smallestNumber("1234567", 1),"123456");//test for normal cases
		assertEquals(StringManipulation.smallestNumber("700040", 2),"0");//test for having 0 at the end
		assertEquals(StringManipulation.smallestNumber("131313", 6),"");//test for n equals number of digit
		assertEquals(StringManipulation.smallestNumber("17390", 1),"1390");//test for deleting numbers in the middle once
		assertEquals(StringManipulation.smallestNumber("17390", 2),"130");//test for deleting numbers in the middle more than once
		assertEquals(StringManipulation.smallestNumber("32410", 3),"10");//test for deleting two consecutive numbers in the middle
	}
	
	@Test
	public void unbelievableStringTest(){
		assertEquals(StringManipulation.unbelievableString("abDDdddE"),"abdE");//test for conditions of more than once of recursion
		assertEquals(StringManipulation.unbelievableString("abB"),"a");//test for short strings
		assertEquals(StringManipulation.unbelievableString("abDDdddDDddDDddE"),"abdE");//test for conditions of more than once of recursion
		assertEquals(StringManipulation.unbelievableString("abDDdddDDddDDddE"),"abdE");//test for conditions of more than once of recursion
		assertEquals(StringManipulation.unbelievableString("aAbBcC"),"");//test for removing more than one pair at the same time
	}
}
