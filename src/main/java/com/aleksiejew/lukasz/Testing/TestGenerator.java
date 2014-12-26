package com.aleksiejew.lukasz.Testing;

import com.aleksiejew.lukasz.Algorithm.AlgorithmParameters;
import com.aleksiejew.lukasz.Algorithm.CompletelyRandomCrossoverSelectionStrategy;
import com.aleksiejew.lukasz.Algorithm.Criterions.MaxIterationNumberCriterion;
import com.aleksiejew.lukasz.Algorithm.Criterions.StopCriterion;
import com.aleksiejew.lukasz.Algorithm.CrossoverSelectionStrategy;
import com.aleksiejew.lukasz.Algorithm.GeneticOperators.AddingRandomPointMutation;
import com.aleksiejew.lukasz.Algorithm.GeneticOperators.Crossover;
import com.aleksiejew.lukasz.Algorithm.GeneticOperators.Mutation;
import com.aleksiejew.lukasz.Algorithm.GeneticOperators.RandomHalfOnHalfCrossover;
import com.aleksiejew.lukasz.Algorithm.SelectionMethods.NBestSelectionMethod;
import com.aleksiejew.lukasz.Algorithm.SelectionMethods.SelectionMethod;
import com.aleksiejew.lukasz.Generators.SimpleRandomSolutionGenerator;
import com.aleksiejew.lukasz.Generators.SolutionGenerator;
import com.aleksiejew.lukasz.Model.Point;
import com.aleksiejew.lukasz.Model.Problem;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by Luka on 2014-12-26.
 */
public class TestGenerator {
    private static Random random = new Random();

    public static List<TestInstance> generate(double x, double y, boolean grid) {
        return generate(x, y, grid, 0);
    }

    public static List<TestInstance> generate(double x, double y, boolean grid, int numberOfPoints) {
        Problem problem = new Problem();
        problem.setxBorder(x);
        problem.setyBorder(y);

        List<Point> terminals;
        if (grid) {
            terminals = generateGridTerminals(x, y);
        } else {
            terminals = generateRandomTerminals(x, y, numberOfPoints);
        }
        problem.setTerminals(terminals);
        StopCriterion[] stopCriterions = {new MaxIterationNumberCriterion(30), new MaxIterationNumberCriterion(100)};
        int[] populationNumbers = {10, 50, 200};
        Mutation[][] mutations = {{new AddingRandomPointMutation()}};
        Crossover[][] crossovers = {{new RandomHalfOnHalfCrossover()}};
        double[][] mutationsProbabilities = {{0.02f},{ 0.1f}};

        List<TestInstance> list = new LinkedList<TestInstance>();

        SolutionGenerator solutionGenerator = new SimpleRandomSolutionGenerator();
        SelectionMethod selectionMethod = new NBestSelectionMethod();
        CrossoverSelectionStrategy crossoverSelectionStrategy = new CompletelyRandomCrossoverSelectionStrategy();
        for (int populationNumber : populationNumbers)
            for (StopCriterion stopCriterion : stopCriterions)
                for (Mutation[] mutationsArray : mutations)
                    for (Crossover[] crossoversArray : crossovers)
                        for (double[] mutationsProbabilitiesArray : mutationsProbabilities)
                            list.add(new TestInstance(problem, new AlgorithmParameters(populationNumber, stopCriterion, mutationsArray, crossoversArray, mutationsProbabilitiesArray, solutionGenerator, selectionMethod, crossoverSelectionStrategy)));

        return list;
    }

    private static List<Point> generateGridTerminals(double x, double y) {
        List<Point> terminals = new LinkedList<Point>();
        for (int i = 0; i <= x; i++)
            for (int j = 0; j <= y; j++)
                terminals.add(new Point(i, j));
        return terminals;

    }

    private static List<Point> generateRandomTerminals(double x, double y, int numberOfPoints) {
        List<Point> terminals = new LinkedList<Point>();
        for (int i = 0; i < numberOfPoints; i++)
            terminals.add(new Point(random.nextDouble() * x, random.nextDouble() * y));
        return terminals;
    }
}
