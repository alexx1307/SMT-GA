package com.aleksiejew.lukasz.Algorithm;

import com.aleksiejew.lukasz.Model.Point;
import com.aleksiejew.lukasz.Model.Population;
import com.aleksiejew.lukasz.Model.Solution;
import javafx.util.Pair;
import junit.framework.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;

public class CompletelyRandomCrossoverSelectionStrategyTest {


    @Test
    public void testNumberOfPairs() throws Exception {
        //given
        Population population = mock(Population.class);
        SortedSet<Point> points = mock(SortedSet.class);
        GeneticAlgorithm ga = mock(GeneticAlgorithm.class);

        SortedSet<Solution> solutions = new TreeSet<Solution>();
        for (int i = 0; i < 15; i++)
            solutions.add(new Solution(points, ga));
        int n = 50;

        CrossoverSelectionStrategy crossoverSelectionStrategy = new CompletelyRandomCrossoverSelectionStrategy();

        //when

        stub(population.getSolutions()).toReturn(solutions);
        List<Pair<Solution, Solution>> pairs = crossoverSelectionStrategy.choosePairsToCross(population, n);

        //then
        Assert.assertEquals(pairs.size(), n);
    }
}