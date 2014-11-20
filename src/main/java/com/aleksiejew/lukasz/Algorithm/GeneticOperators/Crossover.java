package com.aleksiejew.lukasz.Algorithm.GeneticOperators;

import com.aleksiejew.lukasz.Model.Solution;

/**
 * Created by Luka on 2014-10-31.
 */
public interface Crossover {
    Solution cross(Solution parent1, Solution parent2);
}
