package com.aleksiejew.lukasz.Algorithm.SelectionMethods;

import com.aleksiejew.lukasz.Model.Population;
import com.aleksiejew.lukasz.Model.Solution;

import java.util.*;

/**
 * Created by Luka on 2014-12-29.
 */
public class TournamentSelection implements SelectionMethod {
    private static Random random = new Random();
    private Solution[] solutions;
    private boolean[] chosenIndices;
    @Override
    public Population selectNextPopulation(Population oldPopulation, int newPopulationSize) {
        solutions = (Solution[]) oldPopulation.getSolutions().toArray();
        if(solutions.length<=newPopulationSize)
            return oldPopulation;
        Population nextPopulation = new Population();

        chosenIndices = new boolean[solutions.length];
        for (int i = 0; i < chosenIndices.length; i++) {
            chosenIndices[i] = false;
        }

        for (int i = 0; i < newPopulationSize; i++) {
            int i1, i2, tries=0;
            do {
                i1 = random.nextInt(solutions.length);
                tries++;
                if(tries>5) i1=linearSearch();
            } while (chosenIndices[i1] == true);
            tries =0;
            do {
                i2 = random.nextInt(solutions.length);
                tries++;
                if(tries>5) i1=linearSearch();
            } while (i2 == i1 && chosenIndices[i2] == true);

            if (i1 < i2) {
                nextPopulation.addNewSolution(solutions[i1]);
                chosenIndices[i1]=true;
            } else {
                nextPopulation.addNewSolution(solutions[i2]);
                chosenIndices[i2]=true;
            }

        }

        return nextPopulation;
    }

    int lastFreeIndex=0;
    private int linearSearch() {
        for (int i = lastFreeIndex; i < chosenIndices.length; i++) {
            if(chosenIndices[i]==true)
                lastFreeIndex = i;
                return i;
        }
        return 0;
    }
}
