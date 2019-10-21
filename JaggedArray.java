
public class JaggedArray {
 public static void main(String[] args) {
	 int a[][] = new int[3][];
	 a[0] = new int[3];
	 a[1] = new int[2];
	 a[2] = new int[3];
	 boolean b = isJagged(a);
	 System.out.println(b);
 }
 
 public static boolean isJagged(int[][] a) {
	 boolean isJagged = false;
	 int jagged = a[0].length;
	 for(int i = 0; i < a.length; i++) {
		 if(a[i].length != jagged) {
			 isJagged = true;
		 }
		 else {
			 isJagged = true;
		 }
	 }

	 return isJagged;
 }
}
