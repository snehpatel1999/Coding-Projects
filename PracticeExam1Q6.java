
public class PracticeExam1Q6 {
	public static void main(String[] args) {
		int[] a = {1,0,0,0,6,7,8,9};
		reverseArray(a);
	}
	public static void reverseArray(int[] a) {
		int temp = 0;
		for(int i = 0; i < a.length/2; i++) {
			temp = a[i];
			a[i] = a[a.length - (1 + i)];
			a[a.length - (1 + i)] = temp;
		}
		for(int i = 0; i < a.length; i ++) {
			System.out.print(a[i] + " ");
		}
	}
}
