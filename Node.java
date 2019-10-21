
public class Node {
	private int data;
	private Node next;
	
	public Node() {}
	
	public Node(int d){
		data = d;
		next = null;
	}
	
	public void setData(int d) {
		data = d;
	}
	
	public void setNext(Node t) {
		next = t;
	}
	
	public int getData() {
		return data;
	}
	
	public Node getNext() {
		return next;
	}
	
	public void printNode() {
        System.out.println(" " + data);   
    }
}
