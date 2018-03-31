import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * This class use to solve Counter class problem by use object of ReentrantLock class to use method to solve it
 * @author wasuthun wanaphongthipakorn
 *
 */
public class CounterWithLock extends Counter{
	/**
	 * Attribute object of ReentrantLock class
	 */
		private Lock lock=new ReentrantLock();
		/**
		 * Use amount to super class method by use method lock and unlock to help solve thread problem
		 */
		public void add(int amount) {
			lock.lock();
			try {
				super.add(amount);
			} finally {
				lock.unlock();
			}
		}
}
