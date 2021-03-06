package nebuloid;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Star extends Sprite
{		
	public Star(int x, int y)
	{
		width = 2;
		height = 2;
		setPos(x, y);
		setVisible(true);
	}
	
	public void draw(Graphics2D g2d, Rectangle view)
	{
		if (getVisible())
		{
			int x = getX() - (int)view.getX();
			int y = getY() - (int)view.getY();
			
			//to restore defaults
    		Color c = g2d.getColor();
			
			g2d.setColor(Color.WHITE);
			g2d.fillRect(x, y, width, height);
			
			//restore defaults
    		g2d.setColor(c);
		}
	}
}
