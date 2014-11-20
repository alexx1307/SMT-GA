package com.aleksiejew.lukasz.Algorithm.GeneticOperators;

import com.aleksiejew.lukasz.Model.Solution;

/**
 * Created by Luka on 2014-11-20.
 */
public class RandomHalfOnHalfCrossover implements Crossover {
    @Override
    public Solution cross(Solution parent1, Solution parent2) {
        if(!parent1.getGeneticAlgorithm().equals(parent2.getGeneticAlgorithm()))
            throw new IllegalArgumentException("Both parents should have the same genetic algorithm reference");
        Solution result = new Solution(parent1.getSteinerPoints(),parent1.getGeneticAlgorithm());
        return result;
    }
}
