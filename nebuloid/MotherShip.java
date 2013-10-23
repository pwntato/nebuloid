package nebuloid;

import java.awt.Image;
import java.awt.Toolkit;
//import java.awt.Rectangle;

//import java.util.ArrayList;
import java.util.Date;
//import java.util.Random;

import nebuloid.Ship;
import nebuloid.Bomb;
//import nebuloid.COLOR;

public class MotherShip extends Ship
{
	//stop between movements
	long next_move = 0;
	long move_delay;
	
	Ship target;
	
	public MotherShip(int x, int y, Ship target)
	{
		//Random rand = new Random((new Date()).getTime() + centerX() + centerY());
		
		//ship image
		image = Toolkit.getDefaultToolkit().getImage(RingShip.class.getResource("/images/mother_ship.png"));
	
		height = 100;
		width = 100;
		
		MAX_HP = 100;
		hp = MAX_HP;
		
		//time to stop between movements
		move_delay = 0;
		shot_delay = 5000;
		
		this.target = target;
		
		setPos(x, y);
		
		setVisible(true);
	}
	
	public void move()
	{
		if (exploded_at <= 0)
		{
			/*long time = (new Date()).getTime();
			Random rand = new Random(time + centerX() + centerY());
		
			//rotate the ship
			angle += drotate;
			if (angle >= 360)
			  	angle -= 360;
			if (angle < 0)
			   	angle += 360;
			   	
			if (time >= next_move)
			{
				drotate = rand.nextInt(rotation_speed*2) - rotation_speed;
				next_move = time + move_delay;
			}*/
			
			angle = (int)calcBaring(target.centerX(), target.centerY());
			
		    if (hp <= 0 && exploded_at <= 0)
		    	explode();
		}
		else
        	move_explosion();
	}
	
	public Laser fire()
	{
		Laser l = null;
		long time = (new Date()).getTime();
		
		if (time >= next_shot && target != null && exploded_at <= 0)
		{
			l = new Bomb(this, centerX(), centerY(), target);
			
			l.setVisible(true);
			next_shot = time + shot_delay;
		}
		
		return l;
	}
	
	//returns the current image to display
	public Image getImage()
	{
		return image;
	}
}
