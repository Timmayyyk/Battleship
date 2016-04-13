/**
 * AircraftCarrier class
 * 
 * @author Tim Davis
 * @author Kellan Nealy
 */

public class AircraftCarrier extends Ship {
	// AircraftCarrier state
	private static final int LENGTH = 5;
	public static final char REFERENCE = 'A';
	
	/**
	 * Constructor for AircraftCarrier
	 */
	public AircraftCarrier() {
		super(LENGTH, REFERENCE);
	}
	
	/**
	 * Public getter for AircraftCarrier length
	 */
	public int getLength() {
		return LENGTH;
	}
}
