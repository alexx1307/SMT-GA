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
    public Solution[] cross(Solution parent1, Solution parent2) {
        Double limit = random.nextDouble()* parent1.getGeneticAlgorithm().getProblem().getyBorder();
         SortedSet<Point> steinerPoints1= new TreeSet<Point>();
         SortedSet<Point> steinerPoints2= new TreeSet<Point>();
        for (Point point : parent1.getSteinerPoints()) {
            if(point.y>limit)
                steinerPoints1.add(point);
            else steinerPoints2.add(point);
        }
        for (Point point : parent2.getSteinerPoints()) {
            if(point.y<limit)
                steinerPoints1.add(point);
            else steinerPoints2.add(point);
        }
        return new Solution[]{new Solution(steinerPoints1,parent1.getGeneticAlgorithm()),new Solution(steinerPoints2,parent1.getGeneticAlgorithm())};


    }

    @Override
    public String toString() {
        return "UpDownCrossover{}" ;
    }
}
