// 3.5 an implementation a queue using two stacks
public class QueueUse2Stack {
	StackC S1 = new StackC();
	StackC S2 = new StackC();
	
	public static void main(String[] args) {
		QueueUse2Stack q = new QueueUse2Stack();
		q.enqueue(1);
		q.enqueue(2);
		q.enqueue(3);
		q.enqueue(4);
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		
	}
	
	public void enqueue(Integer value) {
		S1.push(value);
	}
	
	public Integer dequeue() {
		if (!S2.isEmpty())
			return S2.pop();
		//	else
		while (!S1.isEmpty())
			S2.push(S1.pop());
		return S2.pop();
	}
 }
