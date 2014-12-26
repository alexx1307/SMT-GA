package com.aleksiejew.lukasz.Algorithm;

import com.aleksiejew.lukasz.Algorithm.Criterions.StopCriterion;
import com.aleksiejew.lukasz.Algorithm.GeneticOperators.Crossover;
import com.aleksiejew.lukasz.Algorithm.GeneticOperators.Mutation;
import com.aleksiejew.lukasz.Algorithm.SelectionMethods.SelectionMethod;
import com.aleksiejew.lukasz.Generators.SolutionGenerator;

import java.util.Arrays;

/**
 * Created by Luka on 2014-10-31.
 */
public class AlgorithmParameters {
    private int populationSize;
    private StopCriterion stopCriterion;
    private Mutation[] mutations;
    private Crossover[] crossovers;
    private double[] mutationsProbability;

    private SelectionMethod selectionMethod;

    private CrossoverSelectionStrategy crossoverSelectionStrategy;

    public AlgorithmParameters(int populationSize, StopCriterion stopCriterion, Mutation[] mutations, Crossover[] crossovers, double[] mutationsProbability, SolutionGenerator solutionGenerator, SelectionMethod selectionMethod, CrossoverSelectionStrategy crossoverSelectionStrategy) {
        this.populationSize = populationSize;
        this.stopCriterion = stopCriterion;
        this.mutations = mutations;
        this.crossovers = crossovers;
        this.mutationsProbability = mutationsProbability;
        this.selectionMethod = selectionMethod;
        this.crossoverSelectionStrategy = crossoverSelectionStrategy;
    }

    @Override
    public String toString() {
        return "AlgorithmParameters{" +
                "populationSize=" + populationSize +
                ", stopCriterion=" + stopCriterion +
                ", mutations=" + Arrays.toString(mutations) +
                ", crossovers=" + Arrays.toString(crossovers) +
                ", mutationsProbability=" + Arrays.toString(mutationsProbability) +
                ", selectionMethod=" + selectionMethod +
                ", crossoverSelectionStrategy=" + crossoverSelectionStrategy +
                '}';
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public SelectionMethod getSelectionMethod() {
        return selectionMethod;
    }

    public Crossover[] getCrossovers() {
        return crossovers;
    }

    public CrossoverSelectionStrategy getCrossoverSelectionStrategy() {
        return crossoverSelectionStrategy;
    }

    public double[] getMutationsProbability() {
        return mutationsProbability;
    }

    public Mutation[] getMutations() {
        return mutations;
    }



    public StopCriterion getStopCriterion() {
        return stopCriterion;
    }
}
