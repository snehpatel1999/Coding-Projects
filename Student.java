
/**
 * TODO Put here a description of what this class does.
 *
 * @author sneh.
 *         Created Sep 9, 2019.
 */
public class Student {

	/*
	private UTEPclass[] utepClasses;
	private UTEPclass c;
	private UTEPclass b;
	private UTEPclass d;		
	*/
	private Grade[] grades;
	private int numberOfClasses;
	
	public Student() {
		grades = new Grade[numberOfClasses];
	}
	
}
class UTEPclass {
	
	private Student[] students;
}

class Grade {
	private Student student;
}

class Node {
	private Node[] childNodes;
}