package project2;

// -------------------------------------------------------------------------
/**
 * The snake segment class which manages each segment of the chain and it's
 * place on the map. It sets up a method for the symbols that will be overridden
 * in the two subclasses.
 * 
 * @author Sophie Linton, Chris Tricarico, Ayo Alade-Ojomo, Rafik Khismatov
 * @version Oct 15, 2026
 */
public class SnakeSegment
{
    private int row;
    private int col;

    /**
     * Creates a new SnakeSegment.
     * 
     * @param x
     *            X-coordinate
     * @param y
     *            Y-coordinate
     */
    public SnakeSegment(int x, int y)
    {
        if (x < 0 || y < 0)
        {
            throw new IllegalArgumentException("Coords must be positive.");
        }
        this.row = x;
        this.col = y;
    }


    /**
     * Returns row of the segment.
     * 
     * @return Row of segment.
     */
    public int getRow()
    {
        return row;
    }


    /**
     * Returns column of segment.
     * 
     * @return Column of segment.
     */
    public int getCol()
    {
        return col;
    }


    /**
     * Sets the x-value for the snake segment.
     * 
     * @param x
     *            X-coordinate
     * @return Returns if row was set or not.
     */
    public boolean setRow(int x)
    {
        if (x < 0)
        {
            return false;
        }
        this.row = x;
        return true;
    }


    /**
     * Sets the y-value for the snake segment.
     * 
     * @param y
     *            Y-coordinate
     * @return Returns if column was set or not.
     */
    public boolean setCol(int y)
    {
        if (y < 0)
        {
            return false;
        }
        this.col = y;
        return true;
    }


    /**
     * The symbol of the snake, which is overridden by subclasses.
     * 
     * @return Character of snake segment.
     */
    public char getSymbol()
    {
        return ' ';
    }
}
