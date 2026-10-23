package project2;

// import static org.junit.Assert.*;
import java.util.Random;
// import org.junit.Before;
// import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// -------------------------------------------------------------------------
/**
 * Tests for GameBoard. Each public method has a normal case and a bad-input
 * case.
 *
 * @author Rafik Khismatov, Sophie Linton, Chris Tricarico, Ayo Alade-Ojomo
 * @version Oct 15, 2026
 */
public class GameBoardTest
{
    private GameBoard board;

    /**
     * Sets up a 20 wide by 10 tall board before each test.
     */
    @BeforeEach
    public void setUp()
    {
        board = new GameBoard(20, 10);
    }


    /**
     * A new board is completely empty.
     */
    @Test
    public void testConstructorEmpty()
    {
        for (int y = 0; y < board.getHeight(); y++)
        {
            for (int x = 0; x < board.getWidth(); x++)
            {
                assertEquals(GameBoard.EMPTY, board.getTile(x, y));
            }
        }
    }


    /**
     * A negative width throws an exception.
     */
    @SuppressWarnings("unused")
    @Test // (expected = IllegalArgumentException.class)
    public void testConstructorNegativeWidth()
    {
        Exception exception = null;
        try
        {
            new GameBoard(-1, 5);
        }
        catch (IllegalArgumentException e)
        {
            exception = e;
        }
        assertNotNull(exception);
    }


    /**
     * A zero height throws an exception.
     */
    @SuppressWarnings("unused")
    @Test // (expected = IllegalArgumentException.class)
    public void testConstructorZeroHeight()
    {
        Exception exception = null;
        try
        {
            new GameBoard(5, 0);
        }
        catch (IllegalArgumentException e)
        {
            exception = e;
        }
        assertNotNull(exception);
    }


    /**
     * getWidth and getHeight return the board size.
     */
    @Test
    public void testGetWidthHeight()
    {
        assertEquals(20, board.getWidth());
        assertEquals(10, board.getHeight());
    }


    /**
     * isValidCoordinate is true inside the board, including the edges.
     */
    @Test
    public void testIsValidCoordinateInside()
    {
        assertTrue(board.isValidCoordinate(3, 3));
        assertTrue(board.isValidCoordinate(0, 0));
        assertTrue(board.isValidCoordinate(19, 9));
        assertFalse(board.isValidCoordinate(0, -1));
    }


    /**
     * isValidCoordinate is false outside the board.
     */
    @Test
    public void testIsValidCoordinateOutside()
    {
        assertFalse(board.isValidCoordinate(999, -999));
        assertFalse(board.isValidCoordinate(-1, 0));
        assertFalse(board.isValidCoordinate(20, 0));
        assertFalse(board.isValidCoordinate(0, 10));
    }


    /**
     * setTile changes a tile and getTile reads it back.
     */
    @Test
    public void testGetSetTile()
    {
        assertEquals(GameBoard.EMPTY, board.getTile(5, 5));
        board.setTile(2, 2, GameBoard.BODY);
        assertEquals(GameBoard.BODY, board.getTile(2, 2));
    }


    /**
     * getTile throws when off the board.
     */
    @Test // (expected = IndexOutOfBoundsException.class)
    public void testGetTileOutOfBounds()
    {
        Exception exception = null;
        try
        {
            board.getTile(-1, 50);
        }
        catch (IndexOutOfBoundsException e)
        {
            exception = e;
        }
        assertNotNull(exception);
    }


    /**
     * setTile throws when off the board.
     */
    @Test // (expected = IndexOutOfBoundsException.class)
    public void testSetTileOutOfBounds()
    {
        Exception exception = null;
        try
        {
            board.setTile(25, -3, GameBoard.BODY);
        }
        catch (IndexOutOfBoundsException e)
        {
            exception = e;
        }
        assertNotNull(exception);
    }


    /**
     * spawnItem places exactly one item and records the food position.
     */
    @Test
    public void testSpawnFood()
    {
        GameBoard seeded = new GameBoard(5, 5, new Random(42));
        assertEquals(-1, seeded.getFoodRow());
        assertEquals(-1, seeded.getFoodColumn());

        seeded.spawnItem(GameBoard.FOOD);
        int x = seeded.getFoodColumn();
        int y = seeded.getFoodRow();
        assertEquals(GameBoard.FOOD, seeded.getTile(x, y));

        int count = 0;
        for (int r = 0; r < 5; r++)
        {
            for (int c = 0; c < 5; c++)
            {
                if (seeded.getTile(c, r) == GameBoard.FOOD)
                {
                    count++;
                }
            }
        }
        assertEquals(1, count);
    }


