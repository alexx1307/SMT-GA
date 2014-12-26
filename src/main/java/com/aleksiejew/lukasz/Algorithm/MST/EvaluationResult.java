package com.aleksiejew.lukasz.Algorithm.MST;

import java.util.List;

/**
 * Created by Luka on 2014-12-27.
 */
public class EvaluationResult {
    List<ResolvedEdge> spanningTree;
    double costValue;

    public List<ResolvedEdge> getSpanningTree() {
        return spanningTree;
    }

    public double getCostValue() {
        return costValue;
    }

    public EvaluationResult(List<ResolvedEdge> spanningTree, double v) {
        this.spanningTree = spanningTree;
        this.costValue = v;

    }
}
