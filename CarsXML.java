package cs3331programs;
import java.io.*;
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * TODO Put here a description of what this class does.
 *
 * @author sneh.
 *         Created Oct 9, 2019.
 */

public class CarsXML {
	
	private boolean finalSegment = false;
	private double location = 0.0;
	private double speed = 0.0;
	private double topSpeed = 0.0;
	private double acceleration = 0.00284166667;
	private double time = 0.0;
	private ArrayList<Double> distance = new ArrayList<Double>();
	private ArrayList<Double> speedList = new ArrayList<Double>();

	
	public CarsXML() { 
		this.addDistance(0);
		this.addSpeedList(0);
	}
	
	public CarsXML(double location, double speed, double topSpeed, double acc, double t) {
		this.setLocation(location);
		this.setSpeed(speed);
		this.setTopSpeed(topSpeed);
		this.setAcceleration(acc);
		this.setTime(t);
		this.addDistance(0);
		this.addSpeedList(0);
	}
	
	public void setFinalSegment(boolean finalSeg) {
		this.finalSegment = finalSeg;
	}
	
	public void setLocation(double location) {
		this.location = location;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public void setTopSpeed(double topSpeed) {
		this.topSpeed = topSpeed;
	}
	
	public void setAcceleration(double acceleration) {
		this.acceleration = acceleration;
	}
	
	public void setTime(double time) {
		this.time = time;
	}
	
	public void addDistance(double distance) {
		this.distance.add(distance);
	}
	
	public void addSpeedList(double speedList) {
		this.speedList.add(speedList);
	}
	
	public boolean getFinalSegment() {
		return this.finalSegment;
	}
	
	public double getLocation() {
		return this.location;
	}
	
	public double getSpeed() {
		return this.speed;
	}
	
	public double getTopSpeed() {
		return this.topSpeed;
	}
	
	public double getAcceleration() {
		return this.acceleration;
	}
	
	public double getTime() {
		return this.time;
	}
	
	public ArrayList<Double> getDistance(){
		return this.distance;
	}
	
	public ArrayList<Double> getSpeedList(){
		return this.speedList;
	}
	
	class Segment {
		
		private int Segment_Number;
		private double Segment_Length;
		private double Segment_SpeedLimit;
		
		public Segment() { }
		
		public Segment(int Seg_Num, double Seg_Len, double Seg_SpeedLim) {
			this.setSegment_Number(Seg_Num);
			this.setSegment_Length(Seg_Len);
			this.setSegment_SpeedLimit(Seg_SpeedLim);
		}
		
		public void setSegment_Number(int Seg_Num) {
			this.Segment_Number = Seg_Num;
		}
		
		public void setSegment_Length(double Seg_Len) {
			this.Segment_Length = Seg_Len;
		}
		
		public void setSegment_SpeedLimit(double Seg_SpeedLim) {
			this.Segment_SpeedLimit = Seg_SpeedLim;
		}
		
		public int getSegment_Number() {
			return this.Segment_Number;
		}
		
		public double getSegment_Length() {
			return this.Segment_Length;
		}
		
		public double getSegment_SpeedLimit() {
			return this.Segment_SpeedLimit;
		}
		
	}
	
	public void XMLDistanceCalculation(String input) {
		try {
			File inputFile = new File(input);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("SEGMENT");

			//while(getFinalSegment() == false) {
			int tempLength = nList.getLength();
			ArrayList<Segment> stackSegment = new ArrayList<Segment>();
			
			while(stackSegment.size() != nList.getLength()) {
				
				for (int temp = 0; temp < nList.getLength(); temp++) {
					Node nNode = nList.item(temp);

					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNode;
						if(Double.parseDouble(eElement.getElementsByTagName("LENGTH").item(0).getTextContent()) < 0.5) {
							System.out.println("SEGMENT NUMBER" + eElement.getElementsByTagName("SEGMENT_NUMBER").item(0).getTextContent() + "has a length less than 0.5. Please Fix and Try Again!");
							System.exit(0);
						}
						if(eElement.getElementsByTagName("SEGMENT_NUMBER").item(0).getTextContent().contains(Integer.toString(tempLength))) {
							Segment seg = new Segment(tempLength, Double.parseDouble(eElement.getElementsByTagName("LENGTH").item(0).getTextContent()), Double.parseDouble(eElement.getElementsByTagName("SPEED_LIMIT").item(0).getTextContent()));
							stackSegment.add(0, seg);
							tempLength--;
						}
						}
					}
				}
			
			double tempSegLen = 0;
			
			for(int i = 0; i < stackSegment.size(); i++) {
				
				setTime(30);

				tempSegLen += stackSegment.get(i).getSegment_Length();
				
				while(getLocation() < tempSegLen) {
					
					double tempT = 0;
					double tempS = 0;
					double tempV = 0;
					double tempL = 0;
					double tempDecT = 0;
					double tempDecS = 0;
					double tempDecV = 0;
					
					setTopSpeed((stackSegment.get(i).Segment_SpeedLimit)/3600);
					
					tempT = Math.abs((getTopSpeed() - getSpeed())/getAcceleration());
					if(i >0) {
						tempS = (getSpeed()*tempT + 0.5*(getAcceleration() * (tempT * tempT))) + (tempSegLen - stackSegment.get(i-1).getSegment_Length());
					}
					else {
						tempS = (getSpeed()*tempT + 0.5*(getAcceleration() * (tempT * tempT))) + (tempSegLen);
					}
					tempV = Math.min(getAcceleration()*getTime(), getTopSpeed());
					tempL = (tempV*getTime()) + (tempSegLen - 1);
					
					if((i + 1) < stackSegment.size()) {
						if((stackSegment.get(i+1).Segment_SpeedLimit)/3600  >= getTopSpeed()) {
							
							if(tempL < tempS) {
								setLocation(tempS);
								addDistance(tempS);
								setSpeed(tempV);
								addSpeedList(tempV);
							}
							else {
								setLocation(tempL);
								addDistance(tempL);
								setSpeed(tempV);
								addSpeedList(tempV);
							}
	
						}
						else {
							
							if(i > 0) {
								setSpeed(stackSegment.get(i-1).getSegment_SpeedLimit());
							}
							
							tempDecT = Math.abs((((stackSegment.get(i+1).Segment_SpeedLimit)/3600) - getSpeed())/getAcceleration());
							if(i > 0) {
								tempDecS = (getSpeed()*tempDecT - 0.5*(getAcceleration() * (tempDecT * tempDecT))) + (tempSegLen - stackSegment.get(i-1).getSegment_Length());
							}
							else {
								tempDecS = (getSpeed()*tempDecT - 0.5*(getAcceleration() * (tempDecT * tempDecT))) + (tempSegLen);
							}
							tempDecV = Math.max(getSpeed() - getAcceleration()*getTime(), stackSegment.get(i+1).Segment_SpeedLimit/3600);
							
							if(tempL < tempS) {
								setLocation(tempS);
								addDistance(tempS);
								setSpeed(tempV);
								addSpeedList(tempV);
							}
							else if(tempL >= tempS && tempL <= tempDecS) {
								setLocation(tempL);
								addDistance(tempL);
								setSpeed(tempV);
								addSpeedList(tempV);
							}
							else {
								setLocation(tempDecS);
								addDistance(tempDecS);
								setSpeed(tempDecV);
								addSpeedList(tempDecV);
							}
						}
					}
					else {
						setLocation(tempL);
						addDistance(tempL);
						setSpeed(tempV);
						addSpeedList(tempV);
					}
					setTime(getTime() + 30);
				}
				
			}
	
	
		}
		catch(FileNotFoundException e) {
			System.out.println("The File Name inputed was not found, please correct and try again!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void printTable(CarsXML car1, CarsXML car2, CarsXML car3) {
		
		ArrayList<Double> Car1Distance = car1.getDistance();
		ArrayList<Double> Car2Distance = car2.getDistance();
		ArrayList<Double> Car3Distance = car3.getDistance();
		ArrayList<Double> Car1Speed = car1.getSpeedList();
		ArrayList<Double> Car2Speed = car2.getSpeedList();
		ArrayList<Double> Car3Speed = car3.getSpeedList();
		boolean isEmpty = false;
		double tempTime = 0;
		int iterA = 0;
		int iterB = 0;
		int iterC = 0;
		
		System.out.println("Time\t\tCar A\t\t\t\tCar B\t\t\t\tCar C");
		System.out.println("\t\t\tSpeed\tLocation\t\tSpeed\tLocation\t\tSpeed\tLocation");
		
		while(isEmpty == false) {
			System.out.println(String.format("%06.2f", tempTime) + "\t\t" + String.format("%05.2f", Car1Speed.get(iterA) * 3600) + "\t" + String.format("%.2f", Car1Distance.get(iterA)) 
			+ "\t\t\t" + String.format("%05.2f", Car2Speed.get(iterB) * 3600) + "\t" + String.format("%.2f", Car2Distance.get(iterB)) 
			+ "\t\t\t" + String.format("%05.2f", Car3Speed.get(iterC) * 3600) + "\t" + String.format("%.2f", Car3Distance.get(iterC)));
			
			
			
			if(tempTime < 60) {
					iterA++;
			}
			else if(tempTime >= 60  && tempTime < 120) {
				iterA++;
				iterB++;
			}
			else if(tempTime >= 120) {
				if(Car1Distance.get(iterA) != Car1Distance.get(Car1Distance.size()-1)) {
					iterA++;
				}
				if(Car2Distance.get(iterB) != Car2Distance.get(Car2Distance.size()-1)) {
					iterB++;
				}
				if(Car3Distance.get(iterC) != Car3Distance.get(Car3Distance.size()-1)) {
					iterC++;
				}
				else {
					isEmpty = true;
				}
			}
			
			tempTime += 30;
		}
		
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		
		CarsXML car1 = new CarsXML();
		CarsXML car2 = new CarsXML();
		CarsXML car3 = new CarsXML();
		
		String input;
		Scanner in = new Scanner(System.in);
		System.out.println("Enter File Name:");
		input = in.nextLine();

		car1.XMLDistanceCalculation(input);
		car2.XMLDistanceCalculation(input);
		car3.XMLDistanceCalculation(input);
		
		printTable(car1, car2, car3);
		
		in.close();
	}
	
}
