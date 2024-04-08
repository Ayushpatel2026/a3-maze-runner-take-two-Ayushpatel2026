package ca.mcmaster.se2aa4.mazerunner.MazeSolvingAlgorithms;

import ca.mcmaster.se2aa4.mazerunner.Maze;
import ca.mcmaster.se2aa4.mazerunner.Path;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraSolverTest {
    @Test 
    void testDijkstraSolver(){
        try {
            Maze maze = new Maze("./examples/straight.maz.txt");
            DijkstraSolver solver = new DijkstraSolver();
            Path path = solver.solve(maze);
            assertEquals("FFFF", path.getCanonicalForm());

            
        } catch (Exception e) {
            fail("Exception thrown");
        
        }
    }

}