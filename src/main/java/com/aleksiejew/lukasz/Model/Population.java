package com.aleksiejew.lukasz.Model;

import java.util.*;

/**
 * Created by Luka on 2014-10-31.
 */
public class Population {
    private SortedSet<Solution> solutions;

    public SortedSet<Solution> getSolutions() {
        return solutions;
    }


    public Population() {
        this.solutions = new TreeSet<Solution>();
    }

    public void addNewSolution(Solution solution) {
        solutions.add(solution);
    }

    public void setSolutions(SortedSet<Solution> solutions) {
        this.solutions = solutions;
    }


    public Solution getBestSolution() {
        //System.out.println(solutions.size());
        return solutions.first();
    }
}
