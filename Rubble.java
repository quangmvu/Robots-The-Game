//Quang Vu

import java.awt.*;
import java.io.*;
import javax.imageio.*;

public class Rubble extends GameObject{

	public Rubble(int row, int col){
		currentRow = row;
		currentCol = col;
	}

	@Override
	public void draw(Graphics2D g2d, int width, int height) {
		try{
			Image rubble = ImageIO.read(new File("rubble.png"));
			rubble = rubble.getScaledInstance(width/colNum, height/rowNum, 5);
			g2d.drawImage(rubble, (currentCol-1) * width/colNum, (currentRow-1) * height/rowNum, null);
		}
		catch(Exception e){
			e.printStackTrace();
		}	
	}

	public int getCurrentRow(){
		return currentRow;
	}

	public int getCurrentCol(){
		return currentCol;
	}

}
