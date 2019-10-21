
public class QualityCheckProblem {
 public static void main(String [] args) {
 
 int[][] A = {{0, 1, 1, 1}, {1, 1, 1, 1}, {1, 0, 1, 1}, {1, 1, 1, 1}};
 int n = 4;
 boolean[] test = isRight(A, n);
 printQualityCheck(test);
 

 }
 
 public static void QualityCheck(boolean[][] A) {
	 
  for(int i = 0; i < A.length; i++) {
	  for(int j = 0; j < A[0].length; j++) {
		  if(A[i][j] == false) {
			  System.out.println("Box " + (i+1) + " has an error, because it is missing part " + (j+1));
		  }
		  else {
			  System.out.println("Box " + (i+1) + " and Part " + (j+1) + " are correct!");
		  }
	  }
  }
 }
 public static void printQualityCheck(boolean[] A) {
  for(int i = 0; i < A.length; i ++) {
	  System.out.println(A[i]);
  }
 }
 public static boolean[] isRight(int[][] A, int n) {
	 boolean [] test = new boolean[n];
	 for(int i = 0; i < test.length; i++) {
		 test[i] = true;
	 }
	 for(int i = 0; i < n; i++) {
		 for(int j = 0; i < n; i++) {
			 if(A[i][j] != 1) {
				 test[i] = false;
			 }
		 }
	 }
	 return test;
 }
}
