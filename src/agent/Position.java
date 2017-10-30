package agent;

public class Position {
	
	int x;
	int y;
	
	public Position(int x, int y) {
		this.x=x;
		this.y=y;	
	}
	
	public Position() {
		
	}
	@Override
	public String toString(){
		return "(" + Integer.toString(x) + ", " + Integer.toString(y) + ")";
	}

	@Override
	public boolean equals(Object obj) {
		Position p = (Position) obj;
		return x == p.x && y == p.y;
	}
	
	@Override
	public int hashCode() {
		return 100000 * x + y;
	}
}
