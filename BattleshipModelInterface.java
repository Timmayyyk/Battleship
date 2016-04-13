/**
 * The programatic interface for the Battleship Model class.
 * This interface supports communication with both the view
 * and controller classes in the Battleship application.
 * 
 * @author Sam Cooledge
 * @author Molly Allen
 * @author Tim Davis
 * @author Kellan Nealy
 */

public interface BattleshipModelInterface {
  
	/**
	 * Attempts to place a ship in the board
	 * @param isPlayer1 True is Player 1's turn, false is Player 2's turn
	 * @param ship The reference for the ship to place ('A', 'B', 'C', or 'D')
	 * @param loc The bound-validated starting location to place the ship
	 * @param o The orientation for the ship to be placed in, starting from loc
	 * @return True if ship placement was successful, false otherwise
	 */
	public boolean placeShip(boolean isPlayer1, char ship, String loc, Orientation o);
 
	/**
	 * Attempts to make a shot in the board
	 * @param isPlayer1 True is Player 1's turn, False is Player 2's turn
	 * @param loc The bound-validated location to shoot
	 * @return "Hit", "Miss", "Hit and sunk <ship_name>", or "Unsuccessful"
	 */
	public String makeShot(boolean isPlayer1, String loc);

	/**
	 * Returns Player 1's name
	 * @return Player 1's name
	 */
	public String getPlayer1Name();
	
	/**
	 * Returns Player 2's name
	 * @return Player 2's name
	 */
	public String getPlayer2Name();
	
	/**
	 * Return the array of offensive grid values for player based on passed parameter
	 * @param isPlayer1 True for Player 1, False for Player 2
	 * @return Array of offensive grid values for passed player
	 */
	public char[] getOffensiveGrid(boolean isPlayer1);
 
	/**
	 * Return the array of defensive grid values for player based on passed parameter
	 * @param isPlayer1 True for Player 1, False for Player 2
	 * @return Array of defensive grid values for passed player
	 */
	public char[] getDefensiveGrid(boolean isPlayer1);

	/**
	 * Returns whether or not the game is over
	 * @return True if game is over, false if not
	 */
	public boolean isGameOver();
}
