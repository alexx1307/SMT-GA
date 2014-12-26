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
    private State currentState = new State();
    private AlgorithmParameters algorithmParameters;
    private ResultEvaluator resultEvaluator;
    private Metrics metrics;
    private final double defaultMutationProbability = 0.05f;

    public GeneticAlgorithm(AlgorithmParameters algorithmParameters, Problem problem, ResultEvaluator resultEvaluator, Metrics metrics, SolutionGenerator solutionGenerator) {
        this.algorithmParameters = algorithmParameters;
        this.problem = problem;
        this.resultEvaluator = resultEvaluator;
        this.metrics = metrics;
        this.solutionGenerator = solutionGenerator;
    }

    private SolutionGenerator solutionGenerator;

    public void doIteration() {

        applyGeneticOperators();
        //System.out.println("after operators:"+currentState.getNewPopulation().getSolutions().size());
        makeSelection();
        //System.out.println("after selection:"+currentState.getNewPopulation().getSolutions().size());
        currentState.swapPopulations();
    }

    public void doSimulation() {
        createFirstGeneration();
        do {
            doIteration();
            currentState.setIteration(currentState.getIteration()+1);
            //System.out.println("best result is:"+currentState.getPopulation().getBestSolution().getEvaluatedResult());
        }
        while (!isStopCriterionFullfiled());
    }

    private void makeSelection() {
        currentState.setNewPopulation(algorithmParameters.getSelectionMethod().selectNextPopulation(currentState.getPopulation(), currentState.getNewPopulation(), getAlgorithmParameters().getPopulationSize()));
    }

    private void applyGeneticOperators() {
        applyMutations();
        applyCrossovers();

    }


    private void applyCrossovers() {
        Crossover[] crossovers = algorithmParameters.getCrossovers();
        for (int i = 0; i < crossovers.length; i++) {
            Crossover crossover = crossovers[i];
            List<Pair<Solution, Solution>> pairsToCross = algorithmParameters.getCrossoverSelectionStrategy().choosePairsToCross(currentState.getPopulation(), algorithmParameters.getPopulationSize());
            for (Pair<Solution, Solution> pair : pairsToCross)
                currentState.getNewPopulation().addNewSolution(crossover.cross(pair.getKey(), pair.getValue()));
        }
    }

    private double getMutationProbability(int i) {
        double[] mutationsProbability = algorithmParameters.getMutationsProbability();
        if (mutationsProbability != null && i < mutationsProbability.length)
            return mutationsProbability[i];
        return defaultMutationProbability;
    }


    private void applyMutations() {
        Mutation[] mutations = algorithmParameters.getMutations();
        for (int i = 0; i < mutations.length; i++) {
            Mutation mutation = mutations[i];
            for (Solution solution : currentState.getPopulation().getSolutions()) {
                double mutationProb = getMutationProbability(i);
                if (CommonTools.passProbabilityTest(mutationProb)) {
                    currentState.getNewPopulation().addNewSolution(mutation.mutate(solution));
                    //System.out.println("mutation applied");
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
        return algorithmParameters.getStopCriterion().check(currentState);
    }

    @Required
    public void setAlgorithmParameters(AlgorithmParameters algorithmParameters) {
        this.algorithmParameters = algorithmParameters;
    }

    public AlgorithmParameters getAlgorithmParameters() {
        return algorithmParameters;
    }

    public Problem getProblem() {
        return problem;
    }

    public int getDefaultSolutionSize() {
        return problem.getTerminals().size()-1; //algorithmParameters.getDefaultSteinerPointsNumber();
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

    public SolutionGenerator getSolutionGenerator() {
        return solutionGenerator;
    }
}
