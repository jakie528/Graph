

import java.util.Hashtable;
import java.util.List;
import java.util.LinkedList;

public class GraphInplementation<T> {
    boolean[][] adjacencyMatrix;
    Hashtable<T, Integer> nodeIndices;
    Hashtable<T, GraphNode<T>> vertexTable;
    boolean useMatrixRepresentation = true; // Change this to false if you want to use adjacency list representation

    public GraphInplementation(int size) {
        if (useMatrixRepresentation) {
            adjacencyMatrix = new boolean[size][size];
            nodeIndices = new Hashtable<>(size);
        } else {
            vertexTable = new Hashtable<>(size);
        }
    }

    protected class GraphNode<T> {
        protected T data;
        protected List<GraphNode<T>> adjacencyList;

        public GraphNode(T data) {
            this.data = data;
            adjacencyList = new LinkedList<>();
        }
    }

    public void addNode(T nodeData) {
        if (useMatrixRepresentation) {
            int index = nodeIndices.size();
            nodeIndices.put(nodeData, index);
        } else {
            vertexTable.put(nodeData, new GraphNode<>(nodeData));
        }
    }

    public void addEdge(T nodeData1, T nodeData2) {
        if (useMatrixRepresentation) {
            int index1 = nodeIndices.get(nodeData1);
            int index2 = nodeIndices.get(nodeData2);
            adjacencyMatrix[index1][index2] = true;
            adjacencyMatrix[index2][index1] = true;
        } else {
            GraphNode<T> node1 = vertexTable.get(nodeData1);
            GraphNode<T> node2 = vertexTable.get(nodeData2);
            node1.adjacencyList.add(node2);
            node2.adjacencyList.add(node1);
        }
    }

    public boolean isConnected(T nodeData1, T nodeData2) {
        if (useMatrixRepresentation) {
            int index1 = nodeIndices.get(nodeData1);
            int index2 = nodeIndices.get(nodeData2);
            return adjacencyMatrix[index1][index2];
        } else {
            GraphNode<T> node1 = vertexTable.get(nodeData1);
            return node1.adjacencyList.contains(vertexTable.get(nodeData2));
        }
    }
}
