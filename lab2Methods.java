import java.io.*;
import java.util.Scanner;
public class lab2Methods {

  //Private array created so that it can be used across multiple methods
  private static int sudokuArray[][];

//Method for Task 1
public static void readSudoku(String filename) throws FileNotFoundException,IOException,NumberFormatException{
  try { //Try and Catch used to handle the possible error of incorrect file name or placement of the file
	File file = new File(filename); 
    Scanner in = new Scanner(file);
    int row = 9;
    int col = 9;
    sudokuArray = new int[row][col]; //Assigning the array with the value of rows and columns

    while (in.hasNextLine()) { //This loop will run until the .txt file does not have any more info
        for(int i = 0; i < sudokuArray.length; i++) { //using a for loop to work with the 2d array
         String[] line = in.nextLine().split(" "); //For each line of the .txt file, the String array line will hold each value in its elements
          for(int j = 0; j < line.length; j++) { //uses the length of the line array 
        	 try{ //Try and Catch used to handle any possible errors such as missing or extra numbers in the file
        		 sudokuArray[i][j] = Integer.parseInt(line[j]); //takes each element from the line array and after turning it from string to int, the int assigned to an element in the 2d array
        		 
        	 }
        	 catch(NumberFormatException n) { //catches errors with missing numbers and incorrect values
        		 System.out.println("The sudoku board is missing a number or has an incorrect value, please fix and try again!");
        		 System.exit(0);
        	 }
        	 catch(ArrayIndexOutOfBoundsException a) { //catches errors with an extra number or value in the file
        		 System.out.println("There is an extra number or value in the sudoku board, please fix and try again!");
        		 System.exit(0);
        	 }
          }
        } 
    }
    in.close(); //closes the scanner object
    
    for(int i = 0; i < sudokuArray.length; i++) { //catches errors with incorrect values of numbers greater than 9 or less than 1 using a nested for loop and an if statement
    	for(int j = 0; j < sudokuArray[0].length; j++) {
    		if(sudokuArray[i][j] > 9 || sudokuArray[i][j] < 1) {
    			System.out.println("There is an invalid number in the sudoku board, numbers must be within the range of 1 and 9, please fix and try again!");
    			System.exit(0);
    		}
    	}
    }
  }
  catch(FileNotFoundException f) { //catches errors with incorrect file name or file path
	  System.out.println("The file name is incorrect or this file is placed in another part of the directory, please fix and try again!");
	  System.exit(0);
  }
}
//Method for Task 2
public static void printSudoku(){
	int row = 9;
	int col = 9;
	
  for(int i = 0; i < row; i++) { //using a for loop to work with 2d array
	  for(int j = 0; j < col; j++) {
		  System.out.print(sudokuArray[i][j] + " | "); //prints of the sudoku board nicely
	  }
	  if(i == row-1) {
        System.out.print("\n" + "-----------------------------------" + "\n"); //To print a clean and well organized sudoku board
	  }
	  else {
		System.out.print("\n" + "--|---|---|---|---|---|---|---|---|" + "\n"); //To print a clean and well organized sudoku board
	  }
  }
}
//Method for Task 3
public static boolean checkSudoku(){
	int row = 9;
	int col = 9;
	int sumVal = 0;
	
	//check rows
	for(int i = 0; i < row; i++) {
		for(int j = 0; j < col; j++) {
		 sumVal = sumVal + sudokuArray[i][j]; //takes the sum of the elements in each row
		}
	 if(sumVal != 45) { //if the sum of the elements is not this then it means that an incorrect number is in the row therefore the sudoku board must be a false board
		 return false;
	 }
	 
	 sumVal = 0;
	}
	
	//check columns
	for(int i = 0; i < row; i++) {
		for(int j = 0; j < col; j++) {
			sumVal = sumVal + sudokuArray[j][i]; //takes the sum of the elements in each column
		}
	 if(sumVal != 45){ //if the sum of the elements is not this then it means that an incorrect number is in the row therefore the sudoku board must be a false board
		 return false;
	 }
	 
	 sumVal = 0;
	}
	
	//check 3x3s
	
    int endRow = 0;
    int endCol = 0;
	for(int a = 0; a < sudokuArray.length; a+=3) { //first nested for loop used to check each 3x3 one after each other.
	  for(int b = 0; b < sudokuArray[0].length; b+=3) {
		
		endRow = a;
		endCol = b;
		for(int startRow = a; startRow < endRow+3; startRow++) { //second nested for loop used to check the elements in each 3x3 from the first for loop.
			for(int startCol = b; startCol < endCol+3; startCol++) {
				sumVal = sumVal + sudokuArray[startRow][startCol];
			}
		}
		if(sumVal != 45) { //if the sum of the elements is not this then it means that an incorrect number is in the row therefore the sudoku board must be a false board
		    	return false;
		 }
	     sumVal = 0;
	  }
	}
	
	return true; //if the false return is never hit, then the sudoku board must be true
}
//Method for Task 4
public static void resultSudoku(boolean result) {
	if(result == true) { //if the sudoku board was true then it is a winning board
		System.out.println("This Sudoku Board is a winning board!");
	}
	else { //if the sudoku board was false then it is a losing board
	System.out.println("This Sudoku Board is a losing board!");
    }
}

}
