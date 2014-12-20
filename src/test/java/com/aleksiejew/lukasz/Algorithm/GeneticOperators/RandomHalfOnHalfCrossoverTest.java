package com.aleksiejew.lukasz.Algorithm.GeneticOperators;

import com.aleksiejew.lukasz.Algorithm.GeneticAlgorithm;
import com.aleksiejew.lukasz.Model.Point;
import com.aleksiejew.lukasz.Model.Solution;
import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.*;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;

public class RandomHalfOnHalfCrossoverTest {
    static Point point11;
    static Point commonPoint;
    static Point samePoint1;
    static Point samePoint2;
    static Point point21;
    static Point point22;

    @BeforeClass
    public static void init() {
        point11 = mock(Point.class);
        point21 = mock(Point.class);
        point22 = mock(Point.class);
        samePoint1 = mock(Point.class);
        samePoint2 = mock(Point.class);
        commonPoint = mock(Point.class);
    }

    @Test
    public void testCross() throws Exception {

        SortedSet<Point> set1 = new TreeSet<Point>(asList(point11, samePoint1));
        SortedSet<Point> set2 = new TreeSet<Point>(asList(point21, point22));
        GeneticAlgorithm geneticAlgorithm = mock(GeneticAlgorithm.class);
        Solution solution1 = new Solution(set1, geneticAlgorithm);
        Solution solution2 = new Solution(set2, geneticAlgorithm);

        Random random = mock(Random.class);
        stub(random.nextInt(2)).toReturn(0).toReturn(1);

        RandomHalfOnHalfCrossover crossover = new RandomHalfOnHalfCrossover();
        crossover.setRandom(random);

        Solution result = crossover.cross(solution1, solution2);

        Assert.assertEquals(2, result.getSteinerPoints().size());
        Assert.assertTrue(result.getSteinerPoints().contains(point11));
        Assert.assertTrue(result.getSteinerPoints().contains(point22));
    }
}