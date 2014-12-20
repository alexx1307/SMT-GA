package com.aleksiejew.lukasz.Algorithm.SelectionMethods;

import com.aleksiejew.lukasz.Algorithm.GeneticAlgorithm;
import com.aleksiejew.lukasz.Model.Point;
import com.aleksiejew.lukasz.Model.Population;
import com.aleksiejew.lukasz.Model.Solution;
import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verifyZeroInteractions;

public class NBestSelectionMethodTest {
    private static Solution solution1;
    private static Solution solution2;
    private static Solution solution3;
    private static Population firstPopulation;
    private static Population childrenPopulation;


    @BeforeClass
    public static void init() {
        GeneticAlgorithm ga = mock(GeneticAlgorithm.class);
        SortedSet<Point> points = mock(SortedSet.class);
        solution1 = new Solution(points,ga);
        solution1.setEvaluatedResult(1.0);

        solution2 = new Solution(points,ga);
        solution2.setEvaluatedResult(2.0);

        solution3 = new Solution(points,ga);
        solution3.setEvaluatedResult(3.0);

        SortedSet<Solution> solutions = new TreeSet<Solution>();
        solutions.add(solution1);
        solutions.add(solution2);
        solutions.add(solution3);
        firstPopulation = new Population();
        childrenPopulation = new Population();
        firstPopulation.setSolutions(solutions);
        childrenPopulation.setSolutions(new TreeSet<Solution>());
    }

    @Test
    public void testSelectNextPopulation() throws Exception {

        NBestSelectionMethod nBestSelectionMethod = new NBestSelectionMethod();

        Population nextGen = nBestSelectionMethod.selectNextPopulation(firstPopulation,childrenPopulation,2);

        Assert.assertEquals(3,firstPopulation.getSolutions().size());
        Assert.assertEquals(2,nextGen.getSolutions().size());

        Assert.assertTrue(nextGen.getSolutions().contains(solution1));
        Assert.assertTrue(nextGen.getSolutions().contains(solution2));
        Assert.assertFalse(nextGen.getSolutions().contains(solution3));
    }

    @Test
    public void testSelectNextPopulationWithNBiggerThenPopulationSize() throws Exception {
        NBestSelectionMethod nBestSelectionMethod = new NBestSelectionMethod();
        childrenPopulation.setSolutions(new TreeSet<Solution>());
        Population newPopulation = nBestSelectionMethod.selectNextPopulation(firstPopulation,childrenPopulation,4);

        Assert.assertEquals(3,firstPopulation.getSolutions().size());
        Assert.assertEquals(3,newPopulation.getSolutions().size());

        Assert.assertTrue(newPopulation.getSolutions().contains(solution1));
        Assert.assertTrue(newPopulation.getSolutions().contains(solution2));
        Assert.assertTrue(newPopulation.getSolutions().contains(solution3));
    }
}