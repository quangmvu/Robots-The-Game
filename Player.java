//Quang Vu

import java.awt.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;

public class Player extends GameObject{

	public Player(){
		//player location is in the middle of the board
		currentRow = rowNum/2;
		currentCol = colNum/2;
	}

	@Override
	public void draw(Graphics2D g2d, int width, int height) {
		try{
			Image player = ImageIO.read(new File("player.png"));
			player = player.getScaledInstance(width/colNum, height/rowNum, 5);
			g2d.drawImage(player, (currentCol-1) * width/colNum, (currentRow-1) * height/rowNum, null);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	//player move method
	public void move(int x, int y){
		currentCol = currentCol + x;
		currentRow = currentRow + y;
	}

	//player teleport method
	public void teleport(){
		Random rand = new Random();
		currentRow = rand.nextInt(rowNum) + 1;
		currentCol = rand.nextInt(colNum) + 1;
	}

	public int getCurrentRow(){
		return currentRow;
	}

	public int getCurrentCol(){
		return currentCol;
	}

}
