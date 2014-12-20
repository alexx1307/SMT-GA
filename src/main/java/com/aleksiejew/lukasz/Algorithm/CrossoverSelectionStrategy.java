package com.aleksiejew.lukasz.Algorithm;

import com.aleksiejew.lukasz.Model.Population;
import com.aleksiejew.lukasz.Model.Solution;
import javafx.util.Pair;

import java.util.List;
import java.util.Random;

/**
 * Created by Luka on 2014-12-19.
 */
public interface CrossoverSelectionStrategy {
    List<Pair<Solution,Solution>> choosePairsToCross( Population population, int numberOfPairs);
}
