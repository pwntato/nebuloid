package nebuloid;

import java.awt.Rectangle;

import java.util.ArrayList;
import java.util.Random;
import java.util.Date;

import nebuloid.COLOR;
import nebuloid.Ship;
//import nebuloid.Laser;
import nebuloid.Nebula;
import nebuloid.Ball;
import nebuloid.RingShip;
import nebuloid.FighterShip;
import nebuloid.BombShip;
import nebuloid.MotherShip;

public class Level
{
	//the game world dimensions
	public Rectangle world;
	
	//player ship
	Ship ship;
	public int start_x;
	public int start_y;
	
	//stars to show movement
	public ArrayList stars;
	//Nebulae
	public ArrayList nebulae;
	//balls
	public ArrayList balls;
	//enemies
	public ArrayList enemies;
	//the mother ship (for tracking, included in enemies array)
	public MotherShip mother_ship;
	
	public Level()
	{
		ship = new Ship();
        nebulae = new ArrayList();
        balls = new ArrayList();
        enemies = new ArrayList();
        stars = new ArrayList();
	}
	
	public static Level getLevel(int level)
	{
		Level l = new Level();
		
		if (level == 1)
			l = level1();
		else if (level == 2)
			l = level2();
		else if (level == 3)
			l = level3();
		else if (level == 4)
			l = level4();
		else if (level == 6)
			l = level7();
		else if (level == 9)
			l = level5();
		else if (level == 12)
			l = level6();
		else if (level == 15)
			l = level8();
		else if (level == 20)
			l = level9();
		else		
			l = RandomLevel(level);
			
		return l;
	}
	
	//into, no nebulae
	public static Level level1()
	{
		Level level = new Level();
		Random rand = new Random((new Date()).getTime());
		
		level.world = new Rectangle(0, 0, 2000, 2000);
       
        level.start_x = (int)(level.world.getWidth()/2);
        level.start_y = (int)(level.world.getHeight()/2);
       	level.ship.setPos(level.start_x, level.start_y);
       	level.ship.setColor(COLOR.RED);
       	
       	level.mother_ship = new MotherShip(level.start_x + 200, level.start_y - 200, level.ship);
        level.enemies.add(level.mother_ship);
        
        level.balls.addAll(Ball.BallRing(level.mother_ship.centerX(), level.mother_ship.centerY(), 100, COLOR.RED));
       	
        //make sure ship won't automatically die
        while (level.overlapsExisiting(level.ship))
        {
        	level.start_x = rand.nextInt((int)level.world.getWidth());
        	level.start_y = rand.nextInt((int)level.world.getHeight());
       		level.ship.setPos(level.start_x, level.start_y);
       	}
       	
        //random stars
        for (int i=0; i<100; i++)
        	level.stars.add(new Star(rand.nextInt((int)level.world.getWidth()), rand.nextInt((int)level.world.getHeight())));
		
		return level;
	}
	
	//intro to nebulae
	public static Level level2()
	{
		Level level = new Level();
		Random rand = new Random((new Date()).getTime());
		
		level.world = new Rectangle(0, 0, 2000, 2000);
       
        level.start_x = (int)(level.world.getWidth()/2);
        level.start_y = (int)(level.world.getHeight()/2);
       	level.ship.setPos(level.start_x, level.start_y);
       	level.ship.setColor(COLOR.RED);
       	
       	level.mother_ship = new MotherShip(level.start_x + 200, level.start_y - 200, level.ship);
        level.enemies.add(level.mother_ship);
        
        level.balls.addAll(Ball.BallRing(level.mother_ship.centerX(), level.mother_ship.centerY(), 100, COLOR.BLUE));
      
        level.nebulae.add(new Nebula(COLOR.BLUE, level.start_x - 200, level.start_y + 200));
        
        //make sure ship won't automatically die
        while (level.overlapsExisiting(level.ship))
        {
        	level.start_x = rand.nextInt((int)level.world.getWidth());
        	level.start_y = rand.nextInt((int)level.world.getHeight());
       		level.ship.setPos(level.start_x, level.start_y);
       	}
       	
        //random stars
        for (int i=0; i<100; i++)
        	level.stars.add(new Star(rand.nextInt((int)level.world.getWidth()), rand.nextInt((int)level.world.getHeight())));
		
		return level;
	}
	
