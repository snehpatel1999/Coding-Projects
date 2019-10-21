
public class StackA {
	
	private int size; //private integer variable size
	Integer[] stack; //Integer class array variable stack
	
	public StackA(int max) { //Constructor that sets the max size of the array 
		this.stack = new Integer[max];
	}
	
	public int getSize() { //Returns the value in size (getter).
		return this.size;
	}
	
	public void push(int element) { //This method adds an int variable on the next spot in the array and increases size
		if(!this.isFull()) {
			this.stack[size] = element;
			this.size++;
		}
	}
	
	public void pop() { //This method removes of the top/right most element in the array 
		if(!this.isEmpty()) {
			this.stack[size-1] =  null;
			this.size--;
		}
	}

	public Integer peek() { //This method returns the value in the top/right most element in the array
		if(this.isEmpty()) {
			return null;
		}
		return this.stack[size-1];
	}
	
	
	public void clear() { //This method removes all elements from the array and sets the size to 0
		for(int i = 0; i < size; i++) {
			this.stack[i] = null;
		}
		this.size = 0;
	}
	
	public boolean isEmpty() { //This method checks if the array is empty
		return (this.getSize() == 0);
	}
	
	public boolean isFull() { //This method checks if the array is full
		return (this.size == this.stack.length);
	}
}
