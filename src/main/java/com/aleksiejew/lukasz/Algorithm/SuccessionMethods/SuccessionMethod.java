package com.aleksiejew.lukasz.Algorithm.SuccessionMethods;

import com.aleksiejew.lukasz.Model.Population;

/**
 * Created by Luka on 2014-12-29.
 */
public interface SuccessionMethod {
    public Population resolveNewGeneration(Population oldPopulation, Population newPopulation,double percentage);
}
