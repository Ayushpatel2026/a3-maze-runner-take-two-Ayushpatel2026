package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PositionTest {
    @Test  
    void add() {
        Position p1 = new Position(1, 2);
        Position p2 = new Position(3, 4);

        assertEquals(new Position(4, 6), p1.add(p2));
        assertEquals(p1, p1.add(new Position(0, 0)));
        assertEquals(new Position(3, 3), p2.add(new Position(0, -1)));
        assertEquals(new Position(0, 1), p1.add(new Position(-1, -1)));
    }

    @Test
    void move() {
        Position p = new Position(1, 2);

        assertEquals(new Position(1, 1), p.move(Direction.UP));
        assertEquals(new Position(1, 3), p.move(Direction.DOWN));
        assertEquals(new Position(0, 2), p.move(Direction.LEFT));
        assertEquals(new Position(2, 2), p.move(Direction.RIGHT));
    }

    @Test
    void toStringTest() {
        Position p = new Position(1, 2);

        assertEquals("Position(1,2)", p.toString());
    }
}
