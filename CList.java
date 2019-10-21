/*************************************************************************
 * This class is for a circular linked list. You will notice that it has 
 * the same attributes as in a regular linked list. What will be different 
 * though is the way we manipulate the list.
 * Also, instead of calling a node the head, we call it start since there 
 * is no more head of the list
 *************************************************************************/ 

public class CList<T> {
    private Node<T> start;
    private int size;
    
    // CONSTRUCTORS ******************************************************
    public CList() {}
    
    // TODO 1: Complete the following constructor that takes a node as a parameter
    // Pre-condition: N is a single node
    public CList(Node<T> N) { 
        start = N;
        start.setNext(N);
    }
    
    // SETTERS ***********************************************************
    // TODO 2: Write a setter method for setting the attribute start:
    // YOUR CODE (INCLUDING THE SIGNATURE) GOES HERE...
    public void setStart(Node<T> N) {
    	start = N;
    }
    
    // no setter for the size since it is a consequence of other methods
        
    // GETTERS ***********************************************************
    // TODO 3 & TODO 4: Write a getter method for accessing each of the attributes:
    // YOUR CODE (INCLUDING SIGNATURE) GOES HERE...
    public Node<T> getStart(){
    	return start;
    }
    
    public int getSize() {
    	return size;
    }
    
    
    // OTHER METHODS *****************************************************
    
    // ADDING NODES OR SEQUENCES OF NODES ////////////////////////////////
    /* Method addAtEnd: 
     *      Takes a node N 
     *      Adds it to the circle "at the end", i.e., just before start.
     *      Notes: 1/ take into account when the list is null
     *          2/ Make sure to update the size
     ********************************************************************/
    public void addAtEnd(Node<T> N) {
        // YOUR CODE GOES HERE...
		Node<T> iter = start;
    	if(start == null) {
    		start = N;
    		N.setNext(start);
    		this.size++;
    	}
    	else {
    		size += N.sizeFromNode();
    		while(iter.getNext() != start){
    			iter = iter.getNext();
    		}
    	
    	
    		iter.setNext(N);
    		N.setNext(start);

    	}
		
    }
    
    /* Method addDataAtEnd: 
     *      Takes data of type T 
     *      Creates a node that contains T
     *      Adds it to the circle "at the end", i.e., just before start.
     *      Notes: 1/ take into account when the list is null
     *          2/ Make sure to update the size
     ********************************************************************/
    public void addDataAtEnd(T data) {
        // YOUR CODE GOES HERE...
    	Node<T> N = new Node<T>(data);
    	this.addAtEnd(N);
    }
    
    /* Method addAtStart: 
     *      Takes a node N 
     *      Adds it to the circle just before start and makes it the new start.
     *      Notes: 1/ take into account when the list is null
     *          2/ Make sure to update the size
     ********************************************************************/
    public void addAtStart(Node<T> N) {
        // YOUR CODE GOES HERE...
    	if(start == null) {
    		start = N;
    		N.setNext(start);
    		this.size++;
    	}
    	else {
    		this.size++;
    		Node<T> iter = start;
    		iter = iter.getNext();
    		start.setNext(N);
    		N.setNext(iter);
    	}
    }
    
    /* Method addAtLocation: 
     *      Takes a node N and an integer n
     *      Adds N to the circle just after the n-th node in the circle
     *          (where start is the first node)
     *      Notes: 1/ take into account when the list is null or has 
     *          less than n nodes
     *          2/ Make sure to update the size
     ********************************************************************/
    public void addAtLocation(Node<T> N, int n) {
        // YOUR CODE GOES HERE...
    	if(size <= n) {
    		System.out.println(size);
    		this.addAtEnd(N);
    		this.size++;
    	}
    	else {
    		this.size++;
    		Node<T> iter = start;
    		Node<T> iter2 = start;
    		
    		for(int i = 1; i < n-1; i++) {
    			iter = iter.getNext();
    			iter2 = iter2.getNext();
    		}
    		
    		iter2 = iter2.getNext();
    		
    		iter.setNext(N);
    		N.setNext(iter2);
    	}
    }   
    
