package agent;

import java.util.ArrayList;
import java.util.List;

public class State {
	
	List<FieldType> fields = new ArrayList<FieldType>();
	static List<Direction> dirs = new ArrayList<Direction>();
	int[][] board=new int[8][10];                                //rozmiar planszy
	Agent agent = new Agent();
	
	/*public State(int x, int y){
		board=new int[x][y];
		fields.add(new Hole());   //0
		fields.add(new Ground()); //1
		fields.add(new Block());  //2
	}*/
	
	static {	
		
		dirs.add(new Direction(1, 0));  //0-prawo
		dirs.add(new Direction(-1, 0)); //1-lewo
		dirs.add(new Direction(0, -1)); //2-góra
		dirs.add(new Direction(0, 1));  //3-dół

	}
	
	public State() {

		fields.add(new Hole());   //0
		fields.add(new Ground()); //1
		fields.add(new Block());  //2
	}
	
	class Ground implements FieldType {

		@Override
		public void move(Position position) {
		
			agent.position.x=position.x;
			agent.position.y=position.y;
			
		}

		@Override
		public void interact(Position position) {
			
			if(agent.fullPocket==true) {
				agent.fullPocket=false;
				board[position.x][position.y]=2;
			}
		}
	}
	
	class Hole implements FieldType {

		@Override
		public void move(Position position) {
		
			agent.position.x=position.x;
			agent.position.y=position.y;
			
		}

		@Override
		public void interact(Position position) {
			
			if(agent.fullPocket==true) {
				agent.fullPocket=false;
				board[position.x][position.y]=1;
			}
		}
	}
	
	class Block implements FieldType {

		@Override
		public void move(Position position) {
			
		}

		@Override
		public void interact(Position position) {

			if(agent.fullPocket==false) {
			
				agent.fullPocket=true;
				board[position.x][position.y]=1;
				
			}
		}
	}
	
	public void printBoard(){
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++)
			{
				System.out.print(board[j][i]);
				System.out.print("   ");
			}
			System.out.println();

		}	
		System.out.println();
	}
	
	boolean isBlocked() {
		return board[agent.position.x][agent.position.y] == 0;
	}

	public State copy() {
		State stateCopy=new State();
		for(int i = 0; i < this.board.length; i++){
			stateCopy.board[i] = this.board[i].clone();
		}
		stateCopy.agent.position.x=this.agent.position.x;
		stateCopy.agent.position.y=this.agent.position.y;
		stateCopy.agent.fullPocket=this.agent.fullPocket;
		return stateCopy;
	}
	
	void modify(int action, int direction) {
			
			if (action!=2) {
				Position activeFiledPos = dirs.get(direction).calcDirection(agent.position);
				if (activeFiledPos.x>=0 && activeFiledPos.x<board.length && activeFiledPos.y<board[0].length &&activeFiledPos.y>=0) {
					int fieldType = board[activeFiledPos.x][activeFiledPos.y];
					if(!isBlocked() || (isBlocked() && fieldType==0)) {
						if (action == 0) {                                  //ruch
							fields.get(fieldType).move(activeFiledPos);
						}
						else  {                                             //interakcja
							fields.get(fieldType).interact(activeFiledPos);
						}
					}	
				}
			}
			else {
				if(agent.fullPocket==true) {
					agent.fullPocket=false;                            //niszczy klocek
				}
			}
		
	}
	
	
}
