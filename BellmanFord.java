package sssp;
/**
 * File: BellmanFord.java
 * Class: CSCI 4330
 * Author: Junior Meador
 * Created on: April 9, 2024
 * Last Modified: April 15, 2024
 * Description: MST & SSSP HA4
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BellmanFord {

	// Class variables
	private final int numberOfVertices;
	private final List<Edge> edges;
	private final Vertex[] vertexInfo;
	private final Map<String, Integer> vertexIndexMap = new HashMap<>();
	private final boolean[] isInNegativeCycle;
	private final Map<Integer, List<Edge>> adjacencyEdges;

	// Constructor to initialize the graph with a specified number of vertices
	public BellmanFord(int vertices) {
		this.numberOfVertices = vertices;
		this.edges = new ArrayList<>();
		this.vertexInfo = new Vertex[vertices];
		this.adjacencyEdges = new HashMap<>(); 
		for (int i = 0; i < vertices; i++) {
			vertexInfo[i] = new Vertex(i);
			adjacencyEdges.put(i, new ArrayList<>()); 
		}
		this.isInNegativeCycle = new boolean[vertices];
	}

	// Adds a directed, weighted edge to the graph
	public void addEdge(String source, String destination, int weight) {
		int srcIndex = getVertexIndex(source);
		int destIndex = getVertexIndex(destination);
		Edge newEdge = new Edge(srcIndex, destIndex, weight);
		edges.add(newEdge);
		adjacencyEdges.get(srcIndex).add(newEdge);
	}

	// Retrieves or assigns an index to a new vertex identifier
	private int getVertexIndex(String id) {
		return vertexIndexMap.computeIfAbsent(id, key -> vertexIndexMap.size());
	}

	// Executes the Bellman-Ford algorithm from a specified start vertex
	public void bellmanFord(String startVertex, String graphName) {
		prepareGraph(startVertex);
		relaxEdges();
		checkNegativeCycles();
		propagateNegativeCycles();
		GraphResultsPrinter.printResults(System.out, graphName, numberOfVertices, edges, vertexInfo, buildVertexIdMap(),
				isInNegativeCycle);
	}

	// Prepares initial graph settings for the algorithm
	private void prepareGraph(String startVertex) {
		int startIdx = getVertexIndex(startVertex);
		vertexInfo[startIdx].setDistance(0);
	}

	// Relaxes all edges in the graph multiple times to find shortest paths
	private void relaxEdges() {
		for (int i = 1; i < numberOfVertices; i++) {
			for (Edge edge : edges) {
				relaxEdge(edge);
			}
		}
	}

	// Checks for negative weight cycles in the graph
	private void checkNegativeCycles() {
		for (Edge edge : edges) {
			if (shouldRelax(edge)) {
				isInNegativeCycle[edge.getSource()] = true;
			}
		}
	}

	// Propagates the detection of negative cycles through the graph
	private void propagateNegativeCycles() {
		Queue<Integer> queue = new LinkedList<>();
		// Add all vertices that are known to be in a negative cycle
		for (int i = 0; i < numberOfVertices; i++) {
			if (isInNegativeCycle[i]) {
				queue.add(i);
			}
		}

		// Process each vertex in the queue until empty
		while (!queue.isEmpty()) {
			int u = queue.poll(); // Dequeue next vertex

			// Examine all adjacency edges from vertex u
			for (Edge edge : adjacencyEdges.get(u)) {
				int v = edge.getDestination();

				// Ensure destination vertex isn't already identified as part of a cycle
				if (!isInNegativeCycle[v]) {
					isInNegativeCycle[v] = true; // If not, identify it as one
					queue.add(v); // Add to queue for further propagation
				}
			}
		}
	}

	// Determines if an edge can be relaxed based on current distances
	private boolean shouldRelax(Edge edge) {
		Vertex uVertex = vertexInfo[edge.getSource()];
		Vertex vVertex = vertexInfo[edge.getDestination()];
		int weight = edge.getWeight();
		return uVertex.getDistance() != Integer.MAX_VALUE && uVertex.getDistance() + weight < vVertex.getDistance();
	}

	// Relaxes an individual edge if conditions allow
	private void relaxEdge(Edge edge) {
		int u = edge.getSource();
		int v = edge.getDestination();
		int weight = edge.getWeight();
		Vertex uVertex = vertexInfo[u];
		Vertex vVertex = vertexInfo[v];
		if (uVertex.getDistance() != Integer.MAX_VALUE && uVertex.getDistance() + weight < vVertex.getDistance()) {
			vVertex.setDistance(uVertex.getDistance() + weight);
			vVertex.setPredecessor(uVertex);
		}
	}

	// Maps vertex indices back to their identifiers for results output
	private Map<Integer, String> buildVertexIdMap() {
		Map<Integer, String> idMap = new HashMap<>();
		vertexIndexMap.forEach((key, value) -> idMap.put(value, key));
		return idMap;
	}
}