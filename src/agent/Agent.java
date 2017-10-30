package agent;

public class Agent {
	
	Position position;
	boolean fullPocket;
	
	public Agent(Position position) {
		this.position=position;
	}
	
	public Agent() {
		this.position=new Position();
	}
	
	public void printPosition(){
		System.out.print(position.toString());
		System.out.println();
	}
}
