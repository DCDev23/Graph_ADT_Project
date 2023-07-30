package ui;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.view.Viewer;

import algorithms.KMeansClustering;

/**
 * Class which visualizes the K-Means algorithm for a given graph
 * 
 * @author DA Chan
 * @version miniproject
 * 
 */
public class KMeansVisualization {
	
	/**
     * Method which visualizes the K-Means algorithm for a given graph   
     */
	public static void visualizeGraph(int[][] adjacencyMatrix) {
		
		 int numClusters = 3;

		    KMeansClustering kMeansClustering = new KMeansClustering(numClusters);
		    // Use a map to store the graph vertices in their allocated clusters
		    Map<Integer, List<Integer>> clusters = kMeansClustering.cluster(adjacencyMatrix);

		    for (int i = 0; i < clusters.size(); i++) {
		        System.out.println("Cluster " + (i+1) + ": " + clusters.get(i));
		    }

		    Graph graph = new SingleGraph("K-Means Clustering");

		    // Add nodes to the graph
		    for (int i = 0; i < adjacencyMatrix.length; i++) {
		        Node node = graph.addNode(Integer.toString(i));
		        node.addAttribute("ui.label", Integer.toString(i));
		        node.addAttribute("ui.label", String.format("Student%s\n", node));
		    }

		    // Add edges to the graph
		    for (int i = 0; i < adjacencyMatrix.length; i++) {
		        for (int j = i+1; j < adjacencyMatrix.length; j++) {
		            if (adjacencyMatrix[i][j] != 0) {
		                Edge edge = graph.addEdge(Integer.toString(i) + "-" + Integer.toString(j),
		                                          Integer.toString(i),
		                                          Integer.toString(j),
		                                          false); // create undirected edge
		                edge.addAttribute("ui.label", Integer.toString(adjacencyMatrix[i][j]));
		                edge.addAttribute("ui.style", "fill-color: orange;");
		            }
		        }
		    }

		    // Assign colors to nodes based on their cluster assignments
		    String[] colors = {"red", "blue", "green"}; // use a different color for each cluster
		    for (int i = 0; i < clusters.size(); i++) {
		        String color = colors[i];
		        for (Integer nodeIndex : clusters.get(i)) {
		            Node node = graph.getNode(Integer.toString(nodeIndex));
		            node.addAttribute("ui.style", "fill-color: " + color + ";");
		        }
		    }

		    // Improve the graph display resolution
		    graph.setAttribute("ui.antialias");

		    // Display the graph
	        Viewer viewer = graph.display();
	        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.CLOSE_VIEWER);
	        
	        // Add message box to display graph controls
	        JOptionPane.showMessageDialog(viewer.getDefaultView(),
					"Z-Key: Zoom-In\n\n"
					+ "X-Key: Zoom-Out\n\n"
					+ "Up, Down, Left, Right arrow keys: move the camera view\n\n"
					+ "H-Key: View Key Controls\n\n",
					"Graph Visualization Controls",
					JOptionPane.PLAIN_MESSAGE);
		    
	        // Add a key listener to the viewer for zooming in/out
	        viewer.getDefaultView().addKeyListener(new KeyAdapter() {
	            public void keyPressed(KeyEvent event) {
	                if (event.getKeyCode() == KeyEvent.VK_Z) {
	                    viewer.getDefaultView().getCamera().setViewPercent(viewer.getDefaultView().getCamera().getViewPercent() * 0.9);
	                } else if (event.getKeyCode() == KeyEvent.VK_X) {
	                    viewer.getDefaultView().getCamera().setViewPercent(viewer.getDefaultView().getCamera().getViewPercent() * 1.1);
	                }
	                else if(event.getKeyCode() == KeyEvent.VK_H){
	                	JOptionPane.showMessageDialog(viewer.getDefaultView(),
	                			"Z-Key: Zoom-In\n\n"
	                			 + "X-Key: Zoom-Out\n\n"
	                			 + "Up, Down, Left, Right arrow keys: move the camera view\n\n"
	                			 + "H-Key: View Key Controls\n\n",
	                			 "Graph Visualization Controls",
	            				
	                			 JOptionPane.PLAIN_MESSAGE);
	                }
	            }
	        });
	}

}