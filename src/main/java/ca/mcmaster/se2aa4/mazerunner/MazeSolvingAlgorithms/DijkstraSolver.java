package ca.mcmaster.se2aa4.mazerunner.MazeSolvingAlgorithms;

import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.GraphRepresentation.*;
import ca.mcmaster.se2aa4.mazerunner.*;

public class DijkstraSolver implements MazeSolver{

    private static final Logger logger = LogManager.getLogger();

    private Maze maze;
    private Graph graph;

    private Map <Position, Integer> distances = new HashMap<>();
    private PriorityQueue<Position> queue = new PriorityQueue<>(new Comparator<Position>(){
        /**
         * Compare the distances of two nodes
         * @param a Position one
         * @param b Position two
         * @return int representing the comparison
         */
        @Override
        public int compare(Position a, Position b){
            return distances.get(a) - distances.get(b);
        }
    });

    private Map<Position, Position> previousNodes = new HashMap<>();
    private List<Position> nodePath = new ArrayList<>();
    private Path path = new Path();

    @Override
    public Path solve(Maze maze){
        this.maze = maze;
        MazeVisitor visitor = new MazeGraphBuilder();
        maze.accept(visitor);
        this.graph = (Graph) visitor.getResult();
        dijkstra(graph, maze.getStart());
        computeNodePath(previousNodes, maze.getStart(), maze.getEnd());
        computePathFromNodes(nodePath);
        return this.path;
    }

    /**
     * Dijkstra's algorithm to find the shortest path in a graph
     * @param graph
     * @param startNode
     */
    public void dijkstra(Graph graph, Position startNode){

        //set distances to infinity except the start node will be 0
        for(Position node : graph.getNodes()){
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(startNode, 0);

        queue.add(startNode);

        while(!queue.isEmpty()){
            Position currentNode = queue.poll();
            int currentDistance = distances.get(currentNode);
            for(Position neighbor : graph.getAdjNodes(currentNode)){
                int newDistance = currentDistance + graph.getEdgeWeight(currentNode, neighbor);
                if(newDistance < distances.get(neighbor)){
                    distances.put(neighbor, newDistance);
                    previousNodes.put(neighbor, currentNode);
                    queue.add(neighbor);
                }
            }
        }
    }

    /**
     * Compute the shortest path of nodes from the previous nodes
     * @param previousNodes
     * @param startNode
     * @param endNode
     */
    public void computeNodePath(Map<Position, Position> previousNodes, Position startNode, Position endNode){
        Position current = endNode;
        while(!current.equals(startNode)){
            nodePath.add(current);
            current = previousNodes.get(current);
        }

        nodePath.add(startNode);
        Collections.reverse(nodePath);
    }

    /**
     * Compute the path in terms of 'FLR' from the nodePath
     * @param nodePath
     */
    public void computePathFromNodes(List<Position> nodePath){
        Direction dir = Direction.RIGHT;
        for(int i = 0; i < nodePath.size() - 1; i++){
            Position current = nodePath.get(i);
            Position next = nodePath.get(i + 1);
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