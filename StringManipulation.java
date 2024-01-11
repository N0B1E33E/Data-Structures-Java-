/**
 * This class is a String Manipulation class.
 * Contains methods that can transfer a proper infix expression to a postfix expression, 
 * calculate the value of a proper postfix expression,
 * 
 * @author Eric Chen
 *
 */
public class StringManipulation {
	
	/**
	 * 
	 * @param infix
	 * @return
	 */
	public static String toPostfix(String infix){
		StringBuilder postfix = new StringBuilder();
		StringBuilder num = new StringBuilder();
		StringBuilder temp = new StringBuilder();
		Stack<Character> stack = new Stack<Character>();
		char operator = 1;
		for(int i = 0; i < infix.length(); i++){
			char c = infix.charAt(i);
			//if a number is read
			if(Character.isDigit(c))
				num.append(c);
			//if a left parenthesis "(" is read
			else if(c == '('){
				int count = i + 1;
				//create a temporary stringBuilder that 
				//contains the infix expression within the parenthesis
				while(infix.charAt(count) != ')'){
					temp.append(infix.charAt(count));
					count ++;
				}
				//use a recursive method
				//change the expression within the parenthesis to a postfix expression
				//then append it to the string that is returned by this method
				postfix.append(toPostfix(temp.toString()) + " ");
				temp.setLength(0);
				//jump to the end of the parenthesis
				i = count;
			}
			else{
				if(!num.toString().equals("")){
					postfix.append(num + " ");
					num.setLength(0);
				}
				//first operator encountered in the string
				if(operator == 1){
					stack.push(c);
					operator = c;
				}
				else{
					//if * and / are read
					if(c == '*' || c == '/'){
						if(operator == 43 || operator == 45){
							stack.push(c);
							operator = c;
						}
						else{
							while(stack.peek() != null)
							postfix.append(stack.pop() + " ");
							stack.push(c);
							operator = c;
						}
					}
					else if(c == '+' || c == '-'){
						if(operator == 42 || operator == 47){
							while(stack.peek()!= null)
							postfix.append(stack.pop() + " ");
							stack.push(c);
							operator = c;
						}
						else{
							stack.push(c);
							operator = c;
						}
					}
				
				}
			}
		}
		if(!num.toString().equals("")){
			postfix.append(num + " ");
			num.setLength(0);
		}
		if(stack.peek() != null)
			postfix.append(stack.pop() + " ");
		postfix.delete(postfix.length() - 1, postfix.length());
		return postfix.toString();
	}
	
	/**
	 * this method takes one string input which is a proper postfix expression,
	 * calculate the result and return the result
	 * @param postfix a proper postfix expression contains numbers, spaces and operators
	 * @return the value calculated from the input postfix expression
	 */
	public static int result(String postfix){
		StringBuilder num = new StringBuilder();
		Stack<Integer> stack = new Stack<Integer>();
		int num1,num2;
		//traverse through the whole string to read the full postfix expression
		for(int i = 0; i < postfix.length(); i++){
			char c = postfix.charAt(i);
			//store the number temporarily in a stringBuilder
			//dealing with case of more than 1 digit number
			//number will stored in the stringBuilder instead of being pushed in to the stack
			if(Character.isDigit(postfix.charAt(i))){
				num.append(c);
			}
			//push the number temporarily stored in the stringBuilder into the stack
			//do nothing if there is nothing stored in the stringBuilder
			else if(c == ' ' && !num.toString().equals("")){
				stack.push(Integer.parseInt(num.toString()));
				num.delete(0,num.length());
			}
			//read operators and do the operations
			else if(c == '+' || c == '-' || c == '*' || c == '/'){
				num2 = stack.pop();
				num1 = stack.pop();
				//4 cases of operations +, -, * and /
				switch(c){
					case '+':
						stack.push(num1 + num2);
						break;
					case '-':
						stack.push(num1 - num2);
						break;
					case '*':
						stack.push(num1 * num2);
						break;
					case '/':
						stack.push(num1 / num2);
						break;
				}
			}
			
		}
		return stack.pop();
	}
	
