package algorithms;

import java.util.List;

/**
 * Class which 
 * 
 * @author DA Chan
 * @version miniproject
 *
 */
public class BFSTest {
	/**
     * @param args
     */
    public static void main(String[] args) {
        testBFSWithEmptyGraph();
        testBFSWithSingleVertexGraph();
        testBFSWithTwoVertexGraph();
        testBFSWithThreeVertexGraph();
        testBFSWithCyclicGraph();
    }
    
    /**
     * Method to test BFS for an empty graph
     */
    private static void testBFSWithEmptyGraph() {
        int[][] graph = new int[0][0];
        int source = 0;
        int destination = 0;

        if (graph.length == 0) {
            System.out.println("Shortest path length (Empty Graph): -1");
            return;
        }

        BreadthFirstSearch bfs = new BreadthFirstSearch(graph, source, destination);
        int shortestPathLength = bfs.findShortestPathLength();

        System.out.println("Shortest path length (Empty Graph): " + shortestPathLength);
    }


    /**
     *  Method to test BFS for an a single vertex graph
     */
    private static void testBFSWithSingleVertexGraph() {
        int[][] graph = {{0}};
        int source = 0;
        int destination = 0;

        BreadthFirstSearch bfs = new BreadthFirstSearch(graph, source, destination);
        int shortestPathLength = bfs.findShortestPathLength();

        System.out.println("Shortest path length (Single Vertex Graph): " + shortestPathLength);
    }

    /**
     *  Method to test BFS for graph with two vertices
     */
    private static void testBFSWithTwoVertexGraph() {
        int[][] graph = {
            {0, 1},
            {1, 0}
        };
        int source = 0;
        int destination = 1;

        BreadthFirstSearch bfs = new BreadthFirstSearch(graph, source, destination);
        int shortestPathLength = bfs.findShortestPathLength();

        System.out.println("Shortest path length (Two Vertex Graph): " + shortestPathLength);
    }

    /**
     *  Method to test BFS for a graph with three vertices
     */
    private static void testBFSWithThreeVertexGraph() {
        int[][] graph = {
            {0, 1, 0},
            {1, 0, 1},
            {0, 1, 0}
        };
        int source = 0;
        int destination = 2;

        BreadthFirstSearch bfs = new BreadthFirstSearch(graph, source, destination);
        int shortestPathLength = bfs.findShortestPathLength();

        System.out.println("Shortest path length (Three Vertex Graph): " + shortestPathLength);
    }

    /**
     * Method to test BFS with a Cyclic graph
     */
    private static void testBFSWithCyclicGraph() {
        int[][] graph = {
        		{0,1,1,1,1,1,1},
        		{1,0,1,1,1,1,1},
        		{1,1,0,1,1,1,1},
        		{1,1,1,0,1,1,1},
        		{1,1,1,1,0,1,1},
        		{1,1,1,1,1,0,1},
        		{1,1,1,1,1,1,0}

        };
        int source = 0;
        int destination = 3;

        BreadthFirstSearch bfs = new BreadthFirstSearch(graph, source, destination);
        int shortestPathLength = bfs.findShortestPathLength();

        System.out.println("Shortest path length (Cyclic Graph): " + shortestPathLength);

        if (shortestPathLength != -1) {
            List<Integer> visitedVertices = bfs.getVisitedVertices();
            System.out.println("BFS Visited Vertices: " + visitedVertices.toString());
        }
    }

}
