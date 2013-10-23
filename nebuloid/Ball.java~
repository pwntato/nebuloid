package nebuloid;

import java.util.ArrayList;

import java.awt.Toolkit;

public class Ball extends Nebula
{
	static int size = 25;
	
	public Ball(int color, int x, int y)
	{
		red = Toolkit.getDefaultToolkit().getImage(Nebula.class.getResource("/images/ball_red.png"));
		blue = Toolkit.getDefaultToolkit().getImage(Nebula.class.getResource("/images/ball_blue.png"));
		purple = Toolkit.getDefaultToolkit().getImage(Nebula.class.getResource("/images/ball_purple.png"));
		orange = Toolkit.getDefaultToolkit().getImage(Nebula.class.getResource("/images/ball_orange.png"));
		yellow = Toolkit.getDefaultToolkit().getImage(Nebula.class.getResource("/images/ball_yellow.png"));
		green = Toolkit.getDefaultToolkit().getImage(Nebula.class.getResource("/images/ball_green.png"));
		
		width = size;
		height = size;
		
		explosion_time = 500;
		
		setPos(x, y);
		setColor(color);
		setVisible(true);
	}
	
	public static ArrayList BallRing(int x, int y, int radius, int color)
	{
		ArrayList balls = new ArrayList();
		double perimiter = 2 * Math.PI * radius;
		int ball_count = (int)Math.round(perimiter / size) - 2;
		double angle = 0;
		int bcolor = color;
		
		while (balls.size() <= 2 || !((Ball)balls.get(0)).getRect().intersects(((Ball)balls.get(balls.size() - 1)).getRect()))
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
		
			balls.add(new Ball(bcolor, bx, by));
			
			angle += 360 / ball_count;
		}
		
		return balls;
	}
	
	public static ArrayList BallRingRing(int x, int y, int radius, int color, int count)
	{
		ArrayList balls = new ArrayList();
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
		
			balls.addAll(BallRing(bx, by, 125, bcolor));
			
			angle += 360 / count;
		}
		
		return balls;
	}
	
	//allows the ball to explode
	public void move()
	{	
		if (exploded_at >= 0)
			move_explosion();
	}
}
