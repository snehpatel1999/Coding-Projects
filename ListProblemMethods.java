
public class ListProblemMethods {
    
	public static int list(int[] A) {
		int largest = 0;
		for(int i = 0; i < A.length; i++) {
			if(A[i] >= largest) {
				largest = A[i];
			}
		}
		return largest;
	}
	
	public static int max1(int []A) {
		int max = 0;
		for(int i = 0; i < A.length - 1; i++) {
		if(A[i+1] > A[i]){
			
			max = A[i+1];
		}
		else{
			max = A[i];
		}
	    }
		return max;
	}
}
