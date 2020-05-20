package org.example.graph.control;

import org.example.graph.entity.Graph;
import org.example.graph.entity.GraphType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GraphServiceTest {
    private GraphService service;
    private Graph<Integer> testIntGraph;
    private static final Integer DEFAULT_INT_VERTEX = 1;

    @Before
    public void setUp() {
        service = new GraphService();
        testIntGraph = createIntGraph();
    }

    @Test
    public void testAddVertex_success() {
        service.addVertex(testIntGraph, DEFAULT_INT_VERTEX);

        assertTrue(testIntGraph.getVertices().contains(DEFAULT_INT_VERTEX));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddVertex_the_same_vertex_two_times() {
        service.addVertex(testIntGraph, DEFAULT_INT_VERTEX);
        service.addVertex(testIntGraph, DEFAULT_INT_VERTEX);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddVertex_null_graph() {
        service.addVertex(null, DEFAULT_INT_VERTEX);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddVertex_null_vertex() {
        service.addVertex(testIntGraph, null);
    }

    private Graph<Integer> createIntGraph() {
        return new Graph<>(GraphType.UNDIRECTED);
    }
}