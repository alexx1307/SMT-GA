package com.aleksiejew.lukasz.Algorithm.MST;

import com.aleksiejew.lukasz.Algorithm.Metrics;
import com.aleksiejew.lukasz.Model.Point;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

public class SimpleSpanningTreeEvaluatorTest extends TestCase {

    Metrics metricsMock= Mockito.mock(Metrics.class);


    public void testEvaluate() throws Exception {

    }

    public void testCreateFullGraph() throws Exception {
        ArrayList<Point> points = new ArrayList<Point>(asList(new Point(0,0),new Point(1,0),new Point(0,1),new Point(1,1)));

        SimpleSpanningTreeEvaluator spanningTreeEvaluator = new SimpleSpanningTreeEvaluator();
        List<Edge> fullGraph = spanningTreeEvaluator.createFullGraph(points, metricsMock);

        Assert.assertEquals(6,fullGraph.size());
    }
}