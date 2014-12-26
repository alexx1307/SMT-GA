package com.aleksiejew.lukasz.Model;

import org.springframework.beans.factory.annotation.Required;

import java.util.List;

/**
 * Created by Luka on 2014-10-31.
 */
public class Problem {

    private Double xBorder;
    private Double yBorder;
    private List<Point> terminals;

    public List<Point> getTerminals() {
        return terminals;
    }

    public void setTerminals(List<Point> terminals) {
        this.terminals = terminals;
    }

    public Double getxBorder() {
        return xBorder;
    }

    @Required
    public void setxBorder(Double xBorder) {
        this.xBorder = xBorder;
    }

    public Double getyBorder() {
        return yBorder;
    }

    @Required
    public void setyBorder(Double yBorder) {
        this.yBorder = yBorder;
    }


    @Override
    public String toString() {
        return "Problem{" +
                "xBorder=" + xBorder +
                ", yBorder=" + yBorder +
                ", terminals=" + terminals +
                '}';
    }
}
