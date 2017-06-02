//Quang Vu

import java.awt.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;

public class Robot extends GameObject{

	public Robot(){
		//robot location is random(cannot be on top of player)
		Random rand = new Random();
		currentRow = rand.nextInt(rowNum) + 1;
		while(currentRow == rowNum/2){
			currentRow = rand.nextInt(rowNum) + 1;
		}
		currentCol = rand.nextInt(colNum) + 1;
		while(currentCol == colNum/2){
			currentCol = rand.nextInt(colNum) + 1;
		}
	}

	@Override
	public void draw(Graphics2D g2d, int width, int height) {
		try{
			Image robot = ImageIO.read(new File("robot.png"));
			robot = robot.getScaledInstance(width/colNum, height/rowNum, 5);
			g2d.drawImage(robot, (currentCol-1) * width/colNum, (currentRow-1) * height/rowNum, null);
		}
		catch(Exception e){
			e.printStackTrace();
		}	
	}

	//robot move method
	public void move(int x, int y){
		currentCol = currentCol + x;
		currentRow = currentRow + y;
	}

	public int getCurrentRow(){
		return currentRow;
	}

	public int getCurrentCol(){
		return currentCol;
	}
}
