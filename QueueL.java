
public class QueueL {
	
	private int size; //private integer variable size
	private Node head; //private node variable head
	private Node tail; //private node variable tail
	
	public QueueL() {} //Default Constructor
	
	public QueueL(Node element) { //Constructor that sets the head, starting the queue, and increases the size by 1.
		this.head = element;
		this.tail = element;
		this.head.setNext(this.tail);
		this.size++;
	}
	
	public int getSize() { //Returns the value of size (getter).
		return this.size;
	}
	
	public void enqueue(int element) { //This method adds a new Node to the queue, while making the new Node the new tail and increasing size.
		Node temp = new Node(element);
		Node iter = this.tail;
		if(this.isEmpty()) {
			this.head = temp;
			this.tail = temp;
			this.head.setNext(this.tail);
			this.size++;
			return;
		}
		iter.setNext(temp);
		this.tail = iter.getNext();
		this.size++;
	}
	
	public Integer dequeue(){ //This method removes the head Node in the queue
		if(this.isEmpty()) {
			return null;
		}
		int tempHead = this.head.getData();
		this.head = this.head.getNext();
		return tempHead;
	}
	
	public Integer peek(){ //This method returns the value of the head Node in the queue
		if(this.isEmpty()) {
			return null;
		}
		return (this.head.getData());
	}
	
	public void clear() { //This method clears the entire queue of its elements
		this.head.setNext(null);
		this.tail.setNext(null);
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	public boolean isEmpty() { //This method checks if the queue is empty
		return (this.head == null && this.size == 0);
	}
}
