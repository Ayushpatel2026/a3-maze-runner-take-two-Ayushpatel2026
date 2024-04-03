package ca.mcmaster.se2aa4.mazerunner;

import java.util.*;


public class Graph{
    //adjacency list representation using map
    private final Map<Position, List<Edge>> nodes = new HashMap<>();

    public Graph(){

    }

    public void addNode(Position node){
        nodes.put(node, new ArrayList<>());
    }

    public void addEdge(Position source, Position destination, int weight){
        nodes.get(source).add(new Edge(destination, weight));
        nodes.get(destination).add(new Edge(source, weight)); //undirected graph
    }

    public List<Edge> getEdges(Position node){
        return nodes.get(node);
    }

    public Set<Position> getAdjNodes(Position node){
        Set<Position> adjNodes = new HashSet<>();
        for(Edge edge : nodes.get(node)){
            adjNodes.add(edge.getDestination());
        }
        return adjNodes;
    }

    public boolean isEdge(Position source, Position destination){
        for(Edge edge : nodes.get(source)){
            if(edge.getDestination().equals(destination)){
                return true;
            }
        }
        return false;
    }

    static class Edge{
        Position destination;
        int weight;

        public Edge(Position destination, int weight){
            this.destination = destination;
            this.weight = weight;
        }

        public Position getDestination(){
            return destination;
        }

        public int getWeight(){
            return weight;
        }
    }
}
