package com.aleksiejew.lukasz.Algorithm;

import com.aleksiejew.lukasz.Algorithm.Criterions.StopCriterion;
import com.aleksiejew.lukasz.Algorithm.GeneticOperators.Crossover;
import com.aleksiejew.lukasz.Algorithm.GeneticOperators.Mutation;
import com.aleksiejew.lukasz.Algorithm.SelectionMethods.SelectionMethod;
import com.aleksiejew.lukasz.Generators.SolutionGenerator;
import com.aleksiejew.lukasz.Model.Population;
import com.aleksiejew.lukasz.Model.Problem;
import com.aleksiejew.lukasz.Model.Solution;
import com.aleksiejew.lukasz.Model.State;
import com.aleksiejew.lukasz.Tools.CommonTools;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;
import java.util.Random;

/**
 * Created by Luka on 2014-10-31.
 */
public class GeneticAlgorithm {


    private Problem problem;

    private Mutation[] mutations;
    private Crossover[] crossovers;
    private double[] mutationsProbability;
    private State currentState = new State();

    private StopCriterion stopCriterion;
    private AlgorithmParameters algorithmParameters;
    private SolutionGenerator solutionGenerator;
    private ResultEvaluator resultEvaluator;
    private Metrics metrics;
    private SelectionMethod selectionMethod;
    private final double defaultMutationProbability = 0.05f;


    private CrossoverSelectionStrategy crossoverSelectionStrategy;


    public void doIteration() {

        applyGeneticOperators();
        System.out.println("after operators:"+currentState.getNewPopulation().getSolutions().size());
        makeSelection();
        System.out.println("after selection:"+currentState.getNewPopulation().getSolutions().size());
        currentState.swapPopulations();
    }

    public void doSimulation() {
        createFirstGeneration();
        do {
            doIteration();
            currentState.setIteration(currentState.getIteration()+1);
            System.out.println("best result is:"+currentState.getPopulation().getBestSolution().getEvaluatedResult());
        }
        while (!isStopCriterionFullfiled());
    }

    private void makeSelection() {
        currentState.setNewPopulation(selectionMethod.selectNextPopulation(currentState.getPopulation(), currentState.getNewPopulation(), getAlgorithmParameters().getPopulationSize()));
    }

    private void applyGeneticOperators() {
        applyCrossovers();
        applyMutations();

    }


    private void applyCrossovers() {
        for (int i = 0; i < crossovers.length; i++) {
            Crossover crossover = crossovers[i];
            List<Pair<Solution, Solution>> pairsToCross = crossoverSelectionStrategy.choosePairsToCross(currentState.getPopulation(), algorithmParameters.getPopulationSize());
            for (Pair<Solution, Solution> pair : pairsToCross)
                currentState.getNewPopulation().addNewSolution(crossover.cross(pair.getKey(), pair.getValue()));
        }
    }

    private double getMutationProbability(int i) {
        if (mutationsProbability != null && i < mutationsProbability.length)
            return mutationsProbability[i];
        return defaultMutationProbability;
    }


    private void applyMutations() {
        for (int i = 0; i < mutations.length; i++) {
            Mutation mutation = mutations[i];
            for (Solution solution : currentState.getPopulation().getSolutions()) {
                double mutationProb = getMutationProbability(i);
                if (CommonTools.passProbabilityTest(mutationProb)) {
                    currentState.getNewPopulation().addNewSolution(mutation.mutate(solution));
                    System.out.println("mutation applied");
                }
            }
        }
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

    @Required
    public void setMutations(Mutation[] mutations) {
        this.mutations = mutations;
    }

    @Required
    public void setCrossovers(Crossover[] crossovers) {
        this.crossovers = crossovers;
    }

    public void setMutationsProbability(double[] mutationsProbability) {
        this.mutationsProbability = mutationsProbability;
    }

    @Required
    public void setCrossoverSelectionStrategy(CrossoverSelectionStrategy crossoverSelectionStrategy) {
        this.crossoverSelectionStrategy = crossoverSelectionStrategy;
    }

    @Required
    public void setSelectionMethod(SelectionMethod selectionMethod) {
        this.selectionMethod = selectionMethod;
    }

}
