package ca.mcmaster.se2aa4.mazerunner;

public interface MazeVisitor{
    /**
     * Visit the maze.
     * @param maze Maze to visit
     */
    void visitMaze(Maze maze);

    /**
     * Get the result of the visit.
     * @return The result of the visit
     */
    Object getResult();
}