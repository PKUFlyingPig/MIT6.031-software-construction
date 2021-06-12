/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph();
    }
    
    /*
     * Testing ConcreteVerticesGraph...
     */
    
    // tests for ConcreteVerticesGraph.toString()

    @Test
    public void testGraphToString() {
        Graph<String> G = emptyInstance();
        G.set("aa", "bb", 3);
        G.set("aa", "cc", 2);
        assertEquals("aa ==> bb : 3\naa ==> cc : 2\n", G.toString());
    }
    
    /*
     * Testing Vertex...
     */
    // tests for operations of Vertex

    @Test
    public void testSet() {
        Set<String> groundTruth = new HashSet<>();
        Vertex vertex = new Vertex("aa");

        // set a new vertex
        assertEquals(0, vertex.set("bb", 3));
        groundTruth.add("bb");
        assertEquals(groundTruth, vertex.getTargets());

        // set weight = 0, i.e., remove the edge
        assertEquals(3, vertex.set("bb", 0));
        groundTruth.remove("bb");
        assertEquals(groundTruth, vertex.getTargets());

        // set weight > 0
        assertEquals(0, vertex.set("bb", 2));
        groundTruth.add("bb");
        assertEquals(groundTruth, vertex.getTargets());
    }

    @Test
    public void testObserver() {
        Vertex vertex = new Vertex("aa");
        assertEquals("aa", vertex.getLabel());
        vertex.set("bb", 1);
        assertTrue(vertex.targetAt("bb"));
        assertFalse(vertex.targetAt("cc"));
        assertEquals(1, vertex.targetWeight("bb"));
    }

    @Test
    public void testVertexToString() {
        Vertex vertex = new Vertex("aa");
        vertex.set("bb", 1);
        vertex.set("cc", 2);
        assertEquals("aa ==> bb : 1\naa ==> cc : 2\n", vertex.toString());
    }
}
