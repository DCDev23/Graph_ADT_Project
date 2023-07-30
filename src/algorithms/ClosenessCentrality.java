package algorithms;

/**
 * Class which contains method for calculating Closeness Centrality based on
 * the Centrality definition by Chavdar Dangalchev
 * 
 * (https://www.sciencedirect.com/science/article/abs/pii/S0378437105012768)
 * 
 * @author DA Chan
 * @version miniproject
 * 
 */
public class ClosenessCentrality {

	/**
	 * Method calculates Closeness Centrality for a given graph and vertex
	 */	
	public static Double calculateClosenessCentrality(int[][] graph, int vertex) {
	    int numVertices = graph.length;

	    int totalDistance = 0;
	    int reachableVerticesCount = 0;
	    
	    for (int i = 0; i < numVertices; i++) {
	        if (i != vertex) {
	            BreadthFirstSearch bfs = new BreadthFirstSearch(graph, vertex, i);
	            int shortestPathLength = bfs.findShortestPathLength();
	            if (shortestPathLength != -1) {
	                totalDistance += shortestPathLength;
	            	reachableVerticesCount++;
	            }
	        }
	    }
	    
	    if (reachableVerticesCount == 0) {
	    	// Return -1 for empty graph or disconnected vertices
	        System.out.println("-1 undefined"); 
	    }
	
	    Double closenessCentrality = 1.0 / Math.pow(2, totalDistance);
	    return closenessCentrality;
	}
}