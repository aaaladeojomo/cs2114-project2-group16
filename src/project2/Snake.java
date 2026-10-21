package project2;

// -------------------------------------------------------------------------
/**
 * Creates a Snake object and the methods that allow it to move around the map,
 * shrink, and grow in size.
 * 
 * @author Sophie Linton, Chris Tricarico, Ayo Alade-Ojomo, Rafik Khismatov
 * @version Oct 15, 2026
 */
public class Snake
{
    private LinkedChain<SnakeSegment> segments;
    private String direction;
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
        segments.add(head);
        this.direction = "d";
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
        SnakeSegment newHead = new SnakeSegment(xNext, yNext);
        segments.add(newHead);

        if (!segments.isEmpty())
        {
            SnakeSegment tail = segments.get(segments.size() - 1);
            segments.remove(tail);
        }
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
        segment.setRow(xNext);
        segment.setCol(yNext);
        segments.add(segment);
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
            SnakeSegment tail = segments.get(segments.size() - 1);
            segments.remove(tail);
            return tail;
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
        return segments.get(0);
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
        for (int i = 0; i < segments.size(); i++)
        {
            SnakeSegment segment = segments.get(i);
            if (segment.getRow() == x && segment.getCol() == y)
            {
                return true;
            }
        }
        return false;
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
    public String getDirection()
    {
        return direction;
    }


    /**
     * Allows to set a possible direction.
     * 
     * @param direction
     *            The given direction.
     */
    public void setDirection(String direction)
    {
        this.direction = direction;
    }

}
