import java.util.Scanner;
/**
 * TODO Put here a description of what this class does.
 *
 * @author Robert Estrada
 *         Created Oct 1, 2019.
 */
public class ROBE_TEST {
	public static void main(String [] args) {
		
		//variables
		float x0, y0, x1, y1, xp, yp;
		String in;
		Scanner input = new Scanner(System.in);
		
		//Inputs
		System.out.println("Enter first point x (x0):");
		in = input.nextLine();
		x0 = Float.parseFloat(in);
		System.out.println("Enter first point y (y0):");
		in = input.nextLine();
		y0 = Float.parseFloat(in);
		System.out.println("Enter second point x (x1):");
		in = input.nextLine();
		x1 = Float.parseFloat(in);
		System.out.println("Enter second point y (y1):");
		in = input.nextLine();
		y1 = Float.parseFloat(in);
		System.out.println("Enter interpolation point: ");
		in = input.nextLine();
		xp = Float.parseFloat(in);
		input.close();
		
		//Calculation
		yp = y0 + ((y1-y0)/(x1-x0)) * (xp-x0);
		
		//Output
		System.out.println("Interpolated value at " + xp + " is: " + yp);
		
	}
}
