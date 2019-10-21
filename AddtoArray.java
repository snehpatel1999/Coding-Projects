
public class AddtoArray {
	
	public void addToArray(int[] a, int[] b) {
		int[] temp = new int[b.length + a.length];
		int c = 0;
		for(int i = 0; i < b.length; i++) {
			temp[i] = b[i];
			c++;
		}
		
		for(int i = 0; i < a.length ; i++) {
			temp[c] = a[i];
			c++;
		}
		
		b = new int[temp.length];
		for(int i = 0; i < b.length; i++) {
			b[i] = temp[i];
		}
		for(int i = 0; i < b.length; i++) {
			System.out.print(b[i] + " ");
		}
	}



}