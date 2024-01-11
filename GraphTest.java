import static org.junit.Assert.*;

import java.io.*;

import org.junit.*;

public class GraphTest {

    private Graph<String, Integer> graph;

    @Before
    public void setUp() {
        graph = new Graph<>();
    }

    @Test
    public void testAddNode() {
        assertTrue(graph.addNode("A", 1));
        assertFalse(graph.addNode("A", 2));
        assertTrue(graph.addNode("B", 2));
    }

    @Test
    public void testAddNodes() {
        String[] names = {"A", "B", "C"};
        Integer[] data = {1, 2, 3};
        assertTrue(graph.addNodes(names, data));
        assertFalse(graph.addNodes(names, data));
        assertEquals(3, graph.getNodes().size());
    }

    @Test
    public void testAddEdge() {
        graph.addNode("A", 1);
        graph.addNode("B", 2);
        assertTrue(graph.addEdge("A", "B"));
        assertFalse(graph.addEdge("A", "B"));
        assertFalse(graph.addEdge("C", "D"));
    }

    @Test
    public void testAddEdges() {
        graph.addNode("A", 1);
        graph.addNode("B", 2);
        graph.addNode("C", 3);
        assertTrue(graph.addEdges("A", "B", "C"));
        assertFalse(graph.addEdges("A", "B", "C"));
        assertFalse(graph.addEdges("A", "D"));
    }

    @Test
    public void testRemoveNode() {
        graph.addNode("A", 1);
        graph.addNode("B", 2);
        graph.addEdge("A", "B");
        assertTrue(graph.removeNode("A"));
        assertFalse(graph.getNodes().contains(graph.getNode("A")));
        assertFalse(graph.getNodes().get(0).neighbors.contains(graph.getNode("A")));
    }

    @Test
    public void testRemoveNodes() {
        graph.addNode("A", 1);
        graph.addNode("B", 2);
        graph.addNode("C", 3);
        assertTrue(graph.removeNodes("A", "B"));
        assertFalse(graph.getNodes().contains(graph.getNode("A")));
        assertFalse(graph.getNodes().contains(graph.getNode("B")));
    }

    @Test
    public void testPrintGraph() {
        graph.addNode("A", 1);
        graph.addNode("B", 2);
        graph.addNode("C", 2);
        graph.addNode("D", 2);
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("A", "D");
        graph.addEdge("B", "C");
        graph.printGraph();
    }

    
    @Test
    public void testRead() throws IOException {
        Graph<String, String> graph = Graph.read("test.txt");

        // Test adding nodes
        assertTrue(graph.getNode("A") != null);
        assertTrue(graph.getNode("B") != null);
        assertTrue(graph.getNode("C") != null);
        assertTrue(graph.getNode("D") != null);
        assertTrue(graph.getNode("E") != null);
        assertTrue(graph.getEdge(graph.getNode("A"), graph.getNode("B")));
        assertTrue(graph.getEdge(graph.getNode("A"), graph.getNode("C")));
        assertTrue(graph.getEdge(graph.getNode("B"), graph.getNode("C")));
        assertTrue(graph.getEdge(graph.getNode("D"), graph.getNode("E")));
        assertTrue(graph.getNode("F") == null);
        assertTrue(graph.getNode("G") == null);
        assertFalse(graph.getEdge(graph.getNode("A"), graph.getNode("E")));
        assertFalse(graph.getEdge(graph.getNode("B"), graph.getNode("E")));
    }
    
    
    @Test
    public void testDFS() {
        graph.addNode("A", 1);
        graph.addNode("B", 2);
        graph.addNode("C", 3);
        graph.addNode("D", 4);
        graph.addNode("E", 5);
    	graph.addEdge("A", "B");
    	graph.addEdge("B", "C");
    	graph.addEdge("B", "D");
    	graph.addEdge("D", "E");
    	graph.addEdge("C", "E");
        String[] expected = {"A", "B", "D", "E"};
        assertArrayEquals(expected, graph.DFS("A", "E"));
        
        String[] expected2 = {"B", "D", "E"};
        assertArrayEquals(expected2, graph.DFS("B", "E"));
        
        assertNull(graph.DFS("A", "F"));
        assertNull(graph.DFS("F", "A"));
    }


    @Test
    public void testBFS() {
        Graph<String, Integer> graph = new Graph<>();
        graph.addNode("A", 1);
        graph.addNode("B", 2);
        graph.addNode("C", 3);
        graph.addNode("D", 4);
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");
        graph.addEdge("A", "D");
        
        String[] expected = {"A", "D"};
        assertArrayEquals(expected, graph.BFS("A", "D"));
        
        String[] expected2 = {"B", "A", "D"};
        assertArrayEquals(expected2, graph.BFS("B","D"));
        
        assertNull(graph.BFS("A", "F"));
        assertNull(graph.BFS("F", "A"));
    }
    
    
    
}
