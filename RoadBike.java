
public class RoadBike extends Bike {
	
	private int gearNum; //Initializes an int type that can be used and modified across the whole class
	private double weight; //Initializes a double type that can be used and modified across the whole class
	
	public RoadBike() { //Base Constructor used to create a base object.
		
	}

	public RoadBike(String bike, String model, String priceB, String priceS, String color, String inventoryNum, String ageRange, String gearNum, String weight) { //Constructor used to assign RoadBike's attributes with a value based of the inventory file
		super(bike, model, priceB, priceS, color, inventoryNum, ageRange); //super is used to access the constructors and methods in the parent class Bike
		this.setGearNum(Integer.parseInt(gearNum)); //runs the setGearNum method of this class and parses gearNum to get an int value from the input String
		this.setWeight(Double.parseDouble(weight)); //runs the setWeight method of this class and parses weight to get a double value from the input String
	}
	
	public RoadBike(String bike, String model, String color, String inventoryNum) { //Constructor used to assign RoadBike's attributes with a value based of the client request file
		super(bike, model, color, inventoryNum); //super is used to access the constructors and methods in the parent class Bike
	}

	public int getGearNum() { //getter for gearNum
		return gearNum; //Returns the value in gearNum
	}

	public void setGearNum(int gearNum) { //setter for gearNum
		this.gearNum = gearNum; //Assigns the value into gearNum of this class
	}

	public double getWeight() { //getter for weight
		return weight; //Returns the value in weight
	}

	public void setWeight(double weight) { //setter for weight
		this.weight = weight; //Assigns the value into weight of this class
	}
	
	@Override //Overrides the method in the parent class Bike
	public String toString() {
		String result = super.toString(); //uses super to print out Bike's attributes along side the unique attributes of RoadBike
		result += " | Number of Gears: " + gearNum + " | Weight: " + weight; //adds on the string for MountainBike's attributes
		return result; //Returns the value in result
	}
	
}
