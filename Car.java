package cs3331programs;


/**
 * This programs function is to calculate the distance 3 cars are from the starting points at 30 second intervals. 
 * Also while these 30 second interval are being calculated, the cars are going through 3 different segments of driving.
 * The first was driving with a speed limit of 20 mph, the second was driving with a speed limit of 60 mph, and lastly the 
 * third was driving with a speed limit of 30 mph (exclusively in that order). Also each car started one minute apart from previous
 * one. So Car A started at 0s, Car B started at 60s, and Car C started at 120s.
 * 
 * @author Sneh Patel.
 *         Created Sep 13, 2019.
 *         Change Log: 
 *         		Sep 13, 2019: Created private variables, constructors, and all setters and getter for the private variables.
 *         		Sep 14, 2019: Created the Segment1LocationA(), Segment1LocationB(), Segment1LocationC() methods and debugged those.
 *         		Sep 15, 2019: Created the Segment2LocationA(), Segment2LocationB(), Segment2LocationC() methods and debugged those.
 *         		Sep 17, 2019: Created the Segment3LocationA(), Segment3LocationC(), Segment3LocationC() methods and debugged those. 
 *         								 Also the printTable() method which prints out the outputs in the correct format was created. Also the comments were added.
 */

public class Car {
	
	private double location = 0.0;
	private double speed = 0.0;
	private double acceleration = 15.0;
	private double time = 0.0;
	
	public Car() {
		//Base Constructor 
	}
	
	public Car(double location, double speed, double acceleration, double time) {
		this.setLocation(location);
		this.setSpeed(speed);
		this.setAcceleration(acceleration);
		this.setTime(time);
	}
	
