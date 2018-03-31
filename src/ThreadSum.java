import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * This class is use to run thread to test speed
 * 
 * @author wasuthun wanaphongthipakorn
 *
 */
public class ThreadSum {
	/**
	 * Main method
	 * 
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		// upper limit of numbers to add/subtract to Counter
		final int LIMIT = 10000000;
		int poolsize = 5;
		// The counter that accumulates a total.
		Counter counter = new SynchronousCounter();
		// runThreads(counter, LIMIT);
		runThreads(poolsize, counter, LIMIT);
	}

	/**
	 * Run 1 adders and 1 subtracters.
	 * 
	 * @param counter
	 * @param limit
	 */
	public static void runThreads(Counter counter, final int limit) {

		// two tasks that add and subtract values using same Counter
		AddTask addtask = new AddTask(counter, limit);
		SubtractTask subtask = new SubtractTask(counter, limit);
		// threads to run the tasks
		Thread thread1 = new Thread(addtask);
		Thread thread2 = new Thread(subtask);
		// start the tasks
		System.out.println("Starting threads");
		long startTime = System.nanoTime();
		thread1.start();
		thread2.start();
		// wait for threads to finish
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			System.out.println("Threads interrupted");
		}
		double elapsed = 1.0E-9 * (System.nanoTime() - startTime);
		System.out.printf("Count 1 to %,d in %.6f sec\n", limit, elapsed);
		// the sum should be 0. Is it?
		System.out.printf("Counter total is %d\n", counter.get());
	}

	/**
	 * Run nthread adders and nthread subtracters.
	 * 
	 * @param nthread
	 * @param counter
	 * @param limit
	 * @throws InterruptedException
	 */
	public static void runThreads(int nthread, Counter counter, final int limit) throws InterruptedException {
		ExecutorService executor = Executors.newFixedThreadPool(2 * nthread);
		// start the tasks
		System.out.println("Starting tasks");
		long startTime = System.nanoTime();
		for (int k = 1; k <= nthread; k++) {
			AddTask addtask = new AddTask(counter, limit);
			SubtractTask subtask = new SubtractTask(counter, limit);
			executor.submit(addtask);
			executor.submit(subtask);
		}
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.MINUTES); // wait at most 1 minute
		System.out.println("All down");
		double elapsed = 1.0E-9 * (System.nanoTime() - startTime);
		System.out.printf("Count 1 to %,d in %.6f sec\n", limit, elapsed);
		// the sum should be 0. Is it?
		System.out.printf("Counter total is %d\n", counter.get());
	}
}

/**
 * AddTask adds number 1 ... limit to the counter, then exits.
 */
class AddTask implements Runnable {
	private Counter counter;
	private int limit;

	public AddTask(Counter counter, int limit) {
		this.counter = counter;
		this.limit = limit;
	}

	public void run() {
		for (int k = 1; k <= limit; k++)
			counter.add(k);
		// If you want to see when a thread finishes, add this line:
		// System.out.println("Done "+Thread.currentThread().getName());
	}
}

/**
 * SubtractTask adds number -1 ... limit to the counter, then exits.
 */
class SubtractTask implements Runnable {
	private Counter counter;
	private int limit;

	public SubtractTask(Counter counter, int limit) {
		this.counter = counter;
		this.limit = limit;
	}

	@Override
	public void run() {
		for (int i = 1; i <= limit; i++) {
			counter.add(-i);
		}
		// System.out.println("Done "+Thread.currentThread().getName());
	}

}
