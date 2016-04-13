/**
 * Ship class
 * 
 * @author Tim
 * @author Kellan Nealy
 */

public abstract class Ship {
	// Ship state
	private int length;
	private char reference;
	public int damage;
	
	/**
	 * Constructor for Ship object
	 * @param length Designated length for the ship
	 * @param reference Designated reference for the ship ('A', 'B', 'C', 'D')
	 */
	public Ship(int length, char reference) {
		this.length = length;
		this.reference = reference;
		damage = 0;
	}
	
	/**
	 * Getter for ship length
	 * @return Ship length
	 */
	public int getLength() {
		return length;
	}
	
	/**
	 * Getter for ship reference
	 * @return Ship reference
	 */
	public char getReference() {
		return reference;
	}
}
