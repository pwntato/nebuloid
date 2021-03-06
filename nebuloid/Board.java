package nebuloid;

import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Rectangle;

import javax.swing.JPanel;

import java.util.ArrayList;
//import java.util.Random;
//import java.util.Date;

import nebuloid.Ship;
import nebuloid.Laser;
import nebuloid.Nebula;
import nebuloid.Ball;
//import nebuloid.RingShip;
//import nebuloid.FighterShip;
//import nebuloid.BombShip;
//import nebuloid.MotherShip;
import nebuloid.Level;

public class Board extends JPanel implements Runnable
{
	//controls main game loop
	boolean alive = true;	
	boolean pause = false;
	Thread animator;
	
	//the game view dimensions
	public Rectangle view;

	//player ship
	int lives;
	
	//shot lasers
	public ArrayList lasers;
	
	//level details
	int level_number;
	Level level;
	
	Image pause_menu;
	ArrayList numbers;
	
	int display_level_frames = 50;
	int display_level_cycles = 0;
	
	//the loop delay 
	final int DELAY = 50;
	
	static final long serialVersionUID = 0;
	
	public Board()
	{
		//Random rand = new Random((new Date()).getTime());
	
		//setup user events
		addKeyListener(new KAdapter());
    	addMouseListener(new MAdapter());
    	
   		//setup window
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        
        pause_menu = Toolkit.getDefaultToolkit().getImage(Board.class.getResource("/images/menu.png"));
        numbers = new ArrayList();
        numbers.add(0, Toolkit.getDefaultToolkit().getImage(Board.class.getResource("/images/zero.png")));
        numbers.add(1, Toolkit.getDefaultToolkit().getImage(Board.class.getResource("/images/one.png")));
        numbers.add(2, Toolkit.getDefaultToolkit().getImage(Board.class.getResource("/images/two.png")));
        numbers.add(3, Toolkit.getDefaultToolkit().getImage(Board.class.getResource("/images/three.png")));
        numbers.add(4, Toolkit.getDefaultToolkit().getImage(Board.class.getResource("/images/four.png")));
        numbers.add(5, Toolkit.getDefaultToolkit().getImage(Board.class.getResource("/images/five.png")));
        numbers.add(6, Toolkit.getDefaultToolkit().getImage(Board.class.getResource("/images/six.png")));
        numbers.add(7, Toolkit.getDefaultToolkit().getImage(Board.class.getResource("/images/seven.png")));
        numbers.add(8, Toolkit.getDefaultToolkit().getImage(Board.class.getResource("/images/eight.png")));
        numbers.add(9, Toolkit.getDefaultToolkit().getImage(Board.class.getResource("/images/nine.png")));

        lives = 3;
        
        lasers = new ArrayList();
        
        level_number = 1;
		level = Level.getLevel(level_number);
		display_level_cycles = display_level_frames;
        	
        pause = true;
	}
	
 	public void addNotify() 
 	{
 		super.addNotify();
		animator = new Thread(this);
		animator.start();
    }
    
