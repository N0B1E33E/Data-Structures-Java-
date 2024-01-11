/**
 * 
 * @author 18092
 *
 * @param <T>
 */
public class Stack<T extends Comparable<? super T>> {
	
	private Node<T> top;
	
	
	public Stack(){
		top = null;
	}
	
	/**
	 * This is an inner private class that works as a Node setup.
	 * have basic functions like
	 * getElement(), getNext(), and hasNext()
	 * @author Eric Chen
	 *
	 * @param <T>generic type of T
	 */
	private class Node<T>{
		T element;
		Node<T> next;
		private Node(T element, Node<T> next){
			this.element = element;
			this.next = next;
		}
		
		/**
		 * this method returns the element value of the node
		 * @return the element value of the node with type T
		 */
		private T getElement(){
			return this.element;
		}
		
		/**
		 * this method returns the next node
		 * @return the next node with type Node<T>
		 */
		private Node<T> getNext(){
			return this.next;
		}
		
		/**
		 * this method checks if the current Node has a next node
		 * @return true if it has a next node,
		 * false otherwise
		 */
		private boolean hasNext(){
			return next != null;
		}
	}
	
	/**
	 * this method takes no input and returns the element that is current at the top of the stack
	 * @return the top element in the stack
	 */
	private Node<T> getTop(){
		return this.top;
	}
	
	/**
	 * this method takes one input in generic type T and returns a boolean value
	 * insert a linked list node with input value to the top of the stack
	 * @param value the value of the node that needed to be added to the top of the stack
	 * @return true if succeeded in adding this input value, otherwise false
	 */
	public boolean push(T value){
		//the stack is empty
		if(this.getTop() == null)
			top = new Node<T>(value,null);
		//the stack is not empty
		else
		{
			top = new Node<T>(value,top);
		}
		return true;
	}
	
	/**
	 * this method takes no input value and delete the top element in the stack
	 * @return the value of the element removed
	 */
	public T pop(){
		//the stack is not empty
		if(this.getTop() != null){
			T temp = top.getElement();
			top = top.getNext();
			return temp;
		}
		//the stack is empty
		else
			return null;
	}
	
	/**
	 * this method takes no input value and returns the value of the top element 
	 * in the stack
	 * @return the value of the element at the top of the stack
	 */
	public T peek(){
		//the stack is not empty
		if(this.getTop() != null){
			return top.getElement();
		}
		//the stack is empty
		else
			return null;
	}
	
}
