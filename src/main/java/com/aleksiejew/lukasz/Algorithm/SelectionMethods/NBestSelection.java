package com.aleksiejew.lukasz.Algorithm.SelectionMethods;

import com.aleksiejew.lukasz.Model.Population;
import com.aleksiejew.lukasz.Model.Solution;

import java.util.Iterator;

/**
 * Created by Luka on 2014-11-20.
 */
public class NBestSelection implements SelectionMethod {

    @Override
    public Population selectNextPopulation(Population oldPopulation, int newPopulationSize) {
        Population nextPopulation = new Population();

        Iterator<Solution> iterator = oldPopulation.getSolutions().iterator();

        int i = 0;
        while (iterator.hasNext() && i < newPopulationSize) {
            nextPopulation.addNewSolution(iterator.next());
            i++;
        }

        return nextPopulation;
    }
}
