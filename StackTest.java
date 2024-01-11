import static org.junit.Assert.*;

import org.junit.Test;

public class StackTest {

	@Test
	public void pushTest() {
		Stack<String> stack = new Stack<String>();
		assertEquals(stack.push("1"),true);//pushing into an empty stack
		assertEquals(stack.peek(),"1");
		assertTrue(stack.push("2"));//test for normal cases
		assertEquals(stack.peek(),"2");
		assertTrue(stack.push("3"));//test for normal cases
	}
	
	@Test
	public void popTest(){
		Stack<String> stack = new Stack<String>();
		assertEquals(stack.pop(),null); //test for pop() for an empty stack
		stack.push("Eric Chen");
		assertEquals(stack.pop(),"Eric Chen");// test for pop() the only element in the stack
		stack.push("Eric Chen");
		stack.push("Dr. Foreback");
		assertEquals(stack.pop(), "Dr. Foreback");//test for pop() multiple elements in the stack
		assertEquals(stack.pop(),"Eric Chen"); 
		assertEquals(stack.pop(),null);//test for pop() for an empty stack
		
	}
	
	@Test
	public void peekTest(){
		Stack<String> stack = new Stack<String>();
		assertEquals(stack.peek(),null); //test for pop() for an empty stack
		stack.push("Eric Chen");
		assertEquals(stack.peek(),"Eric Chen");// test for peek() the only element in the stack
		stack.push("Dr. Foreback");
		assertEquals(stack.peek(), "Dr. Foreback");//test for pop() multiple elements in the stack
		stack.pop();
		assertEquals(stack.peek(),"Eric Chen"); 
		stack.pop();
		assertEquals(stack.peek(),null);//test for peek() for an empty stack
	}

}
