package sssp;
/**
 * File: Vertex.java
 * Class: CSCI 4330
 * Author: Junior Meador
 * Created on: April 9, 2024
 * Last Modified: April 15, 2024
 * Description: MST & SSSP HA4
 */
public class Vertex {
	
	// Class variables
    private final int id;
    private int distance;
    private Vertex predecessor;

    // Vertex constructor
    public Vertex(int id) {
        this.id = id;
        this.distance = Integer.MAX_VALUE; // Infinity representation
        this.predecessor = null; // No predecessor initially
    }

    // Returns vertex identifier
    public int getId() {
        return id;
    }

    // Returns distance from current vertex to source vertex
    public int getDistance() {
        return distance;
    }

    // Sets the distance from current vertex to source vertex
    public void setDistance(int distance) {
        this.distance = distance;
    }

    // Returns the predecessor vertex
    public Vertex getPredecessor() {
        return predecessor;
    }
    
    // Sets the predecessor vertex
    public void setPredecessor(Vertex predecessor) {
        this.predecessor = predecessor;
    }
}