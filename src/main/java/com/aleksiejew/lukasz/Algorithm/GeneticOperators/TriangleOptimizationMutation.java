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
        return LocalOptimization.optimizeSolution(solution);
    }

    @Override
    public String toString() {
        return "TriangleOptimizationMutation{}" ;
    }
}
