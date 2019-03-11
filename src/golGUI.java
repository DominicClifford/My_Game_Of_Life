//Imports required swing functions
import javax.swing.WindowConstants;

//This Class allows window to be created and set up
public class golGUI 
{	

	public static void main(String[] args) 
	{
		//create and set up window according to class Frame
		golFrame frame = new golFrame();
		//Disable exit button
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		//sets the name of 
		frame.setTitle("Dom's Game of Life");
		//Disable window resizing
		frame.setResizable(false);
		//Allows changes to be made in class Frame (change size of window)
		frame.pack();
		//Sets the size of the window according to class Frame
		//the .getInsets allows the window to loop round 360%, the window effectively has no ends it is a round window
		//this creates the wrap around
		frame.setSize(golFrame.WIDTH + frame.getInsets().left + frame.getInsets().right, golFrame.HEIGHTPANEL + frame.getInsets().bottom + frame.getInsets().top);
		//The window can open anywhere on the screen
		frame.setLocationRelativeTo(null);
		//Enables the frame to be visible
		frame.setVisible(true);
		//Update the window according to function "startUpdate" in class Frame
		//shows that the game has begun in the console window
		System.out.println("The Game Has Begun");
		//display the simulation in the frame (starting from golFrame)
		frame.startUpdate();
	}
}
