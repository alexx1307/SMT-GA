package com.aleksiejew.lukasz.Algorithm.Criterions;

import com.aleksiejew.lukasz.Model.State;

/**
 * Created by Luka on 2014-10-31.
 */
public interface StopCriterion {
    void init();
    boolean checkIfFullfiled(State state);
}
