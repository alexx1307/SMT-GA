package com.aleksiejew.lukasz.Algorithm.Criterions;

import com.aleksiejew.lukasz.Model.State;
import org.springframework.beans.factory.annotation.Required;

/**
 * Created by Luka on 2014-11-20.
 */
public class MaxIterationNumberCriterion implements StopCriterion {


    int maxIterationNumber;

    @Override
    public void init() {

    }

    @Override
    public boolean check(State state) {
        int current = state.getIteration();
        return current<maxIterationNumber;
    }

    public int getMaxIterationNumber() {
        return maxIterationNumber;
    }

    @Required
    public void setMaxIterationNumber(int maxIterationNumber) {
        this.maxIterationNumber = maxIterationNumber;
    }

}
