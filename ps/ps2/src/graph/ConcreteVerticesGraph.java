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
public class ConcreteVerticesGraph<L> implements Graph<L> {
    
    private final List<Vertex<L>> vertices = new ArrayList<>();
    
    // Abstraction function:
    //   vertices => Graph with these vertices
    // Representation invariant:
    //   vertices != null
    // Safety from rep exposure:
    //   TODO

    public ConcreteVerticesGraph(){}

    @Override public boolean add(L vertex) {
        for (Vertex<L> v : vertices) {
            if (v.getLabel().equals(vertex))
                return false;
        }
        vertices.add(new Vertex<L>(vertex));
        return true;
    }
    
    @Override public int set(L source, L target, int weight) {
        boolean findSource = false;
        Vertex<L> s = null;
        for (Vertex<L> v : vertices) {
            if (v.getLabel().equals(source)) {
                findSource = true;
                s = v;
                break;
            }
        }
        if (!findSource) {
            s = new Vertex<L>(source);
            vertices.add(s);
        }
        return s.set(target, weight);
    }
    
    @Override public boolean remove(L vertex) {
        int idx;
        for (idx = 0; idx < vertices.size(); idx++) {
            if (vertices.get(idx).getLabel().equals(vertex)) {
                break;
            }
        }
        if (idx == vertices.size()) return false;
        vertices.remove(idx);
        for (Vertex<L> v : vertices) {
            if (v.targetAt(vertex)) {
                v.set(vertex, 0);
            }
        }
        return true;
    }
    
    @Override public Set<L> vertices() {
        Set<L> s = new HashSet<>();
        for (Vertex<L> v : vertices) {
            s.add(v.getLabel());
        }
        return s;
    }
    
    @Override public Map<L, Integer> sources(L target) {
        Map<L, Integer> s = new HashMap<>();
        for (Vertex<L> v : vertices) {
            if (v.targetAt(target)) {
                s.put(v.getLabel(), v.targetWeight(target));
            }
        }
        return s;
    }
    
    @Override public Map<L, Integer> targets(L source) {
        Map<L, Integer> t = new HashMap<>();
        Vertex<L> sourceV = null;
        for (Vertex<L> v : vertices) {
            if (v.getLabel().equals(source)){
                sourceV = v;
                break;
            }
        }
        if (sourceV == null) return t;
        Set<L> targetLabels = sourceV.getTargets();
        for (L target : targetLabels) {
            t.put(target, sourceV.targetWeight(target));
        }
        return t;
    }

    @Override
    public String toString() {
        String out = "";
        for (Vertex<L> v : vertices) {
            out += v.toString();
        }
        return out;
    }

}

/**
 * Mutable, labeled vertex with its targets in a positive weight directed Graph.
 * This class is internal to the rep of ConcreteVerticesGraph.
 *
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Vertex <L>{
    
    // fields
    private final Map<L, Integer> targets = new HashMap<>();
    private L label;

    // Abstraction function:
    //   targets & label ==> vertex labelled with 'label' with all its targets and edge weight in 'targets'
    // Representation invariant:
    //   label != null, weight > 0
    // Safety from rep exposure:
    //   only the set will change the representation, and we checkRep at the end of set.
    
    // constructor

    /**
     * Create a new vertex.
     * @param label the label of the vertex
     */
    public Vertex(L label) {
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
     * If weight is nonzero, add a new vertex into targets or update the weight of the edge
     * to the targeting vertex;
     * Vertices with the given labels are added to the targets if they do not
     * already exist.
     * If weight is zero, remove the vertex from targets if it exists.
     * @param label the label of the target vertex
     * @param weight nonnegative weight of the edge
     * @throws IllegalArgumentException when weight is negative
     * @return the previous weight of the edge, or zero if there is no such edge
     */
    public int set(L label, int weight) {
        if (weight < 0) {
            throw new IllegalArgumentException("weight must be nonnegative");
        }
        int preWeight = 0;
        if (targets.containsKey(label)) {
            preWeight = targets.get(label);
        }

        if (weight == 0) {
            if (targets.containsKey(label)) {
                targets.remove(label);
                return preWeight;
            }
        }

        targets.put(label, weight);
        checkRep();
        return preWeight;
    }

    /**
     * @return the label of this vertex.
     */
    public L getLabel() {
        return label;
    }

    /**
     * Check if there exists a directed edge from this vertex to the target
     * @param target the label of the target vertex
     * @return true if there exists such edge and vice versa.
     */
    public boolean targetAt(L target){
        return targets.containsKey(target);
    }

    /**
     * Get the weight of the edge from this vertex to target
     * @param target the label of the target vertex
     * @return zero if the target is not in the targets, or
     * the weight of the edge from this vertex to target.
     */
    public int targetWeight(L target) {
        if (!targets.containsKey(target)) {
            return 0;
        }
        return targets.get(target);
    }

    /**
     * @return the set of the vertice which are directed from this vertex
     */
    public Set<L> getTargets() {
        return targets.keySet();
    }

    @Override
    public String toString() {
        String out = "";
        for (Entry<L, Integer> entry : targets.entrySet()) {
            out += label;
            out += " ==> ";
            out += entry.getKey();
            out += " : ";
            out += entry.getValue();
            out += "\n";
        }
        return out;
    }
}
