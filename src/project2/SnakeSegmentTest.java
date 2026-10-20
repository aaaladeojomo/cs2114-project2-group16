package project2;

// import static org.junit.Assert.*;
// import org.junit.Before;
// import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// -------------------------------------------------------------------------
/**
 * Test class for the SnakeSegment class constructor and methods.
 * 
 * @author Sophie Linton, Chris Tricarico, Rafik Khismatov, Ayo Alade-Ojomo
 * @version Oct 20, 2026
 */
public class SnakeSegmentTest
{
    private SnakeSegment segment;

    /**
     * Sets up snakeObject for testing.
     */
    @BeforeEach
    public void setUp()
    {
        segment = new SnakeSegment(1, 1);
    }


    /**
     * Tests the getRow method.
     */
    @Test
    public void testGetRow()
    {
        int row = segment.getRow();
        assertEquals(1, row);
    }


    /**
     * Tests the getCol method.
     */
    @Test
    public void testGetCol()
    {
        int col = segment.getCol();
        assertEquals(1, col);
    }


    /**
     * Tests the setRow method.
     */
    @Test
    public void testSetRow()
    {
        segment.setRow(3);
        assertEquals(3, segment.getRow());
    }


    /**
     * Tests the setCol method.
     */
    @Test
    public void testSetCol()
    {
        segment.setCol(4);
        assertEquals(4, segment.getCol());
    }


    /**
     * Tests the getSymbol method.
     */
    @Test
    public void testGetSymbol()
    {
        char symbol = segment.getSymbol();
        assertEquals(' ', symbol);
    }
}