    /* Method addMultiAtEnd: 
     *      Similar to addAtEnd
     *      But adds N along with its possible sequence of following nodes
     ********************************************************************/
    public void addMultiAtEnd(Node<T> N) {
        // YOUR CODE GOES HERE...
    	if(start == null) {
    		start = N;
    		N.setNext(start);
    		this.size++;
    	}
    	else {
    		size += N.sizeFromNode();
    	
    		Node<T> iter = start;
    		Node<T> iter2 = N;
    	
    		while(iter.getNext() != start) {
    			iter = iter.getNext();
    		}
    		
    		iter.setNext(N);
    		
    		while(iter2.getNext() != null) {
    			iter2 = iter2.getNext();
    		}
    	
    		iter2.setNext(start);
    	}
    }
    
    /* Method addMultiAtStart: 
     *      Similar to addAtStart
     *      But adds N along with its possible sequence of following nodes
     ********************************************************************/
    public void addMultiAtStart(Node<T> N) {
        // YOUR CODE GOES HERE...
    	if(start == null) {
    		start = N;
    		N.setNext(N);
    		this.size++;
    	}
    	else {
    		size += N.sizeFromNode();
    	
    		Node<T> iter = start;
    		Node<T> iter2 = N;
    	
    		while(iter2.getNext() != null) {
    			iter2 = iter2.getNext();
    		}
    	
    		iter = iter.getNext();
    	
    		start.setNext(N);
    		iter2.setNext(iter);
    	}
    }
    
    /* Method addMultiAtLocation: 
     *      Similar to addAtLocation
     *      But adds N along with its possible sequence of following nodes
     ********************************************************************/
    public void addMultiAtLocation(Node<T> N, int n) {
        // YOUR CODE GOES HERE...
    	if(size <= n) {
    		this.addMultiAtEnd(N);
    	}
    	else {
    		size += N.sizeFromNode();
    		
    		Node<T> iter = start;
    		Node<T> iter2 = N;
    		Node<T> iter3 = start;
    		
    		for(int i = 1; i < n-1; i++) {
    			iter = iter.getNext();
    			iter3 = iter3.getNext();
    		}
    		
    		iter3 = iter3.getNext();
    		
    		while(iter2.getNext() != null) {
    			iter2 = iter2.getNext();
    		}
    		
    		iter.setNext(N);
    		iter2.setNext(iter3);
    	}
    }   
    
    // REMOVING NODES OR SEQUENCES OF NODES ////////////////////////////////

    /* Method removeStart: 
     *      Removes the start node
     *      Makes the next node in sequence the start
     *  Notes: 1/ take into account the case where the list is empty or 
     *      has only one node
     *      2/ do not forget to update the value of size
     ********************************************************************/
    public void removeStart() {
        // YOUR CODE GOES HERE...
    	if(size <= 1) {
    		start = null;
    		size = 0;
    	}
    	else {
    		this.size--;
    		Node<T> iter = start;
    		
    		while(iter.getNext() != start) {
    			iter = iter.getNext();
    		}
    		start = start.getNext();
    		iter.setNext(start);
    	}
    }
    
    /* Method removeLast: 
     *      Removes the node just before start in the circle (i.e., the last node)
     *  Notes: 1/ take into account the case where the list is empty or 
     *      has only one node
     *      2/ do not forget to update the value of size
     ********************************************************************/
    public void removeLast() {
        // YOUR CODE GOES HERE...
    	if(start == null) {
    		
    	}
    	else {
	    	this.size--;
	    	Node<T> iter = start;
	    	while(iter.getNext().getNext() != start) {
	    		iter = iter.getNext();
	    	}
	    	
	    	iter.setNext(start);
    	}
    }
    
