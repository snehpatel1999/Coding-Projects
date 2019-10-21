
public class MountainBike extends Bike {
	
	private String userLevel; //Initializes a String type that can be used and modified across the whole class
	private int wheelSize; //Initializes an int type that can be used and modified across the whole class
	
	public MountainBike() { //Base Constructor used to create a base object.
		
	}
	
	public MountainBike(String bike, String model, String priceB, String priceS, String bikeColor, String inventoryN, String ageRange, String userLevel, String wheelSize) { //Constructor used to assign MountainBike's attributes with a value based of the inventory file
		super(bike, model, priceB, priceS, bikeColor, inventoryN, ageRange); //super is used to access the constructors and methods in the parent class Bike
		this.setUserLevel(userLevel); //runs the setUserLevel method of this class
		this.setWheelSize(Integer.parseInt(wheelSize)); //runs the setWheelSize method of this class and parse wheelSize to get an int value from the input String
	}
	
	public MountainBike(String bike, String model, String color, String inventoryNum) { //Constructor used to assign MountainBike's attributes with a value based of the client request file
		super(bike, model, color, inventoryNum); //super is used to access the constructors and methods in the parent class Bike
	}
	
	public String getUserLevel() { //getter for userLevel
		return this.userLevel; //Returns the value in userLevel
	}

	public void setUserLevel(String userLevel) { //setter for userLevel
		this.userLevel = userLevel; //Assigns the value into userLevel of this class
	}

	public int getWheelSize() { //getter for wheelSize
		return this.wheelSize; //Returns the value in wheelSize
	}

	public void setWheelSize(int wheelSize) { //setter for wheelSize
		this.wheelSize = wheelSize; //Assigns the value into wheelSize of this class
	}
	
	@Override //Overrides the method in the parent class Bike
	public String toString() {
		String result = super.toString(); //uses super to print out Bike's attributes along side the unique attributes of MountainBike
		result += " | User Level: " + userLevel + " | Wheel Size: " + wheelSize; //adds on the string for MountainBike's attributes
		return result; //Returns the value in result
	}
	
	
	
}
