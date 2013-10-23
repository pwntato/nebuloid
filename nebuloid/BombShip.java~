package nebuloid;

import java.awt.Image;
import java.awt.Toolkit;
//import java.awt.Rectangle;

import java.util.ArrayList;
import java.util.Date;
//import java.util.Random;

import nebuloid.Laser;
import nebuloid.Bomb;
//import nebuloid.COLOR;

public class BombShip extends RingShip
{
	Image loaded_bomb_ship = Toolkit.getDefaultToolkit().getImage(BombShip.class.getResource("/images/bomb_ship.png"));
	//Image bomb = Toolkit.getDefaultToolkit().getImage(BombShip.class.getResource("/images/bomb_ship_bomb.png"));
	Image empty_bomb_ship = Toolkit.getDefaultToolkit().getImage(BombShip.class.getResource("/images/bomb_ship_ship.png"));
	
	boolean loaded = true;
	
	public BombShip(int color, int x, int y)
	{
		image = loaded_bomb_ship;
		
		height = 75;
		width = 75;
		
		target_radius = 400;
		attack_distance = 150;
		
		speed = 7;
		coast_speed = 7;
		
		MAX_HP = 50;
		hp = MAX_HP;
		
		shot_delay = 1000;
		
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
		
		if (time >= next_shot - charge_time && !charged)
			charged = true;
			
    	//head to location if destination location is set
    	if (headToX != Integer.MAX_VALUE && headToY != Integer.MAX_VALUE &&
        	headToAngle != Integer.MAX_VALUE)
			move_to();
        else if (time >= next_move) 
			next_move();
        
		move_ship();
		
		thrust_angle = angle;
		
		if (target != null)
			angle = (int)calcBaring(target.centerX(), target.centerY());
	}
	
	public Laser fire()
	{
		Laser l = null;
		long time = (new Date()).getTime();
		
		if (time >= next_shot && target != null && exploded_at <= 0)
		{
			//shoot bomb if loaded, otherwise shoot laser
			if (loaded)
			{
				l = new Bomb(this, centerX(), centerY(), target);
				loaded = false;
			}
			else
				l = new Laser(this, color, centerX(), centerY(), angle);
			
			l.setVisible(true);
			next_shot = time + shot_delay;
		}
		
		return l;
	}
	
	//returns the current image to display
	public Image getImage()
	{
		if (loaded)
			image = loaded_bomb_ship;
		else
			image = empty_bomb_ship;
			
		return image;
	}
}
