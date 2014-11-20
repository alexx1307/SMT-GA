package com.aleksiejew.lukasz.Algorithm.SelectionMethods;

import com.aleksiejew.lukasz.Model.Population;
import com.aleksiejew.lukasz.Model.Solution;
import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verifyZeroInteractions;

public class NBestSelectionMethodTest {
    private static Solution solution1;
    private static Solution solution2;
    private static Solution solution3;
    private static Population firstPopulation;


    @BeforeClass
    public static void init() {
        solution1 = mock(Solution.class);
        stub(solution1.getEvaluatedResult()).toReturn(1.0);

        solution2 = mock(Solution.class);
        stub(solution1.getEvaluatedResult()).toReturn(2.0);

        solution3 = mock(Solution.class);
        stub(solution1.getEvaluatedResult()).toReturn(3.0);

        firstPopulation = mock(Population.class);
        List<Solution> solutions = Arrays.asList(solution1, solution2, solution3);
        stub(firstPopulation.getSortedSolutions()).toReturn(solutions);
        stub(firstPopulation.getSolutions()).toReturn(solutions);

    }

    @Test
    public void testSelectNextPopulation() throws Exception {

        NBestSelectionMethod nBestSelectionMethod = new NBestSelectionMethod();
        Population newPopulation = nBestSelectionMethod.selectNextPopulation(firstPopulation,2);

        Assert.assertEquals(3,firstPopulation.getSortedSolutions().size());
        Assert.assertEquals(2,newPopulation.getSortedSolutions().size());

        Assert.assertTrue(newPopulation.getSolutions().contains(solution1));
        Assert.assertTrue(newPopulation.getSolutions().contains(solution2));
        Assert.assertFalse(newPopulation.getSolutions().contains(solution3));
    }

    @Test
    public void testSelectNextPopulationWithNBiggerThenPopulationSize() throws Exception {
        NBestSelectionMethod nBestSelectionMethod = new NBestSelectionMethod();
        Population newPopulation = nBestSelectionMethod.selectNextPopulation(firstPopulation,4);

        Assert.assertEquals(3,firstPopulation.getSolutions().size());
        Assert.assertEquals(3,newPopulation.getSolutions().size());

        Assert.assertTrue(newPopulation.getSolutions().contains(solution1));
        Assert.assertTrue(newPopulation.getSolutions().contains(solution2));
        Assert.assertTrue(newPopulation.getSolutions().contains(solution3));
    }
}