package com.aleksiejew.lukasz.Testing;

import com.aleksiejew.lukasz.Algorithm.AlgorithmParameters;
import com.aleksiejew.lukasz.Algorithm.EuclideanMetrics;
import com.aleksiejew.lukasz.Algorithm.GeneticAlgorithm;
import com.aleksiejew.lukasz.Algorithm.MST.SimpleSpanningTreeEvaluator;
import com.aleksiejew.lukasz.Generators.SimpleRandomSolutionGenerator;
import com.aleksiejew.lukasz.Model.Problem;

/**
 * Created by Luka on 2014-12-25.
 */
public class TestInstance {
    Problem problem;
    AlgorithmParameters parameters;

    public TestInstance(Problem problem, AlgorithmParameters parameters) {
        this.problem = problem;
        this.parameters = parameters;
    }

    public TestResult run() {
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(parameters, problem, new SimpleSpanningTreeEvaluator(), new EuclideanMetrics(), new SimpleRandomSolutionGenerator());
        geneticAlgorithm.doSimulation();
        return new TestResult(geneticAlgorithm.getCurrentState());
    }

    @Override
    public String toString() {
        return "TestInstance{" +
                "problem=" + problem +
                ", parameters=" + parameters +
                '}';
    }
}
