//Quang Vu

import java.awt.*;

public abstract class GameObject {

	protected int currentRow;
	protected int currentCol;
	protected int rowNum = 30;
	protected int colNum = 50;

	public abstract void draw(Graphics2D g, int width, int height);
}
