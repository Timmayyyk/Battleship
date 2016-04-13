/**
 * Destroyer class
 * 
 * @author Tim Davis
 * @author Kellan Nealy
 */

public class Destroyer extends Ship {
	// Destroyer state
	private static final int LENGTH = 2;
	public static final char REFERENCE = 'D';
	
	/**
	 * Constructor for Destroyer
	 */
	public Destroyer() {
		super(LENGTH, REFERENCE);
	}
	
	/**
	 * Public getter for Destroyer length
	 */
	public int getLength() {
		return LENGTH;
	}
}
