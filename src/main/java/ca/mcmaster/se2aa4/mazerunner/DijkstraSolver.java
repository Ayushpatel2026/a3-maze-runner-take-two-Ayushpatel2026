package ca.mcmaster.se2aa4.mazerunner;

import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.Position;
import ca.mcmaster.se2aa4.mazerunner.Direction;

public class DijkstraSolver implements MazeSolver{

    private static final Logger logger = LogManager.getLogger();

    private Maze maze;
    private MazeGraph graph;
    Map <Position, Integer> distances = new HashMap<>();
    PriorityQueue<Position> queue = new PriorityQueue<>(new Comparator<Position>(){
        @Override
        public int compare(Position a, Position b){
            return distances.get(a) - distances.get(b);
        }
    });
    Map<Position, Position> previousNodes = new HashMap<>();
    List<Position> nodePath = new ArrayList<>();
    Path path = new Path();

    public DijkstraSolver(){

    }

    @Override
    public Path solve(Maze maze){
        this.maze = maze;
        this.graph = new MazeGraph(maze);
        graph.constructGraph();
        dijkstra(graph, maze.getStart());
        getNodePath(previousNodes, maze.getStart(), maze.getEnd());
        getPathFromNodes(nodePath);
        return this.path;
    }

    public void dijkstra(MazeGraph mazeGraph, Position startNode){

        //set distances to infinity except the start node will be 0
        for(Position node : mazeGraph.graph.getNodes()){
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(startNode, 0);

        queue.add(startNode);

        while(!queue.isEmpty()){
            Position currentNode = queue.poll();
            int currentDistance = distances.get(currentNode);
            for(Position neighbor : mazeGraph.graph.getAdjNodes(currentNode)){
                int newDistance = currentDistance + mazeGraph.graph.getEdgeWeight(currentNode, neighbor);
                if(newDistance < distances.get(neighbor)){
                    distances.put(neighbor, newDistance);
                    previousNodes.put(neighbor, currentNode);
                    queue.add(neighbor);
                }
            }
        }
    }

    public void getNodePath(Map<Position, Position> previousNodes, Position startNode, Position endNode){
        Position current = endNode;
        while(!current.equals(startNode)){
            nodePath.add(current);
            // logger.info("Current Node: " + current.toString());
            current = previousNodes.get(current);
        }

        nodePath.add(startNode);
        Collections.reverse(nodePath);
    }

    public void getPathFromNodes(List<Position> nodePath){
        Direction dir = Direction.RIGHT;
        for(int i = 0; i < nodePath.size() - 1; i++){
            Position current = nodePath.get(i);
            Position next = nodePath.get(i + 1);
            // logger.info("Current Position: " + current.toString());
            // logger.info("Next Position: " + next.toString());
            // logger.info("Current Direction: " + dir.toString());
            switch (dir){
                case UP -> {
                    if(current.x() < next.x()){
                        path.addStep('R');
                        path.addStep('F');
                        dir = Direction.RIGHT;
                    }else if(current.x() > next.x()){
                        path.addStep('L');
                        path.addStep('F');
                        dir = Direction.LEFT;
                    }
                    else if (current.y() < next.y()){
                        path.addStep('L');
                        path.addStep('L');
                        path.addStep('F');
                        dir = Direction.DOWN;
                    }
                    else if (current.y() > next.y()){
                        path.addStep('F');
                    }
                    break;
                }
                case DOWN -> {
                    if(current.x() < next.x()){
                        path.addStep('L');
                        path.addStep('F');
                        dir = Direction.RIGHT;
                    }else if(current.x() > next.x()){
                        path.addStep('R');
                        path.addStep('F');
                        dir = Direction.LEFT;
                    }else if(current.y() < next.y()){
                        path.addStep('F');
                    }else if(current.y() > next.y()){
                        path.addStep('L');
                        path.addStep('L');
                        path.addStep('F');
                        dir = Direction.UP;
                    }
                    break;
                }
                case LEFT -> {
                    if(current.y() < next.y()){
                        path.addStep('L');
                        path.addStep('F');
                        dir = Direction.DOWN;
                    }else if(current.y() > next.y()){
                        path.addStep('R');
                        path.addStep('F');
                        dir = Direction.UP;
                    }else if (current.x() < next.x()){
                        path.addStep('R');
                        path.addStep('R');
                        path.addStep('F');
                        dir = Direction.RIGHT;
                    }else if (current.x() > next.x()){
                        path.addStep('F');
                    }
                    break;
                }
                case RIGHT -> {
                    if(current.y() < next.y()){
                        path.addStep('R');
                        path.addStep('F');
                        dir = Direction.DOWN;
                    }else if(current.y() > next.y()){
                        path.addStep('L');
                        path.addStep('F');
                        dir = Direction.UP;
                    }
                    else if (current.x() < next.x()){
                        path.addStep('F');
                    }else if (current.x() > next.x()){
                        path.addStep('R');
                        path.addStep('R');
                        path.addStep('F');
                        dir = Direction.LEFT;
                    }
                    break;
                }
            }
        }
    }

}