package sssp;
/**
 * File: Main.java
 * Class: CSCI 4330
 * Author: Junior Meador
 * Created on: April 9, 2024
 * Last Modified: April 15, 2024
 * Description: MST & SSSP HA4
 */
public class Main {

	public static void main(String[] args) {
		
		// Graph 3 has 8 vertices and 13 edges
		BellmanFord graph3 = new BellmanFord(8);

		// Graph 4 has 10 vertices and 17 edges
		BellmanFord graph4 = new BellmanFord(10);

		// Add edges to graph 3. Vertex 'S' is the source vertex with index 0
		graph3.addEdge("S", "A", 6);
		graph3.addEdge("S", "B", 5);
		graph3.addEdge("S", "C", -4);
		graph3.addEdge("A", "D", 1);
		graph3.addEdge("A", "E", 5);
		graph3.addEdge("B", "A", 2);
		graph3.addEdge("B", "C", -1);
		graph3.addEdge("B", "F", 3);
		graph3.addEdge("C", "F", 2);
		graph3.addEdge("D", "E", -2);
		graph3.addEdge("E", "G", -3);
		graph3.addEdge("F", "E", 1);
		graph3.addEdge("G", "D", 4);

		// Add edges to graph 4. Vertex 'S' is the source vertex with index 0
		graph4.addEdge("S", "A", 6);
		graph4.addEdge("S", "B", -1);
		graph4.addEdge("S", "C", 5);
		graph4.addEdge("A", "B", 1);
		graph4.addEdge("A", "D", -4);
		graph4.addEdge("B", "C", 2);
		graph4.addEdge("B", "E", 3);
		graph4.addEdge("C", "E", 7);
		graph4.addEdge("C", "F", -2);
		graph4.addEdge("D", "E", 2);
		graph4.addEdge("E", "A", 6);
		graph4.addEdge("E", "F", 5);
		graph4.addEdge("E", "H", 5);
		graph4.addEdge("F", "I", 3);
		graph4.addEdge("G", "D", 4);
		graph4.addEdge("G", "H", -3);
		graph4.addEdge("H", "I", 1);

		// Run Bellman-Ford algorithm on graphs with the source vertex 'S'
		graph3.bellmanFord("S", "Graph 3");
		graph4.bellmanFord("S", "Graph 4");

	}
}