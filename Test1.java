package testPackage;
import java.util.Scanner;
public class Test1 {
	public static void main(String args[]){
		System.out.println("Please type in the number you want to reverse");
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		reverseInt(a);
		System.out.println("Please type in the number you want to sum up to");
		int b = sc.nextInt();
		System.out.println(sum(b));
	}
	
	
	
	private static void reverseInt(int b){
		if(b<10){
			System.out.println(b);
		}
		else{

			System.out.print((b%10));
			reverseInt(b/10);
			
		}
	}
	
	
	private static int sum(int b){
		if(b == 0){
			return 0;
		}
		else{
			return b + sum(b-1);
		}
	}
}