    /* Method removeNode: 
     *      Takes a node N
     *      Removes this node N from the list if it is in the list
     *  Notes: 1/ take into account the case where N is not in the list,
     *      or the list is empty 
     *      2/ do not forget to update the value of size if relevant
     ********************************************************************/
    public void removeNode(Node<T> N) {
        // YOUR CODE GOES HERE...
    	if(start == null) {
    		
    	}
    	else {
	    	this.size--;
	    	Node<T> iter = start;
	    	Node<T> iter2 = start;
	    	
	    	while(iter.getNext() != start) {
	    		if(iter.getNext().getData().toString().contentEquals(N.getData().toString())) {
	    			iter.setNext(iter2.getNext().getNext());
	    		}
	    		else {
	    			iter = iter.getNext();
	    			iter2 = iter2.getNext();
	    		}
	    	}
    	}
    }
    
    /* Method removeLocation: 
     *      Takes an integer n
     *      Removes the n-th node from the list if there is such a node
     *  Notes: 1/ take into account the case there are fewer nodes than n
     *      in the list
     *      2/ do not forget to update the value of size if relevant
     ********************************************************************/
    public void removeLocation(int n) {
        // YOUR CODE GOES HERE...
    	if(size <= n) {
    		System.out.println("List does not contain Node #" + n);
    	}
    	else if(n == 1) {
    		removeStart();
    	}
    	else {
	    	this.size--;
	    	Node<T> iter = start;
	    	Node<T> iter2 = start;
	    	
	    	for(int i = 1; i < n-1; i++) {
	    		iter = iter.getNext();
	    		iter2 = iter2.getNext();
	    	}
	    	
	    	iter2 = iter2.getNext().getNext();
	    	
	    	iter.setNext(iter2);
    	}
    }
    
    // PRINTING THE CONTENT OF A CLIST //////////////////////////////////
    /* Method print: 
     *      Prints every element of the circle once
     *      Prints "There is nothing to print" if the list is empty
     ********************************************************************/
    public void print() {
        // YOUR CODE GOES HERE...
    	if(start == null) {
    		System.out.println("There is nothing to print");

    	}
    	else {
	    	Node<T> iter = start;
	    	
	    	do{
	    		iter.printNode();
	    		iter = iter.getNext();
	    	}while(iter != start);
    	}
    }
    
    /*******************************************************************/
    /* Method: ChildrenRonde: 
     * It applies to a circular linked list and takes an integer s 
     *      (given a CList L, you will call it as L.ChildrenRonde(s)). 
     * It successively removes every s-th child from the circle until 
     *      only one child is left. 
     * It does not return anything, but it directly modifies the list 
     *      of children, which contains only one child at the end of 
     *      the game, the winner. 
     * NOTE: make sure to handle special cases like when the list may
     *      be empty
     * ALSO: if the list contains only one element, print out:
     *      "There is only one element remaining: "
     *      and then print the node (its content) using the appropriate
     *      method
     *******************************************************************/
    public void ChildrenRonde(int s) {
        // YOUR CODE GOES HERE...
		Node<T> iter = start;
		Node<T> iter2 = start;
    	int a = 0;
    	
    	if(size <= 0) {
    		System.out.println("Contains 0 Elements");
    	}
    	else if(size == 1) {
    		System.out.println("There is only one element remaining: ");
    		iter.printNode();
    	}
    	else {
    		if(s < 1) {
    			while(size != 1) {
    				while(a + 2 < s) {
    					iter = iter.getNext();
    					a++;
    				}
    				iter2 = iter.getNext().getNext();
    				
    				if(iter.getNext() == start) {
    					start = iter2;
    				}
    				iter.setNext(iter2);
    				size--;
    				a = 0;
    				iter = iter.getNext();
    			}
    		}
    		else if(s == 1) {
    			while(iter.getNext() != start) {
    				iter = iter.getNext();
    			}
    			start = iter;
    			iter.setNext(iter);
    		}
    		System.out.println("There is only one element remaining: ");
    	}
		start.printNode();
    }

}