package com.aleksiejew.lukasz.Model;

import java.util.List;

/**
 * Created by Luka on 2014-10-31.
 */
public class State {
    private int iteration;
    Population population;
    Population newPopulation;

    public State() {
        this.population = new Population();
        this.newPopulation = new Population();
    }

    public void swapPopulations(){
        population = newPopulation;
        newPopulation = new Population();
    }

    public Population getNewPopulation() {
        return newPopulation;
    }

    public void setNewPopulation(Population newPopulation) {
        this.newPopulation = newPopulation;
    }

    public Population getPopulation() {
        return population;
    }

    public void setPopulation(Population population) {
        this.population = population;
    }


    public int getIteration() {
        return iteration;
    }

    public void setIteration(int iteration) {
        this.iteration = iteration;
    }
}
