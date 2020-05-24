package org.example.graph.control;

import org.example.graph.entity.Graph;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GraphServiceTest {
    private GraphService service;
    private Graph<Integer> testGraph;
    private static final Integer DEFAULT_INT_VERTEX = 1;

    @Before
    public void setUp() {
        service = new GraphService();
        testGraph = createIntDirectedGraph();
    }

    @Test
    public void testAddVertex_success() {
        service.addVertex(testGraph, DEFAULT_INT_VERTEX);

        assertTrue(testGraph.getVertices().contains(DEFAULT_INT_VERTEX));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddVertex_the_same_vertex_two_times() {
        service.addVertex(testGraph, DEFAULT_INT_VERTEX);
        service.addVertex(testGraph, DEFAULT_INT_VERTEX);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddVertex_null_graph() {
        service.addVertex(null, DEFAULT_INT_VERTEX);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddVertex_null_vertex() {
        service.addVertex(testGraph, null);
    }

    @Test
    public void testAddEdge_directed_success() {
        int firstVertex = 1;
        int secondVertex = 2;
        service.addVertex(testGraph, firstVertex);
        service.addVertex(testGraph, secondVertex);

        service.addEdge(testGraph, firstVertex, secondVertex);

        assertEquals(Graph.Type.DIRECTED, testGraph.getGraphType());
        assertFalse(testGraph.getEdges().get(firstVertex).isEmpty());
        assertNull(testGraph.getEdges().get(secondVertex));
        assertTrue(testGraph.getEdges().get(firstVertex).contains(secondVertex));
    }

    @Test
    public void testAddEdge_undirected_success() {
        int firstVertex = 1;
        int secondVertex = 2;
        testGraph = createIntUndirectedGraph();
        service.addVertex(testGraph, firstVertex);
        service.addVertex(testGraph, secondVertex);

        service.addEdge(testGraph, firstVertex, secondVertex);

        assertEquals(Graph.Type.UNDIRECTED, testGraph.getGraphType());
        assertFalse(testGraph.getEdges().get(firstVertex).isEmpty());
        assertFalse(testGraph.getEdges().get(secondVertex).isEmpty());
        assertTrue(testGraph.getEdges().get(firstVertex).contains(secondVertex));
        assertTrue(testGraph.getEdges().get(secondVertex).contains(firstVertex));
    }

    @Test
    public void testAddEdge_same_edge() {
        int firstVertex = 1;
        int secondVertex = 2;
        int entryNumber = 1;
        service.addVertex(testGraph, firstVertex);
        service.addVertex(testGraph, secondVertex);

        service.addEdge(testGraph, firstVertex, secondVertex);
        service.addEdge(testGraph, firstVertex, secondVertex);

        assertEquals(entryNumber, testGraph.getEdges().keySet().size());
        assertEquals(entryNumber, testGraph.getEdges().get(firstVertex).size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddEdge_vertex_not_exists() {
        int firstVertex = 1;
        int secondVertex = 2;
        testGraph = createIntUndirectedGraph();
        service.addVertex(testGraph, firstVertex);

        service.addEdge(testGraph, firstVertex, secondVertex);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddEdge_null_graph() {
        service.addEdge(null, DEFAULT_INT_VERTEX, DEFAULT_INT_VERTEX);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddEdge_null_first_vertex() {
        service.addEdge(testGraph, null, DEFAULT_INT_VERTEX);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddEdge_null_second_vertex() {
        service.addEdge(testGraph, DEFAULT_INT_VERTEX, null);
    }

    private Graph<Integer> createIntDirectedGraph() {
        return new Graph<>(Graph.Type.DIRECTED);
    }

    private Graph<Integer> createIntUndirectedGraph() {
        return new Graph<>(Graph.Type.UNDIRECTED);
    }

}