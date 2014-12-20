package com.aleksiejew.lukasz.Algorithm.GeneticOperators;

import com.aleksiejew.lukasz.Model.Point;
import com.aleksiejew.lukasz.Model.Problem;
import com.aleksiejew.lukasz.Model.Solution;

import java.util.*;

/**
 * Created by Luka on 2014-12-19.
 */
public class AddingRandomPointMutation implements Mutation {
    static Random random = new Random();
    @Override
    public Solution mutate(Solution solution) {
        Double xBorder = solution.getGeneticAlgorithm().getProblem().getxBorder();
        Double yBorder = solution.getGeneticAlgorithm().getProblem().getyBorder();
        SortedSet<Point> newListOfPoints = new TreeSet<Point>(solution.getSteinerPoints());
        newListOfPoints.add(new Point(random.nextDouble()*xBorder,random.nextDouble()*yBorder));
        Solution result = new Solution(newListOfPoints,solution.getGeneticAlgorithm());
        return result;
    }
}
