package com.aleksiejew.lukasz.Generators;

import com.aleksiejew.lukasz.Model.Point;
import com.aleksiejew.lukasz.Model.Problem;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by Luka on 2014-10-31.
 */
public class RandomProblemGenerator {
    public List<Point> generate(int xBound, int yBound, int numberOfPoints) {
        Random random = new Random();
        Problem problem = new Problem();
        List<Point> points = new LinkedList<Point>();
        for (int i = 0; i < numberOfPoints; i++) {
            points.add(new Point(xBound*random.nextDouble(), yBound*random.nextDouble()));
        }
        return points;
    }
}
