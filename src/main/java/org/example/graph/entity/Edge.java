package org.example.graph.entity;

import lombok.Value;

@Value
public class Edge<T> {
    T firstVertex;
    T secondVertex;
}
