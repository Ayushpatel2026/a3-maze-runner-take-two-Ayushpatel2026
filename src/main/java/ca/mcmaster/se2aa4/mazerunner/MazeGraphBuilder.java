package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazeGraphBuilder implements MazeVisitor {
    private static final Logger logger = LogManager.getLogger();

    private MazeGraph mazeGraph;

    public MazeGraphBuilder(MazeGraph mazeGraph) {
        this.mazeGraph = mazeGraph;
    }

    @Override
    public void visitMaze(Maze maze) {
        mazeGraph.constructGraph(maze);
    }

    @Override
    public Object getResult() {
        return mazeGraph;
    }


}