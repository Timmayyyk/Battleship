/**
 * BattleshipModel class for handling battleship game state
 * 
 * @author Tim Davis
 * @author Kellan Nealy
 */
 
public class BattleshipModel {
	// Model state
	private BoardSquare[][] board;
	private String player1Name;
	private String player2Name;
	private int player1ShipCount;
	private int player2ShipCount;
	private boolean isGameOver;
	
	// Private class constant values
	private static final int BOARD_WIDTH = 10;
	private static final int BOARD_HEIGHT = 10;
	private static final String LETTERS = "ABCDEFGHIJ";
	
	/**
	 * Constructor for BattleshipModel
	 * @param player1 The name of Player 1
	 * @param player2 The name of Player 2
	 */
	public BattleshipModel(String player1, String player2) {
		// Create new board, and populate it with BoardSquares
		board = new BoardSquare[BOARD_WIDTH][BOARD_HEIGHT];
		populateBoard();
		
		// Set initial game state
		player1Name = player1;
		player2Name = player2;
		player1ShipCount = 0;
		player2ShipCount = 0;
		isGameOver = false;
	}
	
	private void populateBoard() {
		for (int row = 0; row < BOARD_HEIGHT; row++) {
			for (int col = 0; col < BOARD_WIDTH; col++) {
				board[row][col] = new BoardSquare();
			}
		}
	}
	
	/**
	 * Attempts to place a ship in the board
	 * @param isPlayer1 True is Player 1's turn, false is Player 2's turn
	 * @param ship The reference for the ship to place ('A', 'B', 'C', or 'D')
	 * @param loc The bound-validated starting location to place the ship
	 * @param o The orientation for the ship to be placed in, starting from loc
	 * @return True if ship placement was successful, false otherwise
	 */
	public boolean placeShip(boolean isPlayer1, char ship, String loc, Orientation o) {
		Ship shipToPlace = getShip(ship);
		int startRow = getRow(loc);
		int startCol = getCol(loc);
		int dx = o.dx;
		int dy = o.dy;
		
		// attempt to place the ship in each BoardSquare
		for (int i = 0; i < shipToPlace.getLength(); i++) {
			int row = startRow + i * dy;
			int col = startCol + i * dx;
			
			if (row < 0 || row > 9 || col < 0 || col > 9) {
				return false;
			}
			
			Ship locToChange = (isPlayer1) ? board[row][col].P1Ship : board[row][col].P2Ship;
			
			if (locToChange != null) {
				shipToPlace = null;
				return false;
			} else {
				if (isPlayer1) {
					board[row][col].P1Ship = shipToPlace;
				} else {
					board[row][col].P2Ship = shipToPlace;
				}
			}
		}
		
		incrementShipCount(isPlayer1);
		return true;
	}
	
	/**
	 * Private helper to get a new ship object from a ship reference
	 * @param shipReference Character ship reference
	 * @return New ship object
	 */
	private Ship getShip(char shipReference) {
		if (shipReference == AircraftCarrier.REFERENCE) {
			return new AircraftCarrier();
		} else if (shipReference == Battleship.REFERENCE) {
			return new Battleship();
		} else if (shipReference == Cruiser.REFERENCE) {
			return new Cruiser();
		} else if (shipReference == Destroyer.REFERENCE) {
			return new Destroyer();
		} else {
			return null;
		}
	}
	
	/**
	 * Private helper for incrementing players' ship counts
	 * @param isPlayer1 True is Player 1's turn, false is Player 2's turn
	 */
	private void incrementShipCount(boolean isPlayer1) {
		if (isPlayer1) {
			player1ShipCount++;
		} else {
			player2ShipCount++;
		}
	}
	
