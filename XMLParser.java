package cs3331programs;
import java.io.*;
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.util.Scanner;
public class XMLParser {
	
	
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

	
	public static void main(String[] args) throws FileNotFoundException{

		try {
			String input;
			Scanner in = new Scanner(System.in);
			System.out.println("Enter File Name:");
			input = in.nextLine();
			File inputFile = new File(input);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("Student");  //Entire block of code above is used to read the XML file and translate its to a JDOM object (which can be used to manipulate the data)
			System.out.println("----------------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					System.out.println("Student: Name =" 
							+ eElement.getElementsByTagName("Name").item(0).getTextContent() + ", Rank =  "
							+ eElement.getElementsByTagName("Rank").item(0).getTextContent() + ", GPA =  "
							+ eElement.getElementsByTagName("GPA").item(0).getTextContent());
					
					String inputTxt;
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					DOMSource source = new DOMSource(doc);
					
					System.out.println("CHOOSE: SHOW (S), CHANGE (C), WRITE (W), NEXT (N), PREVIOUS (P), EXIT (E)");
					inputTxt = in.nextLine();
				
					if(inputTxt.charAt(0) == 'S') {
						StreamResult consoleResult = new StreamResult(System.out);
						transformer.transform(source, consoleResult);
						temp -= 1;
					}
					else if(inputTxt.charAt(0) == 'C'){
						String[] TagName;
						TagName = inputTxt.split(" ");
						if(TagName.length <= 1) {
							System.out.println("Must Provide Tag Name (Ex. C GPA 2.0)");
							temp -= 1;
							continue;
						}
						eElement.getElementsByTagName(TagName[1]).item(0).setTextContent(TagName[2]);
						temp -= 1;
					}
					else if(inputTxt.charAt(0) == 'W'){
						String[] fileName;
						fileName = inputTxt.split(" ");
						if(fileName.length <= 1) {
							System.out.println("Must Provide File Name (Ex. W input.txt)");
							temp -= 1;
							continue;
						}
						StreamResult result = new StreamResult(new File(fileName[1]));
						transformer.transform(source, result);
						temp -= 1;
					}
					else if(inputTxt.charAt(0) == 'N'){
						continue;
					}
					else if(inputTxt.charAt(0) == 'P'){
						if((temp -= 2) >= -1) {
							continue;
						}
						else {
						temp += 1;
						System.out.println("There are no more previous elements!");
						continue;
						}
					}
					else if(inputTxt.charAt(0) == 'E') {
						System.exit(0);
					}
						
				}
			}
			in.close();
			System.out.println();

		}
		catch (FileNotFoundException e) {
			System.out.println("The File Name inputed was not found, please correct and try again!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}

