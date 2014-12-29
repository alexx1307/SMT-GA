package com.aleksiejew.lukasz.Model;

import com.aleksiejew.lukasz.Algorithm.GeneticAlgorithm;
import com.aleksiejew.lukasz.Algorithm.MST.Edge;
import com.aleksiejew.lukasz.Algorithm.MST.EvaluationResult;
import com.aleksiejew.lukasz.Algorithm.MST.ResolvedEdge;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;

/**
 * Created by Luka on 2014-10-31.
 */
public class Solution implements Comparable<Solution> {
    private SortedSet<Point> steinerPoints;
    private EvaluationResult evaluatedResult;

    private GeneticAlgorithm geneticAlgorithm;

    public Solution(SortedSet<Point> steinerPoints, GeneticAlgorithm geneticAlgorithm) {
        this.steinerPoints = steinerPoints;
        this.geneticAlgorithm = geneticAlgorithm;
    }

    public SortedSet<Point> getSteinerPoints() {
        return steinerPoints;
    }


    public GeneticAlgorithm getGeneticAlgorithm() {
        return geneticAlgorithm;
    }

    public EvaluationResult getEvaluatedResult() {
        if (evaluatedResult != null)
            return evaluatedResult;
        evaluatedResult = geneticAlgorithm.getResultEvaluator().evaluate(geneticAlgorithm, steinerPoints);
        return evaluatedResult;
    }

    public void setEvaluatedResult(EvaluationResult evaluatedResult) {
        this.evaluatedResult = evaluatedResult;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\n");
        stringBuilder.append("RESULT = " + getEvaluatedResult().getCostValue() + "\n");
       /* for (Point point : steinerPoints) {
            stringBuilder.append("\t" + point.toString() + "\n");
        }*/
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    @Override
    public int compareTo(Solution o) {
        int comparisonResult = Double.compare(getEvaluatedResult().getCostValue(), o.getEvaluatedResult().getCostValue());
        if (comparisonResult != 0)
            return comparisonResult;
        if (getSteinerPoints().equals(o.getSteinerPoints()) == true)
            return 0;
        return Integer.compare(this.hashCode(), o.hashCode());
    }

    public List<Point> getConnectedPointsInSpanningTree(Point point) {
        List<ResolvedEdge> spanningTree =getEvaluatedResult().getSpanningTree();
        List<Point> connectedPoints = new LinkedList<Point>();
        for (ResolvedEdge edge : spanningTree) {
            if (edge.getP1().equals(point))
                connectedPoints.add(edge.getP2());
            else if(edge.getP2().equals(point))
                connectedPoints.add(edge.getP1());
        }
        return connectedPoints;
    }
}
