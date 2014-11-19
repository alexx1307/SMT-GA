package com.aleksiejew.lukasz.Algorithm.MST;

import com.aleksiejew.lukasz.Algorithm.Metrics;
import com.aleksiejew.lukasz.Model.Point;
import com.sun.javafx.collections.transformation.SortedList;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Luka on 2014-11-19.
 */
public class KruskalAlgorithm {
    private ArrayList<Point> points;
    private SortedSet<Edge> sortedEdges;
    private List<Edge> tree;

    private UnionFindStructure forest;

    public KruskalAlgorithm(ArrayList<Point> points, List<Edge> edges) {
        this.points = points;
        this.sortedEdges = new TreeSet<Edge>(edges);
        tree = new LinkedList<Edge>();
        forest = new UnionFindStructure(points.size());
    }

    public List<Edge> findMinimalSpanningTree() {
        tree.clear();
        Iterator<Edge> iterator = sortedEdges.iterator();
        while(tree.size()< points.size()-1){
            addNewEdge(iterator);

        }
        return tree;
    }

    private void addNewEdge(Iterator<Edge> it) {
        Edge shortestEdge = it.next();
        while(!isEdgeConnectingTwoSeparateTrees(shortestEdge)){
            shortestEdge = it.next();
        }
        tree.add(shortestEdge);
        addEdgeToForest(shortestEdge);
    }

    private void addEdgeToForest(Edge shortestEdge) {
        forest.union(shortestEdge.p1,shortestEdge.p2);
    }


    private boolean isEdgeConnectingTwoSeparateTrees(Edge edge) {
        return forest.connected(edge.p1,edge.p2);
    }
}
