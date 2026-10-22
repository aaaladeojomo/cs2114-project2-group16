package project2;

import java.util.Random;

// -------------------------------------------------------------------------
/**
 * stores the game’s text grid as a 2D char spawn items on random empty tiles,
 * and print the board.
 * 
 * @author Rafik Khismatov, Sophie Linton, Chris Tricarico, Ayo Alade-Ojomo
 * @version Oct 15, 2026
 */
public class GameBoard
{
    /** An empty tile. */
    public static final char EMPTY = ' ';
    /** A food tile. */
    public static final char FOOD = '*';
    /** A wall tile. */
    public static final char WALL = '#';
    /** A spike tile. */
    public static final char SPIKE = '^';
    /** A shield pickup tile. */
    public static final char SHIELD = '+';
    /** The snake's head. */
    public static final char HEAD = 'H';
    /** A normal body segment. */
    public static final char BODY = 'o';
    /** A special body segment. */
    public static final char SPECIAL = 'S';

    private int rows;
    private int columns;
    private char[][] grid;
    private int foodRow;
    private int foodColumn;
    private Random random;

    /**
     * Creates a new empty GameBoard.
     *
     * @param width
     *            Number of columns.
     * @param height
     *            Number of rows.
     */
    public GameBoard(int width, int height)
    {
        this(width, height, new Random());
    }


    /**
     * @param width
     *            Number of columns.
     * @param height
     *            Number of rows.
     * @param random
     *            The Random used to pick spawn locations.
     */
    public GameBoard(int width, int height, Random random)
    {
        if (width <= 0 || height <= 0)
        {
            throw new IllegalArgumentException(
                "Grid width and length must be positive.");
        }
        this.columns = width;
        this.rows = height;
        this.random = random;
        this.foodRow = -1;
        this.foodColumn = -1;
        grid = new char[rows][columns];
        for (int r = 0; r < rows; r++)
        {
            for (int c = 0; c < columns; c++)
            {
                grid[r][c] = EMPTY;
            }
        }
    }


    /**
     * Returns the width of the board.
     *
     * @return Number of columns.
     */
    public int getWidth()
    {
        return columns;
    }


    /**
     * Returns the height of the board.
     *
     * @return Number of rows.
     */
    public int getHeight()
    {
        return rows;
    }


    /**
     * Returns the row of the most recently spawned food.
     *
     * @return Food row, or -1 if no food has been spawned.
     */
    public int getFoodRow()
    {
        return foodRow;
    }


    /**
     * Returns the column of the most recently spawned food.
     *
     * @return Food column, or -1 if no food has been spawned.
     */
    public int getFoodColumn()
    {
        return foodColumn;
    }


    /**
     * Checks whether a coordinate is on the board.
     *
     * @param x
     *            X-coordinate.
     * @param y
     *            Y-coordinate.
     * @return True if the coordinate is inside the board.
     */
    public boolean isValidCoordinate(int x, int y)
    {
        return x >= 0 && x < columns && y >= 0 && y < rows;
    }


    /**
     * Returns the symbol at a coordinate.
     *
     * @param x
     *            X-coordinate.
     * @param y
     *            Y-coordinate.
     * @return The character on that tile.
     */
    public char getTile(int x, int y)
    {
        if (!isValidCoordinate(x, y))
        {
            throw new IndexOutOfBoundsException(
                "(" + x + ", " + y + ") is off the board");
        }
        return grid[y][x];
    }


    /**
     * Sets the symbol at a coordinate.
     *
     * @param x
     *            X-coordinate.
     * @param y
     *            Y-coordinate.
     * @param tile
     *            The character to place.
     */
    public void setTile(int x, int y, char tile)
    {
        if (!isValidCoordinate(x, y))
        {
            throw new IndexOutOfBoundsException(
                "(" + x + ", " + y + ") is off the board");
        }
        grid[y][x] = tile;
    }


    /**
     * Places an item on a random empty tile. Does nothing if the board is full.
     *
     * @param tile
     *            The item symbol to place.
     */
    public void spawnItem(char tile)
    {
        int emptyCount = 0;
        for (int r = 0; r < rows; r++)
        {
            for (int c = 0; c < columns; c++)
            {
                if (grid[r][c] == EMPTY)
                {
                    emptyCount++;
                }
            }
        }
        if (emptyCount == 0)
        {
            return;
        }

        int x;
        int y;
        do
        {
            x = random.nextInt(columns);
            y = random.nextInt(rows);
        }
        while (grid[y][x] != EMPTY);

        grid[y][x] = tile;
        if (tile == FOOD)
        {
            foodColumn = x;
            foodRow = y;
        }
    }


    /**
     * Returns the board as text with a border around it.
     *
     * @return The board as a String.
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("=======================\n");
        sb.append("     DIZZY SNAKES      \n");
        sb.append("=======================\n");
        StringBuilder border = new StringBuilder("+");
        for (int c = 0; c < columns; c++)
        {
            border.append('-');
        }
        border.append("+\n");

        sb.append(border);
        for (int r = 0; r < rows; r++)
        {
            sb.append('|');
            for (int c = 0; c < columns; c++)
            {
                sb.append(grid[r][c]);
            }
            sb.append("|\n");
        }
        sb.append(border);
        return sb.toString();
    }
}
