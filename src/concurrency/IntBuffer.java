package concurrency;

public class IntBuffer {

	public static void main(String[] args) {
		IntBuffer b = new IntBuffer();
		
		Producer p = new Producer(b);
		Consumer c = new Consumer(b);
		p.start();
		c.start();
	}

	private int index;
	private int[] buffer = new int[8];

	public int index() {
		return index;
	}
	public synchronized void add(int num) {
		while (index == buffer.length - 1) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		buffer[index++] = num;
		notifyAll();
	}

	public synchronized int remove() {
		while (index == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		int ret = buffer[--index];
		notifyAll();
		return ret;
	}

}
