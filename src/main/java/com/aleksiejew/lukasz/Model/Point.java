package com.aleksiejew.lukasz.Model;

/**
 * Created by Luka on 2014-10-31.
 */
public class Point implements Comparable<Point> {
    public double x;
    public double y;
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public String toString(){
        return ""+x+", "+y+"";
    }


    @Override
    public int compareTo(Point o) {
        int compare = Double.compare(x, o.x);
        if(compare!=0)
            return compare;
        return Double.compare(y,o.y);
    }

    public Point add(Point other) {
        return new Point(this.x+other.x,this.y+other.y);
    }

    public Point subtract(Point other) {
        return new Point(this.x-other.x,this.y-other.y);
    }

    public Point multiply(double multiplier) {
        return new Point(this.x*multiplier,this.y*multiplier);
    }
}
