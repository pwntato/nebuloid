import nebuloid.Board;

import javax.swing.JFrame;

import java.awt.Toolkit;
//import java.awt.Dimension;

class Nebuloid extends JFrame 
{	
	static final long serialVersionUID = 0;

	public Nebuloid()
	{        
		//setup window
		add(new Board());
		setTitle("Nebuloid - By Jimmy Hendrix");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//full screen on Linux
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		
		//maximize on windows
		setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(true);
	}
	
	public static void main(String args[])
	{
		new Nebuloid();
	}
}