	/**
	 * Attempts to make a shot in the board
	 * @param isPlayer1 True is Player 1's turn, False is Player 2's turn
	 * @param loc The bound-validated location to shoot
	 * @return "Hit", "Miss", "Hit and sunk <player_name>'s <ship_name>", or "Unsuccessful"
	 */
	public String makeShot(boolean isPlayer1, String loc) {
		int row = getRow(loc);
		int col = getCol(loc);
		BoardSquare current = board[row][col];
		
		boolean isShot = (isPlayer1) ? current.P1Offensive : current.P2Offensive;
		if (isShot) {
			return "Unsuccessful";
		} else {
			// loc has not been shot by player, set it as shot
			if (isPlayer1) {
				current.P1Offensive = true;
			} else {
				current.P2Offensive = true;
			}
			
			Ship target = (isPlayer1) ? board[row][col].P2Ship : board[row][col].P1Ship;
			if (target == null) {
				return "Miss";
			} else {
				target.damage++;
				if (target.damage == target.getLength()) {
					// ship is destroyed, decrement ship count
					if (isPlayer1) {
						player2ShipCount--;
					} else {
						player1ShipCount--;
					}
					isGameOver = player1ShipCount <= 0 || player2ShipCount <= 0;
					
					String targetPlayerName = (isPlayer1) ? player1Name : player2Name;
					return "Hit and sunk " + targetPlayerName + "'s " + target.getReference() + "!";
				}
			}
		return "Hit";
		}
	}
	
	/**
	 * Private getter for the zero-based row index for the board
	 * @param pos The board location to get the row of (i.e. A1)
	 * @return Zero-based row index for the board
	 */
	private int getRow(String pos) {
		return LETTERS.indexOf(pos.charAt(0));
	}
	
	/**
	 * Private getter for the zero-based col index for the board
	 * @param pos The board location to get the col of (i.e. A1)
	 * @return Zero-based col index for the board
	 */
	private int getCol(String pos) {
		return Integer.parseInt(pos.substring(1)) - 1;
	}
	
	/**
	 * Returns Player 1's name
	 * @return Player 1's name
	 */
	public String getPlayer1Name() {
		return player1Name;
	}
	
	/**
	 * Returns Player 2's name
	 * @return Player 2's name
	 */
	public String getPlayer2Name() {
		return player2Name;
	}
	
	/**
	 * Return the array of offensive grid values for player based on passed parameter
	 * @param isPlayer1 True for Player 1, False for Player 2
	 * @return Array of offensive grid values for passed player
	 */
	public char[] getOffensiveGrid(boolean isPlayer1) {
		char[] offenseGridVals = new char[BOARD_WIDTH * BOARD_HEIGHT];
		
		for (int row = 0; row < BOARD_WIDTH; row++) {
			for (int col = 0; col < BOARD_HEIGHT; col++) {
				//value begins as not shot
				char gridVal = ' ';
				
				//get and return proper offensive grid values
				BoardSquare current = board[row][col];
				boolean isShot = (isPlayer1) ? current.P1Offensive : current.P2Offensive;
				boolean hasShip = false;
				if (isPlayer1) {
					if (current.P2Ship != null) { 
						hasShip = true;
					}
				} else {
					if (current.P1Ship != null) {
						hasShip = true;
					}
				}
				
				if (isShot) {
					gridVal = hasShip ? 'H' : 'M';
				}
				
				offenseGridVals[row * BOARD_WIDTH + col] = gridVal;
			}
		}
		return offenseGridVals;
	}
	
	/**
	 * Return the array of defensive grid values for player based on passed parameter
	 * @param isPlayer1 True for Player 1, False for Player 2
	 * @return Array of defensive grid values for passed player
	 */
	public char[] getDefensiveGrid(boolean isPlayer1) {
		char[] defenseGridVals = new char[BOARD_WIDTH * BOARD_HEIGHT];
		
		for (int row = 0; row < BOARD_WIDTH; row++) {
			for (int col = 0; col < BOARD_HEIGHT; col++) {
				//value begins as empty
				char gridVal = ' ';
				
				//get and return proper defensive grid values
				BoardSquare current = board[row][col];
				Ship ship = (isPlayer1) ? current.P1Ship : current.P2Ship;
				
				if (ship != null) {
					gridVal = ship.getReference();
				}
				
				defenseGridVals[row * BOARD_WIDTH + col] = gridVal;
			}
		}
		return defenseGridVals;
	}

	/**
	 * Returns whether or not the game is over
	 * @return True if game is over, false if not
	 */
	public boolean isGameOver() {
		return isGameOver;
	}
}
