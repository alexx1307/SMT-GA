package com.aleksiejew.lukasz.Algorithm.MST;

import com.aleksiejew.lukasz.Algorithm.GeneticAlgorithm;
import com.aleksiejew.lukasz.Algorithm.Metrics;
import com.aleksiejew.lukasz.Algorithm.ResultEvaluator;
import com.aleksiejew.lukasz.Model.Point;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;

/**
 * Created by Luka on 2014-11-19.
 */
public class SimpleSpanningTreeEvaluator implements ResultEvaluator {
    @Override
    public EvaluationResult evaluate(GeneticAlgorithm geneticAlgorithm, SortedSet<Point> steinerPoints) {
        ArrayList<Point> points = new ArrayList<Point>(steinerPoints);
        points.addAll(geneticAlgorithm.getProblem().getTerminals());

        List<Edge> edges = createFullGraph(points, geneticAlgorithm.getMetrics());
        KruskalAlgorithm minimalSpanningTreeProblem = new KruskalAlgorithm(points, edges);
        List<Edge> spanningTree = minimalSpanningTreeProblem.findMinimalSpanningTree();
        List<ResolvedEdge> resolvedSpanningTree = resolve(spanningTree,points);
        return new EvaluationResult(resolvedSpanningTree, sumEdges(spanningTree));
    }

    private List<ResolvedEdge> resolve(List<Edge> spanningTree, ArrayList<Point> points) {
        List<ResolvedEdge> resolvedEdges= new LinkedList<ResolvedEdge>();
        for (Edge edge : spanningTree) {
            resolvedEdges.add(new ResolvedEdge(points.get(edge.p1), points.get(edge.p2)));
        }

        return resolvedEdges;
    }

    private double sumEdges(List<Edge> spanningTree) {
        double sum = 0;
        for (Edge edge : spanningTree) {
            sum += edge.dist;
        }
        return sum;
    }

    List<Edge> createFullGraph(ArrayList<Point> pointsArray, Metrics metrics) {
        List<Edge> edges = new LinkedList<Edge>();
        for (int i = 0; i < pointsArray.size(); i++) {
            for (int j = i + 1; j < pointsArray.size(); j++) {
                Edge e = new Edge(i, j, metrics.dist(pointsArray.get(i), pointsArray.get(j)));
                edges.add(e);
            }
        }
        return edges;
    }
}
