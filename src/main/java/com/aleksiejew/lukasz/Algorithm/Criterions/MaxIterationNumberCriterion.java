package com.aleksiejew.lukasz.Algorithm.Criterions;

import com.aleksiejew.lukasz.Model.State;
import org.springframework.beans.factory.annotation.Required;

/**
 * Created by Luka on 2014-11-20.
 */
public class MaxIterationNumberCriterion implements StopCriterion {


    int maxIterationNumber;

    public MaxIterationNumberCriterion(int maxIterationNumber) {
        this.maxIterationNumber = maxIterationNumber;
    }

    @Override
    public void init() {

    }

    @Override
    public boolean check(State state) {
        int current = state.getIteration();
        return current>maxIterationNumber;
    }

}
