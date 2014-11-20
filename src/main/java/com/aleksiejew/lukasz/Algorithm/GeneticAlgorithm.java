package com.aleksiejew.lukasz.Algorithm;

import com.aleksiejew.lukasz.Algorithm.Criterions.StopCriterion;
import com.aleksiejew.lukasz.Algorithm.GeneticOperators.Crossover;
import com.aleksiejew.lukasz.Algorithm.GeneticOperators.Mutation;
import com.aleksiejew.lukasz.Algorithm.SelectionMethods.SelectionMethod;
import com.aleksiejew.lukasz.Generators.SolutionGenerator;
import com.aleksiejew.lukasz.Model.Population;
import com.aleksiejew.lukasz.Model.Problem;
import com.aleksiejew.lukasz.Model.State;
import org.springframework.beans.factory.annotation.Required;

/**
 * Created by Luka on 2014-10-31.
 */
public class GeneticAlgorithm {


    private Problem problem;
    private Mutation[] mutations;
    private Crossover[] crossovers;
    private double[] mutationsProbability;
    private double[] crossoversProbability;
    private State currentState = new State();

    private StopCriterion stopCriterion;
    private AlgorithmParameters algorithmParameters;
    private SolutionGenerator solutionGenerator;
    private ResultEvaluator resultEvaluator;
    private Metrics metrics;
    private SelectionMethod selectionMethod;


    public void doIteration() {
        applyGeneticOperators();
        makeSelection();
    }

    public void doSimulation() {
        createFirstGeneration();
        do {
            doIteration();
        }
        while (!isStopCriterionFullfiled());
    }

    private void makeSelection() {
        currentState.setPopulation(selectionMethod.selectNextPopulation(currentState.getPopulation(), getDefaultSolutionSize()));
    }

    private void applyGeneticOperators() {
        applyCrossovers();
        applyMutations();
    }



    private void applyCrossovers() {
        for(int i=0;i<crossovers.length;i++){
            double prob = crossoversProbability[i];
            Crossover crossover = crossovers[i];
        }
    }

    private void applyMutations() {
    }
    public void createFirstGeneration() {
        Population population = new Population();
        for (int i = 0; i < algorithmParameters.getPopulationSize(); i++) {
            population.addNewSolution(solutionGenerator.generateSolution(this));
        }
        currentState.setPopulation(population);
    }

    private boolean isStopCriterionFullfiled() {
        return stopCriterion.check(currentState);
    }

    @Required
    public void setAlgorithmParameters(AlgorithmParameters algorithmParameters) {
        this.algorithmParameters = algorithmParameters;
    }

    public AlgorithmParameters getAlgorithmParameters() {
        return algorithmParameters;
    }


    @Required
    public void setSolutionGenerator(SolutionGenerator solutionGenerator) {
        this.solutionGenerator = solutionGenerator;
    }

    public SolutionGenerator getSolutionGenerator() {
        return solutionGenerator;
    }


    public Problem getProblem() {
        return problem;
    }

    public int getDefaultSolutionSize() {
        return algorithmParameters.getDefaultSteinerPointsNumber();//problem.getTerminals().size()-1;
    }

    public ResultEvaluator getResultEvaluator() {
        return resultEvaluator;
    }

    @Required
    public void setResultEvaluator(ResultEvaluator resultEvaluator) {
        this.resultEvaluator = resultEvaluator;
    }

    public Metrics getMetrics() {
        return metrics;
    }

    @Required
    public void setMetrics(Metrics metrics) {
        this.metrics = metrics;
    }

    @Required
    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public State getCurrentState() {
        return currentState;
    }


    public StopCriterion getStopCriterion() {
        return stopCriterion;
    }

    @Required
    public void setStopCriterion(StopCriterion stopCriterion) {
        this.stopCriterion = stopCriterion;
    }

}
