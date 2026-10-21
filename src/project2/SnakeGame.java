package project2;

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


    public boolean isGameOver()
    {
        return !running;
    }


    public int getScore()
    {
        return score;
    }


    public boolean hasShield()
    {
        return hasShield;
    }


    public GameBoard getBoard()
    {
        return board;
    }


    public Snake getSnake()
    {
        return snake;
    }


    /**
     * Checks that a direction is not a 180 degree turn.
     */
    public boolean isDirectionValid(String input)
    {
        String current = snake.getDirection();
        return !((current.equals("w") && input.equals("s"))
            || (current.equals("s") && input.equals("w"))
            || (current.equals("a") && input.equals("d"))
            || (current.equals("d") && input.equals("a")));
    }


    /**
     * Your stashed version: processInput(char key) returning boolean.
     */
    public boolean processInput(char key)
    {
        key = Character.toLowerCase(key);

        if (key != 'w' && key != 'a' && key != 's' && key != 'd')
        {
            return false;
        }

        String dir = String.valueOf(key);

        if (isDirectionValid(dir))
        {
            snake.setDirection(dir);
            return true;
        }

        return false;
    }


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
        }

        collision(nextX, nextY);
    }


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


    private void endGame()
    {
        running = false;
        snake.setAlive(false);
    }


    private boolean isSnakeTile(char tile)
    {
        return tile == GameBoard.BODY || tile == GameBoard.SPECIAL
            || tile == GameBoard.HEAD;
    }


    public void play()
    {
        Scanner in = new Scanner(System.in);

        System.out.println("Welcome to Snake!");
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
                        processInput(line.charAt(0));
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
            }
        }

        System.out.println(board);
        System.out.println("Game over! Final score: " + score);
    }


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
                        "That is not a valid choice, please enter either 'y' or 'n'.");
                }
            }
        }
    }
}