	public static Level level3()
	{
		Level level = new Level();
		Random rand = new Random((new Date()).getTime());
		
		level.world = new Rectangle(0, 0, 5000, 5000);
       
        level.start_x = (int)(level.world.getWidth()/2);
        level.start_y = (int)(level.world.getHeight()/2);
       	level.ship.setPos(level.start_x, level.start_y);
       	level.ship.setColor(COLOR.BLUE);
       	
		level.addMotherShip();
        level.balls.addAll(Ball.BallRing(level.mother_ship.centerX(), level.mother_ship.centerY(), 100, COLOR.RED));
        
        for (int i=0; i<10; i++)
        	level.enemies.add(new FighterShip(COLOR.BLUE, rand.nextInt((int)level.world.getWidth()), rand.nextInt((int)level.world.getHeight())));
        
        Nebula n = level.newNebula(COLOR.RED);
        level.nebulae.add(n);
        
        //make sure ship won't automatically die
        while (level.overlapsExisiting(level.ship))
        {
        	level.start_x = rand.nextInt((int)level.world.getWidth());
        	level.start_y = rand.nextInt((int)level.world.getHeight());
       		level.ship.setPos(level.start_x, level.start_y);
       	}
       	
        //random stars
        for (int i=0; i<1000; i++)
        	level.stars.add(new Star(rand.nextInt((int)level.world.getWidth()), rand.nextInt((int)level.world.getHeight())));
		
		return level;
	}
	
	public static Level level4()
	{
		Level level = new Level();
		Random rand = new Random((new Date()).getTime());
		
		ArrayList colors = RandomColors();
		int color1 = ((Integer)colors.get(0)).intValue();
		int color2 = ((Integer)colors.get(1)).intValue();
		int color3 = ((Integer)colors.get(2)).intValue();
		int color4 = ((Integer)colors.get(3)).intValue();
		
		level.world = new Rectangle(0, 0, 5000, 5000);
       
        level.start_x = (int)(level.world.getWidth()/2);
        level.start_y = (int)(level.world.getHeight()/2);
       	level.ship.setPos(level.start_x, level.start_y);
       	level.ship.setColor(color1);
       	
       	level.addMotherShip();
        
        level.balls.addAll(Ball.BallRing(level.mother_ship.centerX(), level.mother_ship.centerY(), 100, color2));
        level.balls.addAll(Ball.BallRing(level.mother_ship.centerX(), level.mother_ship.centerY(), 125, color3));
        
        for (int i=0; i<5; i++)
        	level.enemies.add(new FighterShip(color4, rand.nextInt((int)level.world.getWidth()), rand.nextInt((int)level.world.getHeight())));
       	
       	for (int i=0; i<5; i++)
        	level.enemies.add(new RingShip(color4, rand.nextInt((int)level.world.getWidth()), rand.nextInt((int)level.world.getHeight())));
        
        //yellow
        Nebula n = level.newNebula(color1);
        level.nebulae.add(n);
        
        //red with yellow ring
        n = level.newNebula(color2);
        level.nebulae.add(n);
        level.balls.addAll(Ball.BallRing(n.centerX(), n.centerY(), 150, color1));
        
        //blue with red ring
        n = level.newNebula(color3);
        level.nebulae.add(n);
        level.balls.addAll(Ball.BallRing(n.centerX(), n.centerY(), 150, color2));
        
        //make sure ship won't automatically die
        while (level.overlapsExisiting(level.ship))
        {
        	level.start_x = rand.nextInt((int)level.world.getWidth());
        	level.start_y = rand.nextInt((int)level.world.getHeight());
       		level.ship.setPos(level.start_x, level.start_y);
       	}
       	
        //random stars
        for (int i=0; i<1000; i++)
        	level.stars.add(new Star(rand.nextInt((int)level.world.getWidth()), rand.nextInt((int)level.world.getHeight())));
		
		return level;
	}
	
