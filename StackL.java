
public class StackL {
	
	private int size; //private integer variable size
	private Node top; //private node variable top
	
	public StackL() {} //Default constructor
	
	public StackL(Node t) { //constructor that sets the top(head), starting the stack, and increases the size by 1.
		this.top = t;
		this.size++;
	}
	
	public int getSize() { //Returns the value of size (getter).
		return this.size;
	}
	
	public void push(int element) { //This method adds a new Node to the stack, while making the new Node the top and increases size.
		Node temp = new Node(element);
		temp.setNext(this.top);
		this.top = temp;
		this.size++;
	}
	
	public void pop() { //This method removes the top Node in the stack
		if(!this.isEmpty()) {
			this.top = this.top.getNext();
			this.size--;
		}
	}
	
	public Integer peek(){ //This method returns the value of the top Node in the stack
		if(this.isEmpty()) {
			return null;
		}
		return this.top.getData();
	}
	
	public void clear() { //This method clears the entire stack of its elements
		if(!this.isEmpty()) {
			this.top.setNext(null);
			this.top = null;
		}
	}
	
	public boolean isEmpty() { //This method checks if the stack is empty
		return (this.top == null);
	}
}
