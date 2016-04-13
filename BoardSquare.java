/**
 * BoardSquare object represents all of the players' state for a board location
 * 
 * @author Tim Davis
 * @author Kellan Nealy
 */

public class BoardSquare {
	// BoardSquare state
	boolean P1Offensive;
	boolean P2Offensive;
	Ship P1Ship;
	Ship P2Ship;
	
	/**
	 * Constructor for BoardSquare
	 */
	public BoardSquare() {
		// Set initial BoardSquare state
		P1Offensive = false;
		P2Offensive = false;
	}
}
