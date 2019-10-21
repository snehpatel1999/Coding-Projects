
public class DrawerProblemMethods {

	public static int whereIsMyPhone(boolean[] A) {
		for(int i = 0; i < A.length; i++) {
			if(A[i] == true) {
				return i;
			}
		}
		return 0;
	}
}
