package nebuloid;

import java.util.Random;
import java.util.Date;

public class COLOR
{
	public static final int RANDOM = -2;
	public static final int NONE = -1;
	public static final int RED = 0;
	public static final int BLUE= 1;
	public static final int PURPLE = 2;
	public static final int YELLOW = 3;
	public static final int ORANGE = 4;
	public static final int GREEN = 5;
	
	public static int randomColor()
	{
		Random rand = new Random((new Date()).getTime());
		return rand.nextInt(5);
	}
} 

