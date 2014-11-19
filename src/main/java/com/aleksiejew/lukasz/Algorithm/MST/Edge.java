package com.aleksiejew.lukasz.Algorithm.MST;

import com.aleksiejew.lukasz.Model.Point;

/**
 * Created by Luka on 2014-11-19.
 */
public class Edge  implements Comparable<Edge>{
    public int p1;
    public int p2;
    public final double dist;

    public Edge(int p1, int p2, double dist) {
        this.p1 = p1;
        this.p2 = p2;
        this.dist = dist;
    }


    @Override
    public int compareTo(Edge o) {
        int comparisonResult = Double.compare(dist, o.dist);
        return comparisonResult != 0?comparisonResult: Integer.compare(this.hashCode(), o.hashCode());
    }
}
