package com.aleksiejew.lukasz.Model;

/**
 * Created by Luka on 2014-10-31.
 */
public class Point {
    public double x;
    public double y;
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public String toString(){
        return "("+x+","+y+")";
    }
}
