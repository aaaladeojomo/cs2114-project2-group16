package project2;

// -------------------------------------------------------------------------
/**
 * A special snake segment that has a unique ability. Shown as 's' on the game
 * board.
 * 
 * @author Chris Tricarico, Sophie Linton, Ayo Alade-Ojomo, Rafik Khismatov
 * @version Oct 15, 2026
 */
public class SpecialSegment
    extends SnakeSegment
{
    private String ability;

    /**
     * Creates a new SpecialSegment.
     * 
     * @param x
     *            X-coordinate
     * @param y
     *            Y-coordinate
     * @param ability
     *            The special ability of the segment
     */
    public SpecialSegment(int x, int y, String ability)
    {
        super(x, y);
        this.ability = ability;
    }


    /**
     * Returns the special ability of the segment.
     * 
     * @return The name of the special ability.
     */
    public String getAbility()
    {
        return ability;
    }


    /**
     * Returns the symbol for this segment.
     * 
     * @return 's' for a special segment.
     */
    @Override
    public char getSymbol()
    {
        return 's';
    }
}
