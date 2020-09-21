package _04_Maze_Maker;
import java.awt.Color;
import java.awt.Graphics;

public class Maze {
	//1. Create a 2D array of cells. Don't initialize it.

	Cell [][] arr;
	private int width;
	private int height;

	public Maze(int w, int h) {
		this.width = w;
		this.height = h;

		//2. Initialize the cells using the width and height varibles
		arr=new Cell[width][height];
		//3. Iterated through each cell and initialize it
		//   using i and j as the location
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr[i].length;j++) {
				arr[i][j]=new Cell(i,j);
			}
		}
	}
	public void draw(Graphics g) {
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr[i].length;j++) {
				arr[i][j].draw(g);
			}
		}
	}
	
	//4b. This method returns the selected cell.
	public Cell getCell(int x, int y){
		return arr[x][y];
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
