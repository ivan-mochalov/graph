package org.example.graph.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {
    public static final String VERTEX_EXISTS_ERROR_MESSAGE = "The vertex already exists in the graph: %s";
    public static final String ADD_EDGE_UNSUPPORTED_ERROR_MESSAGE = "%s graph cannot contain edges";
    public static final String UNKNOWN_VERTEX_ERROR_MESSAGE = "The vertex '%s' does not exist in the graph";
    public static final String UNREACHABLE_VERTEX_ERROR_MESSAGE = "The vertex is unreachable (has no edges): %s";
}
