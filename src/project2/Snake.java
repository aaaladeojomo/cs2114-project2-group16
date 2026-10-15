package project2;

// -------------------------------------------------------------------------
/**
 * Creates a Snake object and the methods that allow it to move around the map,
 * shrink, and grow in size.
 * 
 * @author sjlin
 * @version Oct 15, 2026
 */
public class Snake
{
    private LinkedChain<SnakeSegment> segments;
    private char direction;
    private boolean alive;

    /**
     * Create a new Snake object.
     * 
     * @param xStart
     *            The starting x coordinate of the snake.
     * @param yStart
     *            The starting y coordinate of the snake.
     */
    public Snake(int xStart, int yStart)
    {
        segments = new LinkedChain<>();
        NormalSegment head = new NormalSegment(xStart, yStart, true);
        segments.addFirst(head);
        this.direction = 'd';
        this.alive = true;
    }

    // ~Public Methods ........................................................


    /**
     * Lets the snake move around the map.
     * 
     * @param xNext
     *            Next x-coordinate.
     * @param yNext
     *            Next y-coordinate.
     */
    public void move(int xNext, int yNext)
    {
        SnakeSegment oldHead = getHead();
        if (oldHead instanceof NormalSegment)
        {
            ((NormalSegment)oldHead).setHead(false);
        }
        NormalSegment newHead = new NormalSegment(xNext, yNext, true);
        segments.addFirst(newHead);
        segments.removeLast();
    }


    /**
     * Grows the size of the snake
     * 
     * @param xNext
     *            Next x-coordinate.
     * @param yNext
     *            Next y-coordinate.
     * @param segment
     *            Segment of the snake chain.
     */
    public void grow(int xNext, int yNext, SnakeSegment segment)
    {
        SnakeSegment oldHead = getHead();
        if (oldHead instanceof NormalSegment)
        {
            ((NormalSegment)oldHead).setHead(false);
        }
        segments.addFirst(segment);
    }


    /**
     * Shrinks the size of the snake.
     * 
     * @return If the snake shrank or is null.
     */
    public SnakeSegment shrink()
    {
        if (!segments.isEmpty())
        {
            return segments.removeLast();
        }
        return null;
    }


    /**
     * The head of the snake chain.
     * 
     * @return The head of the snake.
     */
    public SnakeSegment getHead()
    {
        if (segments.isEmpty())
        {
            return null;
        }
        SnakeSegment head = segments.removeFirst();
        segments.addFirst(head);
        return head;
    }


    /**
     * Returns the whole snake chain.
     * 
     * @return Segments of the snake.
     */
    public LinkedChain<SnakeSegment> getSnake()
    {
        return segments;
    }


    /**
     * Length of the snake.
     * 
     * @return Returns the length of the snake.
     */
    public int getLength()
    {
        return segments.size();
    }


    /**
     * If snake segment is at a given coordinate.
     * 
     * @param x
     *            X-coordinate.
     * @param y
     *            Y-Coordinate.
     * @return Returns found if there is a segment at given coordinates.
     */
    public boolean atCoord(int x, int y)
    {
        if (segments.isEmpty())
        {
            return false;
        }
        boolean found = false;
        for (int i = 0; i < segments.size(); i++)
        {
            SnakeSegment segment = segments.removeFirst();
            if (segment.getRow() == x && segment.getColumn() == y)
            {
                found = true;
            }
            segments.addLast(segment);
        }
        return found;
    }


    /**
     * Keeps track of if game is running or snake is "alive".
     * 
     * @return Returns if snake is alive or not.
     */
    public boolean isAlive()
    {
        return alive;
    }


    /**
     * Sets snake to alive or not alive.
     * 
     * @param alive
     *            If snake is alive.
     */
    public void setAlive(boolean alive)
    {
        this.alive = alive;
    }


    /**
     * Gets the direction.
     * 
     * @return Returns the direction set.
     */
    public char getDirection()
    {
        return direction;
    }


    /**
     * Allows to set a possible direction.
     * 
     * @param direction
     *            The given direction.
     */
    public void setDirection(char direction)
    {
        this.direction = direction;
    }

}