    /**
     * spawnItem only uses empty tiles.
     */
    @Test
    public void testSpawnItemSkipsOccupied()
    {
        GameBoard small = new GameBoard(2, 1);
        small.setTile(0, 0, GameBoard.WALL);
        small.spawnItem(GameBoard.FOOD);
        assertEquals(GameBoard.WALL, small.getTile(0, 0));
        assertEquals(GameBoard.FOOD, small.getTile(1, 0));
    }


    /**
     * spawnItem does nothing on a full board instead of looping forever.
     */
    @Test
    public void testSpawnItemFullBoard()
    {
        GameBoard tiny = new GameBoard(1, 1);
        tiny.setTile(0, 0, GameBoard.WALL);
        tiny.spawnItem(GameBoard.FOOD);
        assertEquals(GameBoard.WALL, tiny.getTile(0, 0));
        assertEquals(-1, tiny.getFoodRow());
    }


    /**
     * toString prints the board with a border.
     */
    @Test
    public void testToString()
    {
        GameBoard b = new GameBoard(2, 1);
        b.setTile(0, 0, GameBoard.FOOD);
        assertEquals(
            "=======================\n" + "     DIZZY SNAKES      \n"
                + "=======================\n" + "+--+\n" + "|* |\n" + "+--+\n",
            b.toString());
    }
    
    
    // ----------------------------------------------------------
    /**
     * test that this will return an empty tile
     */
    @Test
    public void testGetRandomEmptyTile()
    {
        GameBoard gb = new GameBoard(5, 5, new Random(42));
        
       gb.setTile(0, 0, GameBoard.WALL);
       
       int[] position = gb.getRandomEmptyTile();
       
       assertTrue(gb.isValidCoordinate(position[0], position[1]));
       assertEquals(GameBoard.EMPTY, gb.getTile(position[0], position[1]));
    
    }
    
    // ----------------------------------------------------------
    /**
     * Test that this will return the only empty tile on the board
     */
    @Test
    public void testGetRandomEmptyTile2()
    {
        GameBoard gb = new GameBoard(3, 3, new Random(1));
        
        for (int i = 0; i < 3; i++ )
        {
            for (int j = 0; j < 3; j++)
            {
                gb.setTile(j, i, GameBoard.WALL);
            }
        }
        
        gb.setTile(1, 1, GameBoard.EMPTY);
        
        int[] position = gb.getRandomEmptyTile();
        
        assertEquals(1, position[0]);
        assertEquals(1, position[1]);
    }
    
    
    // ----------------------------------------------------------
    /**
     * test we can count the right amount of tiles
     */
    @Test
    public void testCountItems()
    {
        GameBoard gb = new GameBoard(4, 4);
        
        gb.setTile(0, 0, GameBoard.FOOD);
        gb.setTile(1, 1, GameBoard.FOOD);
        gb.setTile(2,2, GameBoard.WALL);
        
        assertEquals(2, gb.countItems(GameBoard.FOOD));
        assertEquals(1, gb.countItems(GameBoard.WALL));
        assertEquals(0, gb.countItems(GameBoard.SPIKE));
    }
    
    // ----------------------------------------------------------
    /**
     * tests we can move an item
     */
    @Test
    public void testMoveItem()
    {
        GameBoard gb = new GameBoard(5, 5, new Random(42));
        
        gb.setTile(2, 2, GameBoard.SPIKE);
        
        gb.moveItem(GameBoard.SPIKE); 
        
        assertEquals(GameBoard.EMPTY, gb.getTile(2, 2));
        
        assertEquals(1, gb.countItems(GameBoard.SPIKE));
        
    }
    
    // ----------------------------------------------------------
    /**
     * test that the item will move to the onyl empty spot on the map
     */
    @Test
    public void testMoveItem2()
    {
       GameBoard gb = new GameBoard(3, 3, new Random(7));
       for (int i = 0; i < 3; i++ )
       {
           for (int j = 0; j < 3; j++)
           {
               gb.setTile(j, i, GameBoard.WALL);
           }
       }
       
       gb.setTile(1, 1, GameBoard.EMPTY);
       
       gb.moveItem(GameBoard.SHIELD);
       
       assertEquals(GameBoard.SHIELD, gb.getTile(1, 1));
       
       
    }
    
}
