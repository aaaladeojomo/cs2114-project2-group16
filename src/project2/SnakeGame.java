package project2;

import java.util.Random;
import java.util.Scanner;

/**
 * in SnakeSegment, getRow() holds the x-coordinate and getCol() holds the
 * y-coordinate.
 *
 * @author Rafik Khismatov, Sophie Linton, Chris Tricarico, Ayo Alade-Ojomo
 * @version Oct 15, 2026
 */
public class SnakeGame
{
    private Snake snake;
    private GameBoard board;
    private boolean running;
    private int score;
    private boolean hasShield;

    /**
     * SnakeGame constructor.
     * 
     * @param width
     *            Width of the game board
     * @param height
     *            Height of the game board
     */
    public SnakeGame(int width, int height)
    {
        board = new GameBoard(width, height);
        int startX = width / 2;
        int startY = height / 2;
        snake = new Snake(startX, startY);
        board.setTile(startX, startY, GameBoard.HEAD);

        running = true;
        score = 0;
        hasShield = false;

        board.spawnItem(GameBoard.FOOD);
        board.spawnItem(GameBoard.SPIKE);
        board.spawnItem(GameBoard.WALL);
        board.spawnItem(GameBoard.SHIELD);
    }


    /**
     * Checks if the game is running
     * 
     * @return if game is running
     */
    public boolean isGameOver()
    {
        return !running;
    }


    /**
     * Gets the current score.
     * 
     * @return The number of the score
     */
    public int getScore()
    {
        return score;
    }


    /**
     * Checks if user collected shield
     * 
     * @return If the shield is possessed
     */
    public boolean hasShield()
    {
        return hasShield;
    }


    /**
     * Gets the current board
     * 
     * @return Returns the game board
     */
    public GameBoard getBoard()
    {
        return board;
    }


    /**
     * Gets the whole snake.
     * 
     * @return The snake chain
     */
    public Snake getSnake()
    {
        return snake;
    }


    /**
     * Checks that a direction is not a 180 degree turn.
     * 
     * @return If the direction is valid or not.
     * @param c
     *            character input
     */
    public boolean isDirectionValid(String c)
    {
        String current = snake.getDirection();

        if (snake.getLength() > 1)
        {
            return !((current.equals("w") && c.equals("s"))
                || (current.equals("s") && c.equals("w"))
                || (current.equals("a") && c.equals("d"))
                || (current.equals("d") && c.equals("a")));
        }
        return true;

    }


    /**
     * Your stashed version: processInput(char key) returning boolean.
     * 
     * @param key
     *            Key inputed by user.
     * @return If the key input is valid or not.
     */
    public boolean processInput(String key)
    {
        if (key == null)
        {
            return false;
        }

        String lowerKey = key.toLowerCase();

        if (!lowerKey.equals("w") && !lowerKey.equals("a")
            && !lowerKey.equals("s") && !lowerKey.equals("d"))
        {
            return false;
        }
        if (isDirectionValid(lowerKey))
        {
            snake.setDirection(lowerKey);
            return true;
        }
        return false;
    }


    /**
     * Updates the snakes direction.
     */
    @SuppressWarnings("incomplete-switch")
    public void update()
    {
        if (!running)
        {
            return;
        }

        double chance = Math.random();
        Random r = new Random();

        SnakeSegment head = snake.getHead();
        int nextX = head.getRow();
        int nextY = head.getCol();

        switch (snake.getDirection())
        {
            case "w":
                nextY--;
                break;
            case "s":
                nextY++;
                break;
            case "a":
                nextX--;
                break;
            case "d":
                nextX++;
                break;
        }

        collision(nextX, nextY);

        // 10% chance for a spike item to spawn in up to 2 spikes can be on the
        // map at once
        if (board.countItems(GameBoard.SPIKE) < 2 && r.nextDouble() < 0.10)
        {
            board.spawnItem(GameBoard.SPIKE);
        }

        // 7% chance for a wall item to spawn in, up to 2 walls can be on the
        // map at once
        if (board.countItems(GameBoard.WALL) < 2 && r.nextDouble() < 0.07)
        {
            board.spawnItem(GameBoard.WALL);
        }

        if (chance < 0.10)
            board.moveItem(GameBoard.SPIKE);
        if (chance < 0.10)
            board.moveItem(GameBoard.WALL);

        // 4% chance for a shield item to spawn in
        if (!hasShield && board.countItems(GameBoard.SHIELD) == 0)
        {
            if (Math.random() < 0.04)
            {
                board.spawnItem(GameBoard.SHIELD);
            }
        }

    }


