package com.aleksiejew.lukasz.Algorithm.GeneticOperators;

import com.aleksiejew.lukasz.Model.Point;
import com.aleksiejew.lukasz.Model.Solution;

import java.util.*;

/**
 * Created by Luka on 2014-11-20.
 */
public class RandomHalfOnHalfCrossover implements Crossover {
    private Random random = new Random();


    public void setRandom(Random random) {
        this.random = random;
    }


    @Override
    public Solution cross(Solution parent1, Solution parent2) {
        if (!parent1.getGeneticAlgorithm().equals(parent2.getGeneticAlgorithm()))
            throw new IllegalArgumentException("Both parents should have the same genetic algorithm reference");

        SortedSet<Point> steinerPoints1 = parent1.getSteinerPoints();
        SortedSet<Point> steinerPoints2 = parent2.getSteinerPoints();

        SortedSet<Point> newSteinerPoints1 = chooseHalfRandomly(steinerPoints1);
        SortedSet<Point> newSteinerPoints2 = chooseHalfRandomly(steinerPoints2);

        newSteinerPoints1.addAll(newSteinerPoints2);

        Solution result = new Solution(newSteinerPoints1, parent1.getGeneticAlgorithm());
        return result;
    }

    private SortedSet<Point> chooseHalfRandomly(SortedSet<Point> steinerPoints) {
        int n = steinerPoints.size() / 2;
        if(steinerPoints.size()%2!=0 && random.nextBoolean())
            n++;
        ArrayList<Point> points = new ArrayList(steinerPoints);
        SortedSet<Point> newSteinerPoints = new TreeSet<Point>();
        //for (int i = 0; i < n; i++) {
        while(newSteinerPoints.size()<n){
            newSteinerPoints.add(points.get(random.nextInt(steinerPoints.size())));
        }

        return newSteinerPoints;
    }
}
