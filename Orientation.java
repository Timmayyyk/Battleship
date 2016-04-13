/**
 * Orientation enum used for ship orientations
 * 
 * @author Tim Davis
 * @author Kellan Nealy
 */
public enum Orientation {
	DD (1, 1),
	DU (1, -1),
	H  (1, 0),
	V  (0, 1);
	
	public final int dx;
	public final int dy;
	
	Orientation(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
	}
}
