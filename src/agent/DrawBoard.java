package agent;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class DrawBoard  extends JPanel{

	private static final long serialVersionUID = -1967028852030465853L;
	int[][] board;
	Agent agent = new Agent();
	
	public DrawBoard(int[][] board, Agent agent) {
		super();
		this.board = board;
		this.agent = agent;
		this.setSize(300, 300);
		
		this.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {

				if(SwingUtilities.isLeftMouseButton(e)){
					changeField(e.getPoint().x, e.getPoint().y);
				}
				
				if(SwingUtilities.isRightMouseButton(e)){
					changeAgentPosition(e.getPoint().x, e.getPoint().y);
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
		});
	}
	
	//rysowanie planszy
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		setBackground(Color.white);
		
		int n=this.getFieldSize();
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++)
			{
				if (board[i][j]==0){
					g2d.setColor(new Color(51, 102, 0));
					g2d.fillRect(n*i, n*j, n, n);
				} 
				else if(board[i][j]==1){
					g2d.setColor(new Color(204, 255, 153));
					g2d.fillRect(n*i, n*j, n, n);
					g2d.setColor(new Color(76, 153, 0));
					g2d.drawRect(n*i, n*j, n, n);
				}
				else{
					g2d.setColor(new Color(204, 255, 153));
					g2d.fillRect(n*i, n*j, n, n);
					g2d.setColor(new Color(76, 153, 0));
					g2d.drawRect(n*i, n*j, n, n);
					g2d.setColor(new Color(229, 255, 204));
					g2d.fillRect(n*i+n/4, n*j+n/4, n/2, n/2);
					g2d.setColor(new Color(76, 153, 0));
					g2d.drawRect(n*i+n/4, n*j+n/4, n/2, n/2);
				}	
			}
		}
	//	nie pacman
	//	g2d.setColor(Color.BLACK);
	//	g2d.fillOval(this.agent.position.x*n,this.agent.position.y*n , n, n);
		
	//	pacman
		g2d.setColor(new Color(250, 240, 70));
		g2d.fillArc(this.agent.position.x*n,this.agent.position.y*n , n, n, 30,300);
		g2d.setColor(Color.BLACK);
		g2d.drawArc(this.agent.position.x*n,this.agent.position.y*n , n, n, 30,300);
		g2d.fillOval(this.agent.position.x*n+3*n/8,this.agent.position.y*n+n/4 , n/8, n/8);
		int dx=(int)(n/2 * Math.cos(30*Math.PI/180));
		int dy=(int)(n/2 * Math.sin(30*Math.PI/180));
		g2d.drawLine(this.agent.position.x*n+n/2,this.agent.position.y*n+n/2,this.agent.position.x*n+dx+n/2,this.agent.position.y*n-dy+n/2);
		g2d.drawLine(this.agent.position.x*n+n/2,this.agent.position.y*n+n/2,this.agent.position.x*n+dx+n/2,this.agent.position.y*n+dy+n/2);
		
		if (this.agent.fullPocket==true){
			g2d.setColor(new Color(229, 255, 204));
			g2d.fillOval(this.agent.position.x*n+3*n/4,this.agent.position.y*n+3*n/8, n/4, n/4);
			g2d.setColor(new Color(76, 153, 0));
			g2d.drawOval(this.agent.position.x*n+3*n/4,this.agent.position.y*n+3*n/8 , n/4, n/4);
			
		}
	}
	
	void changeField(int x, int y){
		int fieldMinX=(int) (Math.floor(x/this.getFieldSize()));
		int fieldMaxX=(int) (Math.ceil(x/this.getFieldSize()));
		int fieldMinY=(int) (Math.floor(y/this.getFieldSize()));
		int fieldMaxY=(int) (Math.ceil(y/this.getFieldSize()));
		
		int fieldType=board[(int) Math.floor(x/this.getFieldSize())][(int) Math.floor(y/this.getFieldSize())];
		System.out.print(fieldType);
		System.out.print((fieldType++)%3);
		if(this.agent.position.x<=fieldMaxX && this.agent.position.x>=fieldMinX && this.agent.position.y<=fieldMaxY && this.agent.position.y>=fieldMinY){
			
			if(fieldType==0){
				this.board[(int) Math.floor(x/this.getFieldSize())][(int) Math.floor(y/this.getFieldSize())] = (fieldType++)%3;
			}
		}
		else{
			this.board[(int) Math.floor(x/this.getFieldSize())][(int) Math.floor(y/this.getFieldSize())] = (fieldType++)%3;
		}
	}
	
	void changeAgentPosition(int x, int y){
		this.agent.position.x=(int) (Math.floor(x/this.getFieldSize()));
		this.agent.position.y=(int) (Math.floor(y/this.getFieldSize()));
	}
	
	int agentOnBoardX(){
		return this.agent.position.x*this.getFieldSize();
	}
	
	int agentOnBoardY(){
		return this.agent.position.y*this.getFieldSize();
	}
	
	int getFieldSize(){
		int n=600/this.board.length;
		int m=600/this.board[1].length;
		if(n>m){
			n=m;
		}
		return n;
	}
	
	void setBoard(int[][] board){
		this.board=board;
	}
	
	void setAgent(Agent agent){
		this.agent=agent;
	}
}
