package com.aleksiejew.lukasz.Tools;

import java.util.Random;

/**
 * Created by Luka on 2014-12-19.
 */
public class CommonTools {

    static Random random = new Random();

    public static boolean passProbabilityTest(double probability) {
        return random.nextDouble() < probability;
    }
}
