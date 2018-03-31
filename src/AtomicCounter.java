import java.util.concurrent.atomic.AtomicLong;
/**
 * This class use to solve Counter class problem by override method to atomic method and use atomic class to help
 * @author wasuthun wanaphongthipakorn
 *
 */
public class AtomicCounter extends Counter{
	/**
	 * Attribute total in AtomicLong type
	 */
	private AtomicLong total;
	/**
	 * Constructor
	 */
	public AtomicCounter() {
		total = new AtomicLong();
	}
	/** add amount to the total. */
	@Override
	public void add(int amount) {
		total.getAndAdd(amount);
		}
	/** return the total as a long value. */
	@Override
	public long get() {
		return total.get();
	}
}
