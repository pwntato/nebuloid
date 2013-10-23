package nebuloid;

import java.awt.Toolkit;
import java.awt.Image;

//import nebuloid.Nebula;
import nebuloid.Ship;
import nebuloid.COLOR;
import nebuloid.Laser;

public class Bomb extends Laser
{	
	Ship target = null;

	public Bomb(Ship shot_by, int x, int y, Ship target)
	{
		image = Toolkit.getDefaultToolkit().getImage(Bomb.class.getResource("/images/bomb_ship_bomb.png"));
		
		this.shot_by = shot_by;
		width = 75;
		height = 75;
		damage = 100;
		speed = 12;
		explosion_time = 1000;
		setPos(x, y);
		this.target = target;
		this.angle = (int)calcBaring(target.centerX(), target.centerY());
		visible = true;
	}
	
	//moves the bomb one frame
	public void move()
	{	
		if (exploded_at <= 0)
		{
			angle = (int)calcBaring(target.centerX(), target.centerY());
			super.move();
		}
		else
			move_explosion();
	}
	
	public void setVisible(boolean visible)
	{
		if (!visible)
			if (exploded_at <= 0)
			{
				//setPos(target.centerX(), target.centerY());
				explode();
			}
		else
			if (exploded_at <= 0)
				this.visible = visible;
	}
	
	//bombs are colorless
	public int getColor()
	{
		return COLOR.NONE;
	}
	
	//calculate the angle the bomb needs to face to get from current location to target location
	public double calcBaring(double targetX, double targetY)
	{
		double baring;
	
		//arctan only returns angles between -180 and 180, used absolute value to narrow that to between -90 and 90
		if (centerX() == targetX)
			baring = 90;
		else if (centerY() == targetY)
			baring = 0;
		else
			baring = Math.round((float)Math.toDegrees(Math.atan(Math.abs(centerY() - targetY) / Math.abs(centerX() - targetX))));
		
		//move the angle to the correct quadrent
		//Q1
		if ((centerX() < targetX) && (centerY() > targetY))
			baring = 90 - baring;
		if ((centerX() < targetX) && (centerY() == targetY))
			baring = 90;
		//Q2
		if ((centerX() < targetX) && (centerY() < targetY))
			baring = 90 + baring;
		if ((centerX() == targetX) && (centerY() < targetY))
			baring = 180;
		//Q3
		if ((centerX() > targetX) && (centerY() < targetY))
			baring = 90 - baring + 180;
		if ((centerX() > targetX) && (centerY() == targetY))
			baring = 270;
		//Q4
		if ((centerX() > targetX) && (centerY() > targetY))
			baring = baring + 270;
		if ((centerX() == targetX) && (centerY() > targetY))
			baring = 0;
			
		return baring;
	}
	
	public int getDamage()
	{
		if (exploded_at <= 0)
			return damage;
		else
			return 0;
	}
	
	//returns the current image to display
	public Image getImage()
	{
		return image;
	}
}
