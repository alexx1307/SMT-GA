package com.aleksiejew.lukasz.Generators;

import com.aleksiejew.lukasz.Model.Point;
import com.aleksiejew.lukasz.Model.Problem;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by Luka on 2014-10-31.
 */
public class GridProblemGenerator {
    public List<Point> generate(int xBound, int yBound) {
        Problem problem = new Problem();
        List<Point> points = new LinkedList<Point>();
        for (int i = 0; i < xBound; i++) {
            for (int j = 0; j < yBound; j++) {
                points.add(new Point(i, j));
            }
        }
        return points;

    }
}
