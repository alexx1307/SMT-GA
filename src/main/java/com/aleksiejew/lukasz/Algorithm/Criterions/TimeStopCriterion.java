package com.aleksiejew.lukasz.Algorithm.Criterions;

import com.aleksiejew.lukasz.Model.State;

import java.util.Timer;

/**
 * Created by Luka on 2014-12-29.
 */
public class TimeStopCriterion implements StopCriterion {
    long maxMillisTime;
    long startTime;

    public TimeStopCriterion(int seconds) {
        this.maxMillisTime = seconds*1000;
    }

    @Override
    public void init() {
        startTime = System.currentTimeMillis();
    }

    @Override
    public boolean checkIfFullfiled(State state) {
        long elapsedTime = System.currentTimeMillis() - startTime;
        if(elapsedTime>maxMillisTime)
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "TimeStopCriterion{" +
                "maxMillisTime=" + maxMillisTime +
                ", startTime=" + startTime +
                '}';
    }
}
