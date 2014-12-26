package com.aleksiejew.lukasz.Algorithm.GeneticOperators;

import com.aleksiejew.lukasz.Algorithm.Geometry.LocalOptimization;
import com.aleksiejew.lukasz.Model.Point;
import com.aleksiejew.lukasz.Model.Solution;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by Luka on 2014-12-27.
 */
public class TriangleOptimizationMutation implements Mutation {
    @Override
    public Solution mutate(Solution solution) {
        SortedSet<Point> steinerPoints = new TreeSet<Point>();
        for (Point point : solution.getSteinerPoints()) {
            List<Point> connectedPoints = solution.getConnectedPointsInSpanningTree(point);
            if (connectedPoints.size() == 3) {
                steinerPoints.add(LocalOptimization.fermatPoint(
                        connectedPoints.get(0),
                        connectedPoints.get(1),
                        connectedPoints.get(2)));
            }
        }

        Solution newSolution = new Solution(steinerPoints,solution.getGeneticAlgorithm());
        return newSolution;
    }
}
