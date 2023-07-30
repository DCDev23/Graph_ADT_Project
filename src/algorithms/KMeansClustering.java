package algorithms;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Class which implements the standard K-Means clustering algorithm (naive k-means) for 
 * grouping nodes into different clusters based on their similarity scores.
 * 
 * (https://stanford.edu/~cpiech/cs221/handouts/kmeans.html)
 * 
 * @author DA Chan
 * @version miniproject
 * 
 */
public class KMeansClustering {
    private int numClusters;
    private Map<Integer, List<Integer>> clusters;

    /**
     * Overloaded constructor
     * @param numClusters - the number of clusters to partition the nodes into
     */
    public KMeansClustering(int numClusters) {
        this.numClusters = numClusters;
        this.clusters = new HashMap<>();
    }

    /**
     * Method which implements the K-Means clustering algorithm
     * 
     * @param adjacencyMatrix - the graph to apply the algorithm on
     */
    public Map<Integer, List<Integer>> cluster(int[][] adjacencyMatrix) {
        // Initialize centroids randomly
        List<Integer> centroids = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < numClusters; i++) {
            int centroidIndex = random.nextInt(adjacencyMatrix.length);
            centroids.add(centroidIndex);
            clusters.put(i, new ArrayList<>());
        }

        boolean converged = false;
        while (!converged) {
            // Assign nodes to clusters based on closest centroid
            for (int i = 0; i < adjacencyMatrix.length; i++) {
                int closestCentroidIndex = -1;
                double closestCentroidDistance = Double.MAX_VALUE;
                for (int j = 0; j < centroids.size(); j++) {
                    int centroidIndex = centroids.get(j);
                    double distance = calculateDistance(adjacencyMatrix, i, centroidIndex);
                    if (distance < closestCentroidDistance) {
                        closestCentroidIndex = j;
                        closestCentroidDistance = distance;
                    }
                }
                clusters.get(closestCentroidIndex).add(i);
            }

            // Update centroids based on mean of each cluster
            List<Integer> newCentroids = new ArrayList<>();
            for (int i = 0; i < numClusters; i++) {
                List<Integer> cluster = clusters.get(i);
                double[] means = new double[adjacencyMatrix.length];
                for (int j = 0; j < cluster.size(); j++) {
                    int nodeIndex = cluster.get(j);
                    for (int k = 0; k < adjacencyMatrix[nodeIndex].length; k++) {
                        means[k] += adjacencyMatrix[nodeIndex][k];
                    }
                }
                for (int j = 0; j < means.length; j++) {
                    means[j] /= cluster.size();
                }
                int closestNodeIndex = -1;
                double closestNodeDistance = Double.MAX_VALUE;
                for (int j = 0; j < means.length; j++) {
                    double distance = calculateDistance(adjacencyMatrix, j, centroids.get(i));
                    if (distance < closestNodeDistance) {
                        closestNodeIndex = j;
                        closestNodeDistance = distance;
                    }
                }
                newCentroids.add(closestNodeIndex);
            }

            // Check for convergence
            if (centroids.equals(newCentroids)) {
                converged = true;
            } else {
                centroids = newCentroids;
                clusters = new HashMap<>();
                for (int i = 0; i < numClusters; i++) {
                    clusters.put(i, new ArrayList<>());
                }
            }
        }

        return clusters;
    }

    /**
     * Method to calculate the Euclidean distance between two nodes
     * @param adjacencyMatrix - the graph to apply the method on
     * @param node1Index
     * @param node2Index
     * 
     * @return The Euclidean distance between nodes
     */
    private double calculateDistance(int[][] adjacencyMatrix, int node1Index, int node2Index) {
        double distance = 0.0;
        for (int i = 0; i < adjacencyMatrix[node1Index].length; i++) {
            distance += Math.pow(adjacencyMatrix[node1Index][i] - adjacencyMatrix[node2Index][i], 2);
        }
        return Math.sqrt(distance);
    }
    
}
