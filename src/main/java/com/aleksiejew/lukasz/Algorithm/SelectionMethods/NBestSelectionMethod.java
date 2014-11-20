package com.aleksiejew.lukasz.Algorithm.SelectionMethods;

import com.aleksiejew.lukasz.Model.Population;
import com.aleksiejew.lukasz.Model.Solution;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Luka on 2014-11-20.
 */
public class NBestSelectionMethod implements SelectionMethod {

    @Override
    public Population selectNextPopulation(Population population, int newPopulationSize) {
        Population newPopulation = new Population();
        List<Solution> sortedSolutions = new LinkedList<Solution>(population.getSortedSolutions());
        newPopulation.setSolutions(sortedSolutions.subList(0, newPopulationSize > sortedSolutions.size() ? sortedSolutions.size() : newPopulationSize));
        return newPopulation;
    }
}