    public void paint(Graphics g)
    {
    	super.paint(g);
    	Graphics2D g2d = (Graphics2D)g;
    	
    	//center ship in view (if possible)
    	view = new Rectangle(level.ship.getX() - (this.getWidth()/2), 
    						level.ship.getY() - (this.getHeight()/2), 
    						this.getWidth(), 
    						this.getHeight());
    	
    	//don't let view leave world
    	if (!level.world.contains(view))
    	{
    		//check upper left
    		if (view.getX() < level.world.getX())
    			view.setRect(level.world.getX(), view.getY(), view.getWidth(), view.getHeight());
    		if (view.getY() < level.world.getY())
    			view.setRect(view.getX(), level.world.getY(), view.getWidth(), view.getHeight());
    			
    		//check lower right
    		if (view.getX() + view.getWidth() > level.world.getX() + level.world.getWidth())
    			view.setRect(level.world.getX() + level.world.getWidth() - view.getWidth(), 
    						view.getY(), 
    						view.getWidth(), 
    						view.getHeight());
    						
    		if (view.getY() + view.getHeight() > level.world.getY() + level.world.getHeight())
    			view.setRect(view.getX(), 
    						level.world.getY() + level.world.getHeight() - view.getHeight(), 
    						view.getWidth(), 
    						view.getHeight());
    	}
    	
    	drawArray(level.stars, g2d);
    	drawArray(lasers, g2d);
    	drawArray(level.balls, g2d);
    	
    	level.ship.draw(g2d, view);
    	
   		drawArray(level.enemies, g2d);
			
		drawArray(level.nebulae, g2d);
		
		//draw interface
		//number of lives
		int size = 25;
		g2d.drawImage((Image)numbers.get(lives), 10, 10, size, size, null);
		
		//player and mother ship health bar
		healthBar(g2d, level.ship.getImage(), 10, level.ship.getHP(), level.ship.MAX_HP, Color.RED);
		if (level.mother_ship != null)
			healthBar(g2d, level.mother_ship.getImage(), 40, level.mother_ship.getHP(), level.mother_ship.MAX_HP, Color.GREEN);
		
		//draw markers for nebulae if not on screen
		for (int i=0; i<level.nebulae.size(); i++)
			drawMarker(g2d, (Nebula)level.nebulae.get(i));
			
		//draw marker if mother ship is not on the screen
		drawMarker(g2d, level.mother_ship);
		
		//draw pause menu
		if (pause)
		{
			int mwidth = 640;
			int mheight = 480;
			
			if (view.getWidth() < mwidth)
				mwidth = (int)view.getWidth();
			if (view.getHeight() < mheight)
				mheight = (int)view.getHeight();
			
			g2d.drawImage(pause_menu, 
							(int)((view.getWidth()/2) - (mwidth/2)), 
							(int)((view.getHeight()/2) - (mheight/2)), 
							mwidth, 
							mheight, null);
		}
		//show level number
		else if (display_level_cycles > 0)
		{
			int digit1 = 0;
			int digit2 = 0;
			
			if (level_number >= 10)
			{
				digit1 = level_number / 10;
				digit2 = level_number % 10;
			}
			else
				digit2 = level_number;
				
			//display level
			g2d.drawImage((Image)numbers.get(digit1), (int)(view.getWidth()/2) - 25, (int)(view.getHeight()/2) - level.ship.getHeight(), 50, 50, null);
			g2d.drawImage((Image)numbers.get(digit2), (int)(view.getWidth()/2) + 25, (int)(view.getHeight()/2) - level.ship.getHeight(), 50, 50, null);
		}
    	
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
    }
    
    public void drawMarker(Graphics2D g2d, Sprite s)
    {
		//draw marker if mother ship is not on the screen
		if (!s.getRect().intersects(view))
		{
			int mark_x = s.centerX() - (int)view.getX();
			int mark_y = s.centerY() - (int)view.getY();
			int img_size = 25;
			int img_space = 5;
				
			if (mark_x < img_size - img_space)
				mark_x = img_space;
			if (mark_x > view.getWidth() - img_size - img_space)
				mark_x = (int)view.getWidth() - img_size - img_space;
				
			if (mark_y < 45 + img_size - img_space)
				mark_y = 45 + img_size - img_space;
			if (mark_y > view.getHeight() - img_size - img_space)
				mark_y = (int)view.getHeight() - img_size - img_space;
				
			g2d.drawImage(s.getImage(), mark_x, mark_y, img_size, img_size, null);		
		}
    }
    
