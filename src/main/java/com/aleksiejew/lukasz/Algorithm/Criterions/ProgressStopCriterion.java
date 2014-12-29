package com.aleksiejew.lukasz.Algorithm.Criterions;

import com.aleksiejew.lukasz.Model.State;

/**
 * Created by Luka on 2014-12-29.
 */
public class ProgressStopCriterion implements StopCriterion {
    private int iterationsNumber;
    private double percentageProgressThreshold;

    public ProgressStopCriterion(int iterationsNumber, double percentageProgressThreshold) {
        this.iterationsNumber = iterationsNumber;
        this.percentageProgressThreshold = percentageProgressThreshold;
    }

    @Override
    public void init() {

    }

    @Override
    public boolean checkIfFullfiled(State state) {
        int currentIteration = state.getIteration();
        if (currentIteration <= iterationsNumber)
            return false;
        else {
            double resultToCompare = state.getResultAfterIteration(currentIteration - iterationsNumber);
            double currentResult = state.getBestSolution().getEvaluatedResult().getCostValue();
            double percentageChange = (resultToCompare - currentResult) / resultToCompare * 100.0f;
            if (percentageChange < percentageProgressThreshold)
                return true;
            else return false;
        }
    }

    @Override
    public String toString() {
        return "ProgressStopCriterion{" +
                "iterationsNumber=" + iterationsNumber +
                ", percentageProgressThreshold=" + percentageProgressThreshold +
                '}';
    }
}
