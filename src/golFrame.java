//Imports required awt(design) functions
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Imports required swing functions
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;

//this class sets up the content of the panel in the window
@SuppressWarnings("serial")
public class golFrame extends JFrame implements ActionListener
{
	//sets size of Window/Jframe this can only be changed here and no where else
	public static final int WIDTH = 500;
	public static final int PANELSIZE = 40;
	public static final int HEIGHT = 500;
	public static final int HEIGHTPANEL = HEIGHT + PANELSIZE;

	private JButton start, pause, reset, exit;
	private JPanel menu;
	private JLabel commandLabel;

	private final static int REFRESH_RATE = 50;	//how quickly the window will refresh the smaller the number the more moves you will see (as the window refreshes slower)
	private final static int MILLIS = 1000/REFRESH_RATE;	//how quickly the window will change after each cycle, smaller number the quick the game will be completed

	private Screen screen;
	private golSimulation simulation;

	//this ensures that the simulation size is not bigger than the window size and that is is placed inside reletive to the centre (doesn't create cells not shown in window)
	public golFrame() 
	{

		setLayout(new BorderLayout());

		//create new panel
		menu = new JPanel();
		menu.setSize(WIDTH, PANELSIZE); //make the panel the same width as the frame and a height panelsize
		menu.setBackground(Color.darkGray);
		menu.setVisible(true);
		add(menu);

		//run simulation
		simulation = new golSimulation();

		screen = new Screen();
		add(screen, BorderLayout.CENTER);

		//create new button to start the game
		start = new JButton("Start");
		start.setActionCommand("Start");
		menu.add(start); //and to JPannel menu

		//create new button to pause the game
		pause = new JButton("Pause");
		pause.setActionCommand("Pause");
		menu.add(pause); //and to JPannel menu

		//create new button to reset the game
		reset = new JButton("Reset");
		reset.setActionCommand("Reset");
		menu.add(reset); //and to JPannel menu

		//create new button to exit the game
		exit = new JButton("Exit");
		exit.setActionCommand("Exit");
		menu.add(exit);

		//create JLabel to show user what the game is doing
		commandLabel = new JLabel("Press Start to play");
		commandLabel.setBorder(BorderFactory.createLineBorder(Color.white));
		commandLabel.setForeground(Color.white);

		//allow the program to listern for the buttons being pushed, make the buttons active
		start.addActionListener(this);
		pause.addActionListener(this);
		reset.addActionListener(this);
		exit.addActionListener(this);		
		menu.add(commandLabel);

	}

	//actions performed by buttons
	public void actionPerformed(ActionEvent e) 
	{
		//Check for the 'Start' event
		if (e.getActionCommand().equals("Start")) 
		{
			//if this event occurs do this
			start.setEnabled(false);
			pause.setEnabled(true);
			reset.setEnabled(false);
			exit.setEnabled(true);
			System.out.println("Start was Pressed");		

			//change text in JLable commandLabel
			commandLabel.setText("Game Started");			
		}
		//Check for "Pause" event
		if(e.getActionCommand().equals("Pause")) 
		{
			//if this event occurs do this
			start.setEnabled(true);
			pause.setEnabled(false);
			reset.setEnabled(true);
			exit.setEnabled(true);
			System.out.println("Pause was Pressed");

			//change text in JLable commandLabel
			commandLabel.setText("Game Paused");
		}
		//Check for "Pause" event
		if(e.getActionCommand().equals("Reset")) 
		{
			//if this event occurs do this
			start.setEnabled(true);
			pause.setEnabled(false);
			reset.setEnabled(false);
			exit.setEnabled(true);
			System.out.println("Reset was Pressed");

			//change text in JLable commandLabel
			commandLabel.setText("Game Reset");
		}
		//Check for "Pause" event
		if (e.getActionCommand().equals("Exit"))
		{
			//if this event occurs do this
			System.out.println("The Game Has stoped ");
			System.out.println("There where "+ golSimulation.numberOfRounds + " generations in this game");
			System.exit(0);
		}
	}

	//This function allows the game to set and refresh after every move
	//Updates the array and round
	public void startUpdate() 
	{
		while(true) 
		{
			long before = System.currentTimeMillis();
			simulation.update();

			screen.repaint();
			long diff = MILLIS - (System.currentTimeMillis() - before);

			//this ensures that the game does not start of static and that some cells have to many live cells next to them
			try 
			{
				if(diff > 0) Thread.sleep(diff);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}

	public class Screen extends JLabel 
	{
		protected void paintComponent(Graphics g) 
		{
			g.setColor(Color.BLACK);	//colour of background/dead cells
			g.fillRect(0, 0, getWidth(), getHeight());	//Sets the area in which to fill the colour (in this case the whole Jpanel)
			//getWidth & getHeight will fill the entire height and width of the Jpanel
			simulation.draw(g);

			//Set the colour of the grid layout
			g.setColor(Color.darkGray);
			for (int x = 0; x < getWidth(); x++)
			{
				g.drawLine((int)(x * golCell.SIZE), 0, (int)(x * golCell.SIZE), this.getHeight());
			}
			for (int x = 0; x < getHeight(); x++)
			{
				g.drawLine(0, (int)(x * golCell.SIZE), this.getWidth(), (int)(x * golCell.SIZE));
			}
		}
	}
}