    //draw a health bar showing the current health relative to max possible health
    public void healthBar(Graphics2D g2d, Image icon, int y, int hp, int max_hp, Color color)
    {
    	Color c = g2d.getColor();
    	
		g2d.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 128));
		g2d.fillRect(70, y, (int)(((double)hp/(double)max_hp)*(double)(view.getWidth() - 90)), 20);
		g2d.setColor(color);
		g2d.drawRect(70, y, (int)(view.getWidth() - 90), 20);
    	g2d.drawImage(icon, 40, y, 25, 25, null);
    	
    	g2d.setColor(c);
    }
    
    public void drawArray(ArrayList a, Graphics2D g2d)
    {
    	for (int i=0; i<a.size(); i++)
    	{
			Sprite s = (Sprite)a.get(i);
			if (s.getRect().intersects(view))
				s.draw(g2d, view);
		}
    }
	
	//executes a singe fram of the game loop
	public void exec_frame()
	{	
		//pause if the upgrade window is open
		if (!pause && view != null)
		{
			//define an "active rect" larger then the current view
			//code speed is improved by only moving enemies close 
			//to the currently visible screen
			Rectangle active_rect = new Rectangle((int)(view.getX() - (view.getWidth()/2)), 
												(int)(view.getY() - (view.getHeight()/2)), 
												(int)view.getWidth()*2, 
												(int)view.getHeight()*2);
												
			//decrement level display frame count
			display_level_cycles -= 1;
												
			level.ship.move();	
			//don't let ship leave the view
			if (level.ship.centerX() < view.getX())
			{
				level.ship.setPos((int)view.getX(), level.ship.getY());
				level.ship.moveBackward();
			}
			if (level.ship.centerY() < view.getY())
			{
				level.ship.setPos(level.ship.getX(), (int)view.getY());
				level.ship.moveBackward();
			}
			if (level.ship.centerX() > view.getX() + view.getWidth())
			{
				level.ship.setPos((int)(view.getX() + view.getWidth()), level.ship.getY());
				level.ship.moveBackward();
			}
			if (level.ship.centerY() > view.getY() + view.getHeight())
			{
				level.ship.setPos(level.ship.getX(), (int)(view.getY() + view.getHeight()));
				level.ship.moveBackward();
			}
			
			//move the lasers, delete any outside the active area
			for (int i=0; i<lasers.size(); i++)
			{
				Laser l = (Laser)lasers.get(i);
				
				if (active_rect.intersects(l.getRect()) && l.getVisible())
				{
					l.move();
					
					//shoot player ship?
					if (l.getShotBy() != level.ship && l.getRect().intersects(level.ship.getRect()))
					{
						level.ship.setHP(level.ship.getHP() - l.getDamage());
						l.setVisible(false);
					}
						
					//shoot enemy ship?
					for (int j=0; j<level.enemies.size(); j++)
					{
						Ship s = (Ship)level.enemies.get(j);
						
						if (l.getShotBy() == level.ship && active_rect.intersects(s.getRect()) && l.getRect().intersects(s.getRect()))
						{
							s.setHP(s.getHP() - l.getDamage());
							l.setVisible(false);
						}
					}
					
					//if laser hits bomb, blow up bomb (laser doesn't implement explode())
					for (int j=0; j<lasers.size(); j++)
					{
						Laser l1 = (Laser)lasers.get(j);
						
						if (l.getShotBy() != l1.getShotBy() && active_rect.intersects(l1.getRect()) && l.getRect().intersects(l1.getRect()))
							l1.explode();
					}
				}
				else
					l.setVisible(false);
					
				if (!l.getVisible())
					lasers.remove(i);
			}
			
			//check if ships/lasers have hit a ball
			for (int i=0; i<level.balls.size(); i++)
			{
				Ball b = (Ball)level.balls.get(i);
				
				if (active_rect.intersects(b.getRect()))
				{
					b.move();
					
					for (int j=0; j<lasers.size(); j++)
					{
						Laser l = (Laser)lasers.get(j);
					
						//remove a laser if it hits a ball
						if (!b.isExploding() && l.getShotBy() != level.mother_ship && l.getRect().intersects(b.getRect()))
						{
							l.setVisible(false);
							
							//remove the ball if the laser is the same color
							if (l.getColor() == b.getColor())
								b.explode();
						}	
					}	
					
					//destroy the ship if the player hits a ball
					if (!b.isExploding() && b.getRect().intersects(level.ship.getRect()))
						level.ship.setHP(0);
				}
				
				if (!b.getVisible())
					level.balls.remove(i);
			}
			
			for (int i=0; i<level.enemies.size(); i++)
			{
				Ship s = (Ship)level.enemies.get(i);
				
				if (s.getVisible())
				{
					if (active_rect.intersects(s.getRect()))
					{
						s.move();
				
						Laser l = s.fire();
						if (l != null)
							lasers.add(l);
						
						if (!s.isExploding() && s.getRect().intersects(level.ship.getRect()))
						{
							level.ship.setHP(level.ship.getHP() - 200);
							if (s != level.mother_ship)
								s.setHP(s.getHP() - 50);
						}
							
						s.aquireTarget(level.ship);
					}
				}
				else
					level.enemies.remove(i);
			}
			
			//move neublae
			for (int i=0; i<level.nebulae.size(); i++)
			{
				Nebula n = (Nebula)level.nebulae.get(i);
				
				if (view.intersects(n.getRect()))
				{
					n.move();
				
					if (n.getRect().contains(level.ship.getRect()))
						level.ship.setColor(n.getColor());
				}
			}
			
			//if player kills mother ship, beating the level
			if (level.mother_ship != null && level.mother_ship.getHP() <= 0)
			{
				lives += 1;
				level_number += 1;
				level = Level.getLevel(level_number);
				display_level_cycles = display_level_frames;
			}
			
			//if player dies
			if (level.ship.getHP() <= 0)
			{
				lives -= 1;
				level.ship.setHP(level.ship.MAX_HP);
				level.ship.setPos(level.start_x, level.start_y);
				level.ship.moveBackward();
				display_level_cycles = display_level_frames;
				pause = true;
			}
				
			//end the game if player runs out of lives and don't let player exceed 9 lives
			if (lives < 0)
			{
				lives = 3;
        		level_number = 1;
				level = Level.getLevel(level_number);
				display_level_cycles = display_level_frames;
				pause = true;
			}
			if (lives > 9)
				lives = 9;
		}
	}
	
	//nuts and bolts part of the game loop
    public void run() 
    {
    	//varibale to track game loop timing
        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

		//only continue the loop while the main loop control variable is true
        while (alive) 
        {
        	//execute single frame of game loop
        	exec_frame();
        	//draw frame
            repaint();
			
			//ensure the game will run around the smae speed on different systems
            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0)
                sleep = 2;
            try 
            {
                Thread.sleep(sleep);
            } catch (InterruptedException e) 
            {
                System.out.println("interrupted");
            }

            beforeTime = System.currentTimeMillis();
        }
    	
    	//run after player death
    }
	
	class MAdapter extends MouseAdapter 
	{
        public void mousePressed(MouseEvent e) 
        {
            //int x = e.getX();
            //int y = e.getY();
        }
        
        public void mouseReleased(MouseEvent e) 
        {
        }
	}
	
	private class KAdapter extends KeyAdapter 
    {
        public void keyReleased(KeyEvent e) 
        {
        	int key = e.getKeyCode();

			if (!pause)
        	{
				//stop movement when user releases key
		    	if (key == KeyEvent.VK_UP) 
		    	    level.ship.stopMove();
		    	if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT ) 
		    	    level.ship.stopRotate();
		   	}
        }

        public void keyPressed(KeyEvent e) 
        {
        	int key = e.getKeyCode();
        
        	//pause/resume on space
        	if (key == KeyEvent.VK_SPACE) 
        	{
        		pause = !pause;
        	}
        
        	if (!pause)
        	{
		    	//fire on control key
		    	if (e.isControlDown()) 
		    	{
					Laser l = level.ship.fire();
					if (l != null)
						lasers.add(l);
		    	}

				//move on arrows
		    	if (key == KeyEvent.VK_UP) 
		    		level.ship.moveForward();

		    	if (key == KeyEvent.VK_DOWN) 
		    		level.ship.moveBackward();
		    
		    	if (key == KeyEvent.VK_LEFT) 
		    		level.ship.rotateLeft();
		    
		    	if (key == KeyEvent.VK_RIGHT)  
		    		level.ship.rotateRight();
        	}
        }
    }
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
