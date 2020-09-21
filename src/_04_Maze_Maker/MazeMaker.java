package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class MazeMaker{
	
	private static int width;
	private static int height;
	
	private static Maze maze;
	
	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();
	
	
	public static Maze generateMaze(int w, int h){
		width = w;
		height = h;
		maze = new Maze(width, height);
		
		//4. select a random cell to start
		int x=new Random().nextInt(width);
		int y=new Random().nextInt(height);
		Cell currentCell=maze.getCell(x, y);
		//5. call selectNextPath method with the randomly selected cell
		selectNextPath(currentCell);
		
		return maze;
	}

	//6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		//A. mark cell as visited
		currentCell.setBeenVisited(true);
		//B. Get an ArrayList of unvisited neighbors using the current cell and the method below
		ArrayList <Cell> unvisitedNeighbors=getUnvisitedNeighbors(currentCell);
		//C. if has unvisited neighbors,
		if(unvisitedNeighbors.isEmpty()==false) {
			int rand=new Random().nextInt(unvisitedNeighbors.size());
			Cell newCell=unvisitedNeighbors.get(rand);
			uncheckedCells.push(newCell);
			removeWalls(currentCell,newCell);
			currentCell=newCell;
			currentCell.setBeenVisited(true);
			selectNextPath(currentCell);
			
		}
			//C1. select one at random.
			
			//C2. push it to the stack
		
			//C3. remove the wall between the two cells

			//C4. make the new cell the current cell and mark it as visited
		
			//C5. call the selectNextPath method with the current cell
			
			
		//D. if all neighbors are visited
		else if(uncheckedCells.isEmpty()==false) {
				Cell thisCell=uncheckedCells.pop();
				currentCell=thisCell;
				selectNextPath(currentCell);
			}
			//D1. if the stack is not empty
			
				// D1a. pop a cell from the stack
				// D1b. make that the current cell
		
				// D1c. call the selectNextPath method with the current cell
				
			
		
	}

	//7. Complete the remove walls method.
	//   This method will check if c1 and c2 are adjacent.
	//   If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		boolean adjacent=false;
		int xDiff=c1.getX()-c2.getX();
		int yDiff=c1.getY()-c2.getY();
		if((xDiff==0)||(yDiff==0)) {
			adjacent=true;
		}
		
		if(adjacent==true) 
			if(yDiff==-1) {
				c1.setSouthWall(false);
				c2.setNorthWall(false);
			}
			else if(yDiff==1) {
				c1.setNorthWall(false);
				c2.setSouthWall(false);
			}
			else if(xDiff==-1) {
				c1.setEastWall(false);
				c2.setWestWall(false);
			}
			else if(xDiff==1) {
				c1.setWestWall(false);
				c2.setEastWall(false);
			}
			
		}
	
	
	//8. Complete the getUnvisitedNeighbors method
	//   Any unvisited neighbor of the passed in cell gets added
	//   to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList <Cell> neighbors=new ArrayList <Cell>();
		int x=c.getX();
		int y=c.getY();
		if(x>0&&!maze.getCell(x - 1, y).hasBeenVisited()) {
			neighbors.add(maze.getCell(x-1,y));
		}
		if(x<maze.getWidth()-1&&!maze.getCell(x + 1, y).hasBeenVisited()) {
			neighbors.add(maze.getCell(x+1,y));
		}
		if(y>0&&!maze.getCell(x , y-1).hasBeenVisited()) {
			neighbors.add(maze.getCell(x,y-1));
		}
		if(y<maze.getHeight()-1&&!maze.getCell(x, y+1).hasBeenVisited()) {
			neighbors.add(maze.getCell(x,y+1));
		}
		return neighbors;
	}
}
