/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph implements Graph<String> {
    
    private final List<Vertex> vertices = new ArrayList<>();
    
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    // TODO constructor
    
    // TODO checkRep
    
    @Override public boolean add(String vertex) {
        throw new RuntimeException("not implemented");
    }
    
    @Override public int set(String source, String target, int weight) {
        throw new RuntimeException("not implemented");
    }
    
    @Override public boolean remove(String vertex) {
        throw new RuntimeException("not implemented");
    }
    
    @Override public Set<String> vertices() {
        throw new RuntimeException("not implemented");
    }
    
    @Override public Map<String, Integer> sources(String target) {
        throw new RuntimeException("not implemented");
    }
    
    @Override public Map<String, Integer> targets(String source) {
        throw new RuntimeException("not implemented");
    }
    
    // TODO toString()
    
}

/**
 * Mutable, labeled vertex with its targets in a positive weight directed Graph.
 * This class is internal to the rep of ConcreteVerticesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Vertex {
    
    // fields
    private final Map<String, Integer> targets = new HashMap<>();
    private String label;

    // Abstraction function:
    //   targets & label ==> vertex labelled with 'label' with all its targets and edge weight in 'targets'
    // Representation invariant:
    //   label != null, weight > 0
    // Safety from rep exposure:
    //   TODO
    
    // constructor

    /**
     * Create a new vertex.
     * @param label the label of the vertex
     */
    public Vertex(String label) {
        this.label = label;
    }

    /**
     * check the representation invariant of the vertex
     * label != null && weight > 0
     */
    private void checkRep() {
        assert label != null;
        for (int weight : targets.values()) {
            assert weight > 0;
        }
    }

    /**
     * Add, change or remove a weighted directed edge whose source is this vertex.
     * If weight is nonzero
     * @param label
     * @param weight
     * @return
     */
    public boolean set(String label, int weight) {
        return true;
    }
    
    // toString()
    @Override
    public String toString() {
        String out = "";
        for (Entry<String, Integer> entry : targets.entrySet()) {
            out += entry.getKey();
            out += " ==> ";
            out += entry.getValue();
            out += "\n";
        }
        return out;
    }
}
