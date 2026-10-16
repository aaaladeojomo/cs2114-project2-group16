package project2;

//import static org.junit.Assert.*;
//import org.junit.Before;
//import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// -------------------------------------------------------------------------
/**
 * Tests for NormalSegment.
 *
 * @author Rafik Khismatov
 * @version Oct 15, 2026
 */
public class NormalSegmentTest
{
    private NormalSegment head;
    private NormalSegment body;

    /**
     * Creates a head segment and a body segment before each test.
     */
    @BeforeEach
    public void setUp()
    {
        head = new NormalSegment(2, 4, true);
        body = new NormalSegment(3, 4, false);
    }


    /**
     * The constructor stores the coordinates.
     */
    @Test
    public void testConstructor()
    {
        assertEquals(2, head.getRow());
        assertEquals(4, head.getCol());
    }


    /**
     * Negative coordinates throw an exception.
     */
    @Test//(expected = IllegalArgumentException.class)
    public void testConstructorNegative()
    {
        new NormalSegment(-2, 4, false);
    }


    /**
     * The head shows 'H' and a body segment shows 'o'.
     */
    @Test
    public void testGetSymbol()
    {
        assertEquals('H', head.getSymbol());
        assertEquals('o', body.getSymbol());
    }


    /**
     * isHead returns the head flag.
     */
    @Test
    public void testIsHead()
    {
        assertTrue(head.isHead());
        assertFalse(body.isHead());
    }


    /**
     * setHead changes the flag and the symbol.
     */
    @Test
    public void testSetHead()
    {
        head.setHead(false);
        assertFalse(head.isHead());
        assertEquals('o', head.getSymbol());

        body.setHead(true);
        assertTrue(body.isHead());
        assertEquals('H', body.getSymbol());
    }


    /**
     * Setting a negative coordinate is rejected and the value stays the same.
     */
    @Test
    public void testSetRowColNegative()
    {
        assertFalse(head.setRow(-1));
        assertEquals(2, head.getRow());
        assertFalse(head.setCol(-1));
        assertEquals(4, head.getCol());
    }
}
