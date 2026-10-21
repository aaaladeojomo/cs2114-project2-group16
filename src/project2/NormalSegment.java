package project2;

// -------------------------------------------------------------------------
/**
 * A normal snake segment. Shown as 'H' if it is the head, 'o' if it is part of
 * the body.
 *
 * @author Rafik Khismatov, Chris Tricarico, Sophie Linton, Ayo Alade-Ojomo
 * @version Oct 15, 2026
 */
public class NormalSegment
    extends SnakeSegment
{
    private boolean isHead;

    /**
     * Creates a new NormalSegment.
     *
     * @param x
     *            X-coordinate
     * @param y
     *            Y-coordinate
     * @param isHead
     *            True if this segment is the head of the snake
     */
    public NormalSegment(int x, int y, boolean isHead)
    {
        super(x, y);
        this.isHead = isHead;
    }


    /**
     * Returns whether this segment is the head.
     *
     * @return True if this is the head.
     */
    public boolean isHead()
    {
        return isHead;
    }


    /**
     * Sets whether this segment is the head.
     *
     * @param isHead
     *            True to make this the head.
     */
    public void setHead(boolean isHead)
    {
        this.isHead = isHead;
    }


    /**
     * Returns the symbol for this segment.
     *
     * @return 'H' for the head, 'o' for the body.
     */
    @Override
    public char getSymbol()
    {
        if (isHead)
        {
            return 'H';
        }
        return 'o';
    }
}
