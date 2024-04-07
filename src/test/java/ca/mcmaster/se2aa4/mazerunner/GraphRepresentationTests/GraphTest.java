package ca.mcmaster.se2aa4.mazerunner.GraphRepresentation;

import ca.mcmaster.se2aa4.mazerunner.Position;
import ca.mcmaster.se2aa4.mazerunner.GraphRepresentation.Graph;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GraphTest {
    
    @Test 
    void testAddNode(){
        Graph graph = new Graph();
        Position node = new Position(0, 0);
        graph.addNode(node);
        assertEquals(1, graph.getNodes().size());
        Position node2 = new Position(1, 1);
        graph.addNode(node2);
        assertEquals(2, graph.getNodes().size());
    }

    @Test 
    void testAddEdge(){
        Graph graph = new Graph();
        Position node = new Position(0, 0);
        Position node2 = new Position(1, 1);
        graph.addNode(node);
        graph.addNode(node2);
        graph.addEdge(node, node2, 1);
        assertEquals(1, graph.getAdjNodes(node).size());
        assertEquals(1, graph.getAdjNodes(node2).size());
        assertEquals(node2, graph.getAdjNodes(node).get(0));
        assertEquals(node, graph.getAdjNodes(node2).get(0));
        assertEquals(1, graph.getEdgeWeight(node, node2));
    }

    @Test  
    void testGetAdjNodes(){
        Graph graph = new Graph();
        Position node = new Position(0, 0);
        Position node2 = new Position(1, 1);
        Position node3 = new Position(2, 2);
        graph.addNode(node);
        graph.addNode(node2);
        graph.addEdge(node, node2, 1);
        assertEquals(1, graph.getAdjNodes(node).size());
        assertEquals(1, graph.getAdjNodes(node2).size());
        assertEquals(node2, graph.getAdjNodes(node).get(0));
        assertEquals(node, graph.getAdjNodes(node2).get(0));

        graph.addNode(node3);
        graph.addEdge(node, node3, 1);
        assertEquals(2, graph.getAdjNodes(node).size());
        assertEquals(1, graph.getAdjNodes(node3).size());
        assertEquals(1, graph.getAdjNodes(node2).size());
        assertEquals(node2, graph.getAdjNodes(node).get(0));
        assertEquals(node3, graph.getAdjNodes(node).get(1));
        assertEquals(node, graph.getAdjNodes(node3).get(0));
    }

    @Test 
    void testGetEdgeWeight(){
        Graph graph = new Graph();
        Position node = new Position(0, 0);
        Position node2 = new Position(1, 1);
        graph.addNode(node);
        graph.addNode(node2);
        graph.addEdge(node, node2, 1);
        assertEquals(1, graph.getEdgeWeight(node, node2));
    }
}