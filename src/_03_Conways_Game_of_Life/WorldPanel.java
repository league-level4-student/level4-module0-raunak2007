package _03_Conways_Game_of_Life;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class WorldPanel extends JPanel implements MouseListener, ActionListener {
	private static final long serialVersionUID = 1L;
	private int cellsPerRow;
	private int cellSize;
	
	private Timer timer;
	
	//1. Create a 2D array of Cells. Do not initialize it.
	Cell[][] arr;
	
	
	public WorldPanel(int w, int h, int cpr) {
		setPreferredSize(new Dimension(w, h));
		addMouseListener(this);
		timer = new Timer(500, this);
		this.cellsPerRow = cpr;
	
		//2. Calculate the cell size.
		cellSize=(w-(w%cellsPerRow))/cellsPerRow;
				
		//3. Initialize the cell array to the appropriate size.
		arr=new Cell[cellsPerRow][cellsPerRow];
		//3. Iterate through the array and initialize each cell.
		//   Don't forget to consider the cell's dimensions when 
		//   passing in the location.
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr[i].length;j++) {
				arr[i][j]=new Cell(i*cellSize,j*cellSize,cellSize);
			}
		}
		
	}
	
	public void randomizeCells() {
		//4. Iterate through each cell and randomly set each
		//   cell's isAlive memeber to true of false
		for(Cell[] sub:arr) {
			for(Cell cell:sub) {
				int rand=new Random().nextInt(2);
				if(rand==0) {
					cell.isAlive=false;
				}
				else {
					cell.isAlive=true;
				}
			}
		}
		repaint();
	}
	
	public void clearCells() {
		//5. Iterate through the cells and set them all to dead.
		for(Cell[] sub:arr) {
			for(Cell cell:sub) {
				cell.isAlive=false;
			}
		}
		repaint();
	}
	
	public void startAnimation() {
		timer.start();
	}
	
	public void stopAnimation() {
		timer.stop();
	}
	
	public void setAnimationDelay(int sp) {
		timer.setDelay(sp);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		//6. Iterate through the cells and draw them all
		for(Cell[] sub:arr) {
			for(Cell cell:sub) {
				cell.draw(g);
			}
		}
		for(Cell[] sub:arr) {
			for(Cell cell:sub) {
				g.setColor(Color.BLACK);
				g.drawRect(cell.getX(),cell.getY(),cellSize,cellSize);
			}
		}
		
		// draws grid
	}
	
	//advances world one step
	public void step() {
		//7. iterate through cells and fill in the livingNeighbors array
		// . using the getLivingNeighbors method.
		int[][] livingNeighbors = new int[cellsPerRow][cellsPerRow];
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr[i].length;j++) {
				livingNeighbors[i][j]=getLivingNeighbors(arr[i][j].getX(),arr[i][j].getY());
			}
		}
		//8. check if each cell should live or die
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr[i].length;j++) {
				arr[i][j].liveOrDie(livingNeighbors[i][j]);
			}
		}
		
		
		
		repaint();
	}
	
	//9. Complete the method.
	//   It returns an int of 8 or less based on how many
	//   living neighbors there are of the 
	//   cell identified by x and y
	public int getLivingNeighbors(int x, int y){
		int result=0;
		int i=(x-x%cellSize)/cellSize;
		int j=(y-y%cellSize)/cellSize;
		ArrayList <Cell> neighbors=new ArrayList <Cell>();
		for(int m=-1;m<=1;m++) {
			for(int n=-1;n<=1;n++) {
				if((i+m)>=0 && (j+n)>=0 && (i+m)<arr.length && (j+n)<arr[i+m].length) {
					neighbors.add(arr[i+m][j+n]);
				}
			}
		}
		for(Cell sub:neighbors) {
			if(sub.isAlive) {
				result++;
			}
		}
		return result;
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//10. Use e.getX() and e.getY() to determine
		//    which cell is clicked. Then toggle
		//    the isAlive variable for that cell.
		int x=e.getX();
		int y=e.getY();
		int i=(x-x%cellSize)/cellSize;
		int j=(y-y%cellSize)/cellSize;
		Cell c=arr[i][j];
		if(c.isAlive==true) {
			c.isAlive=false;
		}
		else {
			c.isAlive=true;
		}
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		step();		
	}
}
