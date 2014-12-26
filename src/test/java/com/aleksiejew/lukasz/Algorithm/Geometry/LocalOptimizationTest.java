package com.aleksiejew.lukasz.Algorithm.Geometry;

import com.aleksiejew.lukasz.Model.Point;
import junit.framework.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class LocalOptimizationTest {

    @Test
    public void testFermatPoint() throws Exception {
        Point[] triangle = {new Point(0,0),new Point(1,0),new Point(0.5f,Math.sqrt(3)/2.0f)};

        Point fermatPoint1 = new LocalOptimization().fermatPoint(triangle[0],triangle[1],triangle[2]);
        Point fermatPoint2 = new LocalOptimization().fermatPoint(triangle[1],triangle[0],triangle[2]);
        Point fermatPoint3 = new LocalOptimization().fermatPoint(triangle[2],triangle[1],triangle[0]);

        Point expectedPoint = new Point(0.5,Math.sqrt(3)/6);

        Assert.assertEquals(expectedPoint.x,fermatPoint1.x,0.01f);
        Assert.assertEquals(expectedPoint.x,fermatPoint2.x,0.01f);
        Assert.assertEquals(expectedPoint.x,fermatPoint3.x,0.01f);

        Assert.assertEquals(expectedPoint.y,fermatPoint1.y,0.01f);
        Assert.assertEquals(expectedPoint.y,fermatPoint2.y,0.01f);
        Assert.assertEquals(expectedPoint.y,fermatPoint3.y,0.01f);


    }
}