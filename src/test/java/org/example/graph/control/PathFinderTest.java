package org.example.graph.control;

import org.example.graph.entity.Graph;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PathFinderTest {
    private PathFinder pathFinder;
    private GraphService graphService;

    @Before
    public void setUp() {
        pathFinder = new PathFinder();
        graphService = new GraphService();
    }

    @Test
    public void testGetPath_directed_success() {
        Graph<String> graph = new Graph<>(Graph.Type.DIRECTED);
        graphService.addVertex(graph, "abc");
        graphService.addVertex(graph, "bcd");
        graphService.addVertex(graph, "cde");
        graphService.addVertex(graph, "def");
        graphService.addVertex(graph, "efg");
        graphService.addVertex(graph, "fgh");
        graphService.addEdge(graph, "abc", "bcd");
        graphService.addEdge(graph, "bcd", "def");
        graphService.addEdge(graph, "def", "abc");
        graphService.addEdge(graph, "bcd", "efg");
        graphService.addEdge(graph, "efg", "fgh");
        graphService.addEdge(graph, "fgh", "def");
        graphService.addEdge(graph, "fgh", "cde");
        graphService.addEdge(graph, "cde", "efg");

        String path = pathFinder.getPath(graph, "abc", "cde").orElseThrow(IllegalStateException::new);

        assertTrue(path.startsWith("abc-"));
        assertTrue(path.endsWith("-cde"));
    }

    @Test
    public void testGetPath_undirected_success() {
        Graph<String> graph = new Graph<>(Graph.Type.UNDIRECTED);
        graphService.addVertex(graph, "abc");
        graphService.addVertex(graph, "bcd");
        graphService.addVertex(graph, "cde");
        graphService.addVertex(graph, "def");
        graphService.addVertex(graph, "efg");
        graphService.addVertex(graph, "fgh");
        graphService.addEdge(graph, "abc", "bcd");
        graphService.addEdge(graph, "abc", "def");
        graphService.addEdge(graph, "bcd", "def");
        graphService.addEdge(graph, "bcd", "efg");
        graphService.addEdge(graph, "def", "fgh");
        graphService.addEdge(graph, "efg", "cde");
        graphService.addEdge(graph, "efg", "fgh");

        String path = pathFinder.getPath(graph, "bcd", "fgh").orElseThrow(IllegalStateException::new);

        assertTrue(path.startsWith("bcd-"));
        assertTrue(path.endsWith("-fgh"));
    }

    @Test
    public void testGetPath_undirected_same_vertex() {
        Graph<String> graph = new Graph<>(Graph.Type.UNDIRECTED);
        graphService.addVertex(graph, "abc");
        graphService.addVertex(graph, "bcd");
        graphService.addVertex(graph, "def");
        graphService.addEdge(graph, "abc", "bcd");
        graphService.addEdge(graph, "abc", "def");
        graphService.addEdge(graph, "bcd", "def");

        String path = pathFinder.getPath(graph, "abc", "abc").orElseThrow(IllegalStateException::new);

        assertTrue(path.startsWith("abc-"));
        assertTrue(path.endsWith("-abc"));
    }

    @Test
    public void testGetPath_undirected_loop() {
        Graph<String> graph = new Graph<>(Graph.Type.UNDIRECTED);
        graphService.addVertex(graph, "abc");
        graphService.addVertex(graph, "bcd");
        graphService.addVertex(graph, "def");
        graphService.addEdge(graph, "abc", "abc");
        graphService.addEdge(graph, "abc", "bcd");
        graphService.addEdge(graph, "abc", "def");
        graphService.addEdge(graph, "bcd", "def");

        String path = pathFinder.getPath(graph, "abc", "abc").orElseThrow(IllegalStateException::new);

        assertTrue(path.startsWith("abc-"));
        assertTrue(path.endsWith("-abc"));
    }

    @Test
    public void testGetPath_undirected_unreachable() {
        Graph<String> graph = new Graph<>(Graph.Type.UNDIRECTED);
        graphService.addVertex(graph, "abc");
        graphService.addVertex(graph, "bcd");
        graphService.addVertex(graph, "cde");
        graphService.addVertex(graph, "def");
        graphService.addVertex(graph, "efg");
        graphService.addVertex(graph, "fgh");
        graphService.addEdge(graph, "abc", "bcd");
        graphService.addEdge(graph, "abc", "def");
        graphService.addEdge(graph, "bcd", "def");
        graphService.addEdge(graph, "efg", "cde");
        graphService.addEdge(graph, "efg", "fgh");

        Optional<String> path = pathFinder.getPath(graph, "abc", "fgh");

        assertFalse(path.isPresent());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetPath_graph_null() {
        pathFinder.getPath(null, "abc", "fgh");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetPath_first_vertex_null() {
        pathFinder.getPath(new Graph<>(Graph.Type.UNDIRECTED), null, "fgh");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetPath_second_vertex_null() {
        pathFinder.getPath(new Graph<>(Graph.Type.UNDIRECTED), "abc", null);
    }
}