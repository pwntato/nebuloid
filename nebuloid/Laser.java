package nebuloid;

import java.awt.Toolkit;

import nebuloid.Nebula;
import nebuloid.Ship;

public class Laser extends Nebula
{	
	int speed = 30;
	int damage = 10;
	
	Ship shot_by;
	
	public Laser(Ship shot_by, int color, int x, int y, int angle)
	{
		red = Toolkit.getDefaultToolkit().getImage(Laser.class.getResource("/images/laser_red.png"));
		blue = Toolkit.getDefaultToolkit().getImage(Laser.class.getResource("/images/laser_blue.png"));
		purple = Toolkit.getDefaultToolkit().getImage(Laser.class.getResource("/images/laser_purple.png"));
		orange = Toolkit.getDefaultToolkit().getImage(Laser.class.getResource("/images/laser_orange.png"));
		yellow = Toolkit.getDefaultToolkit().getImage(Laser.class.getResource("/images/laser_yellow.png"));
		green = Toolkit.getDefaultToolkit().getImage(Laser.class.getResource("/images/laser_green.png"));
	
		this.shot_by = shot_by;
		width = 5;
		height = 20;
		setPos(x, y);
		this.angle = angle;
		setColor(color);
	}
	
	public Laser()
	{
	}
	
	public Ship getShotBy()
	{
		return shot_by;
	}
	
	public int getDamage()
	{
		return damage;
	}
	
	//moves the laser one frame
	public void move()
	{
		//lasers don't explode
		exploded_at = 0;
		
    	y += -speed * Math.cos(Math.toRadians((int)angle));
    	x += speed * Math.sin(Math.toRadians((int)angle));
	}
}
