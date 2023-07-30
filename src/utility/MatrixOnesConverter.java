package utility;
/**
 * Class which implements a method for converting all non-zero entries in an adjacency 
 * matrix to 1's to prepare the ui data for use with closeness centrality calculations
 * 
 * @author DA Chan
 * @version miniproject
 * 
 */
public class MatrixOnesConverter {
	/**
     * Method for converting all non-zero entries in an adjacency matrix to 1's
     */
	 public static int[][] convertToOnes(int[][] matrix) {
	        int rows = matrix.length;
	        int cols = matrix[0].length;
	        int[][] result = new int[rows][cols];
	        for (int i = 0; i < rows; i++) {
	            for (int j = 0; j < cols; j++) {
	                if (matrix[i][j] != 0) {
	                    result[i][j] = 1;
	                }
	            }
	        }
	        return result;
	    }
}
