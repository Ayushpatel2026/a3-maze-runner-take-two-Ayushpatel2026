package ca.mcmaster.se2aa4.mazerunner.GraphRepresentation;

import ca.mcmaster.se2aa4.mazerunner.Maze;
import ca.mcmaster.se2aa4.mazerunner.Position;
import ca.mcmaster.se2aa4.mazerunner.MazeVisitor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazeGraphBuilder implements MazeVisitor {
    private static final Logger logger = LogManager.getLogger();

    private Graph graph = new Graph();

    public MazeGraphBuilder() {

    }

    /**
     * Visit the maze and construct a graph representation of it.
     * @param maze
     */
    @Override
    public void visitMaze(Maze maze) {
        this.constructGraph(maze);
    }

    /**
     * Get the constructed graph.
     * @return the constructed graph
     */
    @Override
    public Object getResult() {
        return graph;
    }

    /**
     * Construct a graph structure from the maze
     * @param maze
     */
    public void constructGraph(Maze maze){
        int numRows = maze.getSizeY();
        int numCols = maze.getSizeX();
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                if (maze.isWall(new Position(col, row)) == false) { 
                    Position nodeName = new Position(col, row);
                    graph.addNode(nodeName);
                }
            }
        }

        // add edges
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                Position node = new Position(col, row);
                if (maze.isWall(node) == false) {
                    // Check adjacent positions
                    if (row > 0 && maze.isWall(new Position(col, row - 1)) == false) { // Up
                        Position adjNode = new Position(col, row - 1);
                        graph.addEdge(node, adjNode, 1);
                    }
                    if (row < numRows - 1 && maze.isWall(new Position(col, row + 1)) == false) { // Down
                        Position adjNode = new Position(col, row + 1);
                        graph.addEdge(node, adjNode, 1);
                    }
                    if (col > 0 && maze.isWall(new Position(col - 1, row)) == false) { // Left
                        Position adjNode = new Position(col - 1, row);
                        graph.addEdge(node, adjNode, 1);
                    }
                    if (col < numCols - 1 && maze.isWall(new Position(col + 1, row)) == false) { // Right
                        Position adjNode = new Position(col + 1, row);
                        graph.addEdge(node, adjNode, 1); 
                    }
                }
            }
        }
    }


}