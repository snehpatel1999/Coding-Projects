
public class Sum2D {
	public static void main(String[] args) {
		int[][] A = {
				{1,1,1,1},
				{1,1,1,1},
				{1,1,1,1},
				{1,1,1}};
		int sum = sumAll(A);
		System.out.println(sum);
		}
	
	
	public static int sumAll(int[][] A) {
		int sum = 0;
		for(int i = 0; i < A.length; i++) {
			sum += sum1D(A[i]);
		}
		return sum;
	}
	
	public static int sum1D(int[] A) {
		int sum = 0;
		for(int i = 0; i < A.length; i++) {
			sum += A[i];
		}
		return sum;
	}
}
