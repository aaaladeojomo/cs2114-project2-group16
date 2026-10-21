package project2;

import java.util.Scanner;

// -------------------------------------------------------------------------
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
     * @param width
     *            Width of the board.
     * @param height
     *            Height of the board.
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
     * Returns whether the game is over.
     *
     * @return True if the game has ended.
     */
    public boolean isGameOver()
    {
        return !running;
    }


    /**
     * Returns the player's score.
     *
     * @return The score.
     */
    public int getScore()
    {
        return score;
    }


    /**
     * Returns whether the snake has a shield.
     *
     * @return True if the snake has a shield.
     */
    public boolean hasShield()
    {
        return hasShield;
    }


    /**
     * Returns the game board.
     *
     * @return The board.
     */
    public GameBoard getBoard()
    {
        return board;
    }


    /**
     * Returns the snake.
     *
     * @return The snake.
     */
    public Snake getSnake()
    {
        return snake;
    }


    /**
     * Checks that a direction is not a 180 degree turn.
     *
     * @param input
     *            The requested direction ('w', 'a', 's', or 'd').
     * @return True if the snake may turn that way.
     */
    public boolean isDirectionValid(String input)
    {
        String current = snake.getDirection();
        return !((current == "w" && input == "s")
            || (current == "s" && input == "w")
            || (current == "a" && input == "d")
            || (current == "d" && input == "a"));
    }


    /**
     * Changes the snake's direction if the key is valid. Invalid keys and 180
     * degree turns are ignored.
     *
     * @param key
     *            The key the user typed.
     * @return If the snake moved or not
     */
<<<<<<< Updated upstream
    public boolean processInput(char key)
=======
    public void processInput(String key)
>>>>>>> Stashed changes
    {
        // key = Character.toLowerCase(key);
        String str = key.toLowerCase();
        if (!str.equals("w") && !str.equals("a") && !str.equals("s")
            && !str.equals("d"))
        {
<<<<<<< Updated upstream
            return false;
=======
            System.out.println("This is not a valid input!");
            return;

>>>>>>> Stashed changes
        }
        if (isDirectionValid(str))
        {
<<<<<<< Updated upstream
            snake.setDirection(key);
            return true;
=======
            snake.setDirection(str);
>>>>>>> Stashed changes
        }
        return false;
    }


    /**
     
     */
    public void update()
    {
        if (!running)
        {
            return;
        }
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
            default:
                break;
        }
        collision(nextX, nextY);
    }


    /**
     * @param xNext
     *            Next x-coordinate of the head.
     * @param yNext
     *            Next y-coordinate of the head.
     */
    public void collision(int xNext, int yNext)
    {
        if (!board.isValidCoordinate(xNext, yNext))
        {
            useShieldOrDie();
            return;
        }

        char tile = board.getTile(xNext, yNext);

        if (tile == GameBoard.WALL || isSnakeTile(tile))
        {
            useShieldOrDie();
            return;
        }

        if (tile == GameBoard.FOOD)
        {
            score++;
            advance(xNext, yNext, new NormalSegment(xNext, yNext, false), true);
            board.spawnItem(GameBoard.FOOD);
        }
        else if (tile == GameBoard.SHIELD)
        {
            hasShield = true;
            advance(xNext, yNext, new NormalSegment(xNext, yNext, false), true);
        }
        else if (tile == GameBoard.SPIKE)
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
     * Moves the head to (x, y). Removes the tail unless the snake is growing.
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


    /**
     * Removes the last segment and clears its tile.
     */
    private void removeTail()
    {
        SnakeSegment tail = snake.shrink();
        if (tail != null)
        {
            board.setTile(tail.getRow(), tail.getCol(), GameBoard.EMPTY);
        }
    }


    /**
     * Uses the shield if there is one, otherwise ends the game.
     */
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
     * Ends the game.
     */
    private void endGame()
    {
        running = false;
        snake.setAlive(false);
    }


    /**
     * Checks whether a tile is part of the snake.
     */
    private boolean isSnakeTile(char tile)
    {
        return tile == GameBoard.BODY || tile == GameBoard.SPECIAL
            || tile == GameBoard.HEAD;
    }


    /**
     * Runs the game loop in the console.
     */
    public void play()
    {

        Scanner in = new Scanner(System.in);
<<<<<<< Updated upstream
        while (true)
        {
            resetGame();
            while (running)
            {
                System.out.println(board);
                System.out.println(
                    "Score: " + score + (hasShield ? "   [SHIELD]" : ""));
                System.out.print("Move (w/a/s/d, q to quit): ");

                if (!in.hasNextLine())
                {
                    return;
                }
                String line = in.nextLine().trim();

                if (line.equalsIgnoreCase("q"))
                {
                    return;
                }

                if (!line.isEmpty())
                {
                    if (processInput(line.charAt(0)))
                    {
                        update();
                    }
                }
            }
            System.out.println(board);
            System.out.println("Game over! Final score: " + score);
            while (true)
            {
                System.out.print("Play again? (y/n): ");
                if (!in.hasNextLine())
                {
                    return;
                }

                String response = in.nextLine().trim();

                if (response.equalsIgnoreCase("y"))
                {
                    break;
                }
                else if (response.equalsIgnoreCase("n"))
                {
                    System.out.println("Thanks for playing Dizzy Snakes!");
                    return;
                }
=======

        System.out.println("Welcome to Snake!");
        System.out.println("press Enter to Start!");
        in.nextLine();
        while (running)
        {
            System.out.println(board);
            System.out
                .println("Score: " + score + (hasShield ? "   [SHIELD]" : ""));
            System.out.print("Move (w/a/s/d, q to quit): ");

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
                        processInput(line);
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
                Thread.sleep(500);
            }
            catch (InterruptedException e)
            {
                // ignore
>>>>>>> Stashed changes
            }
        }
    }


    /**
     * Starts the game.
     *
     * @param args
     *            Not used.
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

                System.out.println("Play again (y/n): ");
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
                else
                {
                    System.out.println(
                        "That is not a valid choice, please enter either 'y' or 'n' ");
                }
            }
        }
        // new SnakeGame(20, 10).play();
    }


    /**
     * Resets the game after round ends.
     */
    private void resetGame()
    {
        int width = board.getWidth();
        int height = board.getHeight();
        this.board = new GameBoard(width, height);
        int startX = width / 2;
        int startY = height / 2;
        this.snake = new Snake(startX, startY);
        this.board.setTile(startX, startY, GameBoard.HEAD);
        this.score = 0;
        this.hasShield = false;
        this.running = true;
        this.board.spawnItem(GameBoard.FOOD);
        this.board.spawnItem(GameBoard.SPIKE);
        this.board.spawnItem(GameBoard.WALL);
        this.board.spawnItem(GameBoard.SHIELD);
    }
}
