package project2;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

// -------------------------------------------------------------------------
/**
 * Where we test the linkedchain class
 * 
 * @author modes
 * @version Oct 15, 2026
 */
public class LinkedChainTest
{

    private LinkedChain<String> linkedchain;
    private LinkedChain<String> empty;

    // ----------------------------------------------------------
    /**
     * setting up a linked chain with "hello" and "bye" in it and also an empty
     * linked chain
     */
    @Before
    public void setUp()
    {
        linkedchain = new LinkedChain<String>();
        linkedchain.add("hello");
        linkedchain.add("bye");

        empty = new LinkedChain<String>();

    }


    // ----------------------------------------------------------
    /**
     * Seeing that we can succesfully add a value to the front of our chain
     */
    // ~ Fields ................................................................

    @Test
    public void testAdd()
    {
        empty.add("12345");

        assertTrue(empty.contains("12345"));
    }


    // ----------------------------------------------------------
    /**
     * Seeing that we can sucessfully add a value to the end of our chain
     */
    @Test
    public void testAddToEnd()
    {
        empty.add("Arsenal");
        empty.addToEnd("Chelsea");

        String[] arr = new String[2];
        empty.toArray(arr);

        assertEquals("Chelsea", arr[1]);

    }


    // ----------------------------------------------------------
    /**
     * seeing that we can remove the value at the front of our linked chain
     */
    @Test
    public void testRemove()
    {
        linkedchain.remove();

        String[] arr = new String[1];
        linkedchain.toArray(arr);

        assertArrayEquals(arr, new String[] { "hello" });

        assertNull(empty.remove());

    }


    // ----------------------------------------------------------
    /**
     * seeing that we can remove a specific value from our linked chain
     */
    @Test
    public void testRemoveSpecified()
    {
        linkedchain.remove("hello");

        String[] arr = new String[1];
        linkedchain.toArray(arr);

        assertArrayEquals(arr, new String[] { "bye" });

        assertFalse(empty.remove("hello"));
        assertFalse(linkedchain.remove("NO VALUE OF THIS TYPE"));
    }


    // ----------------------------------------------------------
    /**
     * seeing that we can clear all values from our linked chain
     */
    @Test
    public void testClear()
    {

        linkedchain.clear();

        assertTrue(linkedchain.isEmpty());
    }


    // ----------------------------------------------------------
    /**
     * test that we can get return a value based off its index in our linked
     * chain
     */
    public void testGet()
    {
        assertEquals(linkedchain.get(1), "bye");

        Exception exception = null;
        try
        {
            linkedchain.get(5);
        }
        catch (IndexOutOfBoundsException e)
        {
            exception = e;
        }
        assertNotNull(exception);

    }


    // ----------------------------------------------------------
    /**
     * test if our linkedchain contains a value
     */
    public void testContains()
    {

        assertFalse(linkedchain.contains(null));
        assertFalse(linkedchain.contains("No Entry here"));
        assertTrue(linkedchain.contains("hello"));
    }


    // ----------------------------------------------------------
    /**
     * test that we can count how many times a value appears in our linked chain
     */
    public void testCount()
    {
        assertEquals(0, linkedchain.count(null));
        assertEquals(0, linkedchain.count("No Entry here"));
        assertEquals(1, linkedchain.count("hello"));
        linkedchain.add("hello");
        linkedchain.add("hello");

        assertEquals(3, linkedchain.count("hello"));

    }


    // ----------------------------------------------------------
    /**
     * test how big our linked chain is
     */
    public void testSize()
    {
        assertEquals(0, empty.size());
        assertEquals(2, linkedchain.size());

    }


    // ----------------------------------------------------------
    /**
     * test that we can return whether our linked chain is empty or not
     */
    public void testIsEmpty()
    {

        assertFalse(linkedchain.isEmpty());
        assertTrue(empty.isEmpty());

    }


    // ----------------------------------------------------------
    /**
     * test that we can return our linked chain as a string
     */
    public void testToString()
    {
        assertEquals("[]", empty.toString());

        assertEquals("[bye, hello]", linkedchain.toString());

        empty.add("A");
        empty.add("B");

        assertEquals("[A, B]", empty.toString());
    }


    // ----------------------------------------------------------
    /**
     * test that we can return our linked chain as an array
     */
    public void testToArray()
    {
        String[] arr = new String[0];
        assertArrayEquals(arr, empty.toArray(arr));

        String[] arr2 = new String[2];
        linkedchain.toArray(arr2);
        assertArrayEquals(new String[] { "bye, hello" }, arr2);

    }

    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................

}
