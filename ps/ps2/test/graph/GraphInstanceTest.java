/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import java.util.Collections;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.junit.Test;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {
    
    // Testing strategy

    /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testInitialVerticesEmpty() {
        // you may use, change, or remove this test
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }
    
    // other tests for instance methods of Graph
    @Test
    public void testAdd() {
        Set<String> groundTruth = new HashSet<>();
        Graph<String> G = emptyInstance();
        groundTruth.add("aa");
        assertTrue("expect successfully add a new vertex 'aa' ", G.add("aa"));
        assertEquals("expect Graph contains vertex 'aa' ",
            groundTruth, G.vertices());

        groundTruth.add("bb");
        assertTrue("expect successfully add a new vertex 'bb' ", G.add("bb"));
        assertEquals("expect Graph contains vertex 'aa' and 'bb' ",
            groundTruth, G.vertices());

        assertFalse("expect failed to add a existing vertex 'aa' ", G.add("aa"));
        assertEquals("expect Graph contains vertex 'aa' and 'bb' ",
            groundTruth, G.vertices());
    }

    @Test
    public void testSet() {
        Set<String> groundTruth= new HashSet<>();
        Graph<String> G = emptyInstance();
        groundTruth.add("aa");
        groundTruth.add("bb");
        G.add("aa");
        G.add("bb");
        assertEquals("expect Graph contains vertex 'aa' and 'bb' ",
            groundTruth, G.vertices());

        assertEquals("expect previous weight = 0", 0, G.set("aa", "bb", 1));
        assertEquals("expect previous weight = 1", 1, G.set("aa", "bb", 0));
        assertEquals("expect previous weight = 0", 0, G.set("aa", "bb", 2));

        groundTruth.add("cc");
        assertEquals("expect previous weight = 0", 0, G.set("aa", "cc", 3));
        assertEquals("expect Graph has vertex 'cc' ",
            groundTruth, G.vertices());
    }

    @Test
    public void testRemove() {
        Set<String> groundTruth= new HashSet<>();
        Graph<String> G = emptyInstance();
        groundTruth.add("aa");
        groundTruth.add("bb");
        G.add("aa");
        G.add("bb");
        assertEquals("expect Graph contains vertex 'aa' and 'bb' ",
            groundTruth, G.vertices());

        assertTrue("expect successfully remove the vertex 'bb' ", G.remove("bb"));
        groundTruth.remove("bb");
        assertEquals("expect Graph only contains 'aa' after removing 'bb' ",
            groundTruth, G.vertices());

        assertFalse("expect failed to remove the non-existing vertex 'bb' ", G.remove("bb"));
        assertEquals("expect Graph only contains 'aa' after removing 'bb' ",
            groundTruth, G.vertices());
    }

    @Test
    public void testTargets() {
        Map<String, Integer> groundTruth = new HashMap<>();
        Graph<String> G = emptyInstance();
        groundTruth.put("bb", 1);
        groundTruth.put("cc", 2);
        groundTruth.put("dd", 3);
        G.add("aa");
        G.add("bb");
        G.add("cc");
        G.add("dd");
        G.set("aa", "bb", 1);
        G.set("aa", "cc", 2);
        G.set("aa", "dd", 3);
        assertEquals(groundTruth, G.targets("aa"));
    }

    @Test
    public void testSources() {
        Map<String, Integer> groundTruth = new HashMap<>();
        Graph<String> G = emptyInstance();
        groundTruth.put("bb", 1);
        groundTruth.put("cc", 2);
        groundTruth.put("dd", 3);
        G.add("aa");
        G.add("bb");
        G.add("cc");
        G.add("dd");
        G.set("bb", "aa", 1);
        G.set("cc", "aa", 2);
        G.set("dd", "aa", 3);
        assertEquals(groundTruth, G.sources("aa"));
    }
}
