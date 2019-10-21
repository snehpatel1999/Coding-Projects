
public class Execute {
	public static <T> void main(String[] args) {
		
		
		Node top = new Node(4);
		Node head = new Node(1);
		StackA stackArr = new StackA(5);
		QueueA queueArr = new QueueA(5);
		StackL stackLin = new StackL(top);
		QueueL queueLin = new QueueL(head);
		stackArr.push(1);
		stackArr.push(2);
		stackArr.push(3);
		stackArr.push(4);
		stackArr.push(5);
		queueArr.enqueue(1);
		queueArr.enqueue(2);
		queueArr.enqueue(3);
		queueArr.enqueue(4);
		queueArr.enqueue(5);
		System.out.println("Arr Size: " + stackArr.getSize());
		System.out.println("QueueArr Size: " + queueArr.getSize());
		System.out.println("Arr Full: " + stackArr.isFull());
		System.out.println("QueueArr Full: " + queueArr.isFull());
		System.out.println("Arr: " + stackArr.peek());
		System.out.println("QueueArr: " + queueArr.peek());
		stackArr.pop();
		queueArr.dequeue();
		System.out.println("Arr: " + stackArr.peek());
		System.out.println("QueueArr: " + queueArr.peek());
		stackArr.pop();
		queueArr.dequeue();
		System.out.println("Arr: " + stackArr.peek());
		System.out.println("QueueArr: " + queueArr.peek());
		stackArr.pop();
		queueArr.dequeue();
		System.out.println("Arr: " + stackArr.peek());
		System.out.println("QueueArr: " + queueArr.peek());
		stackArr.pop();
		queueArr.dequeue();
		System.out.println("Arr: " + stackArr.peek());
		System.out.println("QueueArr: " + queueArr.peek());
		stackArr.pop();
		queueArr.dequeue();
		stackArr.clear();
		queueArr.clear();
		System.out.println("Arr Empty: " + stackArr.isEmpty());
		System.out.println("QueueArr Empty: " + queueArr.isEmpty());
		stackLin.push(5);
		stackLin.push(6);
		stackLin.push(7);
		queueLin.enqueue(2);
		queueLin.enqueue(3);
		queueLin.enqueue(4);
		queueLin.enqueue(5);
		System.out.println("Queue Linked List Size: " + queueLin.getSize());
		System.out.println("Queue Linked List: " + queueLin.peek());
		queueLin.dequeue();
		System.out.println("Queue Linked List: " + queueLin.peek());
		queueLin.dequeue();
		System.out.println("Queue Linked List: " + queueLin.peek());
		queueLin.dequeue();
		System.out.println("Queue Linked List: " + queueLin.peek());
		queueLin.dequeue();
		System.out.println("Queue Linked List: " + queueLin.peek());
		queueLin.clear();
		System.out.println("Queue Linked List Empty: " + queueLin.isEmpty());
		System.out.println("Linked List Size: " + stackLin.getSize());
		System.out.println("Linked List: " + stackLin.peek());
		stackLin.pop();
		System.out.println("Linked List: " + stackLin.peek());
		stackLin.pop();
		System.out.println("Linked List: " + stackLin.peek());
		stackLin.pop();
		System.out.println("Linked List: " + stackLin.peek());
		stackLin.pop();
		stackLin.clear();
		System.out.println("Linked List Empty: " + stackLin.isEmpty());
		
		
		
	}
}
