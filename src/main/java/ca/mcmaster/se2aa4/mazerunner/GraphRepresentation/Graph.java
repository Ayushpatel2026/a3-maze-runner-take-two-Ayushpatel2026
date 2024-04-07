package ca.mcmaster.se2aa4.mazerunner.GraphRepresentation;

import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.Position;

public class Graph{

    private static final Logger logger = LogManager.getLogger();
    
    //adjacency list representation using map
    private final Map<Position, List<Edge>> nodes = new HashMap<>();

    /** 
     * Initialize an empty graph
     */
    public Graph(){

    }

    /**
     * Add a node to the graph
     * @param node
     */
    public void addNode(Position node){
        nodes.put(node, new ArrayList<>());
    }

    /**
     * add an edge to the graph
     * @param source
     * @param destination
     * @param weight
     */
    public void addEdge(Position source, Position destination, int weight){
        nodes.get(source).add(new Edge(destination, weight));
        nodes.get(destination).add(new Edge(source, weight)); //undirected graph
    }


    /**
     * Get the adjacent nodes of a node
     * @param node
     * @return list of adjacent nodes
     */
    public List<Position> getAdjNodes(Position node){
        List<Position> adjNodes = new ArrayList<>();
        for(Edge edge : nodes.get(node)){
            adjNodes.add(edge.getDestination());
        }
        return adjNodes;
    }

    /**
     * Get the weight of an edge (will technically always be one in this scenario)
     * @param source
     * @param destination
     * @return weight of edge
     */
    public int getEdgeWeight(Position source, Position destination){
        for(Edge edge : nodes.get(source)){
            if(edge.getDestination().equals(destination)){
                return edge.getWeight();
            }
        }
        return -1;
    }

    /**
     * Get all the nodes in the graph
     * @return set of nodes
     */
    public Set<Position> getNodes(){
        return nodes.keySet();
    }

    static class Edge{
        Position destination;
        int weight;

        public Edge(Position destination, int weight){
            this.destination = destination;
            this.weight = weight;
        }

        /**
         * Get the destination of the edge
         * @return Position destination node
         */
        public Position getDestination(){
            return destination;
        }

        /**
         * Get the weight of the edge
         * @return int weight of edge
         */        
        public int getWeight(){
            return weight;
        }
    }
}
