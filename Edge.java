package sssp;
/**
 * File: Edge.java
 * Class: CSCI 4330
 * Author: Junior Meador
 * Created on: April 9, 2024
 * Last Modified: April 15, 2024
 * Description: MST & SSSP HA4
 */
public class Edge {
	
	// Class variables
    private final int source;
    private final int destination;
    private final int weight;
    
    // Edge constructor
    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
    
    // Returns source vertex of edge
    public int getSource() {
        return source;
    }

    // Returns the destination vertex of edge
    public int getDestination() {
        return destination;
    }

    // Returns weight of edge
    public int getWeight() {
        return weight;
    }
}