	public void setLocation(double location) {
		this.location = location;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public void setAcceleration(double acceleration) {
		this.acceleration = acceleration;
	}
	
	public void setTime(double time) {
		this.time = time;
	}
	
	public double getLocation() {
		return this.location;
	}
	
	public double getSpeed() {
		return this.speed;
	}
	
	public double getAcceleration() {
		return this.acceleration;
	}
	
	public double getTime() {
		return this.time;
	}
	
	public static void printTable(Car A, Car B, Car C) { //Method used to properly print the outputs as a table 

		System.out.println("Time\t\tCar A\t\t\t\tCar B\t\t\t\tCarC");
		System.out.println("\t\t\tSpeed\tLocation\t\tSpeed\tLocation\t\tSpeed\tLocation");
		for(int i = 0; i < 13; i++) { //For loop will run 14 times to be able to go through each segment with each car.
			if(A.getTime() <= 180) { //if the time is less than or equal to 180 seconds then A will still be in segment 1
				if(A.getTime() <= 60) { //if the time is less than or equal to 60 then B will have not started yet
					Segment1LocationA(A);
					System.out.println( //Printing out the Speed, and Location of each car
							String.format("%06.2f", A.getTime()) + "\t\t" + String.format("%05.2f", A.getSpeed()/1.46666666666666667) + "\t" + String.format("%.2f", A.getLocation()/5280) 
							+ "\t\t\t" + String.format("%05.2f", B.getSpeed()/1.46666666666666667) + "\t" + String.format("%.2f", B.getLocation()/5280) 
							+ "\t\t\t" + String.format("%05.2f", C.getSpeed()/1.46666666666666667) + "\t" + String.format("%.2f", C.getLocation()/5280));
					increaseTime(A, B, C); //Method used to increase the time of all three cars by 30 seconds
				}
				else if(A.getTime() <= 120) { //if the time is less than or equal to 120 then C will have not started yet
					Segment1LocationA(A);
					Segment1LocationB(B);
					System.out.println(
							String.format("%06.2f", A.getTime()) + "\t\t" + String.format("%05.2f", A.getSpeed()/1.46666666666666667) + "\t" + String.format("%.2f", A.getLocation()/5280) 
							+ "\t\t\t" + String.format("%05.2f", B.getSpeed()/1.46666666666666667) + "\t" + String.format("%.2f", B.getLocation()/5280)
							+ "\t\t\t" + String.format("%05.2f", C.getSpeed()/1.46666666666666667) + "\t" + String.format("%.2f", C.getLocation()/5280));
					increaseTime(A, B, C);
				}
				Segment1LocationA(A);
				Segment1LocationB(B);
				Segment1LocationC(C);
				System.out.println(
						String.format("%06.2f", A.getTime()) + "\t\t" + String.format("%05.2f", A.getSpeed()/1.46666666666666667) + "\t" + String.format("%.2f", A.getLocation()/5280) 
						+ "\t\t\t" + String.format("%05.2f", B.getSpeed()/1.46666666666666667) + "\t" + String.format("%.2f", B.getLocation()/5280) 
						+ "\t\t\t" + String.format("%05.2f", C.getSpeed()/1.46666666666666667) + "\t" + String.format("%.2f", C.getLocation()/5280));
				increaseTime(A, B, C);
			}
			else if(A.getTime() > 180 && A.getTime() <= 210) { //if the time is greater than 180 and less than or equal to 210 then A will be in Segment 2 and B and C are still in Segment 1
				Segment2LocationA(A);
				Segment1LocationB(B);
				Segment1LocationC(C);
				System.out.println(
						String.format("%06.2f", A.getTime()) + "\t\t" + String.format("%05.2f", A.getSpeed()/1.46666666666666667) + "\t" + String.format("%.2f", A.getLocation()/5280) 
						+ "\t\t\t" + String.format("%05.2f", B.getSpeed()/1.46666666666666667) + "\t" + String.format("%.2f", B.getLocation()/5280)
						+ "\t\t\t" + String.format("%05.2f", C.getSpeed()/1.46666666666666667) + "\t" + String.format("%.2f", C.getLocation()/5280));
				increaseTime(A, B, C);
			}
			else{
				if(A.getTime() <= 240) { //If the time is greater 210 A will be in segment 3
					Segment3LocationA(A);
					Segment1LocationB(B);
					Segment1LocationC(C);
					System.out.println(String.format("%06.2f", A.getTime()) + "\t\t" + String.format("%05.2f", A.getSpeed()/1.46666666666666667) + "\t" + String.format("%.2f", A.getLocation()/5280) 
					+ "\t\t\t" + String.format("%05.2f", B.getSpeed()/1.46666666666666667) + "\t" + String.format("%.2f", B.getLocation()/5280) 
					+ "\t\t\t" + String.format("%05.2f", C.getSpeed()/1.46666666666666667) + "\t" + String.format("%.2f", C.getLocation()/5280));
					increaseTime(A, B, C);
				}
				else if(A.getTime() > 240 && A.getTime() <= 270) { //if the time is greater than 240 and less than or equal to 270 then B will be in Segment 2 and C will still be in Segment 1
					Segment3LocationA(A);
					Segment2LocationB(B);
					Segment1LocationC(C);
					System.out.println(
							String.format("%06.2f", A.getTime()) + "\t\t" + String.format("%05.2f", A.getSpeed()/1.46666666666666667) + "\t" + String.format("%.2f", A.getLocation()/5280) 
							+ "\t\t\t" + String.format("%05.2f", B.getSpeed()/1.46666666666666667) + "\t" + String.format("%.2f", B.getLocation()/5280) 
							+ "\t\t\t" + String.format("%05.2f", C.getSpeed()/1.46666666666666667) + "\t" + String.format("%.2f", C.getLocation()/5280));
					increaseTime(A, B, C);
				}
				else if(A.getTime() > 270 && A.getTime() <= 300) { //if the time is greater than 270 and less than or equal to 300 then B will be in Segment 3 and C will still be in Segment 1
					Segment3LocationA(A);
					Segment3LocationB(B);
					Segment1LocationC(C);
					System.out.println(
							String.format("%06.2f", A.getTime()) + "\t\t" + String.format("%05.2f", A.getSpeed()/1.46666666666666667) + "\t" + String.format("%.2f", A.getLocation()/5280) 
							+ "\t\t\t" + String.format("%05.2f", B.getSpeed()/1.46666666666666667) + "\t" + String.format("%.2f", B.getLocation()/5280) 
							+ "\t\t\t" + String.format("%05.2f", C.getSpeed()/1.46666666666666667) + "\t" + String.format("%.2f", C.getLocation()/5280));
					increaseTime(A, B, C);
				}
				else if(A.getTime() > 300 && A.getTime() <= 330) { //if the time is greater than 240 and less than or equal to 270 then C will be in Segment 2
					Segment3LocationA(A);
					Segment3LocationB(B);
					Segment2LocationC(C);
					System.out.println(
							String.format("%06.2f", A.getTime()) + "\t\t" + String.format("%05.2f", A.getSpeed()/1.46666666666666667) + "\t" + String.format("%.2f", A.getLocation()/5280) 
							+ "\t\t\t" + String.format("%05.2f", B.getSpeed()/1.46666666666666667) + "\t" + String.format("%.2f", B.getLocation()/5280) 
							+ "\t\t\t" + String.format("%05.2f", C.getSpeed()/1.46666666666666667) + "\t" + String.format("%.2f", C.getLocation()/5280));
					increaseTime(A, B, C);
				}
				else { //if the time is greater than 270 every car will be in segment 3
					Segment3LocationA(A);
					Segment3LocationB(B);
					Segment3LocationC(C);
					System.out.println(
							String.format("%06.2f", A.getTime()) + "\t\t" + String.format("%05.2f", A.getSpeed()/1.46666666666666667) + "\t" + String.format("%.2f", A.getLocation()/5280) 
							+ "\t\t\t" + String.format("%05.2f", B.getSpeed()/1.46666666666666667) + "\t" + String.format("%.2f", B.getLocation()/5280) 
							+ "\t\t\t" + String.format("%05.2f", C.getSpeed()/1.46666666666666667) + "\t" + String.format("%.2f", C.getLocation()/5280));
					increaseTime(A, B, C);
				}
			}
		}
	}
	
	public static void increaseTime(Car a, Car b, Car c) { //Method used to increase the time of each car by 30 seconds

		double tempTime = a.getTime();
		a.setTime(tempTime += 30);
		b.setTime(tempTime += 30);
		c.setTime(tempTime += 30);
	}
	
	public static void Segment1LocationA(Car a) { //Method used to calculate the location of car A during segment 1
		
		double topSpeed = 20*1.46666666666666667;
		double t = a.getTime();
		double deltaT = topSpeed/a.getAcceleration();
		double s = 0.5*(a.getAcceleration())*(deltaT*deltaT);
		double v = Math.min(a.getAcceleration()*t, topSpeed);
		double nextLocation = (v*t);
		
		if(nextLocation < s) { //calculates the distance during the acceleration portion of the segment
			a.setLocation(v*t + (0.5*(a.getAcceleration())*(t*t)));
			a.setSpeed(v);
		}
		else if(nextLocation >= s && nextLocation <= 5280){ //calculates the distance during the constant portion of the segment
			a.setLocation(v*t);
			a.setSpeed(v);
		}
		
	}
	
	public static void Segment2LocationA(Car a) { //Method used to calculate the location of car A during segment 2
		
		double topSpeed = 60*1.46666666666666667;
		double nextTopSpeed = 30*1.46666666666666667;
		double t = a.getTime() - 180;
		double deltaT = topSpeed/a.getAcceleration();
		double decelerationDeltaT = (nextTopSpeed - topSpeed)/a.getAcceleration();
		double s = (0.5*(a.getAcceleration())*(deltaT*deltaT)) + 5280;
		double decelerationS = (0.5*(a.getAcceleration())*(decelerationDeltaT*decelerationDeltaT)) + 5280;
		double v = Math.min(a.getAcceleration()*t, topSpeed);
		double nextLocation = (v*t) + 5280;
		
		if(nextLocation < s) { //calculates the distance during the acceleration portion of the segment
			a.setLocation((v*t + (0.5*(a.getAcceleration())*(t*t))) + 5280);
			a.setSpeed(v);
		}
		else if(nextLocation >= s && nextLocation <= 10560-decelerationS){ //calculates the distance during the constant portion of the segment 
			a.setLocation(v*t + 5280);
			a.setSpeed(v);
		}
		else if(nextLocation > 10560-decelerationS && nextLocation < 10560) { //calculates the distance during the deceleration portion of the segment 
			double deceleration = (topSpeed - nextTopSpeed)/-decelerationDeltaT;
			v = topSpeed - nextTopSpeed;
			a.setLocation(-(v*t - (0.5*(deceleration)*(t*t))) + 5280);
			a.setSpeed(v);
		}
		
	}
	
public static void Segment3LocationA(Car a) { //Method used to calculate the location of car A during segment 3
		
		double topSpeed = 30*1.46666666666666667;
		double t = a.getTime() - 210;
		double v = Math.min(a.getAcceleration()*t, topSpeed);
		double nextLocation = (v*t) + 10560;
		
		if(nextLocation <= 15840){ //calculates the distance during the constant portion of the segment
			a.setLocation(v*t + 10560);
			a.setSpeed(v);
		}
		
	}

public static void Segment1LocationB(Car b) { //Method used to calculate the location of car B during segment 1
	
	double topSpeed = 20*1.46666666666666667;
	double t = b.getTime() - 90;
	double deltaT = topSpeed/b.getAcceleration();
	double s = 0.5*(b.getAcceleration())*(deltaT*deltaT);
	double v = Math.min(b.getAcceleration()*t, topSpeed);
	double nextLocation = (v*t);
	
	if(nextLocation < s) { //calculates the distance during the acceleration portion of the segment
		b.setLocation(v*t + (0.5*(b.getAcceleration())*(t*t)));
		b.setSpeed(v);
	}
	else if(nextLocation >= s && nextLocation <= 5280){ //calculates the distance during the constant portion of the segment
		b.setLocation(v*t);
		b.setSpeed(v);
	}
	
}

public static void Segment2LocationB(Car b) { //Method used to calculate the location of car B during segment 2
	
	double topSpeed = 60*1.46666666666666667;
	double nextTopSpeed = 30*1.46666666666666667;
	double t = (b.getTime() - 90) - 180;
	double deltaT = topSpeed/b.getAcceleration();
	double decelerationDeltaT = (nextTopSpeed - topSpeed)/b.getAcceleration();
	double s = (0.5*(b.getAcceleration())*(deltaT*deltaT)) + 5280;
	double decelerationS = (0.5*(b.getAcceleration())*(decelerationDeltaT*decelerationDeltaT)) + 5280;
	double v = Math.min(b.getAcceleration()*t, topSpeed);
	double nextLocation = (v*t) + 5280;
	
	if(nextLocation < s) { //calculates the distance during the acceleration portion of the segment
		b.setLocation((v*t + (0.5*(b.getAcceleration())*(t*t))) + 5280);
		b.setSpeed(v);
	}
	else if(nextLocation >= s && nextLocation <= 10560-decelerationS){ //calculates the distance during the constant portion of the segment
		b.setLocation(v*t + 5280);
		b.setSpeed(v);
	}
	else if(nextLocation > 10560-decelerationS && nextLocation < 10560) { //calculates the distance during the deceleration portion of the segment
		double deceleration = (topSpeed - nextTopSpeed)/-decelerationDeltaT;
		v = topSpeed - nextTopSpeed;
		b.setLocation(-(v*t - (0.5*(deceleration)*(t*t))) + 5280);
		b.setSpeed(v);
	}
	
}

public static void Segment3LocationB(Car b) { //Method used to calculate the location of car B during segment 3
	
	double topSpeed = 30*1.46666666666666667;
	double t = (b.getTime() - 90) - 210;
	double v = Math.min(b.getAcceleration()*t, topSpeed);
	double nextLocation = (v*t) + 10560;
	
	if(nextLocation <= 15840){ //calculates the distance during the constant portion of the segment
		b.setLocation(v*t + 10560);
		b.setSpeed(v);
	}
	
}

public static void Segment1LocationC(Car c) { //Method used to calculate the location of car C during segment 1
	
	double topSpeed = 20*1.46666666666666667;
	double t = c.getTime() - 180;
	double deltaT = topSpeed/c.getAcceleration();
	double s = 0.5*(c.getAcceleration())*(deltaT*deltaT);
	double v = Math.min(c.getAcceleration()*t, topSpeed);
	double nextLocation = (v*t);
	
	if(nextLocation < s) { //calculates the distance during the acceleration portion of the segment
		c.setLocation(v*t + (0.5*(c.getAcceleration())*(t*t)));
		c.setSpeed(v);
	}
	else if(nextLocation >= s && nextLocation <= 5280){ //calculates the distance during the constant portion of the segment
		c.setLocation(v*t);
		c.setSpeed(v);
	}
	
}

public static void Segment2LocationC(Car c) { //Method used to calculate the location of car C during segment 2
	
	double topSpeed = 60*1.46666666666666667;
	double nextTopSpeed = 30*1.46666666666666667;
	double t = (c.getTime() - 180) - 180;
	double deltaT = topSpeed/c.getAcceleration();
	double decelerationDeltaT = (nextTopSpeed - topSpeed)/c.getAcceleration();
	double s = (0.5*(c.getAcceleration())*(deltaT*deltaT)) + 5280;
	double decelerationS = (0.5*(c.getAcceleration())*(decelerationDeltaT*decelerationDeltaT)) + 5280;
	double v = Math.min(c.getAcceleration()*t, topSpeed);
	double nextLocation = (v*t) + 5280;
	
	if(nextLocation < s) { //calculates the distance during the acceleration portion of the segment
		c.setLocation((v*t + (0.5*(c.getAcceleration())*(t*t))) + 5280);
		c.setSpeed(v);
	}
	else if(nextLocation >= s && nextLocation <= 10560-decelerationS){ //calculates the distance during the constant portion of the segment
		c.setLocation(v*t + 5280);
		c.setSpeed(v);
	}
	else if(nextLocation > 10560-decelerationS && nextLocation < 10560) { //calculates the distance during the deceleration portion of the segment
		double deceleration = (topSpeed - nextTopSpeed)/-decelerationDeltaT;
		v = topSpeed - nextTopSpeed;
		c.setLocation(-(v*t - (0.5*(deceleration)*(t*t))) + 5280);
		c.setSpeed(v);
	}
	
}

public static void Segment3LocationC(Car c) { //Method used to calculate the location of car C during segment 3
	
	double topSpeed = 30*1.46666666666666667;
	double t = (c.getTime() - 180) - 210;
	double v = Math.min(c.getAcceleration()*t, topSpeed);
	double nextLocation = (v*t) + 10560;
	
	if(nextLocation <= 15840){ //calculates the distance during the constant portion of the segment
		c.setLocation(v*t + 10560);
		c.setSpeed(v);
	}
	
}

public static void main(String[] args) {

	Car A = new Car();
	Car B = new Car();
	Car C = new Car();
	
	printTable(A, B, C);
	
}

}
