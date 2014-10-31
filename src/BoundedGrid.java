/**
 * Tetris 
 * Author: Austin Wu
 * Completed: May 23rd, 2014
 * 
 * BoundedGrid Class, used to store a finite number of blocks
 * in rows and columns.
 */

import java.util.ArrayList;

public class BoundedGrid<E> {
	private Object[][] occupantArray; // the array storing the grid elements

	// Constructs an empty BoundedGrid with the given dimensions.
	// (Precondition: rows > 0 and cols > 0.)
	public BoundedGrid(int rows, int cols) {
		occupantArray = new Object[rows][cols];
	}

	// returns the number of rows
	public int getNumRows() {
		return occupantArray.length;
	}

	// returns the number of columns
	public int getNumCols() {
		return occupantArray[0].length;
	}

	// returns the object at location loc (or null if the location is
	// unoccupied)
	// precondition: loc is valid in this grid
	@SuppressWarnings("unchecked")
	public E get(Location loc) {
		return (E) occupantArray[loc.getRow()][loc.getCol()];
	}

	// puts obj at location loc in this grid and returns the object previously
	// at that location (or null if the
	// location is unoccupied)
	public E put(Location loc, E obj) {
		E oldOccupant = get(loc);
		occupantArray[loc.getRow()][loc.getCol()] = obj;
		return oldOccupant;
	}

	// returns true if loc is valid in this grid, false otherwise
	public boolean isValid(Location loc) {
		return (loc.getRow() >= 0 && loc.getRow() < getNumRows()
				&& loc.getCol() >= 0 && loc.getCol() < getNumCols());
	}

	// removes the object at location loc from this grid and returns the object
	// that was removed (or null if the
	// location is unoccupied
	public E remove(Location loc) {
		return put(loc, null);
	}

	// returns a list of all occupied locations in this grid
	public ArrayList<Location> getOccupiedLocations() {
		ArrayList<Location> locs = new ArrayList<Location>();
		for (int r = 0; r < occupantArray.length; r++) {
			for (int c = 0; c < occupantArray[r].length; c++) {
				Location loc = new Location(r, c);
				if (!(get(loc) == null)) {
					locs.add(loc);
				}
			}
		}
		return locs;
	}
}