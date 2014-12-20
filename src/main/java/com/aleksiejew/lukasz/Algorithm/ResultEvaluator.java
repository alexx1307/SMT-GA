package com.aleksiejew.lukasz.Algorithm;

import com.aleksiejew.lukasz.Model.Point;
import com.aleksiejew.lukasz.Model.Problem;

import java.util.List;
import java.util.SortedSet;

/**
 * Created by Luka on 2014-11-19.
 */
public interface ResultEvaluator {
    double evaluate(GeneticAlgorithm algorithm, SortedSet<Point> steinerPoints);
}
