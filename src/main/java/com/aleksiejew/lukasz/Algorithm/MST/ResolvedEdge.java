package com.aleksiejew.lukasz.Algorithm.MST;

import com.aleksiejew.lukasz.Model.Point;

/**
 * Created by Luka on 2014-12-27.
 */
public class ResolvedEdge {
    Point p1;
    Point p2;

    public ResolvedEdge(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }
}
