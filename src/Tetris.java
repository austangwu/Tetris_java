/**
 * Tetris  
 * Author: Austin Wu 
 * Completed: May 23rd, 2014
 * 
 * Tetris class, runs the actual game
 */

public class Tetris implements ArrowListener {
	public static void main(String[] args) {
		Tetris tetris = new Tetris();
		tetris.play();
	}

	private BoundedGrid<Block> grid;
	private BlockDisplay display;
	private Tetrad activeTetrad;

	public Tetris() {
		grid = new BoundedGrid<Block>(20, 10);
		display = new BlockDisplay(grid);

		display.setTitle("Tetris");
		activeTetrad = new Tetrad(grid);
		display.setArrowListener(this);
	}

	public void upPressed() {
		activeTetrad.rotate();
		display.showBlocks();
	}

	public void downPressed() {
		activeTetrad.translate(1, 0);
		display.showBlocks();
	}

	public void leftPressed() {
		activeTetrad.translate(0, -1);
		display.showBlocks();
	}

	public void rightPressed() {
		activeTetrad.translate(0, 1);
		display.showBlocks();
	}

	public void spacePressed() {
		while (activeTetrad.translate(1, 0))
			;
		display.showBlocks();
	}

	public void play() {
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}

			boolean danny = !activeTetrad.translate(1, 0);

			if (danny) {
				display.showBlocks();
				if (!topRowsEmpty()) {
					System.out.println("Game Over");
					return;
				}
				clearCompletedRows();
				activeTetrad = new Tetrad(grid);
			}
			display.showBlocks();
		}
	}

	private boolean isCompletedRow(int row) {
		boolean completed = true;
		for (int i = 0; i < grid.getNumCols(); i++) {
			if (!(grid.get(new Location(row, i)) instanceof Block)) {
				return false;
			}
		}
		return true;
	}

	private void clearRow(int row) {
		for (int i = 0; i < grid.getNumCols(); i++) {
			((Block) grid.get(new Location(row, i))).removeSelfFromGrid();
		}
		for (int r = row - 1; r > -1; r--) {
			for (int c = 0; c < grid.getNumCols(); c++) {
				if (grid.get(new Location(r, c)) instanceof Block) {
					((Block) grid.get(new Location(r, c))).putSelfInGrid(grid,
							new Location(r + 1, c));
					grid.put(new Location(r, c), null);
				}
			}
		}
	}

	// postcondition: All completed rows have been cleared.
	private void clearCompletedRows() {
		for (int i = grid.getNumRows() - 1; i >= 0; i--) {
			if (isCompletedRow(i)) {
				clearRow(i);
				i++;
			}
		}
	}

	// returns true if top two rows of the grid are empty (no blocks), false
	// otherwise
	private boolean topRowsEmpty() {
		for (int i = 1; i >= 0; i--) {
			for (int k = 0; k < grid.getNumCols(); k++)
				if (grid.get(new Location(i, k)) instanceof Block) {
					return false;
				}
		}
		return true;
	}

}