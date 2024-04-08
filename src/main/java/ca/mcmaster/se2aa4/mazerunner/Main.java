package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.MazeSolvingAlgorithms.*;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");
        CommandLineParser parser = new DefaultParser();

        CommandLine cmd = null;
        try {
            cmd = parser.parse(getParserOptions(), args);
            String filePath = cmd.getOptionValue('i');

            double startTime = System.currentTimeMillis();
            Maze maze = new Maze(filePath);
            double endTime = System.currentTimeMillis();
            double timeToLoadMaze = endTime - startTime;

            if (cmd.getOptionValue("p") != null) {
                logger.info("Validating path");
                Path path = new Path(cmd.getOptionValue("p"));
                if (maze.validatePath(path)) {
                    System.out.println("correct path");
                } else {
                    System.out.println("incorrect path");
                }
            } else if (cmd.getOptionValue("baseline") != null) {
                System.out.printf("Maze loaded in %.2f miliseconds\n", timeToLoadMaze);

                String method = cmd.getOptionValue("method", "righthand");
                double startTimeMethod = System.currentTimeMillis();
                Path pathFromMethod = solveMaze(method, maze);
                double endTimeMethod = System.currentTimeMillis();
                double timeForMethod = endTimeMethod - startTimeMethod;
                System.out.println("Number of steps for given method: "+pathFromMethod.getNumSteps());
                System.out.println("Path calculated from given method: "+pathFromMethod.getFactorizedForm());
                System.out.printf("Time to compute path using given method: %.2f miliseconds\n", timeForMethod);

                String baselineMethod = cmd.getOptionValue("baseline");

                double startTimeBaseline = System.currentTimeMillis();
                Path baselinePath = solveMaze(baselineMethod, maze);
                double endTimeBaseline = System.currentTimeMillis();
                double timeForBaseline = endTimeBaseline - startTimeBaseline;

                System.out.println("Number of steps for baseline method: "+baselinePath.getNumSteps());
                System.out.println("Path calculated from baseline method: "+baselinePath.getFactorizedForm());
                System.out.printf("Time to compute path using baseline method: %.2f miliseconds\n", timeForBaseline);
                System.out.printf("Speedup: %.2f\n", (double) baselinePath.getNumSteps() / pathFromMethod.getNumSteps());
            }
            else{
                String method = cmd.getOptionValue("method", "righthand");
                Path path = solveMaze(method, maze);
                System.out.println(path.getFactorizedForm());
            }
        } catch (Exception e) {
            System.err.println("MazeSolver failed.  Reason: " + e.getMessage());
            logger.error("MazeSolver failed.  Reason: " + e.getMessage());
            logger.error("PATH NOT COMPUTED");
        }

        logger.info("End of MazeRunner");
    }

    /**
     * Solve provided maze with specified method.
     *
     * @param method Method to solve maze with
     * @param maze Maze to solve
     * @return Maze solution path
     * @throws Exception If provided method does not exist
     */
    private static Path solveMaze(String method, Maze maze) throws Exception {
        MazeSolver solver = null;
        switch (method) {
            case "righthand" -> {
                logger.debug("RightHand algorithm chosen.");
                solver = new RightHandSolver();
            }
            case "tremaux" -> {
                logger.debug("Tremaux algorithm chosen.");
                solver = new TremauxSolver();
            }
            case "dijkstra" -> {
                logger.debug("Dijkstra algorithm chosen.");
                solver = new DijkstraSolver();
            }
            default -> {
                throw new Exception("Maze solving method '" + method + "' not supported.");
            }
        }

        logger.info("Computing path");
        return solver.solve(maze);
    }

    /**
     * Get options for CLI parser.
     *
     * @return CLI parser options
     */
    private static Options getParserOptions() {
        Options options = new Options();

        Option fileOption = new Option("i", true, "File that contains maze");
        fileOption.setRequired(true);
        options.addOption(fileOption);

        options.addOption(new Option("p", true, "Path to be verified in maze"));
        options.addOption(new Option("method", true, "Specify which path computation algorithm will be used"));
        options.addOption(new Option("baseline", true, "Specify the baseline path (to determine optimization factor)"));

        return options;
    }
}
