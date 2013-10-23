package nebuloid;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.Toolkit;

import java.util.Date;

import nebuloid.Laser;
import nebuloid.COLOR;
import nebuloid.Nebula;

public class Ship extends Nebula
{
	int thrust_angle = 0;
	
	//ship speed
	int speed = 15; 
	int coast_speed = 10;
	int rotation_speed = 8;
	
	public int MAX_HP = 1000;
	int hp = MAX_HP;
	
	//ship images	
	Image flame = Toolkit.getDefaultToolkit().getImage(Ship.class.getResource("/images/viper_flame.png"));
	
	//current movement per frame (delta)
	int dspeed = 0;
	int drotate = 0;
	
	boolean thrust = false;
	
	boolean visible = true;
	
	//control rate of fire
	long next_shot = 0;
	long shot_delay = 130;
	
	public Ship()
	{
		//ship images
		red = Toolkit.getDefaultToolkit().getImage(Ship.class.getResource("/images/viper_red.png"));
		yellow = Toolkit.getDefaultToolkit().getImage(Ship.class.getResource("/images/viper_yellow.png"));
		orange = Toolkit.getDefaultToolkit().getImage(Ship.class.getResource("/images/viper_orange.png"));
		green = Toolkit.getDefaultToolkit().getImage(Ship.class.getResource("/images/viper_green.png"));
		purple = Toolkit.getDefaultToolkit().getImage(Ship.class.getResource("/images/viper_purple.png"));
		blue = Toolkit.getDefaultToolkit().getImage(Ship.class.getResource("/images/viper_blue.png"));
	
		height = 50;
		width = 50;
		color = COLOR.RED;
		
		setVisible(true);
	}
	
	//moves the ship one frame
	public void move()
	{
		move_ship();
		
		if (exploded_at <= 0 && thrust)
			thrust_angle = angle;
	}
	
	public void move_ship()
	{
		if (exploded_at <= 0)
		{
			//move the ship
			if (dspeed != 0)
			{
				//speed broken into x and y components based on current ship angle
				y += dspeed * Math.cos(Math.toRadians((int)thrust_angle));
				x += -dspeed * Math.sin(Math.toRadians((int)thrust_angle));
			}
		    
		    //rotate the ship
		    angle += drotate;
		    if (angle >= 360)
		    	angle -= 360;
		    if (angle < 0)
		    	angle += 360;
		    	
		    if (thrust_angle >= 360)
		    	thrust_angle -= 360;
		    if (thrust_angle < 0)
		    	thrust_angle += 360;
		    	
		    if (hp <= 0 && exploded_at <= 0)
		    	explode();
		}
		else
        	move_explosion();
	}
	
	public void moveForward()
	{
		dspeed = -speed;
		thrust = true;
	}
	
	public void moveBackward()
	{
		//dspeed = speed;
		dspeed = 0;
		drotate = 0;
		thrust = false;
	}
	
	public void rotateLeft()
	{
        drotate = -rotation_speed;
	}

	public void rotateRight()
	{
        drotate = rotation_speed;
	}
    
    public void stopMove()
    {
    	//dspeed = 0;
    	dspeed = -coast_speed;
    	thrust = false;
    }
    
    public void stopRotate()
    {
    	drotate = 0;
	}
	
	public void aquireTarget(Ship s)
	{
	}
	
	public Laser fire()
	{
		Laser l = null;
		long time = (new Date()).getTime();
		
		if (time >= next_shot && exploded_at <= 0)
		{
			l = new Laser(this, color, (int)(centerX() + ((width/2) * Math.sin(Math.toRadians((int)angle)))), 
						 (int)(centerY() + (-(height/2) * Math.cos(Math.toRadians((int)angle)))), angle);
		
			l.setVisible(true);
			
			next_shot = time + shot_delay;
		}
		
		return l;
	}
		
	//calculate the angle the ship needs to face to get from current location to target location
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
	
	//draws the sprite and any subsprites
	public void draw(Graphics2D g2d, Rectangle view)
	{
		if (getVisible())
		{
			//to restore default transform
			AffineTransform saveTrans = g2d.getTransform();
			int x = getX() - (int)view.getX();
			int y = getY() - (int)view.getY();
		
			//only rotate if angle != 0
			if (getAngle() != 0)
			{
				AffineTransform trans = new AffineTransform();
		
				trans.rotate(Math.toRadians(getAngle()), x + (getWidth()/2), y + (getHeight()/2));
		
				g2d.setTransform(trans);	
			}
		
			g2d.drawImage(getImage(), x, y, getWidth(), getHeight(), null);
			
			if (thrust)
				g2d.drawImage(flame, x, y, getWidth(), getHeight(), null);
		
			g2d.setTransform(saveTrans);
			
			if (exploded_at > 0)
			{
				g2d.drawImage(explosion, 
							centerX() - (explode_width/2) - (int)view.getX(), 
							centerY() - (explode_height/2) - (int)view.getY(), 
							explode_width, 
							explode_height, null);
			}
		}
	}
	
	public int getHP()
	{
		return hp;
	}
	
	public void setHP(int hp)
	{
		this.hp = hp;
		
		if (hp > MAX_HP)
			hp = MAX_HP;
		if (hp < 0)
			hp = 0;
	}
}
