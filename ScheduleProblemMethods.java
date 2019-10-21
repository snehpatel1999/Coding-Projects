
public class ScheduleProblemMethods {

	
	public static void schedule(boolean [][]S, int a, int b) {
		
		if(S[a][b] == true) {
	      System.out.println("I am busy at that time, how about another time?");
		}
		else {
			System.out.println("I am free then let's meet then!");
		}

	}
}
