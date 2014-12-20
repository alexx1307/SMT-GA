package com.aleksiejew.lukasz.Algorithm.GeneticOperators;

import com.aleksiejew.lukasz.Model.Solution;

/**
 * Created by Luka on 2014-10-31.
 */
public interface Mutation {
    Solution mutate(Solution solution);
}
