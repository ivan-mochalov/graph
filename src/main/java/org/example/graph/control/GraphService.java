package org.example.graph.control;

import lombok.NonNull;
import org.example.graph.entity.Graph;

public class GraphService {

    public <T> void addVertex(@NonNull Graph<T> graph, @NonNull T vertex) {
        if (graph.getVertices().contains(vertex)) {
            throw new IllegalArgumentException(String.format("The vertex already exists in the graph: %s", vertex));
        } else {
            graph.getVertices().add(vertex);
        }
    }
}