	public static Level level5()
	{
		Level level = new Level();
		Random rand = new Random((new Date()).getTime());
		
		ArrayList colors = RandomColors();
		int color1 = ((Integer)colors.get(0)).intValue();
		int color2 = ((Integer)colors.get(1)).intValue();
		int color3 = ((Integer)colors.get(2)).intValue();
		int color4 = ((Integer)colors.get(3)).intValue();
		
		level.world = new Rectangle(0, 0, 5000, 5000);
       
        level.start_x = (int)(level.world.getWidth()/2);
        level.start_y = (int)(level.world.getHeight()/2);
       	level.ship.setPos(level.start_x, level.start_y);
       	level.ship.setColor(color1);
       	
       	level.addMotherShip();
        
        level.balls.addAll(Ball.BallRing(level.mother_ship.centerX(), level.mother_ship.centerY(), 100, color2));
        level.balls.addAll(Ball.BallRing(level.mother_ship.centerX(), level.mother_ship.centerY(), 125, color3));
        
        ArrayList ring = RingShip.ringShipRing(color4, level.mother_ship.centerX(), level.mother_ship.centerY(), 200, 8);
        for (int i=0; i<ring.size(); i++)
        {
        	RingShip r = (RingShip)ring.get(i);
       		r.setRandom(false);
        	r.setPursue(false);
        	level.enemies.add(r);
        }
        
        for (int i=0; i<5; i++)
        	level.enemies.add(new FighterShip(color4, rand.nextInt((int)level.world.getWidth()), rand.nextInt((int)level.world.getHeight())));
       	
       	for (int i=0; i<5; i++)
        	level.enemies.add(new RingShip(color4, rand.nextInt((int)level.world.getWidth()), rand.nextInt((int)level.world.getHeight())));
        	
       	for (int i=0; i<5; i++)
        	level.enemies.add(new FighterShip(color4, rand.nextInt((int)level.world.getWidth()), rand.nextInt((int)level.world.getHeight())));
        
        //purple
        Nebula n = level.newNebula(color1);
        level.nebulae.add(n);
        
        //green with purple ring
        n = level.newNebula(color2);
        level.nebulae.add(n);
        level.balls.addAll(Ball.BallRing(n.centerX(), n.centerY(), 150, color1));
        
        //blue with red ring
        n = level.newNebula(color3);
        level.nebulae.add(n);
        level.balls.addAll(Ball.BallRing(n.centerX(), n.centerY(), 150, color2));
        
        //make sure ship won't automatically die
        while (level.overlapsExisiting(level.ship))
        {
        	level.start_x = rand.nextInt((int)level.world.getWidth());
        	level.start_y = rand.nextInt((int)level.world.getHeight());
       		level.ship.setPos(level.start_x, level.start_y);
       	}
       	
        //random stars
        for (int i=0; i<1000; i++)
        	level.stars.add(new Star(rand.nextInt((int)level.world.getWidth()), rand.nextInt((int)level.world.getHeight())));
		
		return level;
	}

