package com.aleksiejew.lukasz;

import com.aleksiejew.lukasz.Algorithm.GeneticAlgorithm;
import com.aleksiejew.lukasz.Model.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("hej");
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/AppContext.xml");

        Problem testProblem = context.getBean(Problem.class);
        for (Point p : testProblem.getTerminals()) {
            System.out.println("p=" + p);
        }

        GeneticAlgorithm algorithm = context.getBean(GeneticAlgorithm.class);
        algorithm.setProblem(testProblem);
        //algorithm.createFirstGeneration();
        algorithm.doSimulation();

        State currentState = algorithm.getCurrentState();
        Population population = currentState.getPopulation();
        int i = 1;
        for (Solution solution : population.getSolutions()) {
            System.out.println("solution nr " + i + " = " + solution);
            i++;
        }

    }
}
