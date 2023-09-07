package domini.tests;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class SuiteRunner {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestSuite.class);
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("Quantitat de fallades: " + result.getFailureCount());
    }
}
