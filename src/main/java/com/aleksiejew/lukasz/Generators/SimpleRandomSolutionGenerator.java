package com.aleksiejew.lukasz.Generators;

import com.aleksiejew.lukasz.Algorithm.GeneticAlgorithm;
import com.aleksiejew.lukasz.Model.Point;
import com.aleksiejew.lukasz.Model.Solution;

import java.util.*;

/**
 * Created by Luka on 2014-11-19.
 */
public class SimpleRandomSolutionGenerator implements SolutionGenerator {
    Random random = new Random();

    @Override
    public Solution generateSolution(GeneticAlgorithm geneticAlgorithm) {
        SortedSet<Point> points = new TreeSet<Point>();
        for (int i = 0; i < geneticAlgorithm.getDefaultSolutionSize(); i++) {
            points.add(new Point(random.nextDouble(), random.nextDouble()));
        }
        return new Solution(points, geneticAlgorithm);
    }
}
