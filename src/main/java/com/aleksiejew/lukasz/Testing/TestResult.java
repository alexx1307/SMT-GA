package com.aleksiejew.lukasz.Testing;

import com.aleksiejew.lukasz.Model.Population;
import com.aleksiejew.lukasz.Model.Solution;
import com.aleksiejew.lukasz.Model.State;

/**
 * Created by Luka on 2014-12-26.
 */
public class TestResult {
    State lastState;

    public TestResult(State lastState) {
        this.lastState = lastState;
    }

    @Override
    public String toString() {
        int i = 1;
        StringBuilder stringBuilder = new StringBuilder();
        /*for (Solution solution : lastState.getPopulation().getSolutions()) {
            stringBuilder.append("solution nr " + i + " = " + solution+"\n");
            i++;
        }*/
        Solution bestSolution = lastState.getPopulation().getBestSolution();
        stringBuilder.append(bestSolution.toString()+"\n");
        return stringBuilder.toString();
    }
}
