package project2;

// import static org.junit.Assert.*;
// import org.junit.Before;
// import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SnakeSegmentTest {
    private SnakeSegment segment;

    @BeforeEach
    public void setUp() {
        segment = new SnakeSegment(1, 1);
    }

    @Test
    public void testGetRow() {
        int row = segment.getRow();
        assertEquals(1, row);
    }


    @Test
    public void testGetCol() {
        int col = segment.getCol();
        assertEquals(1, col);
    }


    @Test
    public void testSetRow() {
        segment.setRow(3);
        assertEquals(3, segment.getRow());
    }


    @Test
    public void testSetCol() {
        segment.setCol(4);
        assertEquals(4, segment.getCol());
    }


    @Test
    public void testGetSymbol() {
        char symbol = segment.getSymbol();
        assertEquals(' ', symbol);
    }
}
