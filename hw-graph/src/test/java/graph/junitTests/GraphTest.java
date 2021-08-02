package graph.junitTests;

import org.junit.Rule;
import org.junit.rules.Timeout;

import org.junit.*;
import static org.junit.Assert.*;
import graph.*;



public class GraphTest {
    private Graph g1;
    @Rule public Timeout globalTimeout = Timeout.seconds(10);
// 10 seconds max per method tested
    @Before
    public void setUp(){


        g1 = new Graph();
    }
    @Test
    public void isPresentTest() {
        g1.addNode("n1");
        assertTrue(g1.isPresent("n1"));
        assertFalse(g1.isPresent("n2"));
    }
    @Test(expected=IllegalArgumentException.class)
    public void isPresentNullExceptionTest() {
        g1.isPresent(null);
    }
    @Test
    public void popNodeTest() {
        g1.addNode("n1");

        assertFalse(g1.popNode("n2"));
        assertTrue(g1.isPresent("n1"));
        assertTrue(g1.popNode("n1"));
        assertFalse(g1.isPresent("n1"));
    }
    @Test(expected=IllegalArgumentException.class)
    public void popNodeNullExceptionTest() {
        g1.popNode(null);
    }
    @Test
    public void isEdgeTest() {
        g1.addNode("n1");
        g1.addNode("n2");

        g1.addEdge("n1","e1","n2");

        assertTrue(g1.isEdge("n1","e1","n2"));
        assertFalse(g1.isEdge("n2","e1","n1"));
    }
    @Test(expected=IllegalArgumentException.class)
    public void isEdgeNullExceptionTest() {
        g1.addNode("n1");
        g1.addNode("n2");
        g1.addEdge("n1","e1","n2");

        g1.isEdge(null,"e1","n2");
    }
    @Test
    public void popEdgeTest() {
        g1.addNode("n1");
        g1.addNode("n2");

        g1.addEdge("n1","e1","n2");

        assertFalse(g1.popEdge("n2","e1","n1"));
        assertTrue(g1.isEdge("n1","e1","n2"));
        assertTrue(g1.popEdge("n1","e1","n2"));
        assertFalse(g1.isEdge("n1","e1","n2"));
    }
    @Test(expected=IllegalArgumentException.class)
    public void popEdgeNullExceptionTest() {
        g1.addNode("n1");
        g1.addNode("n2");
        g1.addEdge("n1","e1","n2");

        g1.popEdge("poolTable","e1",null);
    }
}