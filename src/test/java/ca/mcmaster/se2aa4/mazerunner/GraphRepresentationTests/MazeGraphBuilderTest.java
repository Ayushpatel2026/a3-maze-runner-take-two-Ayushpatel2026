package ca.mcmaster.se2aa4.mazerunner.GraphRepresentation;

import ca.mcmaster.se2aa4.mazerunner.Position;
import ca.mcmaster.se2aa4.mazerunner.Maze;
import ca.mcmaster.se2aa4.mazerunner.MazeVisitor;

import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MazeGraphBuilderTest {
    
    @Test 
    void testConstructGraph(){

        // building the maze first
        try {
            Maze maze = new Maze("./examples/straight.maz.txt");
            MazeGraphBuilder builder = new MazeGraphBuilder();
            builder.constructGraph(maze);
            Graph graph = (Graph) builder.getResult();
            assertEquals(5, graph.getNodes().size());

            maze = new Maze("./examples/tiny.maz.txt");
            builder = new MazeGraphBuilder();
            builder.constructGraph(maze);
            graph = (Graph) builder.getResult();
            assertEquals(19, graph.getNodes().size());
            for (Position node : graph.getNodes()) {
                // the graph should not contain any walls
                assertFalse(maze.isWall(node));
            }

            for (Position node : graph.getNodes()) {
                List<Position> adjNodes = graph.getAdjNodes(node);
                for (Position adjNode : adjNodes) {
                    assertFalse(maze.isWall(adjNode));
                    assertTrue(adjNode.x() == node.x() + 1 || adjNode.x() == node.x() - 1 || adjNode.y() == node.y() + 1 || adjNode.y() == node.y() - 1);
                }
            }

        }
        catch (Exception e) {
            System.out.println("Error reading maze file");
        }
    }
    
}
