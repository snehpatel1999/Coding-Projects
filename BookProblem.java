import java.util.Scanner;
public class BookProblem {
 public static void main(String [] args) {
	 
	int n;
	Scanner keyboard = new Scanner(System.in);
	int sumVal;
	
	System.out.println("The num of customers:");
	n = keyboard.nextInt();
	
	int clientBook[] = new int[n];
    
	for(int i = 0; i < clientBook.length; i++) {
		System.out.println("What is the num of book that customer " + (i + 1) + " needs:");
		clientBook[i] = keyboard.nextInt();
	}
	
	sumVal = 0;
	for(int i = 0; i < clientBook.length; i++) {
      sumVal = sumVal + clientBook[i];
	}
	
	System.out.println("The total amount of books needed is " + sumVal);
	 
	 
	 
	 keyboard.close();
	 
 }
}
