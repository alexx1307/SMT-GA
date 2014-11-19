package com.aleksiejew.lukasz.Algorithm;

import com.aleksiejew.lukasz.Model.Point;

/**
 * Created by Luka on 2014-11-19.
 */
public class EuclideanMetrics implements Metrics {
    @Override
    public double dist(Point p1, Point p2) {
        return Math.sqrt((p1.x-p2.x)*(p1.x-p2.x)+(p1.y-p2.y)*(p1.y-p2.y));
    }
}
