package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

class MazeTest {

    @Test
    void testFindStart() {
        try {
            Maze maze = new Maze("./examples/tiny.maz.txt");
            assertEquals(new Position(0, 5), maze.getStart());

            maze = new Maze("./examples/test.maz.txt");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("Invalid maze (no start position available)"));
        } 
    }

    @Test 
    void testFindEnd() {
        try {
            Maze maze = new Maze("./examples/tiny.maz.txt");
            assertEquals(new Position(6, 1), maze.getEnd());

            maze = new Maze("./examples/test2.maz.txt");
        } catch (Exception e) {
            assertEquals("Invalid maze (no end position available)", e.getMessage());
        } 
    }

    @Test 
    void testIsWall() {
        try {
            Maze maze = new Maze("./examples/tiny.maz.txt");
            assertTrue(maze.isWall(new Position(0, 0)));
            assertFalse(maze.isWall(new Position(0, 5)));
        } catch (Exception e) {
            fail("Exception thrown");
        } 
    }

    @Test  
    void testGetSize(){
        try {
            Maze maze = new Maze("./examples/tiny.maz.txt");
            assertEquals(7, maze.getSizeX());
            assertEquals(7, maze.getSizeY());
        } catch (Exception e) {
            fail("Exception thrown");
        }
    }

    @Test 
    void testPathValidation(){
        try {
            Maze maze = new Maze("./examples/straight.maz.txt");
            Path path = new Path("FFFF");
            assertTrue(maze.validatePath(path));

            maze = new Maze("./examples/direct.maz.txt");
            path = new Path("F R 2F L 3F R F L F R F L 2F");
            assertTrue(maze.validatePath(path));
            path = new Path("F R 2F L 4F R 2F L 2F");
            assertTrue(maze.validatePath(path));

            //reverse path from right to left
            path = new Path("2F R F L F R F L 3F R 2F L F");
            assertTrue(maze.validatePath(path));

            //incorrect path given
            path = new Path("F 20F F");
            assertFalse(maze.validatePath(path));

        }catch (Exception e) {
            fail("Exception thrown");
        }
    }
}