	/**
	 * this method takes a numeric string input number and a integer input n,
	 * delete n numbers in the string input number to find the smallest number,
	 * delete all the leading zeros if existed.
	 * the numbers to the left are more significant to the value of the whole number.
	 * For example if the second digit is smaller than the first digit in the string and we want to delete one digit,
	 * we need to delete the first one since we want a smaller number at the same level of significance.
	 * For example, for the input string 132, and we want to delete one digit to obtain the smallest number,
	 * the most significant number will be k * 10^1 where k could be any integer among 1,3 and 2.
	 * The number after deleting would be a two-digit number, so we could only compare 1 and 3.
	 * Since 1 is smaller than 3, so we would choose to keep one and delete 3.
	 * return the final number as a string
	 * @param number a String input, the string off numbers that needed to be deleted
	 * @param n the number of digit that needed to be deleted
	 * @return the numeric string that does not have leading zeros after deleting n digits
	 */
	public static String smallestNumber(String number, int n){
		Stack<Character> stack = new Stack<Character>();
		Stack<Character> reversedStack = new Stack<Character>();
		StringBuilder newNumber = new StringBuilder();
		int count = 0;
		stack.push(number.charAt(0));
		//check if the number n input is proper
		//if n is larger than the length of the input string
		//throw StringIndexOutOfBoundsException
		if(n > number.length())
			throw new StringIndexOutOfBoundsException();
		//return an empty string if all numbers are deleted(n = length)
		else if(n == number.length())
			return newNumber.toString();
		//traverse the whole string
		for(int i = 1; i < number.length(); i++){
			//if the previous digit read and stored in the stack is smaller than or equal to the current digit read
			if(stack.peek() <= number.charAt(i)){
				//push the current digit into the stack
				stack.push(number.charAt(i));
			}
			//if the previous digit read and stored in the stack is larger than the current digit read
			//meaning it's needed to be deleted
			else{
				//loops for conditions where the current read is still larger than even more previous numbers in the stack
				while(stack.peek() != null && stack.peek() > number.charAt(i)){
					stack.pop();
					//used to count the number of deletion made
					count ++;
					//enough number of deletion made in the previous loop
					if(count == n)
						//jump off the loop
						break;
				}
				//push the number into the stack
				stack.push(number.charAt(i));
			}
			//enough number of deletion made before reaches the end of the string
			if(count == n){
				//traverse till the end of the string
				//push all the rest digit to the stack
				for(int j = i + 1; j < number.length(); j++){
					stack.push(number.charAt(j));
				}
				break;
			}
			
		}
		//when reaches the end of the string and still need to complete more deletion
		while(count < n){
			//delete from end
			stack.pop();
			count ++;
		}
		//a stack is a FILO(first in last out) data type, so it would be reversed after popping every digit out
		while(stack.peek() != null)
			//store digit in a proper order in another stack
			reversedStack.push(stack.pop());
		//pop all the digit in a proper order and append it to the stringBuilder
		while(reversedStack.peek() != null)
			newNumber.append(reversedStack.pop());
		//deleting leading zeros but keep one if it is the only number left
		while(newNumber.charAt(0) == '0' && newNumber.length() > 1)
			newNumber.deleteCharAt(0);
		return newNumber.toString();
	}
	

	
	/**
	 * this method takes a string input and change it to an unbelievable string,
	 * which there are no adjacent two letters that is the same letter but differ in lower case or upper case
	 * @param s a string input that need to be changed to an unbelievable string
	 * @return the unbelievable string changed from the input string s
	 */
	public static String unbelievableString(String s){
		//a one letter string is an unbelievable string
		if(s.length() == 1){
			return s;
		}
		StringBuilder sb = new StringBuilder();
		//traverse the whole string
		for(int i = 0; i <= s.length() - 2; i++){
			//if the word read is an upper case letter
			if(Character.isUpperCase(s.charAt(i))){
				//if the next one read is not the lower case of the same letter
				if(Character.toLowerCase(s.charAt(i)) != s.charAt(i + 1)){
					sb.append(s.charAt(i));
					//if reaches the end of the string(last 2 letters and they don't need to be deleted)
					if(i == s.length() - 2)
						//append the last one
						sb.append(s.charAt(i+1));
				}
				else
					//ignore the second letter and proceed
					i++;
			}
			//if the word read is a lower case letter
			else{
				//the next one read is not the upper case of the same letter
				if(Character.toUpperCase(s.charAt(i)) != s.charAt(i + 1)){
					sb.append(s.charAt(i));
					//if reaches the end of the string(last 2 letters and they don't need to be deleted)
					if(i== s.length() - 2)
						sb.append(s.charAt(i+1));
				}
				else
					//skip the second letter and proceed
					i++;
			}
		}
		//special case to stop the recursion
		//if the string before and after deletion are the same, then it is an unbelievable string
		if(s.length() == sb.length())
			return sb.toString();
		else
			//recursion that do the deletion again
			return unbelievableString(sb.toString());
	}
}
