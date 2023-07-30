package algorithms;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/**
 * Class which implements the BreadthFirstSearch algorithm for finding
 * the shortest path between two vertices in a graph.
 * 
 * @author DA Chan
 * @version miniproject
 * 
 */
public class BreadthFirstSearch {
    private int[][] graph;
    private boolean[] visited;
    private int[] distance;
    private int source;
    private int destination;
    // List to track visited vertices
    private List<Integer> visitedVertices; 

    /**
     * Default constructor
     * 
     * @param adjacencyMatrix
     * @param sourceNode
     * @param destinationNode
     */
    public BreadthFirstSearch(int[][] adjacencyMatrix, int sourceNode, int destinationNode) {
        graph = adjacencyMatrix;
        int numVertices = graph.length;
        visited = new boolean[numVertices];
        distance = new int[numVertices];
        source = sourceNode;
        destination = destinationNode;
        
        visitedVertices = new LinkedList<Integer>(); 
    }

    /**
     * Method which calculates the shortest path length between two vertices in a graph
     * @return -1 if undefined
     */
    public int findShortestPathLength() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();

            if (currentNode == destination) {
                return distance[currentNode];
            }

            for (int neighbor = 0; neighbor < graph.length; neighbor++) {
                if (graph[currentNode][neighbor] == 1 && !visited[neighbor]) {
                    queue.add(neighbor);
                    visited[neighbor] = true;
                    distance[neighbor] = distance[currentNode] + 1;
                    // Add visited vertex to the list
                    visitedVertices.add(neighbor); 
                }
            }
        }

        // If the destination node is not reachable
        return -1;
    }

    /**
     * Accessor method for list of visited vertices
     * @return visitedVertices
     */
    public List<Integer> getVisitedVertices() {
        return visitedVertices;
    }
}
