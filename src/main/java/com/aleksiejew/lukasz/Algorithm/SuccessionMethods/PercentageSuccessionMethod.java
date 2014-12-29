package com.aleksiejew.lukasz.Algorithm.SuccessionMethods;

import com.aleksiejew.lukasz.Algorithm.SelectionMethods.NBestSelection;
import com.aleksiejew.lukasz.Model.Population;

/**
 * Created by Luka on 2014-12-29.
 */
public class PercentageSuccessionMethod implements SuccessionMethod {

    @Override
    public Population resolveNewGeneration(Population oldPopulation, Population newPopulation, double percentage) {
        Population nextPopulation = new Population();
        NBestSelection nBestSelection = new NBestSelection();
        nextPopulation.getSolutions().addAll(newPopulation.getSolutions());
        int solutionsFromOldPopulation = (int)(percentage *(double) oldPopulation.getSolutions().size());
        nextPopulation.getSolutions().addAll(nBestSelection.selectNextPopulation(oldPopulation,solutionsFromOldPopulation).getSolutions());
        return nextPopulation;
    }
}
