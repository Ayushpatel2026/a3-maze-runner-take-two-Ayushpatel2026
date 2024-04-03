package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DirectionTest {
    @Test
    void turnRight() {
        assertEquals(Direction.RIGHT, Direction.UP.turnRight());
        assertEquals(Direction.LEFT, Direction.DOWN.turnRight());
        assertEquals(Direction.UP, Direction.LEFT.turnRight());
        assertEquals(Direction.DOWN, Direction.RIGHT.turnRight());
    }

    @Test
    void turnLeft() {
        assertEquals(Direction.LEFT, Direction.UP.turnLeft());
        assertEquals(Direction.RIGHT, Direction.DOWN.turnLeft());
        assertEquals(Direction.DOWN, Direction.LEFT.turnLeft());
        assertEquals(Direction.UP, Direction.RIGHT.turnLeft());
    }
}
