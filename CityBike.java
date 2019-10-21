
public class CityBike extends Bike {
	
	private int numOfBaskets;  //Initializes an int type that can be used and modified across the whole class
	private String brakes;  //Initializes a String type that can be used and modified across the whole class
	
	public CityBike() { //Base Constructor used to create a base object.
		
	}

	public CityBike(String bike, String model, String priceB, String priceS, String bikeColor, String inventoryN, String ageRange, String baskets, String brakes) { //Constructor used to assign CityBike's attributes with a value based of the inventory file
		super(bike, model, priceB, priceS, bikeColor, inventoryN, ageRange); //super is used to access the constructors and methods in the parent class Bike
		this.setNumOfBaskets(Integer.parseInt(baskets)); //runs the setNumOfBaskets method of this class and parses baskets to get an int value from the input String
		this.setBrakes(brakes); //runs the setBrakes method of this class
	}
	
	public CityBike(String bike, String model, String color, String inventoryNum) { //Constructor used to assign CityBike's attributes with a value based of the client request file
		super(bike, model, color, inventoryNum); //super is used to access the constructors and methods in the parent class Bike
	}

	public int getNumOfBaskets() { //getter for numOfBaskets
		return numOfBaskets; //Returns the value in numOfBaskets
	}

	public void setNumOfBaskets(int numOfBaskets) { //setter for numOfBaskets
		this.numOfBaskets = numOfBaskets; //Assigns the value into numOfBasket of this class
	}

	public String getBrakes() { //getter for brakes
		return brakes; //Returns the value in brakes
	}

	public void setBrakes(String brakes) { //setter for brakes
		this.brakes = brakes; //Assigns the value into brakes of this class
	}
	
	@Override //Overrides the method in the parent class Bike
	public String toString() {
		String result = super.toString(); //uses super to print out Bike's attributes along side the unique attributes of CityBike
		result += " | Number of Baskets: " + numOfBaskets + " | Brake Type: " + brakes; //adds on the string for CityBike's attributes
		return result; //Returns the value in result
	}
	
}
