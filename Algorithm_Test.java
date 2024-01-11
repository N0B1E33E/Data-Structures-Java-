package testPackage;

import java.util.Scanner;
import java.util.Arrays;
public class Algorithm_Test {
	
	private static int[] A;
	private static Integer[] ans = new Integer[2];
	public static void main(String[] args){
		System.out.println("Please type in the number of element in the array");
		Scanner in = new Scanner(System.in);
		int a = in.nextInt();
		A = new int[a];
		for(int i = 0; i < a; i ++){
			A[i] = (int)(Math.random() * 100);
		}
		Arrays.sort(A);
		for(int i = 0; i < a; i++){
			System.out.print(A[i] + " ");
		}
		int m = in.nextInt();
		Algorithm_Test.LinearSearch(A,m);
		System.out.println(ans[0] + "," + ans[1]);
		System.out.println(Algorithm_Test.Find_Min_Max(A));
	}
	
	
	private static Integer[] BinarySearch(int[] arr, int head, int tail, int k){
		int ptr = (head + tail) / 2;
		if(k < arr[0]){
			ans[0] = null;
			ans[1] = arr[0];
		}
		else if(k > arr[arr.length - 1]){
			ans[0] = arr[arr.length - 1];
			ans[1] = (Integer) null;
		}	
		else{
			if(head >= tail){
				return null;
			}
			if(k > arr[ptr]){
				head = ptr;
				BinarySearch(arr, head, tail, k);
			}
			else if(k < arr[ptr]){
				tail = ptr;
				BinarySearch(arr, head, tail, k);
			}
			else if( k == arr[ptr]){
				if(ptr == 0){
					ans[0] = null;
					ans[1] = arr[0];
				}
				else{
					ans[0] = arr[ptr - 1];
					ans[1] = arr[ptr + 1];
				}
			}
			
		}
		return ans;
	}
	
	private static Integer[] LinearSearch(int[] arr,int k){
		boolean first_set = false;
		if(k > arr[arr.length - 1]){
			ans[0] = arr[arr.length - 2];
			return ans;
		}
		for(int i = 0; i < arr.length; i++){
			if(k <= arr[i]){
				if(first_set == false){
					if(i == 0)
						ans[0] = null;
					else
						ans[0] = arr[i - 1];
					first_set = true;
				}
				else if(k < arr[i]){
				ans[1] = arr[i];
				return ans;
				}
			}
		}
		return ans;
	}
	
	private static int[] left;
	private static int[] right;
	
	private static int[] Find_Min_Max(int[] A){
		
		return Min_Max(A,0,A.length - 1);
	}
	
	private static int[] Min_Max(int[] arr, int head, int tail){
		
		if(A.length <= 1 || A == null)
			return arr;
		if(tail - head <= 1)
			return arr;
		int mid = (head + tail)/2;
		left = Min_Max(arr, head, mid);
		right = Min_Max(arr, mid, head);
		return left;
	}
}
