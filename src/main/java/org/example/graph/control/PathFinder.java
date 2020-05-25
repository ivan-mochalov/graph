package org.example.graph.control;

import lombok.NonNull;
import org.example.graph.entity.Graph;

import java.util.*;
import java.util.stream.Collectors;

import static org.example.graph.util.Constants.UNREACHABLE_VERTEX_ERROR_MESSAGE;

public class PathFinder {
    public <T> Optional<String> getPath(@NonNull Graph<T> graph, @NonNull T startVertex, @NonNull T endVertex) {
        checkVertexHasEdges(graph.getEdges(), startVertex);
        checkVertexHasEdges(graph.getEdges(), endVertex);

        List<T> path = findPath(graph, startVertex, endVertex);

        Optional<String> result = Optional.empty();
        if (!path.isEmpty()) {
            result = Optional.of(path.stream().map(Object::toString).collect(Collectors.joining("-")));
        }

        return result;
    }

    private <T> void checkVertexHasEdges(Map<T, Set<T>> edges, T vertex) {
        if (edges.get(vertex) == null) {
            throw new IllegalStateException(String.format(UNREACHABLE_VERTEX_ERROR_MESSAGE, vertex));
        }
    }

    private <T> List<T> findPath(Graph<T> graph, T startVertex, T endVertex) {
        Map<T, Set<T>> edges = graph.getEdges();
        List<T> path = new LinkedList<>();

        checkAdjacentVertices(path, edges, startVertex, endVertex);

        return path;
    }

    private <T> void checkAdjacentVertices(List<T> path, Map<T, Set<T>> edges, T startVertex, T endVertex) {
        path.add(startVertex);
        Set<T> edgesByVertex = edges.get(startVertex);

        if (edgesByVertex.contains(endVertex)) {
            path.add(endVertex);
        } else {
            edgesByVertex.forEach(e -> {
                if ((!path.contains(e) && (!path.contains(endVertex) || path.size() == 1))
                        || (startVertex == endVertex && e == endVertex)) {
                    checkAdjacentVertices(path, edges, e, endVertex);
                }
            });
        }

        if (!path.contains(endVertex)) {
            path.remove(startVertex);
        }
    }
}
