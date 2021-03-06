package org.example.graph.entity;

import lombok.Data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
public class Graph<T> {
    private final Set<T> vertices = new HashSet<>();
    private final Map<T, Set<T>> edges = new HashMap<>();
    private final Type graphType;

    public enum Type {
        DIRECTED,
        UNDIRECTED
    }
}
