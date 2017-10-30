package agent;

import java.util.Random;

public class Action {
	int rand = new Random().nextInt(9);
	
	int getActionType() {
		if (rand == 8)
			return 2;
		else
			return rand%2;
	}
	
	int getDirection() {
		return rand/2;
	}
	
	public Action(int rand){
		this.rand = rand;
	}
	
	public Action(){
	}
}
