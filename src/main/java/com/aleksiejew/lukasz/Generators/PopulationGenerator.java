package com.aleksiejew.lukasz.Generators;

import com.aleksiejew.lukasz.Algorithm.GeneticAlgorithm;
import com.aleksiejew.lukasz.Model.Solution;

/**
 * Created by Luka on 2014-10-31.
 */
public interface PopulationGenerator {
    com.aleksiejew.lukasz.Model.Population generatePopulation(GeneticAlgorithm geneticAlgorithm);
}
