/**
 * Cruiser class
 * 
 * @author Tim Davis
 * @author Kellan Nealy
 */

public class Cruiser extends Ship {
	// Crusier state
	private static final int LENGTH = 3;
	public static final char REFERENCE = 'C';
	
	/**
	 * Constructor for Cruiser
	 */
	public Cruiser() {
		super(LENGTH, REFERENCE);
	}
	
	/**
	 * Public getter for Cruiser length
	 */
	public int getLength() {
		return LENGTH;
	}
}
