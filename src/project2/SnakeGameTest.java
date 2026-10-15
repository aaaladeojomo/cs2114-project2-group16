package project2;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

// -------------------------------------------------------------------------
/**
 * Tests for SnakeGame and GameBoard. The snake starts at (5, 5) on a 10x10
 * board, moving right, so the next tile is (6, 5).
 *
 * @author Rafik Khismatov
 * @version Oct 15, 2026
 */
public class SnakeGameTest
{
    private SnakeGame game;

    /**
     * Sets up a new game before each test.
     */
    @Before
    public void setUp()
    {
        game = new SnakeGame(10, 10);
    }


    /**
     * Eating food grows the snake and adds to the score.
     */
    @Test
    public void testEatFood()
    {
        game.getBoard().setTile(6, 5, GameBoard.FOOD);
        game.update();
        assertEquals(1, game.getScore());
        assertEquals(2, game.getSnake().getLength());
    }


    /**
     * Moving onto an empty tile moves the head.
     */
    @Test
    public void testNormalMove()
    {
        game.getBoard().setTile(6, 5, GameBoard.EMPTY);
        game.update();
        assertEquals(6, game.getSnake().getHead().getRow());
        assertEquals(5, game.getSnake().getHead().getCol());
        assertEquals(GameBoard.HEAD, game.getBoard().getTile(6, 5));
        assertEquals(GameBoard.EMPTY, game.getBoard().getTile(5, 5));
    }


    /**
     * Hitting a wall with no shield ends the game.
     */
    @Test
    public void testHitWallNoShield()
    {
        game.getBoard().setTile(6, 5, GameBoard.WALL);
        game.update();
        assertTrue(game.isGameOver());
    }


    /**
     * A shield absorbs one wall hit.
     */
    @Test
    public void testShieldBlocksWall()
    {
        game.getBoard().setTile(6, 5, GameBoard.SHIELD);
        game.update();
        assertTrue(game.hasShield());
        game.getBoard().setTile(7, 5, GameBoard.WALL);
        game.update();
        assertFalse(game.isGameOver());
        assertFalse(game.hasShield());
    }


    /**
     * A spike on a length-1 snake ends the game.
     */
    @Test
    public void testSpikeKillsShortSnake()
    {
        game.getBoard().setTile(6, 5, GameBoard.SPIKE);
        game.update();
        assertTrue(game.isGameOver());
    }


    /**
     * Update does nothing after the game is over.
     */
    @Test
    public void testUpdateAfterGameOver()
    {
        game.getBoard().setTile(6, 5, GameBoard.WALL);
        game.update();
        game.update();
        assertEquals(5, game.getSnake().getHead().getRow());
    }


    /**
     * A 180 degree turn is not allowed, a 90 degree turn is.
     */
    @Test
    public void testIsDirectionValid()
    {
        assertFalse(game.isDirectionValid('a'));
        assertTrue(game.isDirectionValid('w'));
    }


    /**
     * Invalid keys are ignored.
     */
    @Test
    public void testProcessInvalidInput()
    {
        game.processInput('x');
        assertEquals('d', game.getSnake().getDirection());
        game.processInput('w');
        assertEquals('w', game.getSnake().getDirection());
    }


    /**
     * Score starts at 0.
     */
    @Test
    public void testStartingScore()
    {
        assertEquals(0, game.getScore());
        assertFalse(game.isGameOver());
    }


    /**
     * A board with a negative size throws an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNegativeBoard()
    {
        new GameBoard(-1, 5);
    }


    /**
     * isValidCoordinate checks the bounds.
     */
    @Test
    public void testIsValidCoordinate()
    {
        GameBoard b = new GameBoard(20, 10);
        assertTrue(b.isValidCoordinate(3, 3));
        assertFalse(b.isValidCoordinate(999, -999));
        assertEquals(20, b.getWidth());
        assertEquals(10, b.getHeight());
    }


    /**
     * getTile and setTile work in bounds.
     */
    @Test
    public void testGetSetTile()
    {
        GameBoard b = new GameBoard(5, 5);
        assertEquals(GameBoard.EMPTY, b.getTile(2, 2));
        b.setTile(2, 2, 'o');
        assertEquals('o', b.getTile(2, 2));
    }


    /**
     * getTile throws when the position is off the board.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetTileOutOfBounds()
    {
        GameBoard b = new GameBoard(5, 5);
        b.getTile(-1, 50);
    }


    /**
     * spawnItem places an item on an empty board and does nothing on a full
     * one.
     */
    @Test
    public void testSpawnItem()
    {
        GameBoard b = new GameBoard(1, 1);
        b.spawnItem(GameBoard.FOOD);
        assertEquals(GameBoard.FOOD, b.getTile(0, 0));
        assertEquals(0, b.getFoodRow());

        b.spawnItem(GameBoard.WALL); // board is full now
        assertEquals(GameBoard.FOOD, b.getTile(0, 0));
    }
}
