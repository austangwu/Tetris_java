/**
 * Tetris 
 * Author: Austin Wu
 * Completed: May 23rd, 2014
 * 
 * Block class for individual blocks within the grid.
 */
import java.awt.Color;

public class Block
{
    private BoundedGrid<Block> grid;
    private Location location;
    private Color color;

    //constructs a blue block, because blue is the greatest color ever!
    public Block()
    {
        color = Color.BLUE;
        grid = null;
        location = null;
    }

    //gets the color of this block
    public Color getColor()
    {
        return color;
    }

    //sets the color of this block to newColor.
    public void setColor(Color newColor)
    {
        color = newColor;
    }

    //gets the grid of this block, or null if this block is not contained in a grid
    public BoundedGrid<Block> getGrid()
    {
        return grid;
    }

    //gets the location of this block, or null if this block is not contained in a grid
    public Location getLocation()
    {
        return location;
    }

    //removes this block from its grid
    //precondition:  this block is contained in a grid
    public void removeSelfFromGrid()
    {
        grid.put(location, null);
        grid = null;
        location = null;
    }

    //puts this block into location loc of grid gr
    //if there is another block at loc, it is removed
    //precondition:  (1) this block is not contained in a grid
    //               (2) loc is valid in gr
    public void putSelfInGrid(BoundedGrid<Block> gr, Location loc)
    {
        if (gr.get(loc) != null)
            gr.get(loc).removeSelfFromGrid();
        gr.put(loc, this);
        grid = gr;
        location = loc;
    }

    //moves this block to newLocation
    //if there is another block at newLocation, it is removed
    //precondition:  (1) this block is contained in a grid
    //               (2) newLocation is valid in the grid of this block
    public void moveTo(Location newLocation)
    {
        grid.put(location, null);
        if(grid.get(newLocation) instanceof Block)
        {
            grid.get(newLocation).removeSelfFromGrid();
        }
        location = newLocation;
        grid.put(newLocation, this);
    }

    //returns a string with the location and color of this block
    public String toString()
    {
        return "Block[location=" + location + ",color=" + color + "]";
    }
}