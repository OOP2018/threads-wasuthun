/**
 * This class use to solve Counter class problem by using synchronized method to solve it
 * @author wasuthun wanaphongthipakorn
 *
 */
public class SynchronousCounter extends Counter{
	/**
	 * Synchronized method of add by use attribute in superclass
	 */
		@Override
		public synchronized void add (int amount) {
			total=total+amount;
		}
}