	public static Level level6()
	{
		Level level = new Level();
		Random rand = new Random((new Date()).getTime());
		
		ArrayList colors = RandomColors();
		int color1 = ((Integer)colors.get(0)).intValue();
		int color2 = ((Integer)colors.get(1)).intValue();
		int color3 = ((Integer)colors.get(2)).intValue();
		int color4 = ((Integer)colors.get(3)).intValue();
		int color5 = ((Integer)colors.get(4)).intValue();
		
		level.world = new Rectangle(0, 0, 5000, 5000);
       
        level.start_x = (int)(level.world.getWidth()/2);
        level.start_y = (int)(level.world.getHeight()/2);
       	level.ship.setPos(level.start_x, level.start_y);
       	level.ship.setColor(color1);
       	
       	level.addMotherShip();
        
        level.balls.addAll(Ball.BallRing(level.mother_ship.centerX(), level.mother_ship.centerY(), 100, color2));
        level.balls.addAll(Ball.BallRing(level.mother_ship.centerX(), level.mother_ship.centerY(), 125, color3));
        
        level.enemies.addAll(RingShip.ringShipRing(color5, level.mother_ship.centerX(), level.mother_ship.centerY(), 200, 8));
        
        level.balls.addAll(Ball.BallRing(level.mother_ship.centerX(), level.mother_ship.centerY(), 275, color4));
        
        for (int i=0; i<10; i++)
        	level.enemies.add(new FighterShip(color5, rand.nextInt((int)level.world.getWidth()), rand.nextInt((int)level.world.getHeight())));
       	
       	for (int i=0; i<5; i++)
        	level.enemies.add(new RingShip(color5, rand.nextInt((int)level.world.getWidth()), rand.nextInt((int)level.world.getHeight())));
        	
       	for (int i=0; i<5; i++)
        	level.enemies.add(new FighterShip(color5, rand.nextInt((int)level.world.getWidth()), rand.nextInt((int)level.world.getHeight())));
        
        Nebula n = level.newNebula(color1);
        level.nebulae.add(n);
        
        n = level.newNebula(color2);
        level.nebulae.add(n);
        level.balls.addAll(Ball.BallRing(n.centerX(), n.centerY(), 150, color1));
        
        n = level.newNebula(color3);
        level.nebulae.add(n);
        level.balls.addAll(Ball.BallRing(n.centerX(), n.centerY(), 150, color2));
        
        n = level.newNebula(color4);
        level.nebulae.add(n);
        level.balls.addAll(Ball.BallRing(n.centerX(), n.centerY(), 150, color3));
        
        //make sure ship won't automatically die
        while (level.overlapsExisiting(level.ship))
        {
        	level.start_x = rand.nextInt((int)level.world.getWidth());
        	level.start_y = rand.nextInt((int)level.world.getHeight());
       		level.ship.setPos(level.start_x, level.start_y);
       	}
       	
        //random stars
        for (int i=0; i<1000; i++)
        	level.stars.add(new Star(rand.nextInt((int)level.world.getWidth()), rand.nextInt((int)level.world.getHeight())));
		
		return level;
	}
	
