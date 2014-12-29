package com.aleksiejew.lukasz.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luka on 2014-10-31.
 */
public class State {
    private int iteration;
    private Population population;
    private Population newPopulation;
    private Solution bestSolution;
    private ArrayList<Double> bestResultsPerIteration;

    public State() {
        this.population = new Population();
        this.newPopulation = new Population();
        bestResultsPerIteration = new ArrayList<Double>();
    }


    public void updateBestSolution() {
        if (bestSolution == null || bestSolution.getEvaluatedResult().getCostValue() > population.getBestSolution().getEvaluatedResult().getCostValue())
            bestSolution = population.getBestSolution();
        bestResultsPerIteration.add(bestSolution.getEvaluatedResult().getCostValue());
    }

    public Population getNewPopulation() {
        return newPopulation;
    }

    public void setNewPopulation(Population newPopulation) {
        this.newPopulation = newPopulation;
    }

    public Population getPopulation() {
        return population;
    }

    public void setPopulation(Population population) {
        this.population = population;
    }


    public int getIteration() {
        return iteration;
    }

    public void setIteration(int iteration) {
        this.iteration = iteration;
    }

    public Solution getBestSolution() {
        return bestSolution;
    }

    public double getResultAfterIteration(int i) {
        if (i < bestResultsPerIteration.size())
            return bestResultsPerIteration.get(i);
        else return Double.NaN;
    }

    public void incrementIteration() {
        iteration++;
    }
}
