package project2;

// import static org.junit.Assert.*;
// import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
// import org.junit.Test;

// -------------------------------------------------------------------------
/**
 * Where we test the linkedchain class
 * 
 * @author Ayo Alade-Ojomo, Sophie Linton, Chris Tricarico, Rafik Khismatov
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
    @BeforeEach
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
    @Test
    public void testGet()
    {
        assertEquals("bye", linkedchain.get(0));
        assertEquals("hello", linkedchain.get(1));

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
    @Test
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
    @Test
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
    @Test
    public void testSize()
    {
        assertEquals(0, empty.size());
        assertEquals(2, linkedchain.size());

    }


    // ----------------------------------------------------------
    /**
     * test that we can return whether our linked chain is empty or not
     */
    @Test
    public void testIsEmpty()
    {

        assertFalse(linkedchain.isEmpty());
        assertTrue(empty.isEmpty());

    }


    // ----------------------------------------------------------
    /**
     * test that we can return our linked chain as a string
     */
    @Test
    public void testToString()
    {
        assertEquals("[]", empty.toString());

        assertEquals("[bye, hello]", linkedchain.toString());

        empty.add("A");
        empty.add("B");

        assertEquals("[B, A]", empty.toString());
    }


    // ----------------------------------------------------------
    /**
     * test that we can return our linked chain as an array
     */
    @Test
    public void testToArray()
    {
        String[] arr = new String[0];
        assertArrayEquals(arr, empty.toArray(arr));

        String[] arr2 = new String[2];
        linkedchain.toArray(arr2);
        assertArrayEquals(new String[] { "bye", "hello" }, arr2);

        Exception e = null;
        try
        {
            linkedchain.toArray(null);
        }
        catch (IllegalArgumentException ex)
        {
            e = ex;
        }
        assertNotNull(e);

        Exception e2 = null;
        try
        {
            linkedchain.toArray(new String[1]);
        }
        catch (IllegalArgumentException ex2)
        {
            e2 = ex2;
        }
        assertNotNull(e2);

    }

    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................

}
