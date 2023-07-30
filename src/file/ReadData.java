package file;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.jwetherell.algorithms.data_structures.*;
import com.jwetherell.algorithms.data_structures.Graph.Edge;
import com.jwetherell.algorithms.data_structures.Graph.Vertex;

/**
 * Class which implements methods for reading student data sets and 
 * creating undirected graphs using the required graph ADT class  
 * 
 * @author DA Chan
 * @version miniproject
 *
 */
public class ReadData {
	
	/**
     * Method which reads a Student Dataset file
     * @param path - the file path to read the dataset file from
     */
	public static StudentData[] readStudentData(String path) {
	    BufferedReader reader = null;
	    try {
	        reader = new BufferedReader(new FileReader(path));
	        
	        // Count the number of lines in the file
	        int numOfLines = 0;
	        while (reader.readLine() != null) {
	            numOfLines++;
	        }
	        reader.close();
	        
	        // Create the array with the appropriate size
	        StudentData[] studentData = new StudentData[numOfLines];
	        
	        // Read and parse the data from the file
	        reader = new BufferedReader(new FileReader(path));
	        String line = reader.readLine();
	        int i = 0;
	        while (line != null) {
	            String[] fields = line.split(",");
	            StudentData data = new StudentData();
	            data.setIndex(Integer.parseInt(fields[0].trim()));
	            data.setAcademicResult(fields[1].trim());
	            data.setHomeLanguage(Integer.parseInt(fields[2].trim()));
	            data.setHighSchoolFees(fields[3].trim());
	            data.setFirstGeneration(fields[4].trim());
	            data.setLivingSpace(Integer.parseInt(fields[5].trim()));
	            data.setFamilyStructure(Integer.parseInt(fields[6].trim()));
	            studentData[i] = data;
	            i++;
	            line = reader.readLine();
	        }
	        
	        return studentData;
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (reader != null) {
	                reader.close();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    return null;
	}
	
	
	/**
     * Method which partitions the Student Dataset into three separate lists based on
     * academic performance
     * 
     * @param students - the array of students from the Student Dataset
     * @param failed - linked list for the students with failed performance
     * @param average - linked list for the students with average performance
     * @param good - linked list for the students with good performance
     */
	public static void splitStudents(StudentData[] students, List<StudentData> failed, List<StudentData> average, List<StudentData> good) {
	    for (StudentData student : students) {
	        String academicResult = student.getAcademicResult();
	        // Partition students into separate lists based on academic performance
	        if (academicResult.equals("F")) {
	            failed.add(student);
	        } else if (academicResult.equals("A")) {
	            average.add(student);
	        } else if (academicResult.equals("G")) {
	            good.add(student);
	        }
	    }
	}

	
	/**
	 * Computes score for two students being compared within a linked list 
	 * based on the number of common features they have and creates a graph
	 * using the provided Graph ADT
	 *  
	 * @param list - the linked list to apply the method on
	 * @param graph - the graph to be created
	 */
	public static void computeSimilarityScore(LinkedList<StudentData> list, Graph<Integer> graph) {
	    int numStudents = list.size();
	    // Use a 2D array to keep track of which students are being compared
	    int[][] scores = new int[numStudents][numStudents];
	    // Use a nested loop to iterate through each pair of students in the linked list
	    for (int i = 0; i < numStudents; i++) {
	        StudentData student1 = list.get(i);
	        
	        // Create a vertex for the first student to be compared 
	        Vertex<Integer> v1 = new Vertex<Integer>(student1.getIndex());
	        if(!graph.getVertices().contains(v1)) {
		        graph.getVertices().add(v1);	
		        
	        }
	        
	        for (int j = i + 1; j < numStudents; j++) {
	        	// Get the index of the second student to be compared
	            StudentData student2 = list.get(j);
	            
	            // Create a vertex for the second student to be compared 
	            Vertex<Integer> v2 = new Vertex<Integer>(student2.getIndex());
	            if(!graph.getVertices().contains(v2)) {
		            graph.getVertices().add(v2);

	            }
	            	            
	            int score = 0;
	            // Compare each of the student's features and increase the score for each matching feature
	            if (student1.getIndex() == student2.getIndex()) {
	                score++;
	            }
	            if (student1.getAcademicResult().equals(student2.getAcademicResult())) {
	                score++;
	            }
	            if (student1.getHomeLanguage() == student2.getHomeLanguage()) {
	                score++;
	            }
	            if (student1.getHighSchoolFees().equals(student2.getHighSchoolFees())) {
	                score++;
	            }
	            if (student1.getFirstGeneration().equals(student2.getFirstGeneration())) {
	                score++;
	            }
	            if(student1.getLivingSpace() == (student2.getLivingSpace())){
	            	score++;
	            }
	            
	            scores[i][j] = score;
	            scores[j][i] = score;
	            
	            // Add an edge between the nodes representing the compared students
	            graph.getEdges().add(new Edge<Integer>(score, v1, v2));
	            
	            // Remove edges with a low score (<=2)
	            removeEdges(graph, score, v1, v2);

	        }
	    }
	}
	
	/**
     * Method which removes edges with edge cost less than or equal to 2
     * 
     * @param graph - the graph to apply this method on
     * @param score - the edge cost representing the number of similiar features
     * between two students
     * @param v1 - the vertex representing the first student being compared
     * @param v2 - the vertex representing the second student being compared
     */
	public static void removeEdges(Graph<Integer> graph, int score,  Vertex<Integer> v1,  Vertex<Integer> v2) {
		graph.getEdges().removeIf(edge -> edge.getCost() <= 2);
	}
	
	
	/**
	 * Convert graph data into an Adjacency Matrix
	 * 
	 * @param graph - the graph to convert to an adjacency matrix representation
	 * @return a String representation of the adjacency matrix
	 */
	public static String convertToAdjacencyMatrix(Graph<Integer> graph) {
	    // Get the number of vertices in the graph		
	    int numVertices = graph.getVertices().size();
	    System.out.println("numvertices: " + numVertices);
	    // Create a 2D array to hold the adjacency matrix
	    Integer max = 0;

	   List<Vertex<Integer>> vertices = (List<Vertex<Integer>>) graph.getVertices();
	   for (Vertex<Integer> vertice: vertices){
		   if(vertice.getValue() > max) {
			   // Set max to the maximum vertex value 
			   max = vertice.getValue();
		   }
	   }
	    		
	    // Array size needs to be the max value +1		
	    int[][] matrix = new int[max + 1][max + 1];
	    
	    // Populate the adjacency matrix based on the edges in the graph
	    for (Edge<Integer> edge : graph.getEdges()) {
	        Vertex<Integer> v1 = edge.getFromVertex();
	        Vertex<Integer> v2 = edge.getToVertex();
	        int cost = edge.getCost();
	        int i = v1.getValue();
	        int j = v2.getValue();
	        
	        matrix[i][j] = cost;
	        matrix[j][i] = cost; 	        
	    }
	    	    
	    // Convert the adjacency matrix to a string format that is readable and writable
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < max + 1; i++) {
	        for (int j = 0; j < max + 1; j++) {
	            sb.append(matrix[i][j]);
	        }
	        sb.append("\n");
	    }
	    
	    return sb.toString();
	}
	
	/**
     * Method which writes an adjacency matrix to a specific text file
     * 
     * @param fileName - the name of the file to write to
     * @param filePath - the path of the file to write to
     * @param matrixString - the adjacency matrix to write to the file
     */
	public static void createAndWriteToFile(String fileName, String filePath, String matrixString) throws IOException {
        String fullPath = filePath + "/" + fileName;
        BufferedWriter writer = new BufferedWriter(new FileWriter(fullPath));
        writer.write(matrixString);
        writer.close();
    }
		
}


