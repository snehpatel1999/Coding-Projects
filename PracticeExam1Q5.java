
public class PracticeExam1Q5 {
	public static void main(String[] args) {
		double[][] m = 
			{{5,0,0,0}
			,{0,5,0,0}
			,{0,0,5,0}
			,{0,0,0,5}};
		double sum = sumMainDiagonal(m);
		System.out.println(sum);
	}
	
	public static double sumMainDiagonal(double[][] m) {
		int col = 0;
		double sum = 0;
		for(int row = 0; row < m.length; row++) {
			sum = sum + m[row][col];
			col++;
		}
		return sum;
	}

}
