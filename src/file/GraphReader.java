package file;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class which reads a graph stored as an adjacency matrix in a text file, 
 * and stores this in a 2D array
 * 
 * @author DA Chan
 * @version miniproject
 * 
 */
public class GraphReader {
	  private int[][] adjacencyMatrix;

	  	/**
	     * Method which reads a graph stored as an adjacency matrix
	     * @param filePath - the file path of the text file  
	     */
	    public GraphReader(String filePath) {
	        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
	            String line = reader.readLine();
	            String[] rows = line.split("\\},\\{");
	            int numRows = rows.length;
	            int numCols = rows[0].split(",").length;
	            adjacencyMatrix = new int[numRows][numCols];
	            for (int i = 0; i < numRows; i++) {
	            	// Replace {} brackets with [] brackets
	                String[] cols = rows[i].replaceAll("[{}]", "").split(",");
	                for (int j = 0; j < numCols; j++) {
	                    adjacencyMatrix[i][j] = Integer.parseInt(cols[j]);
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    /**
	     * Accessor method for the adjacency matrix
	     * @return adjacencyMatrix
	     */
	    public int[][] getAdjacencyMatrix() {
	        return adjacencyMatrix;
	    }
}
