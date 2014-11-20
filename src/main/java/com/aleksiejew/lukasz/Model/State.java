package com.aleksiejew.lukasz.Model;

import java.util.List;

/**
 * Created by Luka on 2014-10-31.
 */
public class State {
    private int iteration;

    public State() {
        this.population = new Population();
    }

    public Population getPopulation() {
        return population;
    }

    public void setPopulation(Population population) {
        this.population = population;
    }

    Population population;

    public int getIteration() {
        return iteration;
    }

    public void setIteration(int iteration) {
        this.iteration = iteration;
    }
}
