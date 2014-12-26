package com.aleksiejew.lukasz;

import com.aleksiejew.lukasz.Testing.TestGenerator;
import com.aleksiejew.lukasz.Testing.TestInstance;
import com.aleksiejew.lukasz.Testing.TestResult;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        //ApplicationContext context = new ClassPathXmlApplicationContext("spring/AppContext.xml");

        List<TestInstance> generatedTests = TestGenerator.generate(10, 10, true);
        for (TestInstance generatedTest : generatedTests) {
            System.out.println(generatedTest.toString());
            TestResult testResult = generatedTest.run();
            System.out.println(testResult.toString());
        }
       /* Problem testProblem = context.getBean(Problem.class);
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
        }*/

    }
}
