package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

import ca.mcmaster.se2aa4.mazerunner.Position;

public class MazeGraph{
    Maze maze;

    private final int numRows;
    private final int numCols;
    Graph graph = new Graph();

    public MazeGraph(Maze maze) {
        this.maze = maze;
        this.numRows = maze.getSizeY();
        this.numCols = maze.getSizeX();
    }

    public void constructGraph(){
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
