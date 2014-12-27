package com.aleksiejew.lukasz.Algorithm.GeneticOperators;

import com.aleksiejew.lukasz.Model.Point;
import com.aleksiejew.lukasz.Model.Solution;

import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by Luka on 2014-12-27.
 */
public class UpDownCrossover implements Crossover {
    private static Random random = new Random();
    @Override
    public Solution cross(Solution parent1, Solution parent2) {
        Double limit = random.nextDouble()* parent1.getGeneticAlgorithm().getProblem().getyBorder();
         SortedSet<Point> steinerPoints= new TreeSet<Point>();
        for (Point point : parent1.getSteinerPoints()) {
            if(point.y>limit)
                steinerPoints.add(point);
        }
        for (Point point : parent2.getSteinerPoints()) {
            if(point.y<limit)
                steinerPoints.add(point);
        }
        return new Solution(steinerPoints,parent1.getGeneticAlgorithm());


    }
}
