/**
 * Battleship class
 * 
 * @author Tim Davis
 * @author Kellan Nealy
 */

public class Battleship extends Ship {
	// Battleship state
	private static final int LENGTH = 4;
	public static final char REFERENCE = 'B';
	
	/**
	 * Constructor for Battleship
	 */
	public Battleship() {
		super(LENGTH, REFERENCE);
	}
	
	/**
	 * Public getter for Battleship length
	 */
	public int getLength() {
		return LENGTH;
	}
}
