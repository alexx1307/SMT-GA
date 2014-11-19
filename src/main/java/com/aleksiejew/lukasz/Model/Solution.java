package com.aleksiejew.lukasz.Model;

import com.aleksiejew.lukasz.Algorithm.GeneticAlgorithm;

import java.util.List;

/**
 * Created by Luka on 2014-10-31.
 */
public class Solution implements Comparable<Solution> {
    private List<Point> steinerPoints;
    private Double evaluatedResult;
    private GeneticAlgorithm geneticAlgorithm;

    public Solution(List<Point> steinerPoints, GeneticAlgorithm geneticAlgorithm) {
        this.steinerPoints = steinerPoints;
        this.geneticAlgorithm = geneticAlgorithm;
    }

    public List<Point> getSteinerPoints() {
        return steinerPoints;
    }


    public double getEvaluatedResult() {
        if (evaluatedResult != null)
            return evaluatedResult;
        evaluatedResult = geneticAlgorithm.getResultEvaluator().evaluate(geneticAlgorithm, steinerPoints);
        return evaluatedResult;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\n");
        stringBuilder.append("RESULT = "+evaluatedResult+"\n");
        for (Point point : steinerPoints) {
            stringBuilder.append("\t" + point.toString() + "\n");
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    @Override
    public int compareTo(Solution o) {
        int comparisonResult = Double.compare(getEvaluatedResult(), o.getEvaluatedResult());
        if(comparisonResult!=0 )
            return comparisonResult;
        return Integer.compare(this.hashCode(),o.hashCode());
    }
}
