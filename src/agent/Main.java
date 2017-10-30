/*
 * Anna Sprysak
 */

package agent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class Main {
	public static volatile JToggleButton startButton = new JToggleButton("GO!");
	public static volatile JToggleButton writeButton = new JToggleButton("zakoncz zapis");

	public static void main(String[] args) throws FileNotFoundException {
		
		JFrame frame = new JFrame("Empowerment");
		int frameWidth = 800;
		int frameHeight = 700;
		int screenWidth = frame.getToolkit().getScreenSize().width;
		int screenHeight = frame.getToolkit().getScreenSize().height;
		frame.setLocation((screenWidth - frameWidth) / 2, (screenHeight - frameHeight) / 2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel graphicPanel = new JPanel();
		graphicPanel.setLayout(new BorderLayout());
		graphicPanel.setBackground(Color.WHITE);

		frame.setSize(frameWidth, frameHeight);
		frame.add(graphicPanel, BorderLayout.CENTER);

		JPanel controlPanel = new JPanel();
		controlPanel.setSize((int)(frameWidth - graphicPanel.getSize().getWidth()), frameHeight);
		
		frame.add(controlPanel, BorderLayout.EAST);
		frame.setVisible(true);
		
		
		controlPanel.add(startButton);
		boolean start=true;
		controlPanel.add(writeButton);
	
		State state = new State();
			for (int i = 0; i < state.board.length; i++) {
				for (int j = 0; j <state. board[i].length; j++) {
					state.board[i][j] = 1;
				}
			}
		
			System.out.print("NEW");
			System.out.println();
			
			state.agent.position.x=1;
			state.agent.position.y=1;
			
			Empowerment emp = new Empowerment(state);
			int[] values = new int[9];
			
			List<Integer> maxInd = new ArrayList<Integer>();
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			Date date = new Date();
			System.out.println(dateFormat.format(date));
			String name = "emp_"+((String) dateFormat.format(date));
			
			
			
			DrawBoard panel = new DrawBoard(state.board, state.agent);
			graphicPanel.add(panel);
			PrintWriter zapis = new PrintWriter(name);
			boolean free = false;

			int iterator=1;
	
				while(true){
					
					panel.repaint();
					frame.revalidate();
					
				/*	try {
					    Thread.sleep(500);                 
					} catch(InterruptedException ex) {
					    Thread.currentThread().interrupt();
					}*/

					if(startButton.isSelected()){
						start=true;
						int maxval = 0;
						for (int k=0; k<9; k++){
							int current = emp.calcEmpowerment(new Action(k));
							values[k] = current;
							
							if (current>=maxval){
								if (current>maxval){
									maxInd.clear();
									maxval = current;
								}
								maxInd.add(k);
							}
						}
						
						int chosenIndex = maxInd.get(new Random().nextInt(maxInd.size()));
						Action chosenAction = new Action(chosenIndex);
						state.modify(chosenAction.getActionType(), chosenAction.getDirection());
			//			int sumMove=0;
			//			int sumInter=0;
						for (int j = 0; j < values.length; j++){
							System.out.print(values[j]);
							System.out.print("   ");
						}
						
					/*	for(int j=1; j<5; j++){
							sumMove+=values[2*(j-1)];
							sumInter+=values[2*j-1];
						}*/
						
					// funkcje sprawdzające, zależne od planszy	
						
						
					/*	if(start){             
							boolean temp=false;			//5x6 strumyk podwojny
							for(int z=0;z<5;z++){
								if(state.board[z][2]==1){
									temp=true;
								}
							}
							for(int z=0;z<5;z++){
								if(state.board[z][3]==1 && temp){
									free=true;
								}
							}
						}*/
						
						
						if(start){             
							boolean temp=false;			//10x15 strumyk podwojny
							for(int z=0;z<4;z++){
								if(state.board[z][2]==1){
									temp=true;
								}
							}
							for(int z=0;z<4;z++){
								if(state.board[z][3]==1 && temp){
									free=true;
								}
							}
							
							boolean temp2=false;			//10x15 strumyk podwojny
							for(int z=0;z<2;z++){
								if(state.board[3][z]==1){
									temp2=true;
								}
							}
							for(int z=0;z<2;z++){
								if(state.board[2][z]==1 && temp2){
									free=true;
								}
							}
						}
						
						
						
						
						
					/*	if(start){                        //5x6 strumyk
							for(int z=0;z<5;z++){
								if(state.board[z][2]==1){
									free=true;
								}
							}
						}*/
						
					/*	if(start){                        //5x6 przypadkowosc-czy w dole
					 		free=state.isBlocked();
						}*/

				/*		if(start){                        //5x6, 10x15  okop
							for(int z=1;z<4;z++){
								if(state.board[z][1]==1 || state.board[z][4]==1){
									free=true;
								}
							}
							for(int z=1;z<5;z++){
								if(state.board[1][z]==1 || state.board[3][z]==1){
									free=true;
								}
							}
						}*/
						
						

						zapis.println(iterator+" "+values[chosenIndex]+" "+free);
						System.out.println();
						System.out.print(iterator + " " + "akcja   " + chosenAction.getActionType() + "   " + chosenAction.getDirection() + free);
						System.out.println();
						
						iterator++;
			}	
					if(!(startButton.isSelected()) && start){
						start=false;
						free=false;
						iterator=1;
						zapis.println("NOWA SERIA");
					}

					if(writeButton.isSelected()){
						zapis.close();
					}
		}
	}
}