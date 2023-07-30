package ui;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.spriteManager.Sprite;
import org.graphstream.ui.spriteManager.SpriteManager;
import org.graphstream.ui.view.Viewer;

import algorithms.ClosenessCentrality;


/**
 * Class which visualizes the closeness centrality of all vertices in a given graph
 * 
 * @author DA Chan
 * @version miniproject
 * 
 */
public class ClosenessGraphVisualization {

	/**
     * Method which visualizes Closeness Centrality for each vertex in a given graph   
     */
    public static void visualizeGraph(int[][] adjacencyMatrix) {
    	
        // Create a new graph
        Graph graph = new SingleGraph("Graph");

        // Add nodes to the graph
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            Node node = graph.addNode(Integer.toString(i));
            node.addAttribute("ui.label", "Student" + i);
        }

        // Add edges to the graph
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = i + 1; j < adjacencyMatrix[i].length; j++) {
                if (adjacencyMatrix[i][j] != 0) {
                   Edge edge =  graph.addEdge(Integer.toString(i) + "-" + Integer.toString(j), Integer.toString(i), Integer.toString(j));
                   edge.addAttribute("ui.style", "fill-color: green;");
                }
            }
        }

        // Calculate and display closeness centrality for each vertex
        for (Node node : graph) {
            int vertex = Integer.parseInt(node.getId());
            Double closenessCentrality = ClosenessCentrality.calculateClosenessCentrality(adjacencyMatrix, vertex);
            
            node.addAttribute("ui.style", String.format("size: %spx;", closenessCentrality));

            node.addAttribute("ui.label", String.format("Student%s\n: %.5E", vertex, closenessCentrality));
            node.addAttribute("ui.style", "fill-color: Red;" );
        }

        // Add sprites to show the vertex indices
        SpriteManager spriteManager = new SpriteManager(graph);
        for (Node node : graph) {
            Sprite sprite = spriteManager.addSprite(Integer.toString(node.getIndex()));
            sprite.setPosition(0.5, 0.5, 0);

            sprite.setAttribute(Integer.toString(node.getIndex()), (Object[]) adjacencyMatrix);
        }

        // Improve the graph display resolution
        graph.setAttribute("ui. antialias");
        
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
    

        // Key listener for zooming in or out
        viewer.getDefaultView().addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_Z) {
                    viewer.getDefaultView().getCamera().setViewPercent(viewer.getDefaultView().getCamera().getViewPercent() * 0.9);
                } if (event.getKeyCode() == KeyEvent.VK_X) {
                    viewer.getDefaultView().getCamera().setViewPercent(viewer.getDefaultView().getCamera().getViewPercent() * 1.1);
                } else if(event.getKeyCode() == KeyEvent.VK_H){
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