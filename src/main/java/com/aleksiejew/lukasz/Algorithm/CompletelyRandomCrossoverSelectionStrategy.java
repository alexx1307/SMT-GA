package com.aleksiejew.lukasz.Algorithm;

import com.aleksiejew.lukasz.Model.Population;
import com.aleksiejew.lukasz.Model.Solution;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by Luka on 2014-12-19.
 */
public class CompletelyRandomCrossoverSelectionStrategy implements CrossoverSelectionStrategy {

    Random random = new Random();

    @Override
    public List<Pair<Solution, Solution>> choosePairsToCross(Population population, int numberOfPairs) {
        List<Pair<Solution, Solution>> list = new LinkedList<Pair<Solution, Solution>>();
        ArrayList<Solution> solutions = new ArrayList<Solution>(population.getSolutions());
        for (int i = 0; i < numberOfPairs; i++) {
            Solution solution1 = solutions.get(random.nextInt(solutions.size()));
            Solution solution2;
            do {
                solution2 = solutions.get(random.nextInt(solutions.size()));
            } while (solution2.equals(solution1));

            list.add(new Pair<Solution, Solution>(solution1, solution2));
        }
        return list;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

}
