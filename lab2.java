import java.io.*;
public class lab2 {
 public static void main(String[] args) throws FileNotFoundException, IOException{

lab2Methods.readSudoku(args[0]); //Run Task 1
lab2Methods.printSudoku(); //Run Task 2
boolean result = lab2Methods.checkSudoku(); //Run Task 3
lab2Methods.resultSudoku(result); //Run Task 4

 }
}
