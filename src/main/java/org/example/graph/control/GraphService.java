package org.example.graph.control;

import lombok.NonNull;
import org.example.graph.entity.Graph;

import java.util.*;

import static org.example.graph.util.Constants.*;

public class GraphService {
    public <T> void addVertex(@NonNull Graph<T> graph, @NonNull T vertex) {
        if (graph.getVertices().contains(vertex)) {
            throw new IllegalArgumentException(String.format(VERTEX_EXISTS_ERROR_MESSAGE, vertex));
        } else {
            graph.getVertices().add(vertex);
        }
    }

    public <T> void addEdge(@NonNull Graph<T> graph, @NonNull T firstVertex, @NonNull T secondVertex) {
        Set<T> vertices = graph.getVertices();
        checkVertexExistence(vertices, firstVertex);
        checkVertexExistence(vertices, secondVertex);

        Graph.Type currentGraphType = graph.getGraphType();
        switch (currentGraphType) {
            case DIRECTED:
                addEdgeToDirectedGraph(graph.getEdges(), firstVertex, secondVertex);
                break;
            case UNDIRECTED:
                addEdgeToUndirectedGraph(graph.getEdges(), firstVertex, secondVertex);
                break;
            default:
                throw new UnsupportedOperationException(String.format(ADD_EDGE_UNSUPPORTED_ERROR_MESSAGE, currentGraphType));
        }
    }

    private <T> void checkVertexExistence(Set<T> vertices, T vertex) {
        if (!vertices.contains(vertex)) {
            throw new IllegalArgumentException(String.format(UNKNOWN_VERTEX_ERROR_MESSAGE, vertex));
        }
    }

    private <T> void addEdgeToDirectedGraph(Map<T, Set<T>> edges, T firstVertex, T secondVertex) {
        addEdge(edges, firstVertex, secondVertex);
    }

    private <T> void addEdgeToUndirectedGraph(Map<T, Set<T>> edges, T firstVertex, T secondVertex) {
        addEdge(edges, firstVertex, secondVertex);
        addEdge(edges, secondVertex, firstVertex);
    }

    private <T> void addEdge(Map<T, Set<T>> edges, T firstVertex, T secondVertex) {
        if (edges.get(firstVertex) == null) {
            edges.put(firstVertex, new TreeSet<>(Collections.singletonList(secondVertex)));
        } else {
            edges.get(firstVertex).add(secondVertex);
        }
    }
}
