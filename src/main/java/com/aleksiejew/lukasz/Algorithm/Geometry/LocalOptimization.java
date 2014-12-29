package com.aleksiejew.lukasz.Algorithm.Geometry;

import com.aleksiejew.lukasz.Algorithm.EuclideanMetrics;
import com.aleksiejew.lukasz.Model.Point;
import com.aleksiejew.lukasz.Model.Solution;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by Luka on 2014-12-26.
 */
public class LocalOptimization {
    private static EuclideanMetrics euclid = new EuclideanMetrics();

    public static Solution optimizeSolution(Solution solution){
        SortedSet<Point> steinerPoints = new TreeSet<Point>();
        for (Point point : solution.getSteinerPoints()) {
            List<Point> connectedPoints = solution.getConnectedPointsInSpanningTree(point);
            if (connectedPoints.size() == 3) {
                steinerPoints.add(LocalOptimization.fermatPoint(
                        connectedPoints.get(0),
                        connectedPoints.get(1),
                        connectedPoints.get(2)));
            }
            else if(connectedPoints.size()>3)
                steinerPoints.add(point);
        }
        Solution newSolution = new Solution(steinerPoints,solution.getGeneticAlgorithm());
        return newSolution;
    }
    public static Point fermatPoint(Point a, Point b, Point c) {
        Point abEquilateralVertex = findEquilateralVertex(a, b, c);
        Point acEquilateralVertex = findEquilateralVertex(a, c, b);
        Point fermatPoint = findIntersection(acEquilateralVertex, b, abEquilateralVertex, c);
        return fermatPoint;
    }


    private static Point findEquilateralVertex(Point p1, Point p2, Point contrary) {
        double lengthOfEdge = euclid.dist(p1, p2);
        double heightOfEqTriangle = lengthOfEdge * Math.sqrt(3) / 2.0f;
        Point heightVectorDirection = new Point(p1.y - p2.y, p2.x - p1.x);
        normalize(heightVectorDirection);
        Point heightVector = heightVectorDirection.multiply(heightOfEqTriangle);
        Point startOfHeight = new Point((p1.x + p2.x) / 2.0f, (p1.y + p2.y) / 2.0f);
        Point pp1 = startOfHeight.add(heightVector);
        Point pp2 = startOfHeight.subtract(heightVector);
        double d1 = euclid.dist(pp1, contrary);
        double d2 = euclid.dist(pp2, contrary);
        if (d1 < d2)
            return pp2;
        return pp1;
    }

    private static void normalize(Point heightVectorDirection) {
        double len = euclid.dist(new Point(0, 0), heightVectorDirection);
        if (len > 0) {
            heightVectorDirection.x /= len;
            heightVectorDirection.y /= len;
        }
    }

    private static Point findIntersection(Point equilateralVertex1, Point contrary1, Point equilateralVertex2, Point contrary2) {
        double a1, a2, b1, b2;
        a1 = getA(equilateralVertex1, contrary1);
        a2 = getA(equilateralVertex2, contrary2);

        b1 = getB(contrary1, a1);
        b2 = getB(contrary2, a2);

        double x;
        double y;
        if (Double.isInfinite(a1)) {
            x = contrary1.x;
            y = a2 * x + b2;
            return new Point(x,y);
        }
        if (Double.isInfinite(a2)) {
            x = contrary2.x;
            y = a1 * x + b1;
            return new Point(x,y);
        }
        x = (b2 - b1) / (a1 - a2);
        y = a1 * x + b1;
        return new Point(x, y);
    }

    private static double getB(Point p, double a) {
        return p.y - a * p.x;
    }

    private static double getA(Point p1, Point p2) {
        return (p2.y - p1.y) / (p2.x - p1.x);
    }

}
