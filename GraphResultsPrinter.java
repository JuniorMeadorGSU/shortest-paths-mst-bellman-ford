package sssp;
/**
 * File: GraphResultsPrinter.java
 * Class: CSCI 4330
 * Author: Junior Meador
 * Created on: April 9, 2024
 * Last Modified: April 15, 2024
 * Description: MST & SSSP HA4
 */
import java.io.PrintStream;
import java.util.List;
import java.util.Map;

public class GraphResultsPrinter {

    public static void printResults(PrintStream out, String graphName, int vertices, List<Edge> edges, Vertex[] verticesInfo, Map<Integer, String> vertexIdMap, boolean[] inNegativeCycle) {
    	
    	// Print graph name and number of vertices and edges
        out.printf("Results for %s\n", graphName);
        out.println("Number of vertices: " + vertices);
        out.println("Number of edges: " + edges.size());

        // Print distance table
        out.println("\nTable of d values");
        out.printf("%-10s | %-15s\n", "Vertex", "Distance");
        for (Vertex vertex : verticesInfo) {
            String distance = getFormattedDistance(vertex, inNegativeCycle);
            out.printf("%-10s | %-15s\n", vertexIdMap.get(vertex.getId()), distance);
        }
        
        // Print predecessor table
        out.println("\nTable of π values");
        out.printf("%-10s | %-15s\n", "Vertex", "Predecessor");
        for (Vertex vertex : verticesInfo) {
            String predecessor = vertex.getPredecessor() == null ? "null" : vertexIdMap.get(vertex.getPredecessor().getId());
            out.printf("%-10s | %-15s\n", vertexIdMap.get(vertex.getId()), predecessor);
        }
        out.println();
    }
    
    // Formats the distance for a vertex if a negative cycle exist
    private static String getFormattedDistance(Vertex vertex, boolean[] inNegativeCycle) {
        if (inNegativeCycle[vertex.getId()]) {
            return "-INF";
        } else {
            return vertex.getDistance() == Integer.MAX_VALUE ? "INF" : String.valueOf(vertex.getDistance());
        }
    }
}