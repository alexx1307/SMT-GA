package com.aleksiejew.lukasz.Algorithm;

import com.aleksiejew.lukasz.Generators.SolutionGenerator;
import com.aleksiejew.lukasz.Model.Population;
import com.aleksiejew.lukasz.Model.Problem;
import com.aleksiejew.lukasz.Model.State;

import javax.sql.rowset.Predicate;
import java.util.List;

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


    }

    private void applyGeneticOperators() {


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

    public void setAlgorithmParameters(AlgorithmParameters algorithmParameters) {
        this.algorithmParameters = algorithmParameters;
    }

    public void setSolutionGenerator(SolutionGenerator solutionGenerator) {
        this.solutionGenerator = solutionGenerator;
    }

    public AlgorithmParameters getAlgorithmParameters() {
        return algorithmParameters;
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

    public void setResultEvaluator(ResultEvaluator resultEvaluator) {
        this.resultEvaluator = resultEvaluator;
    }

    public Metrics getMetrics() {
        return metrics;
    }


    public void setMetrics(Metrics metrics) {
        this.metrics = metrics;
    }


    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public State getCurrentState() {
        return currentState;
    }
}
