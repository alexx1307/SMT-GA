package com.aleksiejew.lukasz.Generators;

import com.aleksiejew.lukasz.Algorithm.AlgorithmParameters;
import com.aleksiejew.lukasz.Algorithm.GeneticAlgorithm;
import com.aleksiejew.lukasz.Model.Population;

/**
 * Created by Luka on 2014-11-19.
 */
public class SimplePopulationGenerator implements PopulationGenerator {



    @Override
    public Population generatePopulation(GeneticAlgorithm geneticAlgorithm) {
        Population population = new Population();
        AlgorithmParameters algorithmParameters = geneticAlgorithm.getAlgorithmParameters();
        int populationSize = algorithmParameters.getPopulationSize();
        for (int i = 0; i < populationSize; i++) {
            population.addNewSolution(geneticAlgorithm.getSolutionGenerator().generateSolution(geneticAlgorithm));
        }
        return population;
    }
}
