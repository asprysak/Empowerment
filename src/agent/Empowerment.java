package agent;

import java.util.HashSet;
import java.util.Set;

public class Empowerment {
	State originalState;

	public Empowerment(State stateCopy) {
		
		this.originalState = stateCopy;
	}

	int calcEmpowerment(Action action){
	
		Set<Position> positions = new HashSet<Position>();
		
		for (int j=0; j<10000; j++) {
			State stateCopy = originalState.copy();
			Action[] actions = new Action[15];
			actions[0]=action;
			
			for (int i=1; i<actions.length; i++){
				actions[i]=new Action();
			}
			
			for (int i=0; i<actions.length; i++){
				//stateCopy.printBoard();
				//stateCopy.agent.printPosition();
				//System.out.print("akcja   " + actions[i].getActionType() + "   " + actions[i].getDirection());
				//System.out.println();
				stateCopy.modify(actions[i].getActionType(), actions[i].getDirection());
	
			}
			//stateCopy.agent.printPosition();
			//stateCopy.printBoard();
			positions.add(stateCopy.agent.position);
	}
		//for (Position p:positions){
		//	System.out.print(p);
		//}
		
		return positions.size();
	}
}
