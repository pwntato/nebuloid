package nebuloid;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Rectangle;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import nebuloid.Laser;
import nebuloid.COLOR;

public class RingShip extends Ship
{
	//charge up lasers
	long charge_time;
	boolean charged = false;
	
	boolean random_color = true;
	
	//stop between movements
	long next_move = 0;
	long move_delay;
	
	Image ring = Toolkit.getDefaultToolkit().getImage(Ship.class.getResource("/images/ring.png"));
	
	//random mode moves randomly
	//otherwise ship moves to points in point list
	//only one point will make ship hold its position
	boolean random_mode = true;
	//x and y coors for path
	ArrayList pathx;
	ArrayList pathy;
	//current point on the path
	int path_point = 0;
	
	//pursue means a the ship will actively follow it's target
	boolean pursue = true;
	
	//variables for targeting other ships
	Ship target = null;
	//the "radius" distance the ship will see and target the player ship
	int target_radius = 400;
	//prefered distance to attack from
	int attack_distance = 150;
	
	//next point to travel to
    double headToX = Integer.MAX_VALUE;
    double headToY = Integer.MAX_VALUE;
    double headToAngle = Integer.MAX_VALUE;
	
	public RingShip()
	{
	}
	
	public RingShip(int color, int x, int y)
	{
		//Random rand = new Random((new Date()).getTime() + centerX() + centerY());
		
		//ship images
		red = Toolkit.getDefaultToolkit().getImage(RingShip.class.getResource("/images/ring_red.png"));
		yellow = Toolkit.getDefaultToolkit().getImage(RingShip.class.getResource("/images/ring_yellow.png"));
		orange = Toolkit.getDefaultToolkit().getImage(RingShip.class.getResource("/images/ring_orange.png"));
		green = Toolkit.getDefaultToolkit().getImage(RingShip.class.getResource("/images/ring_green.png"));
		purple = Toolkit.getDefaultToolkit().getImage(RingShip.class.getResource("/images/ring_purple.png"));
		blue = Toolkit.getDefaultToolkit().getImage(RingShip.class.getResource("/images/ring_blue.png"));
	
		height = 100;
		width = 100;
		
		MAX_HP = 50;
		hp = MAX_HP;
		
		//make it take a second between shots
		charge_time = 300;
		shot_delay = 500;
		
		if (color != COLOR.RANDOM)
		{
			random_color = false;
			this.color = color;
		}
		
		//time to stop between movements
		move_delay = 2000;
		
		setPos(x, y);
		pathx = new ArrayList();
		pathy = new ArrayList();
		pathx.add(new Integer(x));
		pathy.add(new Integer(y));
		
		setVisible(true);
	}
	
	public static ArrayList ringShipRing(int color, int x, int y, int radius, int count)
	{
		ArrayList ships = new ArrayList();
		double angle = 0;
		int bcolor = color;
		
		for (int i=0; i<count; i++)
		{
			if (color == COLOR.RANDOM)
			{
				int c = COLOR.randomColor();
				while (c == bcolor)
					c = COLOR.randomColor();
				bcolor = c;
			}
				
			int bx = x + (int)(radius * Math.sin(Math.toRadians(angle)));
			int by = y + (int)(radius * Math.cos(Math.toRadians(angle)));
		
			RingShip r = new RingShip(bcolor, bx, by);
			r.setRandom(false);
        	r.setPursue(false);
			ships.add(r);
			
			angle += 360 / count;
		}
		
		return ships;
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
	}
	
	public void next_move()
	{
		long time = (new Date()).getTime();
		Random rand = new Random(time + centerX() + centerY());
		
		//pursuing target overrides mode
		if (pursue && target != null)
		{
			if (target.getHP() <= 0)
				target = null;
			else
			{
				Pursue();
			}
		}
		else
		{
			//head to random point
			if (random_mode)
				headTo(centerX() + rand.nextInt(400) - 200, centerY() + rand.nextInt(400) - 200);
			//move to next point on path
			else
			{
				path_point += 1;
				if (path_point >= pathx.size() || path_point >= pathy.size())
					path_point = 0;
					
				headTo(((Integer)pathx.get(path_point)).intValue(), ((Integer)pathy.get(path_point)).intValue());
			}
		}
	}
	
