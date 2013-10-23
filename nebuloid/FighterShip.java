package nebuloid;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Rectangle;

import java.util.ArrayList;
import java.util.Date;
//import java.util.Random;

import nebuloid.Laser;
//import nebuloid.COLOR;

public class FighterShip extends RingShip
{
	Image fighter = Toolkit.getDefaultToolkit().getImage(FighterShip.class.getResource("/images/fighter.png"));
	
	public FighterShip(int color, int x, int y)
	{
		image = fighter;
		
		height = 50;
		width = 50;
		
		target_radius = 400;
		attack_distance = 250;
		
		MAX_HP = 30;
		hp = MAX_HP;
		
		shot_delay = 300;
		
		//time to stop between movements
		move_delay = 0;
		
		setPos(x, y);
		pathx = new ArrayList();
		pathy = new ArrayList();
		pathx.add(new Integer(x));
		pathy.add(new Integer(y));
		
		this.color = color;
		
		setVisible(true);
	}
	
	public void move()
	{
		long time = (new Date()).getTime();
		//Random rand = new Random(time + centerX() + centerY());
			
    	//head to location if destination location is set
    	if (headToX != Integer.MAX_VALUE && headToY != Integer.MAX_VALUE &&
        	headToAngle != Integer.MAX_VALUE)
        {
			move_to();
			dspeed = -speed;
		}
        else if (time >= next_move) 
			next_move();
        
		move_ship();
		
		if (target == null || !random_mode)
			thrust_angle = angle;
		else
			angle = (int)calcBaring(target.centerX(), target.centerY());
	}
	
	//set s as target if s is in the target radius
	public void aquireTarget(Ship s)
	{
		Rectangle target_area = new Rectangle(centerX() - target_radius,
											  centerY() - target_radius,
											  target_radius * 2,
											  target_radius * 2);
							  
		if (s.getRect().intersects(target_area))
			target = s;
		//don't let it lose the target
		else if (target == s && pursue)
		{
			//the target coord to head to
			int tx = target.centerX();
			int ty = target.centerY();
			
			thrust_angle = (int)calcBaring(xNear(tx, ty), yNear(tx, ty));
			dspeed = -speed;
			target = null;
		}
		
		if (target != null && s != target && pursue)
		{
	    	stopAutoMove();
	    	
	    	//the target coord to head to
			int tx = target.centerX();
			int ty = target.centerY();
			
			thrust_angle = (int)calcBaring(xNear(tx, ty), yNear(tx, ty));
			dspeed = -coast_speed;
	    }
	}
	
	public void Pursue()
	{
		//overide and do nothing :)
	}
	
	//y coord ner the point
	public int xNear(int x, int y)
	{
		//difference in coord values
		int dx = centerX() - x;
		//the target coord to head to
		int tx = x;

		if (dx > 0)
			tx += attack_distance;
		if (dx < 0)
			tx -= attack_distance;

		return tx;
	}
	
	//y coord ner the point
	public int yNear(int x, int y)
	{
		//difference in coord values
		int dy = centerY() - target.centerY();
		//the target coord to head to
		int ty = target.centerY();

		if (dy > 0)
			ty += attack_distance;
		if (dy < 0)
			ty -= attack_distance; 
			
		return ty;
	}
	
	//tell the ship to head to specific location
	public void headTo(int x, int y)
	{
		super.headTo(x, y);
		
		//comment out to rotate THEN move
		dspeed = -speed;
	}
	
	public Laser fire()
	{
		Laser l = null;
		long time = (new Date()).getTime();
		
		if (time >= next_shot && target != null && exploded_at <= 0)
		{
			//use target baring
			l = new Laser(this, color, centerX(), centerY(), angle);
		
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

