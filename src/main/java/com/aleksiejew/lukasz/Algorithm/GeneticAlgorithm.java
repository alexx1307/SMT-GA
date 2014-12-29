package com.aleksiejew.lukasz.Algorithm;

import com.aleksiejew.lukasz.Algorithm.GeneticOperators.Crossover;
import com.aleksiejew.lukasz.Algorithm.GeneticOperators.Mutation;
import com.aleksiejew.lukasz.Algorithm.GeneticOperators.UpDownCrossover;
import com.aleksiejew.lukasz.Algorithm.Geometry.LocalOptimization;
import com.aleksiejew.lukasz.Algorithm.SuccessionMethods.PercentageSuccessionMethod;
import com.aleksiejew.lukasz.Algorithm.SuccessionMethods.SuccessionMethod;
import com.aleksiejew.lukasz.Generators.SolutionGenerator;
import com.aleksiejew.lukasz.Model.Population;
import com.aleksiejew.lukasz.Model.Problem;
import com.aleksiejew.lukasz.Model.Solution;
import com.aleksiejew.lukasz.Model.State;
import com.aleksiejew.lukasz.Tools.CommonTools;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;

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
    private SolutionGenerator solutionGenerator;
    private SuccessionMethod successionMethod;


    public GeneticAlgorithm(AlgorithmParameters algorithmParameters, Problem problem, ResultEvaluator resultEvaluator, Metrics metrics, SolutionGenerator solutionGenerator) {
        this.algorithmParameters = algorithmParameters;
        this.problem = problem;
        this.resultEvaluator = resultEvaluator;
        this.metrics = metrics;
        this.solutionGenerator = solutionGenerator;
        this.successionMethod = new PercentageSuccessionMethod();

    }


    public void doIteration() {
        makeSelection();
        applyGeneticOperators();
        doSuccession();

    }

    private void doSuccession() {
        currentState.updateBestSolution();
        currentState.setPopulation(successionMethod.resolveNewGeneration(currentState.getPopulation(), currentState.getNewPopulation(),algorithmParameters.getOldPopulationPart()));
    }

    public void doSimulation() {
        initializeSimulation();
        createFirstGeneration();
        do {
            doIteration();
            currentState.incrementIteration();
        }
        while (!isStopCriterionFullfiled());
        currentState.updateBestSolution();
        if (shouldLocalOptimizationBeAppliedOnTheEnd()) {
            applyLocalOptimization();
        }
    }


    private boolean shouldLocalOptimizationBeAppliedOnTheEnd() {
        if (algorithmParameters.getLevelOfLocalOptimalizationOnTheEnd() > 0)
            return true;
        return false;
    }

    private void applyLocalOptimization() {
        int optimalizationLevel = algorithmParameters.getLevelOfLocalOptimalizationOnTheEnd();
        System.out.println("iter"+currentState.getIteration()+"before optimization = " + currentState.getResultAfterIteration(currentState.getIteration()));
        Population newPopulation = new Population();
        currentState.getPopulation().addNewSolution(currentState.getBestSolution());
        for (Solution solution : currentState.getPopulation().getSolutions()) {
            Solution optimizedSolution = solution;
            for (int i = 0; i < optimalizationLevel; i++) {
                optimizedSolution = LocalOptimization.optimizeSolution(optimizedSolution);
            }
            newPopulation.addNewSolution(optimizedSolution);
        }
        currentState.incrementIteration();
        currentState.setPopulation(newPopulation);
        currentState.updateBestSolution();
        System.out.println("iter"+currentState.getIteration()+"after " + optimalizationLevel + "-times optimization = " + currentState.getResultAfterIteration(currentState.getIteration()));
    }

    private void initializeSimulation() {
        algorithmParameters.getStopCriterion().init();
        currentState.setIteration(0);
    }

    private void makeSelection() {
        currentState.setPopulation(algorithmParameters.getSelectionMethod().selectNextPopulation(currentState.getPopulation(), getAlgorithmParameters().getPopulationSize()));
    }

    private void applyGeneticOperators() {
        applyCrossovers();
        applyMutations();
    }


    private void applyCrossovers() {
        Crossover[] crossovers = algorithmParameters.getCrossovers();
        for (int i = 0; i < crossovers.length; i++) {
            Crossover crossover = crossovers[i];

            int pairsNumber = (int)((double)algorithmParameters.getPopulationSize()*1.5f);
            if(crossover instanceof UpDownCrossover)
                pairsNumber/=2;
            List<Pair<Solution, Solution>> pairsToCross = algorithmParameters.getCrossoverSelectionStrategy().choosePairsToCross(currentState.getPopulation(), pairsNumber );
            for (Pair<Solution, Solution> pair : pairsToCross){
                Solution[] crosses = crossover.cross(pair.getKey(), pair.getValue());
                for (Solution cross : crosses) {
                    currentState.getNewPopulation().addNewSolution(cross);
                }

            }

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
        return algorithmParameters.getStopCriterion().checkIfFullfiled(currentState);
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
        return problem.getTerminals().size() - 1; //algorithmParameters.getDefaultSteinerPointsNumber();
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
