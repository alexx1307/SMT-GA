package com.aleksiejew.lukasz.Model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Luka on 2014-10-31.
 */
public class Population {
    private List<Solution> solutions;

    public List<Solution> getSolutions() {
        return solutions;
    }

    public List<Solution> getSortedSolutions() {
        Collections.sort(solutions);
        return solutions;
    }

    public Population() {
        this.solutions = new LinkedList<Solution>();
    }

    public void addNewSolution(Solution solution) {
        solutions.add(solution);
    }

    public void setSolutions(List<Solution> solutions) {
        this.solutions = solutions;
    }


}
