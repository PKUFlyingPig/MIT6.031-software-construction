/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import org.junit.Test;

/**
 * Tests for static methods of Graph.
 * 
 * To facilitate testing multiple implementations of Graph, instance methods are
 * tested in GraphInstanceTest.
 */
public class GraphStaticTest {
    
    // Testing strategy
    //   empty()
    //     no inputs, only output is empty graph
    //     observe with vertices()
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testEmptyVerticesEmpty() {
        assertEquals("expected empty() graph to have no vertices",
                Collections.emptySet(), Graph.empty().vertices());
    }
    
    //test other vertex label types in Problem 3.2
    @Test
    public void testOtherLabelTypes() {
        Graph<Integer> G1 = Graph.empty();
        G1.add(1);
        G1.set(1, 2, 3);

        Graph<Float> G2 = Graph.empty();
        G2.add(3.14f);
        G2.set(3.14f, 3.15f, 3);
    }
    
}
