//Imports required awt(design) functions
import java.awt.Graphics;

//this class allows the system to show the alive cells
public class golCell 
{
	//Size of each cell
	public static final int SIZE = 10;	//the bigger the number the bigger the cell and the smaller the grid

	//declare variables
	private int x;
	private int y;

	private boolean nextRound;
	private boolean aliveCells;

	public golCell(int x, int y, boolean alive) 
	{
		this.x = x;
		this.y = y;
		this.aliveCells = alive;
	}

	//sets the game to the next round/new generation
	//this changes the grid layout after everything has been changed and entered into the array
	public void setNextRound(boolean nextRound) 
	{
		this.nextRound = nextRound;
	}

	//as soon as all cells (alivecells) have been checked according to the rules the game will go to the next round
	public void update() 
	{
		aliveCells = nextRound;

	}

	//this allows the game to regenerate after each cell has been checked to its neighbour and added to the array
	public boolean aliveCell() 
	{

		return aliveCells;
	}

	//allows the graphics to appear on the screen, without this nothing will show just a while screen
	//sets how big each alive cell should be and fills them imto the grid creates by area and painted in golsimulation class
	public void draw(Graphics g) 
	{
		if(aliveCells) g.fillRect(x * SIZE, y * SIZE, SIZE, SIZE);
	}

}
