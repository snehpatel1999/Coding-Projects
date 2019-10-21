
public class QueueA {
	
	private int head; //private integer variable head
	private int tail; //private integer variable tail
	private int size; //private integer variable size
	Integer[] queue; //Integer class array variable queue
	
	public QueueA(int max) { //Constructor that sets the max size of the array 
		this.queue = new Integer[max];
	}
	
	public int getSize() { //Returns the value in size (getter).
		return this.size;
	}
	
	public void enqueue(int element) { //This method adds an int variable on the next spot in the array where tail is pointing and increases size
		if(this.isFull()) {
			return;
		}
		this.queue[tail] = element;
		this.size++;
		this.tail = (this.tail + 1) % this.queue.length;
	}
	
	public Integer dequeue() { //This method removes of the head element in the array 
		if(this.isEmpty()) {
			return null;
		}
		int temp = this.queue[this.head];
		this.queue[this.head] = null;
		this.head++;
		this.size--;
		return temp;
	}
	
	public Integer peek() { //This method returns the value in the head element in the array
		if(this.isEmpty()) {
			return null;
		}
		return this.queue[this.head];
	}
	
	public void clear() { //This method removes all elements from the array and sets the size to 0
		for(int i = 0; i < queue.length; i++) {
			queue[i] = null;
		}
		this.size = 0;
		this.head = 0;
		this.tail = 0;
		
	}
	
	public boolean isEmpty() { //This method checks if the array is empty
		return (this.getSize() == 0);
	}
	
	public boolean isFull() { //This method checks if the array is full
		return (this.size == this.queue.length);
	}
}
