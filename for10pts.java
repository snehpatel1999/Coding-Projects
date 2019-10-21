public class for10pts {
    
    public static boolean canBalance(int[] A) {
    	if(A.length == 0) {
    		return false;
    	}
    	int sumLeft = 0;
    	int sumRight = 0;
    	if(A.length % 2 == 1) {
	    	int splitter = A.length/2;

	    	for(int i = 0; i <= splitter; i++) {
	    		sumLeft += A[i];
	    	}
	    	for(int i = splitter+1; i < A.length; i++) {
	    		sumRight += A[i];
	    	}
    	}
    	if(A.length % 2 == 0) {
    		int splitter = A.length/2;

	    	for(int i = 0; i < splitter; i++) {
	    		sumLeft += A[i];
	    	}
	    	for(int i = splitter; i < A.length; i++) {
	    		sumRight += A[i];
	    	}
    	}
        return (sumLeft == sumRight);
    }
    
    public static boolean linearIn(int[] A, int[] B) {
        return false;   
    }
    
    public static int maxMirror(int[] A) {
        return 0;   
    }
    
    public static int[] squareUp(int n) {
        int[] A = {0};
        return A;
    }
    
    /*******************************************************/
    
    public static boolean groupSum5(int[] A, int n) {
        return false;   
    }
    
    public static boolean splitOdd10(int[] A) {
        return false;   
    }
    
    public static boolean groupSum6(int[] A, int n) {
        return false;   
    }
    
}