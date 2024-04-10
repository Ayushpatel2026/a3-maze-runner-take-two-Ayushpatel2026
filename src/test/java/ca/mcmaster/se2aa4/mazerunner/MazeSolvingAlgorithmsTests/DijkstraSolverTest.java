package ca.mcmaster.se2aa4.mazerunner.MazeSolvingAlgorithms;

import ca.mcmaster.se2aa4.mazerunner.Maze;
import ca.mcmaster.se2aa4.mazerunner.Path;
import ca.mcmaster.se2aa4.mazerunner.Position;
import java.util.*;

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

            maze = new Maze("./examples/tiny.maz.txt");
            path = solver.solve(maze);
            assertEquals("FFF L FFFF R FFF", path.getCanonicalForm());
        } catch (Exception e) {
            fail("Exception thrown");
        
        }
    }

    @Test
    void testComputeNodePath(){
        try {
            Maze maze = new Maze("./examples/straight.maz.txt");
            DijkstraSolver solver = new DijkstraSolver();

            Map<Position, Position> previousNodes = new HashMap<>();
            Position start = new Position(0, 0);
            Position end = new Position(3, 0);
            Position node1 = new Position(1, 0);
            Position node2 = new Position(2, 0);
            previousNodes.put(node1, start);
            previousNodes.put(node2, node1);
            previousNodes.put(end, node2);

            List<Position> nodePath = solver.computeNodePath(previousNodes, start, end);
            assertEquals(4, nodePath.size());
            assertEquals(start, nodePath.get(0));
            assertEquals(node1, nodePath.get(1));
            assertEquals(node2, nodePath.get(2));
            assertEquals(end, nodePath.get(3));
        } catch (Exception e) {
            fail("Exception thrown");
        }
    }

    @Test 
    void testComputePathFromNodes(){
        try {
            Maze maze = new Maze("./examples/straight.maz.txt");
            DijkstraSolver solver = new DijkstraSolver();

            List<Position> nodePath = new ArrayList<>();
            Position start = new Position(0, 0);
            Position end = new Position(3, 0);
            Position node1 = new Position(1, 0);
            Position node2 = new Position(2, 0);
            nodePath.add(start);
            nodePath.add(node1);
            nodePath.add(node2);
            nodePath.add(end);
            Path path = solver.computePathFromNodes(nodePath);
            assertEquals("FFF", path.getCanonicalForm());

            nodePath = new ArrayList<>();
            nodePath.add(start);
            nodePath.add(new Position(0, 1));
            assertEquals("R F", solver.computePathFromNodes(nodePath).getCanonicalForm());

            nodePath = new ArrayList<>(Arrays.asList(new Position(1, 1), new Position(0, 1), new Position(0, 0)));
            assertEquals("RR F R F", solver.computePathFromNodes(nodePath).getCanonicalForm());

            nodePath = new ArrayList<>(Arrays.asList(new Position(1, 1), new Position(0, 1), new Position(0, 2), new Position(0, 3), new Position(1, 3), new Position(1,2), new Position(2,2)));
            assertEquals("RR F L FF L F L F R F", solver.computePathFromNodes(nodePath).getCanonicalForm());
            } catch (Exception e) {
                fail("Exception thrown");
            }
        }
    }