	public void Pursue()
	{
	    //difference in coord values
		int dx = centerX() - target.centerX();
		int dy = centerY() - target.centerY();
		//the target coord to head to
		int tx = target.centerX();
		int ty = target.centerY();
		
		if (dx > 0)
			tx += attack_distance;
		if (dx < 0)
			tx -= attack_distance;
		if (dy > 0)
			ty += attack_distance;
		if (dy < 0)
			ty -= attack_distance;
		
		headTo(tx, ty);
	}
	
	public void move_to()
	{
		long time = (new Date()).getTime();
		
	    //figure out the angle to get to destination
    	headToAngle = calcBaring(headToX, headToY);
    	
    	//rotate ship to proper angle
    	if ((thrust_angle >= (headToAngle - speed)) && (thrust_angle <= (headToAngle + speed)))
    	{
    		drotate = 0;
    		thrust_angle = Math.round((float)headToAngle);
    		dspeed = -speed;
    	}
    	
    	//set the coord exact if it is close
    	if ((centerX() >= (headToX - speed)) && (centerX() <= (headToX + speed)))
    		setPos(Math.round((float)headToX), centerY());
    	if ((centerY() >= (headToY - speed)) && (centerY() <= (headToY + speed)))
    		setPos(centerX(), Math.round((float)headToY));
    	
    	//stop the ship when it gets to desination
    	if ((centerX() >= (headToX - speed)) && (centerX() <= (headToX + speed)) &&
    		(centerY() >= (headToY - speed)) && (centerY() <= (headToY + speed)))
    	{
    		setPos(Math.round((float)headToX), Math.round((float)headToY));
    		stopAutoMove();
    		next_move = time + move_delay;
    	}
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
		else if (target == s)
			target = null;
	}
	
	public boolean getPursue()
	{
		return pursue;
	}
	
	public void setPursue(boolean pursue)
	{
		this.pursue = pursue;
	}
	
	public boolean getRandom()
	{
		return random_mode;
	}
	
	public void setRandom(boolean random_mode)
	{
		this.random_mode = random_mode;
	}
	
	//tell the ship to head to specific location
	public void headTo(int x, int y)
	{
		headToX = x;
		headToY = y;
		headToAngle = calcBaring(headToX, headToY);
		
		if (headToAngle > thrust_angle)
			drotate = rotation_speed;
		else
			drotate = -rotation_speed;
			
		if ((headToAngle <= 90 && headToAngle >= 0) && (thrust_angle >= 270 && thrust_angle <= 360))
			drotate = rotation_speed;
		if ((thrust_angle <= 90 && thrust_angle >= 0) && (headToAngle >= 270 && headToAngle <= 360))
			drotate = -rotation_speed;
			
		//comment out to rotate THEN move
		//dspeed = -coast_speed;
	}
	
	
	//make the ship stop moving
	public void stopAutoMove()
	{
		if(headToX != Integer.MAX_VALUE || headToY != Integer.MAX_VALUE || headToAngle != Integer.MAX_VALUE)
        {
        	headToX = Integer.MAX_VALUE;
       		headToY = Integer.MAX_VALUE;
        	headToAngle = Integer.MAX_VALUE;
        	dspeed = 0;
        	drotate = 0;
        }
	}
	
	public void addPoint(int x, int y)
	{
		pathx.add(new Integer(x));
		pathy.add(new Integer(y));
	}

	public Laser fire()
	{
		Laser l = null;
		long time = (new Date()).getTime();
		
		if (time >= next_shot && charged && target != null && exploded_at <= 0)
		{
			//use target baring
			l = new Laser(this, color, centerX(), centerY(), (int)calcBaring(target.centerX(), target.centerY()));
		
			l.setVisible(true);
			
			next_shot = time + shot_delay;
			charged = false;
			if (random_color)
				color = COLOR.randomColor();
		}
		
		return l;
	}
	
	//returns the current image to display
	public Image getImage()
	{
		Image img = null;
		
		if (charged)
			img = super.getImage();
		else
			img = ring;
		
		return img;
	}
}
