package com.aleksiejew.lukasz.Algorithm;

import com.aleksiejew.lukasz.Model.State;

/**
 * Created by Luka on 2014-10-31.
 */
public interface StopCriterion {

    void init();
    boolean check(State state);
    void update(State state);
}
