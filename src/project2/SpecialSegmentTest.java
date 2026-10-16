package project2;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

// -------------------------------------------------------------------------
/**
<<<<<<< Updated upstream
 * Test class for the SpecialSegment class. Tests the constructor, ability, and
 * symbol methods.
 * 
=======
 * Test class for the SpecialSegment class.
 * Tests the constructor, ability, and symbol methods.
 *
>>>>>>> Stashed changes
 * @author Chris Tricarico
 * @version Oct 15, 2026
 */
public class SpecialSegmentTest
{
    private SpecialSegment segment;

    /**
     * Sets up the test fixture before each test.
     */
    @Before
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
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidConstructor()
    {
        new SpecialSegment(-1, 5, "shield");
    }


    /**
     * Tests that the correct ability is returned.
     */
    @Test
    public void testGetAbility()
    {
        assertEquals("shield", segment.getAbility());
<<<<<<< Updated upstream
        SpecialSegment speed = new SpecialSegment(2, 3, "speed");
=======

        SpecialSegment speed =
            new SpecialSegment(2, 3, "speed");

>>>>>>> Stashed changes
        assertEquals("speed", speed.getAbility());
    }


    /**
     * Tests null and empty ability strings.
     */
    @Test
    public void testEmptyAbility()
    {
<<<<<<< Updated upstream
        SpecialSegment empty = new SpecialSegment(1, 2, "");
        SpecialSegment nullAbility = new SpecialSegment(1, 2, null);
=======
        SpecialSegment empty =
            new SpecialSegment(1, 2, "");

        SpecialSegment nullAbility =
            new SpecialSegment(1, 2, null);

>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
     * Tests that the symbol is independent of the segment's ability.
=======
     * Tests that the symbol is independent
     * of the segment's ability.
>>>>>>> Stashed changes
     */
    @Test
    public void testGetSymbolWithDifferentAbility()
    {
<<<<<<< Updated upstream
        SpecialSegment speed = new SpecialSegment(3, 4, "speed");
=======
        SpecialSegment speed =
            new SpecialSegment(3, 4, "speed");

>>>>>>> Stashed changes
        assertEquals('s', speed.getSymbol());
    }
}
