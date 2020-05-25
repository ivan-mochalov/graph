package org.example.graph.boundary;

import org.example.graph.control.GraphService;
import org.example.graph.control.PathFinder;
import org.example.graph.entity.Graph;

import java.util.Optional;

/**
 * The graph library API.
 */
public class G {
    /**
     * The method creates new directed graph.
     *
     * @param <T> user defined vertex type
     * @return new graph object
     */
    public <T> Graph<T> createDirectedGraph() {
        return new Graph<>(Graph.Type.DIRECTED);
    }

    /**
     * The method creates new undirected graph.
     *
     * @param <T> user defined vertex type
     * @return new graph object
     */
    public <T> Graph<T> createUndirectedGraph() {
        return new Graph<>(Graph.Type.UNDIRECTED);
    }

    /**
     * The method adds new vertex to the graph.
     * Null parameters are not supported.
     *
     * @param graph  graph object. you can creat one with {@link #createDirectedGraph()} or {@link #createUndirectedGraph()}
     * @param vertex new vertex
     * @param <T>    user defined vertex type
     */
    public <T> void addVertex(Graph<T> graph, T vertex) {
        new GraphService().addVertex(graph, vertex);
    }

    /**
     * The method adds new edge to the graph. Be aware the graph must contain the vertices you enter as edge.
     * Null values are not supported.
     * In case of directed graph, the direction means from the first vertex to the second one.
     * For undirected graph, the both of edges 'A-B' and 'B-A' are correct in such case.
     * Null parameters are not supported.
     *
     * @param graph        graph object. you can creat one with {@link #createDirectedGraph()} or {@link #createUndirectedGraph()}
     * @param firstVertex  first vertex
     * @param secondVertex second vertex
     * @param <T>          user defined vertex type
     */
    public <T> void addEdge(Graph<T> graph, T firstVertex, T secondVertex) {
        new GraphService().addEdge(graph, firstVertex, secondVertex);
    }

    /**
     * The method return the list of edges represented as a string. Return value like 'A-B-C' means two edges:
     * 'A-B' and 'B-C'. So you can imagine the '-' is an edge. In case of the end vertex cannot be reached you'll get empty value.
     * Null parameters are not supported.
     *
     * @param graph       graph object. you can creat one with {@link #createDirectedGraph()} or {@link #createUndirectedGraph()}
     * @param startVertex the vertex where the path starts
     * @param endVertex   the vertex where the path ends
     * @param <T>         user defined vertex type
     * @return The path of edges represented as a {@link String} and wrapped to the {@link Optional}
     */
    public <T> Optional<String> getPath(Graph<T> graph, T startVertex, T endVertex) {
        return new PathFinder().getPath(graph, startVertex, endVertex);
    }
}