	public static Level level7()
	{
		Level level = new Level();
		Random rand = new Random((new Date()).getTime());
		
		level.world = new Rectangle(0, 0, 5000, 5000);
       
        level.start_x = (int)(level.world.getWidth()/2);
        level.start_y = (int)(level.world.getHeight()/2);
       	level.ship.setPos(level.start_x, level.start_y);
       	level.ship.setColor(COLOR.RED);
       	
       	level.addMotherShip();
        
        level.balls.addAll(Ball.BallRing(level.mother_ship.centerX(), level.mother_ship.centerY(), 100, COLOR.RED));
        
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() - 125, level.mother_ship.centerY() - 125));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() - 100, level.mother_ship.centerY() - 125));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() - 75, level.mother_ship.centerY() - 125));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() - 50, level.mother_ship.centerY() - 125));
        
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() + 125, level.mother_ship.centerY() + 125));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() + 100, level.mother_ship.centerY() + 125));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() + 75, level.mother_ship.centerY() + 125));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() + 50, level.mother_ship.centerY() + 125));
       
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() + 125, level.mother_ship.centerY() - 125));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() + 100, level.mother_ship.centerY() - 125));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() + 75, level.mother_ship.centerY() - 125));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() + 50, level.mother_ship.centerY() - 125));
        
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() - 125, level.mother_ship.centerY() + 125));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() - 125, level.mother_ship.centerY() + 100));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() - 125, level.mother_ship.centerY() + 75));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() - 125, level.mother_ship.centerY() + 50));
        
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() + 125, level.mother_ship.centerY() + 125));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() + 125, level.mother_ship.centerY() + 100));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() + 125, level.mother_ship.centerY() + 75));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() + 125, level.mother_ship.centerY() + 50));
        
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() - 125, level.mother_ship.centerY() - 125));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() - 125, level.mother_ship.centerY() - 100));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() - 125, level.mother_ship.centerY() - 75));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() - 125, level.mother_ship.centerY() - 50));
        
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() - 125, level.mother_ship.centerY() + 125));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() - 100, level.mother_ship.centerY() + 125));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() - 75, level.mother_ship.centerY() + 125));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() - 50, level.mother_ship.centerY() + 125));
        
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() + 125, level.mother_ship.centerY() - 125));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() + 125, level.mother_ship.centerY() - 100));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() + 125, level.mother_ship.centerY() - 75));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() + 125, level.mother_ship.centerY() - 50));
        
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() + 150, level.mother_ship.centerY() - 50));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() + 175, level.mother_ship.centerY() - 50));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() + 200, level.mother_ship.centerY() - 50));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() + 225, level.mother_ship.centerY() - 50));
        
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() + 150, level.mother_ship.centerY() - 50));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() + 175, level.mother_ship.centerY() - 50));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() + 200, level.mother_ship.centerY() - 50));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() + 225, level.mother_ship.centerY() - 50));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() + 250, level.mother_ship.centerY() - 50));
        
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() + 150, level.mother_ship.centerY() + 50));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() + 175, level.mother_ship.centerY() + 50));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() + 200, level.mother_ship.centerY() + 50));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() + 225, level.mother_ship.centerY() + 50));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() + 250, level.mother_ship.centerY() + 50));
        
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() - 150, level.mother_ship.centerY() - 50));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() - 175, level.mother_ship.centerY() - 50));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() - 200, level.mother_ship.centerY() - 50));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() - 225, level.mother_ship.centerY() - 50));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() - 250, level.mother_ship.centerY() - 50));
        
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() - 150, level.mother_ship.centerY() + 50));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() - 175, level.mother_ship.centerY() + 50));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() - 200, level.mother_ship.centerY() + 50));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() - 225, level.mother_ship.centerY() + 50));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() - 250, level.mother_ship.centerY() + 50));
        
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() - 50, level.mother_ship.centerY() - 150));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() - 50, level.mother_ship.centerY() - 175));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() - 50, level.mother_ship.centerY() - 200));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() - 50, level.mother_ship.centerY() - 225));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() - 50, level.mother_ship.centerY() - 250));
        
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() + 50, level.mother_ship.centerY() - 150));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() + 50, level.mother_ship.centerY() - 175));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() + 50, level.mother_ship.centerY() - 200));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() + 50, level.mother_ship.centerY() - 225));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() + 50, level.mother_ship.centerY() - 250));
        
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() + 50, level.mother_ship.centerY() + 150));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() + 50, level.mother_ship.centerY() + 175));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() + 50, level.mother_ship.centerY() + 200));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() + 50, level.mother_ship.centerY() + 225));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() + 50, level.mother_ship.centerY() + 250));
        
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() - 50, level.mother_ship.centerY() + 150));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() - 50, level.mother_ship.centerY() + 175));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() - 50, level.mother_ship.centerY() + 200));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() - 50, level.mother_ship.centerY() + 225));
        level.balls.add(new Ball(COLOR.GREEN, level.mother_ship.centerX() - 50, level.mother_ship.centerY() + 250));
        
        level.balls.addAll(Ball.BallRing(level.mother_ship.centerX(), level.mother_ship.centerY(), 280, COLOR.ORANGE));
        
        for (int i=0; i<10; i++)
        	level.enemies.add(new FighterShip(COLOR.PURPLE, rand.nextInt((int)level.world.getWidth()), rand.nextInt((int)level.world.getHeight())));
       	
       	for (int i=0; i<10; i++)
        	level.enemies.add(new RingShip(COLOR.PURPLE, rand.nextInt((int)level.world.getWidth()), rand.nextInt((int)level.world.getHeight())));
        	
       	for (int i=0; i<5; i++)
        	level.enemies.add(new FighterShip(COLOR.PURPLE, rand.nextInt((int)level.world.getWidth()), rand.nextInt((int)level.world.getHeight())));
        	
        Nebula n = level.newNebula(COLOR.RED);
        level.nebulae.add(n);
        
        n = level.newNebula(COLOR.ORANGE);
        level.nebulae.add(n);
        
        //make sure ship won't automatically die
        while (level.overlapsExisiting(level.ship))
        {
        	level.start_x = rand.nextInt((int)level.world.getWidth());
        	level.start_y = rand.nextInt((int)level.world.getHeight());
       		level.ship.setPos(level.start_x, level.start_y);
       	}
       	
        //random stars
        for (int i=0; i<1000; i++)
        	level.stars.add(new Star(rand.nextInt((int)level.world.getWidth()), rand.nextInt((int)level.world.getHeight())));
		
		return level;
	}
	
	public static Level level8()
	{
		Level level = new Level();
		Random rand = new Random((new Date()).getTime());
		
		ArrayList colors = RandomColors();
		int color1 = ((Integer)colors.get(0)).intValue();
		int color2 = ((Integer)colors.get(1)).intValue();
		int color3 = ((Integer)colors.get(2)).intValue();
		int color4 = ((Integer)colors.get(3)).intValue();
		int color5 = ((Integer)colors.get(4)).intValue();
		
		level.world = new Rectangle(0, 0, 5000, 5000);
       
        level.start_x = (int)(level.world.getWidth()/2);
        level.start_y = (int)(level.world.getHeight()/2);
       	level.ship.setPos(level.start_x, level.start_y);
       	level.ship.setColor(color1);
       	
       	level.addMotherShip();
        
        level.balls.addAll(Ball.BallRing(level.mother_ship.centerX(), level.mother_ship.centerY(), 100, color2));
        level.balls.addAll(Ball.BallRing(level.mother_ship.centerX(), level.mother_ship.centerY(), 125, color3));
        
        level.balls.addAll(Ball.BallRingRing(level.mother_ship.centerX(), level.mother_ship.centerY(), 350, color4, 8));
        
        level.enemies.addAll(RingShip.ringShipRing(color5, level.mother_ship.centerX(), level.mother_ship.centerY(), 350, 8));
        
        for (int i=0; i<10; i++)
        	level.enemies.add(new FighterShip(color5, rand.nextInt((int)level.world.getWidth()), rand.nextInt((int)level.world.getHeight())));
       	
       	for (int i=0; i<10; i++)
        	level.enemies.add(new RingShip(color5, rand.nextInt((int)level.world.getWidth()), rand.nextInt((int)level.world.getHeight())));
        	
       	for (int i=0; i<5; i++)
        	level.enemies.add(new FighterShip(color5, rand.nextInt((int)level.world.getWidth()), rand.nextInt((int)level.world.getHeight())));
        
        Nebula n = level.newNebula(color1);
        level.nebulae.add(n);
        
        n = level.newNebula(color2);
        level.nebulae.add(n);
        level.balls.addAll(Ball.BallRing(n.centerX(), n.centerY(), 150, color1));
        
        n = level.newNebula(color3);
        level.nebulae.add(n);
        level.balls.addAll(Ball.BallRing(n.centerX(), n.centerY(), 150, color2));
        
        n = level.newNebula(color4);
        level.nebulae.add(n);
        level.balls.addAll(Ball.BallRing(n.centerX(), n.centerY(), 150, color3));
        
        //make sure ship won't automatically die
        while (level.overlapsExisiting(level.ship))
        {
        	level.start_x = rand.nextInt((int)level.world.getWidth());
        	level.start_y = rand.nextInt((int)level.world.getHeight());
       		level.ship.setPos(level.start_x, level.start_y);
       	}
       	
        //random stars
        for (int i=0; i<1000; i++)
        	level.stars.add(new Star(rand.nextInt((int)level.world.getWidth()), rand.nextInt((int)level.world.getHeight())));
		
		return level;
	}
	
	public static Level level9()
	{
		Level level = new Level();
		Random rand = new Random((new Date()).getTime());
		
		ArrayList colors = RandomColors();
		int color1 = ((Integer)colors.get(0)).intValue();
		int color2 = ((Integer)colors.get(1)).intValue();
		int color3 = ((Integer)colors.get(2)).intValue();
		int color4 = ((Integer)colors.get(3)).intValue();
		int color5 = ((Integer)colors.get(4)).intValue();
		
		level.world = new Rectangle(0, 0, 5000, 5000);
       
        level.start_x = (int)(level.world.getWidth()/2);
        level.start_y = (int)(level.world.getHeight()/2);
       	level.ship.setPos(level.start_x, level.start_y);
       	level.ship.setColor(color1);
       	
       	level.addMotherShip();
        
        level.balls.addAll(Ball.BallRing(level.mother_ship.centerX(), level.mother_ship.centerY(), 100, color1));
        level.balls.addAll(Ball.BallRing(level.mother_ship.centerX(), level.mother_ship.centerY(), 125, color2));
        
        level.balls.addAll(Ball.BallRingRing(level.mother_ship.centerX(), level.mother_ship.centerY(), 350, color3, 8));
        
        level.balls.addAll(Ball.BallRing(level.mother_ship.centerX(), level.mother_ship.centerY(), 525, color4));
        
        level.enemies.addAll(RingShip.ringShipRing(color5, level.mother_ship.centerX(), level.mother_ship.centerY(), 350, 8));
        
        for (int i=0; i<10; i++)
        	level.enemies.add(new FighterShip(color5, rand.nextInt((int)level.world.getWidth()), rand.nextInt((int)level.world.getHeight())));
       	
       	for (int i=0; i<10; i++)
        	level.enemies.add(new RingShip(color5, rand.nextInt((int)level.world.getWidth()), rand.nextInt((int)level.world.getHeight())));
        	
       	for (int i=0; i<10; i++)
        	level.enemies.add(new FighterShip(color5, rand.nextInt((int)level.world.getWidth()), rand.nextInt((int)level.world.getHeight())));
        
        Nebula n = level.newNebula(color1);
        level.nebulae.add(n);
        level.enemies.addAll(RingShip.ringShipRing(color5, n.centerX(), n.centerY(), 150, 8));
        
        n = level.newNebula(color2);
        level.nebulae.add(n);
        level.balls.addAll(Ball.BallRing(n.centerX(), n.centerY(), 150, color1));
        level.enemies.addAll(RingShip.ringShipRing(color5, n.centerX(), n.centerY(), 225, 8));
        
        n = level.newNebula(color3);
        level.nebulae.add(n);
        level.balls.addAll(Ball.BallRing(n.centerX(), n.centerY(), 150, color2));
        level.enemies.addAll(RingShip.ringShipRing(color5, n.centerX(), n.centerY(), 225, 8));
        
        n = level.newNebula(color4);
        level.nebulae.add(n);
        level.balls.addAll(Ball.BallRing(n.centerX(), n.centerY(), 150, color3));
        level.enemies.addAll(RingShip.ringShipRing(color5, n.centerX(), n.centerY(), 225, 8));
        
        //make sure ship won't automatically die
        while (level.overlapsExisiting(level.ship))
        {
        	level.start_x = rand.nextInt((int)level.world.getWidth());
        	level.start_y = rand.nextInt((int)level.world.getHeight());
       		level.ship.setPos(level.start_x, level.start_y);
       	}
       	
        //random stars
        for (int i=0; i<1000; i++)
        	level.stars.add(new Star(rand.nextInt((int)level.world.getWidth()), rand.nextInt((int)level.world.getHeight())));
		
		return level;
	}
	
	public static Level RandomLevel(int level_number)
	{
		Level level = new Level();
		Random rand = new Random((new Date()).getTime());
		
		if (level_number < 10)
			level.world = new Rectangle(0, 0, 5000, 5000);
		else
			level.world = new Rectangle(0, 0, 7000, 7000);
		
		//move colors in random order
        ArrayList colors = RandomColors();
       
        level.start_x = (int)(level.world.getWidth()/2);
        level.start_y = (int)(level.world.getHeight()/2);
       	level.ship.setPos(level.start_x, level.start_y);
       	level.ship.setColor(((Integer)colors.get(0)).intValue());
       	
       	level.addMotherShip();
        
        for (int i=0; i<(level_number/5)+1; i++)
        	level.balls.addAll(Ball.BallRing(level.mother_ship.centerX(), level.mother_ship.centerY(), 100 + (i * 25), rand.nextInt(5)));
        	
        if (level_number >= 10)
        	level.enemies.addAll(RingShip.ringShipRing(((Integer)colors.get(2)).intValue(), 
        			level.mother_ship.centerX(), level.mother_ship.centerY(), 225 + (((level_number/5)+1) * 25), 8));
        	
        if (level_number >= 15)
        	level.balls.addAll(Ball.BallRingRing(level.mother_ship.centerX(), level.mother_ship.centerY(), 
        			225 + (((level_number/5)+1) * 25), ((Integer)colors.get(3)).intValue(), 8));
        	
        if (level_number >= 20)
        	level.balls.addAll(Ball.BallRing(level.mother_ship.centerX(), level.mother_ship.centerY(), 
        			375 + (((level_number/5)+1) * 25), ((Integer)colors.get(4)).intValue()));
        
        //random nebulae
        for (int i=0; i<=5; i++)
        {
        	Nebula n = level.newNebula(((Integer)colors.get(i)).intValue());
        	level.nebulae.add(n);
        	
        	//add a ring of balls of a previous color
        	//the first nebula dosen't get ringed
        	if (i > 0)
        		level.balls.addAll(Ball.BallRing(n.centerX(), n.centerY(), 150, ((Nebula)level.nebulae.get(i - 1)).getColor()));
        		
        	if (level_number >= 25)
        		level.enemies.addAll(RingShip.ringShipRing(n.getColor(), n.centerX(), n.centerY(), 225, 8));
        }
        
        //random enemies
        if (level_number < 25)
        	for (int i=0; i<level_number; i++)
        		level.enemies.add(new RingShip(COLOR.RANDOM, rand.nextInt((int)level.world.getWidth()), rand.nextInt((int)level.world.getHeight())));
        	
        for (int i=0; i<level_number + 5; i++)
        	level.enemies.add(new FighterShip(COLOR.randomColor(), rand.nextInt((int)level.world.getWidth()), rand.nextInt((int)level.world.getHeight())));
        	
        for (int i=0; i<level_number; i++)
        	level.enemies.add(new BombShip(COLOR.randomColor(), rand.nextInt((int)level.world.getWidth()), rand.nextInt((int)level.world.getHeight())));
        	
        //make sure ship won't automatically die
        while (level.overlapsExisiting(level.ship))
        {
        	level.start_x = rand.nextInt((int)level.world.getWidth());
        	level.start_y = rand.nextInt((int)level.world.getHeight());
       		level.ship.setPos(level.start_x, level.start_y);
       	}
       	
        //random stars
        for (int i=0; i<1000; i++)
        	level.stars.add(new Star(rand.nextInt((int)level.world.getWidth()), rand.nextInt((int)level.world.getHeight())));
		
		return level;
	}
	
	//colors in random order
	public static ArrayList RandomColors()
	{
		Random rand = new Random((new Date()).getTime());
        ArrayList colors = new ArrayList();
        
        for (int i=0; i<=5; i++)
        {
        	if (colors.size() > 0)
        		colors.add(rand.nextInt(colors.size()), new Integer(i));
        	else
        		colors.add(new Integer(i));
        }
        
        return colors;
	}
	
	//adds a nebula the specified color at a random location, not overlapping
	public Nebula newNebula(int color)
	{
		Random rand = new Random((new Date()).getTime());
        Nebula n = new Nebula(color, rand.nextInt((int)world.getWidth()), rand.nextInt((int)world.getHeight()));
       	while (overlapsExisiting(n))
        		n.setPos(rand.nextInt((int)world.getWidth()), rand.nextInt((int)world.getHeight()));
        return n;
	}
	
	//adds mother ship at random, non overlapping location
	public void addMotherShip()
	{
		Random rand = new Random((new Date()).getTime());
       	mother_ship = new MotherShip(rand.nextInt((int)world.getWidth()), rand.nextInt((int)world.getHeight()), ship);
       	while (overlapsExisiting(mother_ship))
        		mother_ship.setPos(rand.nextInt((int)world.getWidth()), rand.nextInt((int)world.getHeight()));
        enemies.add(mother_ship);
	}
	
	public boolean overlapsExisiting(Sprite s)
	{
		boolean overlaps = false;
		
		if (!world.contains(s.getRect()))
			overlaps = true;	
			
		if (s != ship && ship.getRect().intersects(s.getRect()))
			overlaps = true;	
			
		if (s != mother_ship && s.getRect().intersects(mother_ship.getRect()))
			overlaps = true;
			
		for (int i=0; i<nebulae.size(); i++)
		{
			Nebula n = (Nebula)nebulae.get(i);
			
			if (s != n && s.getRect().intersects(n.getRect()))
				overlaps = true;
		}
		
		for (int i=0; i<balls.size(); i++)
		{
			Ball b = (Ball)balls.get(i);
			
			if (s != b && s.getRect().intersects(b.getRect()))
				overlaps = true;
		}
		
		return overlaps;
	}
}
