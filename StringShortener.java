
public class StringShortener {
	public static void main(String[] args) {
		
	}
	public void StringS(String s) {
		for(int i = 0; i < s.length(); i++) {
			if(i % 2 == 1) {
				s.replace(s.charAt(i), "");
			}
		}
	}
}
