package cs3331programs;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class Runner {

	private String name;
	private double topSpeed;
	private double acceleration;
	private ArrayList<Double> distanceT = new ArrayList<Double>();
	
	public Runner() {
		//Base Constructor
	}
	
	public Runner(String name, double topSpeed, double acceleration) {
		this.setName(name);
		this.setTopSpeed(topSpeed);
		this.setAcceleration(acceleration);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setTopSpeed(double topSpeed) {
		this.topSpeed = topSpeed;
	}
	
	public void setAcceleration(double acceleration) {
		this.acceleration = acceleration;
	}
	
	public void addToArrayList(double d) {
		this.distanceT.add(d);
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getTopSpeed() {
		return this.topSpeed;
	}
	
	public double getAcceleration() {
		return this.acceleration;
	}
	
	public ArrayList getArrayList() {
		return this.distanceT;
	}

	
	public static void main(String[] args) {
		
		Runner Nelly = new Runner("Nelly", 30.0, 8.0);
		Runner Steve = new Runner("Steve",8.8, 3.0);
		Runner Usain = new Runner("Usain", 41.0, 11.0);
		ArrayList <Runner> runners = new ArrayList <Runner>();
		runners.add(Nelly);
		runners.add(Steve);
		runners.add(Usain);
		for(Runner runner: runners) {
		DistancePreTurn(runner);
		DistancePostTurn(runner);
		}
		//System.out.println(runners.get(1).getArrayList().get(8));
		printTable(runners);
		
	}
	
	public static void printTable(ArrayList<Runner> runners) {
		System.out.println("Runner" + "\t" + "Max Speed" + "\t" + "Acceration");
		for(Runner runner: runners) {
			System.out.println(runner.getName() + "\t" + runner.getTopSpeed() + "\t\t\t" + runner.getAcceleration());
		}
		int x = runners.size();
		StringBuilder string = new StringBuilder();
		string.append("Time");
		for(int i = 0; i < x; i++) {
			string.append("\t");
			string.append(runners.get(i).getName());
		}
		StringBuilder string1 = new StringBuilder();
		System.out.println();
		System.out.println(string);
		String temp = "";
		String tempAppend = "";
		double time = 5;
		boolean condition = false;
		string1.append("0.00" + "\t\t" + "0.00" + "\t\t" + "0.00" + "\t\t" + "0.00");
		string1.append("\n");
		while(condition != true) {
			string1.append(time);
			string1.append("\t\t");
			for(Runner runner: runners) {
				if(runner.getArrayList().size() == 1) {
					string1.append(tempAppend);
					string1.append("\t");
					continue;
				}
				if(runner.getArrayList().get(1).toString().length() < 5) {
					string1.append(runner.getArrayList().get(1).toString() + "0");
					string1.append("\t");
					runner.getArrayList().remove(1);
				}
				else {
					string1.append(runner.getArrayList().get(1));
					string1.append("\t");
					temp = runner.getArrayList().get(1).toString();
					runner.getArrayList().remove(1);
					if(runner.getArrayList().size() == 1) {
						tempAppend = temp;
					}
				}
			}
			string1.append("\n");
			time += 5;
			if(checkArrayListsForAllRunners(runners) == true){
				condition = true;
			}
		}
		System.out.println(string1);
	}
	public static boolean checkArrayListsForAllRunners(ArrayList<Runner> r) {
		boolean bool = true;
		boolean temp = true;
		for(Runner runners: r) {
			if(runners.getArrayList().size() > 1) {
				temp = false;
			}
		}
		if(temp == false) {
			bool = false;
		}
		return bool;
	}
	public static void DistancePreTurn(Runner a) {
		double distance = 0;
		double t = 0;
		double deltaT = a.getTopSpeed()/a.getAcceleration();
		double s = 0.5*(a.getAcceleration())*(deltaT*deltaT);
		double v = 0;
		double decelerationDistance = 300-s;
		while((v*t + (0.5*(a.getAcceleration())*(t*t))) < s) {
			double v1 = Math.min(a.getAcceleration()*t, a.getTopSpeed());
			distance = v*t + (0.5*(a.getAcceleration())*(t*t));
			a.distanceT.add(distance);
			v = v1;
			t+=5;
		}
		while((v*t + (0.5*(a.getAcceleration())*(t*t))) >= s && (v*t + (0.5*(a.getAcceleration())*(t*t)) <= decelerationDistance)){
			double v1 = Math.min(a.getAcceleration()*t, a.getTopSpeed());
			distance = v1*t ;
			a.distanceT.add(distance);
			v = v1;
			t+=5;
		}
		while((v*t + (0.5*(a.getAcceleration())*(t*t))) > decelerationDistance && Math.abs((v*t - (0.5*(a.getAcceleration())*(t*t))))+distance <= 300) {
			double v1 = Math.min(a.getAcceleration()*t, a.getTopSpeed());
			distance = Math.abs(v*t - (0.5*(a.getAcceleration())*(t*t))) + distance;
			a.distanceT.add(distance);
			v = v1;
			t+=5;
		}
	}
	
	public static void DistancePostTurn(Runner a) {
		double distance = 0;
		double distanceA = 300;
		double t = 0;
		double deltaT = a.getTopSpeed()/a.getAcceleration();
		double s = 0.5*(a.getAcceleration())*(deltaT*deltaT);
		double v = 0;
		while((v*t + (0.5*(a.getAcceleration())*(t*t))) < s) {
			double v1 = Math.min(a.getAcceleration()*t, a.getTopSpeed());
			distance = v*t + (0.5*(a.getAcceleration())*(t*t)) + distanceA;
			a.distanceT.add(distance);
			v = v1;
			t+=5;
		}
		while((v*t + (0.5*(a.getAcceleration())*(t*t))) >= s){
			if(distance >= 600) {
				break;
			}
			double v1 = Math.min(a.getAcceleration()*t, a.getTopSpeed());
			distance = v1*t  + distanceA;
			a.distanceT.add(distance);
			v = v1;
			t+=5;
		}
	}
	
}





