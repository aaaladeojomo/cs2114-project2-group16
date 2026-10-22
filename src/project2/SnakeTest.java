package project2;

// import static org.junit.Assert.*;
// import org.junit.Before;
// import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// -------------------------------------------------------------------------
/**
 * Testing the methods of the Snake class.s
 * 
 * @author Sophie Linton, Chris Tricarico, Ayo Alade-Ojomo, Rafik Khismatov
 * @version Oct 15, 2026
 */
public class SnakeTest
{
    private Snake snake;

    /**
     * Sets up snake for testing.
     */
    @BeforeEach
    public void setUp()
    {
        snake = new Snake(3, 3);
    }


    /**
     * Tests Snake constructor.
     */
    @Test
    public void testSnake()
    {
        assertNotNull(snake);
        assertEquals(1, snake.getLength());
        assertTrue(snake.isAlive());
        SnakeSegment head = snake.getHead();
        assertNotNull(head);
        assertEquals(3, head.getRow());
        assertEquals(3, head.getCol());
    }


    /**
     * Tests move method.
     */
    @Test
    public void testMove()
    {
        snake.move(4, 3);
        assertEquals(1, snake.getLength());
        assertEquals(4, snake.getHead().getRow());
        assertEquals(3, snake.getHead().getCol());
    }


    /**
     * Tests move method's bad input.
     */
    @Test
    public void testMoveBadInput()
    {
        Exception exception = null;
        try
        {
            snake.move(-1, 3);
        }
        catch (IllegalArgumentException e)
        {
            exception = e;
        }
        assertNotNull(exception);
    }


    /**
     * Tests the grow method.
     */
    @Test
    public void testGrow()
    {
        SnakeSegment newSegment = new SnakeSegment(4, 3);
        snake.grow(4, 3, newSegment);
        assertEquals(2, snake.getLength());
        assertEquals(4, snake.getHead().getRow());
    }


    /**
     * Tests the grow method's bad input.
     */
    @Test
    public void testGrowMethod()
    {
        Exception exception = null;
        try
        {
            SnakeSegment newSegment = new SnakeSegment(-1, -1);
            snake.grow(-1, -1, newSegment);
        }
        catch (IllegalArgumentException e)
        {
            exception = e;
        }
        assertNotNull(exception);
    }


    /**
     * Tests the shrink method.
     */
    @Test
    public void testShrink()
    {
        SnakeSegment segment = new SnakeSegment(4, 3);
        snake.grow(4, 3, segment);
        assertEquals(2, snake.getLength());
        SnakeSegment removed = snake.shrink();
        assertNotNull(removed);
        assertEquals(3, removed.getRow());
        assertEquals(3, removed.getCol());
        assertEquals(1, snake.getLength());
    }


    /**
     * Tests the shrink method with empty snake.
     */
    @Test
    public void testShrinkEmpty()
    {
        snake.shrink();
        assertEquals(0, snake.getLength());
        SnakeSegment removed = snake.shrink();
        assertNull(removed);
    }


    /**
     * Tests the getHead method.
     */
    @Test
    public void testGetHead()
    {
        SnakeSegment head = snake.getHead();
        assertNotNull(head);
        assertEquals(3, head.getRow());
        assertEquals(3, head.getCol());

        Snake nullSnake = new Snake(4, 5);
        nullSnake.shrink();
        assertNull(nullSnake.getHead());
    }


    /**
     * Tests the getSnake method.
     */
    @Test
    public void testGetSnake()
    {
        assertNotNull(snake.getSnake());
        assertEquals(1, snake.getSnake().size());
    }


    /**
     * Tests the getLength method.
     */
    @Test
    public void getLength()
    {
        assertEquals(1, snake.getLength());
    }


    /**
     * Tests the atCoord method.
     */
    @Test
    public void testAtCoord()
    {
        assertTrue(snake.atCoord(3, 3));
        assertFalse(snake.atCoord(6, 6));
    }


    /**
     * Tests the getAlive and setAlive methods
     */
    @Test
    public void testAlive()
    {
        assertTrue(snake.isAlive());
        snake.setAlive(false);
        assertFalse(snake.isAlive());
        snake.setAlive(true);
        assertTrue(snake.isAlive());
    }


    /**
     * Tests the getDirection and setDirection methods.
     */
    @Test
    public void testDirection()
    {
        assertEquals('d', snake.getDirection());
        snake.setDirection("w");
        assertEquals('w', snake.getDirection());
        snake.setDirection("a");
        assertEquals('a', snake.getDirection());
    }

}
