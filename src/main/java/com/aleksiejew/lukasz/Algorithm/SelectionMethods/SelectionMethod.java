package com.aleksiejew.lukasz.Algorithm.SelectionMethods;

import com.aleksiejew.lukasz.Model.Population;

/**
 * Created by Luka on 2014-11-20.
 */
public interface SelectionMethod {
    Population selectNextPopulation(Population oldPopulation,Population newPopulation, int newPopulationSize);
}
