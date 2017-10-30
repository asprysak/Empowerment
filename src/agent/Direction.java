package agent;

public class Direction {
	
	int x;
	int y;
	
	public Direction(int x, int y) {
		this.x = x;
		this.y = y;
	}

	Position calcDirection(Position p) {
		return new Position(p.x + x, p.y + y);
	}	
}
