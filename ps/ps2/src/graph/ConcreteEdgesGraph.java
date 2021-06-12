/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph<L> implements Graph<L> {
    
    private final Set<L> vertices = new HashSet<>();
    private final List<Edge<L>> edges = new ArrayList<>();
    
    // Abstraction function:
    //   edges with positive weight, vertices -> a Graph with vertices and edges
    // Representation invariant:
    //   edges's froms + edges's tos in vertices
    //   edges's weight >= 0
    // Safety from rep exposure:
    //
    
    // constructor
    public ConcreteEdgesGraph(){}

    // checkRep
    private void checkRep() {
        Set<L> edgeVertices = new HashSet<>();
        for (Edge<L> edge : edges) {
            assert edge.getWeight() >= 0;
            edgeVertices.add(edge.getFrom());
            edgeVertices.add(edge.getTo());
        }
        assert vertices.containsAll(edgeVertices);
    }

    @Override public boolean add(L vertex) {
        if (vertices.contains(vertex)) return false;
        vertices.add(vertex);
        // checkRep
        checkRep();
        return true;
    }
    
    @Override public int set(L source, L target, int weight) {
        if (weight < 0) {
            throw new IllegalArgumentException("weight must be non-negative value");
        }

        int idx;
        if (!vertices.contains(source)) {
            // source does not exist
            vertices.add(source);
        }
        if (!vertices.contains(target)) {
            // target does not exist
            vertices.add(target);
        }

        // check if edge exists
        for (idx = 0; idx < edges.size(); idx++) {
            if (edges.get(idx).getFrom().equals(source) && edges.get(idx).getTo().equals(target)) {
                break;
            }
        }

        // the edge does not exist
        if (idx == edges.size() || idx == -1) {
            edges.add(new Edge(source, target, weight));
            return 0;
        }

        // the edge exists
        int preWeight = edges.get(idx).getWeight();
        edges.set(idx, new Edge(source, target, weight));

        // check Rep
        checkRep();
        return preWeight;
    }
    
    @Override public boolean remove(L vertex) {
        if (!vertices.contains(vertex)) {
            return false;
        }
        vertices.remove(vertex);
        List<Integer> removeEdges = new ArrayList<>();
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).getFrom().equals(vertex) || edges.get(i).getTo().equals(vertex)) {
                removeEdges.add(i);
            }
        }
        for (int idx : removeEdges) {
            Edge<L> edge = edges.get(idx);
            edges.set(idx, new Edge(edge.getFrom(), edge.getTo(), 0));
        }
        return true;
    }
    
    @Override public Set<L> vertices() {
        Set<L> copy = new HashSet<>();
        for (L vertice : vertices) {
            copy.add(vertice);
        }
        return copy;
    }
    
    @Override public Map<L, Integer> sources(L target) {
        Map<L, Integer> froms = new HashMap<>();
        for (Edge<L> edge : edges) {
            if (edge.getTo().equals(target) && edge.getWeight() > 0) {
                froms.put(edge.getFrom(), edge.getWeight());
            }
        }
        return froms;
    }
    
    @Override public Map<L, Integer> targets(L source) {
        Map<L, Integer> tos = new HashMap<>();
        for (Edge<L> edge : edges) {
            if (edge.getFrom().equals(source) && edge.getWeight() > 0) {
                tos.put(edge.getTo(), edge.getWeight());
            }
        }
        return tos;
    }

    @Override public String toString() {
        String output = "";
        for (Edge<L> edge : edges) {
            if (edge.getWeight() == 0) continue;
            output += edge.toString();
            output += "\n";
        }
        return output;
    }

}

/**
 * Immutable edge with non-negative weight of type {@code int}.
 * This class is internal to the rep of ConcreteEdgesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Edge<L>{
    
    // fields
    private L from, to;
    private int weight;
    
    // Abstraction function:
    //   Edge(from, to, weight) -> an directed edge from 'from' to 'to' with non-negative weight
    // Representation invariant:
    //   from != null, to != null, weight >= 0
    // Safety from rep exposure:
    //   all the public function is immutable.
    
    // constructor
    public Edge(L from, L to, int w) {
        if (from == null || to == null || w < 0) {
            throw new IllegalArgumentException("from and to can not be null and w must be non-negative!");
        }
        this.from = from;
        this.to = to;
        weight = w;
        checkRep();
    }
    
    // checkRep
    private void checkRep() {
        assert from != null && to != null && weight >= 0;
    }
    
    // methods
    public L getFrom() {
        return from;
    }

    public L getTo() {
        return to;
    }

    public int getWeight() {
        return weight;
    }

    // toString()
    @Override
    public String toString() {
        return from + " ==> " + to + " : " + weight;
    }
}