    /**
     * Handles when the snake collides with objects or walls.
     * 
     * @param xNext
     *            Next X Value
     * @param yNext
     *            Next Y value
     */
    public void collision(int xNext, int yNext)
    {
        if (!board.isValidCoordinate(xNext, yNext))
        {
            useShieldOrDie();
            return;
        }

        char tile = board.getTile(xNext, yNext);

        if (tile == GameBoard.WALL)
        {
            if (hasShield())
            {

                hasShield = false;
                board.setTile(xNext, yNext, GameBoard.EMPTY);
                advance(
                    xNext,
                    yNext,
                    new NormalSegment(xNext, yNext, false),
                    false);
            }
            else
            {
                endGame();
            }
            return;
        }

        if (tile == GameBoard.BODY || tile == GameBoard.SPECIAL
            || tile == GameBoard.HEAD)
        {
            useShieldOrDie();
            return;

        }

        if (tile == GameBoard.FOOD)
        {
            score++;
            advance(xNext, yNext, new NormalSegment(xNext, yNext, false), true);
            board.spawnItem(GameBoard.FOOD);
            return;
        }
        if (tile == GameBoard.SHIELD)
        {
            hasShield = true;
            advance(xNext, yNext, new NormalSegment(xNext, yNext, false), true);
            return;
        }
        if (tile == GameBoard.SPIKE)
        {
            advance(
                xNext,
                yNext,
                new NormalSegment(xNext, yNext, false),
                false);

            if (hasShield)
            {
                hasShield = false;
            }
            else
            {
                removeTail();
                if (snake.getLength() == 0)
                {
                    endGame();
                }
            }

            board.spawnItem(GameBoard.SPIKE);
        }
        else
        {
            advance(
                xNext,
                yNext,
                new NormalSegment(xNext, yNext, false),
                false);
        }
    }


    /**
     * Moves the snake forward
     * 
     * @param x
     *            X-coordinate
     * @param y
     *            Y-coordinate
     * @param newHead
     *            Where the new head should be
     * @param growing
     *            If the snake should grow or not
     */
    private void advance(int x, int y, SnakeSegment newHead, boolean growing)
    {
        SnakeSegment oldHead = snake.getHead();
        char symbol = oldHead.getSymbol();
        if (symbol == GameBoard.HEAD)
        {
            symbol = GameBoard.BODY;
        }

        board.setTile(oldHead.getRow(), oldHead.getCol(), symbol);

        snake.grow(x, y, newHead);
        board.setTile(x, y, GameBoard.HEAD);

        if (!growing)
        {
            removeTail();
        }
    }


    private void removeTail()
    {
        SnakeSegment tail = snake.shrink();
        if (tail != null)
        {
            board.setTile(tail.getRow(), tail.getCol(), GameBoard.EMPTY);
        }
    }


    private void useShieldOrDie()
    {
        if (hasShield)
        {
            hasShield = false;
        }
        else
        {
            endGame();
        }
    }


    /**
     * Ends the game
     */
    private void endGame()
    {
        running = false;
        snake.setAlive(false);
    }


    /**
     * If the tile contains the snake.
     * 
     * @param tile
     *            The tile being checked
     * @return Returns if the tile contains the snake
     */
    private boolean isSnakeTile(char tile)
    {
        return tile == GameBoard.BODY || tile == GameBoard.SPECIAL
            || tile == GameBoard.HEAD;
    }


    /**
     * Starts the play of the game.
     */
    public void play()
    {
        Scanner in = new Scanner(System.in);

        System.out.println("Welcome to Dizzy Snakes!");
        System.out.println("Game Instructions:");
        System.out.println(
            "Use your Snake to collect as much food and grow as long as you can! Make sure to avoid spikes and walls.");
        System.out.println("* - food");
        System.out.println("+ - shield");
        System.out.println("^ - spikes");
        System.out.println("# - wall");
        System.out.println("Press ENTER to start...");

        in.nextLine();

        while (running)
        {
            System.out.println(board);
            System.out
                .println("Score: " + score + (hasShield ? "   [SHIELD]" : ""));

            try
            {
                if (System.in.available() > 0)
                {
                    String line = in.nextLine().trim();
                    if (line.equalsIgnoreCase("q"))
                    {
                        break;
                    }
                    if (!line.isEmpty())
                    {
                        processInput(String.valueOf(line.charAt(0)));
                    }
                }
            }
            catch (Exception e)
            {
                // ignore
            }

            update();

            try
            {
                Thread.sleep(650);
            }
            catch (InterruptedException e)
            {
                // ignore
            }
        }

        System.out.println(board);
        System.out.println("Game over! Final score: " + score);
    }


    /**
     * Runs the main game
     * 
     * @param args
     *            Inputed starting game.
     */
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        while (true)
        {
            SnakeGame game = new SnakeGame(20, 10);
            game.play();
            String answer = "";
            while (true)
            {
                System.out.print("Play again (y/n): ");
                answer = in.nextLine().trim();

                if (answer.equalsIgnoreCase("y"))
                {
                    break;
                }
                else if (answer.equalsIgnoreCase("n"))
                {
                    System.out.println("Goodbye!");
                    return;
                }
                else if (answer.length() == 1
                    && "wasd".contains(answer.toLowerCase()))
                {
                    continue;
                }
                else
                {
                    System.out.println(
                        "That is not a valid choice, please enter either 'y' or 'n'.");
                }
            }
        }
    }
}
