
public class Bike {
	
	private String bikeType; //Initializes a String type that can be used and modified across the whole class
	private String modelNum; //Initializes a String type that can be used and modified across the whole class
	private int priceBought; //Initializes an int type that can be used and modified across the whole class
	private int priceSold; //Initializes an int type that can be used and modified across the whole class
	private String color; //Initializes a String type that can be used and modified across the whole class
	private int inventoryNum; //Initializes an int type that can be used and modified across the whole class
	private int ageRange; //Initializes an int type that can be used and modified across the whole class
	private String cliBikeType; //Initializes a String type that can be used and modified across the whole class
	private String cliModelNum; //Initializes a String type that can be used and modified across the whole class
	private String cliColor; //Initializes a String type that can be used and modified across the whole class
	private int cliInventoryNum; //Initializes an int type that can be used and modified across the whole class
	
	public Bike() { //Base Constructor used to create a base object.
		
	}
	
	public Bike(String bike, String model, String priceB, String priceS, String color, String inventoryNum, String ageRange) { //Constructor used to assign the parent's attributes with a value based of the inventory file
		this.setBikeType(bike); //runs the setBikeType method of this class
		this.setModelNum(model); //runs the setModelNum method of this class
		String priceTemp = priceB.replace("$", ""); //Gets rid of any $ in order to be able to parse the string
		this.setPriceBought(Integer.parseInt(priceTemp)); //runs the setPriceBought method of this class and parses priceTemp to get an int value from the input String 
		String priceSTemp = priceS.replace("$", ""); //Gets rid of any $ in order to be able to parse the string
		this.setPriceSold(Integer.parseInt(priceSTemp)); //runs the setPriceSold method of this class and parses priceSTemp to get an int value from the input String 
		this.setColor(color); //runs the setColor method of this class
		this.setInventoryNum(Integer.parseInt(inventoryNum)); //runs the setInventoryNum method of this class and parses inventoryNum to get an int value from the input String
		String[] temp = ageRange.split("-"); //Splits the input string (at the char '-') into an array
		this.setAgeRange(Integer.parseInt(temp[1]) - Integer.parseInt(temp[0])); //runs the setAgeRange method of this class and parses temp[0] and temp[1] to get an int value from the input String array, and then subtracts them to get the ageRange
	}
	
	public Bike(String bike, String model, String color, String inventoryNum) { //Constructor used to assign the parent's attributes with a value based of the client request file
		this.setCliBikeType(bike); //runs the setCliBikeType method of this class
		this.setCliModelNum(model); //runs the setCliModelNum method of this class
		this.setCliColor(color); //runs the setCliColor method of this class
		this.setCliInventoryNum(Integer.parseInt(inventoryNum)); //runs the setCliInventoryNum method of this class and parses inventoryNum to get an int value for the input String
	}
	
	public void setCliBikeType(String bikeType) { //setter for cliBikeType
		this.cliBikeType = bikeType; //Assigns the value into cliBikeType of this class
	}
	
	public String getCliBikeType() { //getter for CliBikeType
		return cliBikeType; //Returns the value in cliBikeType
	}
	
	public void setCliModelNum(String model) { //setter for cliModelNum
		this.cliModelNum = model; //Assigns the value into cliModelNum of this class
	}
	
	public String getCliModelNum() { //getter for cliModelNum
		return cliModelNum; //Returns the value in cliModelNum
	}
	
	public void setCliColor(String color) { //setter for cliColor
		this.cliColor = color; //Assigns the value into cliColor of this class
	}
	
	public String getCliColor() { //getter for cliColor
		return cliColor; //Returns the value in cliColor
	}
	
	public void setCliInventoryNum(int inventoryNum) { //setter for cliInventoryNum
		this.cliInventoryNum = inventoryNum; //Assigns the value into cliInventoryNum of this class
	}
	
	public int getCliInventoryNum() { //getter for cliInventoryNum
		return cliInventoryNum; //Returns the value in cliInventoryNum
	}
	
	public String getBikeType() { //getter for bikeType
		return bikeType; //Returns the value in bikeType
	}
	public void setBikeType(String bikeType) { //setter for bikeType
		this.bikeType = bikeType; //Assigns the value into bikeType of this class
	}
	public String getModelNum() { //getter for modelNum
		return modelNum; //Returns the value in modelNum
	}
	public void setModelNum(String modelNum) { //setter for modelNum
		this.modelNum = modelNum; //Assigns the value into modelNum of this class
	}
	public int getPriceBought() { //getter for priceBought
		return priceBought; //Returns the value in priceBought
	}
	public void setPriceBought(int priceBought) { //setter for priceBought
		this.priceBought = priceBought; //Assigns the value into priceBought of this class
	}
	public int getPriceSold() { //getter for priceSold
		return priceSold; //Returns the value in pricesSold
	}
	public void setPriceSold(int priceSold) { //setter for priceSold
		this.priceSold = priceSold; //Assigns the value into priceSold of this class
	}
	public String getColor() { //getter for color
		return color; //Returns the value in color
	}
	public void setColor(String color) { //setter for color
		this.color = color; //Assigns the value into color of this class
	}
	public int getInventoryNum() { //getter for inventoryNum
		return inventoryNum; //Returns the value in inventoryNum
	}
	public void setInventoryNum(int inventoryNum) { //setter for inventoryNum
		this.inventoryNum = inventoryNum; //Assigns the value into inventoryNum of this class
	}
	public int getAgeRange() { //getter for ageRange
		return ageRange; //Returns the value in ageRange
	}
	public void setAgeRange(int ageRange) { //setter for ageRange
		this.ageRange = ageRange; //Assigns the value into ageRange of this class
	}
	
	public String toString() { //A method used to print out the value of each private data/String type 
		String result = ""; //Initializes a blank String
		result += "Bike Type: " + bikeType + " | Model Num: " + modelNum + " | Purchase Price: $" + priceBought + " | Retail Price: $" + priceSold + " | Bike Color: " + color + " | Num in Inventory: " + inventoryNum + " | Age Range: " + ageRange; //Adds this string to result
		return result; //Returns the value in result
	}
	
}
