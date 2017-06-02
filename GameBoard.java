//Quang Vu

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class GameBoard extends JPanel{
	private static final int ROW_LINES = 29;
	private static final int COLUMN_LINES = 49;
	private static final int robotsNum = 15;
	private int score, destroyNum;
	private Player player;
	private ArrayList<Robot> robots;
	private ArrayList<Rubble> rubbles;

	public GameBoard(){
		score = 0;
		destroyNum = 3;
		player = new Player();
		robots = new ArrayList<Robot>();
		rubbles = new ArrayList<Rubble>();

		//creating robot objects
		for(int i = 0; i < robotsNum; i++){
			robots.add(new Robot());
			//make sure two robots are not in the same location
			for(int j = 0; j < robots.size() - 1; j++){
				if(robots.get(i).getCurrentRow() == robots.get(j).getCurrentRow() && 
						robots.get(i).getCurrentCol() == robots.get(j).getCurrentCol()){
					robots.remove(i);
					i--;
				}
			}
		}
	}

	//robot + robot = rubble
	public void robotsCollision(Graphics2D g2d){
		for(int i = 0; i < robots.size()-1; i++){
			for(int j = i + 1; j < robots.size(); j++){
				if(robots.get(i).getCurrentRow() == robots.get(j).getCurrentRow() && 
						robots.get(i).getCurrentCol() == robots.get(j).getCurrentCol()){
					rubbles.add(new Rubble(robots.get(i).getCurrentRow(), robots.get(i).getCurrentCol()));
					robots.remove(j);
					robots.remove(i);
					if(i != 0){
						j--;
						i--;
					}
					score = score + 150;
				}
			}
		}

		for(int j = 0; j < rubbles.size(); j++){
			rubbles.get(j).draw(g2d, getWidth(), getHeight());
		}
		repaint();
	}

	//robot + rubble = rubble
	public void rubbleCollision(Graphics2D g2d){
		for(int i = 0; i < robots.size(); i++){
			for(int j = 0; j < rubbles.size(); j++){
				if(robots.get(i).getCurrentRow() == rubbles.get(j).getCurrentRow() && 
						robots.get(i).getCurrentCol() == rubbles.get(j).getCurrentCol()){
					robots.remove(i);
					score = score + 50;
					if(i == robots.size() && i != 0){
						i--;
					}
					else if(robots.size() == 0){
						return;
					}
				}
			}
		}
		repaint();
	}

	//get score
	public int getScore(){
		return score;
	}

	//destroy a random robot
	public void randomDestroy(){
		if(robots.size() != 0 && destroyNum != 0){
			Random rand = new Random();
			int robotIndex = rand.nextInt(robots.size());
			robots.remove(robotIndex);
			destroyNum--;
		}
	}

	public int getDestroyNum(){
		return destroyNum;
	}
	
	@Override
	public void paintComponent(Graphics g){
		//tell the panel to draw itself
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D)g;

		paintGrid(g2d);
		//draw the player
		player.draw(g2d, getWidth(), getHeight());
		//draw the robots
		for(int i = 0; i < robots.size(); i++){
			robots.get(i).draw(g2d, getWidth(), getHeight());
		}
		robotsCollision(g2d);
		rubbleCollision(g2d);
	}

	//draw the game board
	public void paintGrid(Graphics2D g2d){
		//draw a 5 by 5 grid across the panel
		g2d.setColor(Color.BLACK);

		//write a loop to generate grid lines
		int w = getWidth();
		int h = getHeight();

		for(int i = 1; i <= ROW_LINES; ++i){
			int y = i*h/(ROW_LINES+1);		

			//horizontal
			g2d.drawLine(0, y, w, y);		
		}
		g2d.drawLine(0, h-1, w, h-1);
		g2d.drawLine(0, 0, w, 0);

		for(int j = 1; j <= COLUMN_LINES; ++j){
			int x = j*w/(COLUMN_LINES+1);

			g2d.drawLine(x, 0, x, h);
		}

	}

	public Player getPlayer(){
		return player;
	}

	public ArrayList<Robot> getRobots(){
		return robots;
	}

	//robots moving after player method
	public void robotsMove(){
		int pCurrentRow = player.getCurrentRow();
		int pCurrentCol = player.getCurrentCol();

		for(int i = 0; i < robots.size(); i++){
			//robot is in up left position of player
			if(robots.get(i).getCurrentRow() < pCurrentRow && robots.get(i).getCurrentCol() < pCurrentCol){
				robots.get(i).move(1, 1);
			}
			//robot is in up position of player
			else if(robots.get(i).getCurrentRow() < pCurrentRow && robots.get(i).getCurrentCol() == pCurrentCol){
				robots.get(i).move(0, 1);
			}
			//robot is in up right 
			else if(robots.get(i).getCurrentRow() < pCurrentRow && robots.get(i).getCurrentCol() > pCurrentCol){
				robots.get(i).move(-1, 1);
			}
			//robot is left of player
			else if(robots.get(i).getCurrentRow() == pCurrentRow && robots.get(i).getCurrentCol() < pCurrentCol){
				robots.get(i).move(1, 0);
			}
			//robot is right of player
			else if(robots.get(i).getCurrentRow() == pCurrentRow && robots.get(i).getCurrentCol() > pCurrentCol){
				robots.get(i).move(-1, 0);
			}
			//robot is down left
			else if(robots.get(i).getCurrentRow() > pCurrentRow && robots.get(i).getCurrentCol() < pCurrentCol){
				robots.get(i).move(1, -1);
			}
			//robot is down
			else if(robots.get(i).getCurrentRow() > pCurrentRow && robots.get(i).getCurrentCol() == pCurrentCol){
				robots.get(i).move(0, -1);
			}
			//robot is down right
			else if(robots.get(i).getCurrentRow() > pCurrentRow && robots.get(i).getCurrentCol() > pCurrentCol){
				robots.get(i).move(-1, -1);
			}
		}
		repaint();
	}

	//player teleporting
	public void playerTele(){
		player.teleport();
		repaint();
	}

	//player going up
	public void pUp(){
		if(player.getCurrentRow() == 1){
			player.move(0, 0);
		}
		else{
			player.move(0, -1);
		}
	}

	//player going up left
	public void pUpLeft(){
		if(player.getCurrentRow() == 1 || player.getCurrentCol() == 1){
			player.move(0, 0);
		}
		else{
			player.move(-1, -1);
		}
	}

	//player going up right
	public void pUpRight(){
		if(player.getCurrentRow() == 1 || player.getCurrentCol() == player.colNum){
			player.move(0, 0);
		}
		else{
			player.move(1, -1);
		}
	}

	//player going left
	public void pLeft(){
		if(player.getCurrentCol() == 1){
			player.move(0, 0);
		}
		else{
			player.move(-1, 0);
		}
	}

	//player going right
	public void pRight(){
		if(player.getCurrentCol() == player.colNum){
			player.move(0, 0);
		}
		else{
			player.move(1, 0);
		}
	}

	//player going down left
	public void pDownLeft(){
		if(player.getCurrentRow() == player.rowNum || player.getCurrentCol() == 1){
			player.move(0, 0);
		}
		else{
			player.move(-1, 1);
		}
	}

	//player going down
	public void pDown(){
		if(player.getCurrentRow() == player.rowNum){
			player.move(0, 0);
		}
		else{
			player.move(0, 1);
		}
	}

	//player going down right
	public void pDownRight(){
		if(player.getCurrentRow() == player.rowNum || player.getCurrentCol() == player.colNum){
			player.move(0, 0);
		}
		else{
			player.move(1, 1);
		}
	}

	//game over if a robot hits the player
	public boolean isGameOver(){
		for(int i = 0; i < robots.size(); i++){
			if(robots.get(i).getCurrentCol() == player.getCurrentCol() && robots.get(i).getCurrentRow() == player.getCurrentRow()){
				return true;
			}
		}
		for(int j = 0; j < rubbles.size(); j++){
			if(rubbles.get(j).getCurrentCol() == player.getCurrentCol() && rubbles.get(j).getCurrentRow() == player.getCurrentRow()){
				return true;
			}
		}
		return false;
	}

	//win if no robot is left
	public boolean isWin(){
		if(robots.size() == 0){
			return true;
		}
		return false;
	}
}
