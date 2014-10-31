/**
 * Tetris 
 * Author: Austin Wu
 * Completed: May 23rd, 2014
 * 
 * Tetrad class, represents individual "blocks" in the tetris game made up of instances of the Block class
 */

import java.awt.*;

public class Tetrad {
	private Block[] blocks;

	public Tetrad(BoundedGrid<Block> grid) {
		
		blocks = new Block[4];
		for (int i = 0; i < 4; i++) {
			blocks[i] = new Block();
		}
		
		Color color = Color.WHITE;
		
		Location[] locs = new Location[4];
		
		int shape = 0;
		
		shape = ((int) (Math.random() * 7));
		switch (shape) {
		case 0: // I shape
			color = Color.RED;
			locs[1] = new Location(0, 4);
			locs[0] = new Location(1, 4);
			locs[2] = new Location(2, 4);
			locs[3] = new Location(3, 4);
			break;

		case 1: // T shape
			color = Color.GRAY;
			locs[0] = new Location(0, 4);
			locs[1] = new Location(1, 4);
			locs[2] = new Location(0, 3);
			locs[3] = new Location(0, 5);
			break;

		case 2: // O Shape
			color = Color.CYAN;
			locs[0] = new Location(0, 4);
			locs[1] = new Location(0, 3);
			locs[2] = new Location(1, 4);
			locs[3] = new Location(1, 3);
			break;

		case 3:// L Shape
			color = Color.YELLOW;
			locs[1] = new Location(0, 3);
			locs[0] = new Location(1, 3);
			locs[2] = new Location(2, 3);
			locs[3] = new Location(2, 4);
			break;

		case 4: // J Shape
			color = Color.MAGENTA;
			locs[1] = new Location(0, 4);
			locs[0] = new Location(1, 4);
			locs[2] = new Location(2, 4);
			locs[3] = new Location(2, 3);
			break;

		case 5: // S Shape
			color = Color.BLUE;
			locs[1] = new Location(1, 3);
			locs[0] = new Location(1, 4);
			locs[2] = new Location(0, 4);
			locs[3] = new Location(0, 5);
			break;

		case 6: // Z shape
			color = Color.GREEN;
			locs[1] = new Location(0, 3);
			locs[0] = new Location(0, 4);
			locs[2] = new Location(1, 4);
			locs[3] = new Location(1, 5);
			break;
		}
		for (int i = 0; i < 4; i++) {
			blocks[i].setColor(color);
		}
		addToLocations(grid, locs);
	}

	private void addToLocations(BoundedGrid<Block> grid, Location[] locs) {
		for (int i = 0; i < 4; i++) {
			blocks[i].putSelfInGrid(grid, locs[i]);
		}
	}

	private Location[] removeBlocks() {
		Location[] locs = new Location[4];
		for (int i = 0; i < locs.length; i++) {
			locs[i] = blocks[i].getLocation();
			blocks[i].removeSelfFromGrid();
		}
		return locs;
	}

	private boolean areEmpty(BoundedGrid<Block> grid, Location[] locs) {
		for (int i = 0; i < locs.length; i++) {
			if (!grid.isValid(locs[i])) {
				return false;
			}
			if (grid.get(locs[i]) instanceof Block) {
				return false;
			}
		}
		return true;
	}

	
	public boolean translate(int deltaRow, int deltaCol) {
		BoundedGrid<Block> grid = blocks[0].getGrid();
		Location[] locs = removeBlocks();
		Location[] newLocs = new Location[4];
		for (int i = 0; i < locs.length; i++) {
			newLocs[i] = new Location(locs[i].getRow() + deltaRow,
					locs[i].getCol() + deltaCol);
		}
		boolean empty = areEmpty(grid, newLocs);
		if (empty) {
			addToLocations(grid, newLocs);
			return true;
		} else {
			addToLocations(grid, locs);
			return false;
		}
	}

	public boolean rotate() {
		BoundedGrid<Block> grid = blocks[0].getGrid();
		Location[] locs = removeBlocks();
		Location[] newLocs = new Location[4];
		for (int i = 0; i < newLocs.length; i++) {
			newLocs[i] = new Location(locs[0].getRow() - locs[0].getCol()
					+ locs[i].getCol(), locs[0].getRow() + locs[0].getCol()
					- locs[i].getRow());
		}

		boolean empty = areEmpty(grid, newLocs);
		if (empty) {
			addToLocations(grid, newLocs);
			return true;
		} else {
			addToLocations(grid, locs);
			return false;
		}
	}
}