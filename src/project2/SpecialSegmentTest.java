package project2;

// import static org.junit.Assert.*;
// import org.junit.Before;
// import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// -------------------------------------------------------------------------
/**
 * Test class for the SpecialSegment class. Tests the constructor, ability, and
 * symbol methods.
 * 
 * @author Chris Tricarico, Sophie Linton, Ayo Alade-Ojomo, Rafik Khismatov
 * @version Oct 15, 2026
 */
public class SpecialSegmentTest
{
    private SpecialSegment segment;

    /**
     * Sets up the test fixture before each test.
     */
    @BeforeEach
    public void setUp()
    {
        segment = new SpecialSegment(5, 10, "shield");
    }


    /**
     * Tests the constructor and inherited coordinates.
     */
    @Test
    public void testConstructor()
    {
        assertEquals(5, segment.getRow());
        assertEquals(10, segment.getCol());
        assertEquals("shield", segment.getAbility());
    }


    /**
     * Tests that negative coordinates throw an exception.
     */
    @SuppressWarnings("unused")
    @Test
    public void testInvalidConstructor()
    {
        Exception exception = null;
        try
        {
            new SpecialSegment(-1, 10, "shield");
        }
        catch (IllegalArgumentException e)
        {
            exception = e;
        }
        assertNotNull(exception);
    }


    /**
     * Tests that the correct ability is returned.
     */
    @Test
    public void testGetAbility()
    {
        assertEquals("shield", segment.getAbility());
        SpecialSegment speed = new SpecialSegment(2, 3, "speed");
        assertEquals("speed", speed.getAbility());
    }


    /**
     * Tests null and empty ability strings.
     */
    @Test
    public void testEmptyAbility()
    {
        SpecialSegment empty = new SpecialSegment(1, 2, "");
        SpecialSegment nullAbility = new SpecialSegment(1, 2, null);
        assertEquals("", empty.getAbility());
        assertNull(nullAbility.getAbility());
    }


    /**
     * Tests the symbol for a special segment.
     */
    @Test
    public void testGetSymbol()
    {
        assertEquals('s', segment.getSymbol());
    }


    /**
     * Tests that the symbol is independent of the segment's ability.
     */
    @Test
    public void testGetSymbolWithDifferentAbility()
    {
        SpecialSegment speed = new SpecialSegment(3, 4, "speed");
        assertEquals('s', speed.getSymbol());
    }
}
