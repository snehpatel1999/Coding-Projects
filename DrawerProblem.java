
public class DrawerProblem {
  public static void main(String[] args) {
    boolean A[] = new boolean[4];
    A[0] = false;
    A[1] = false;
    A[2] = false;
    A[3] = true;
	int i = DrawerProblemMethods.whereIsMyPhone(A);
	System.out.println(i);
	  
 }
}
