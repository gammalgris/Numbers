/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2025  Kristian Kutin
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * e-mail: kristian.kutin@arcor.de
 */

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package test.jmul.math.numbers;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jmul.math.numbers.Number;
import jmul.math.operations.ProcessingDetails;
import jmul.math.operations.repository.OperationIdentifiers;

import jmul.test.classification.ManualTest;

import org.junit.Ignore;


/**
 * This test suite compares the speed of different division algorithms.
 *
 * @author Kristian Kutin
 */
@Ignore
@ManualTest
public class DivisionSpeedTest {

    /**
     * The main method.
     *
     * @param args
     *        command line arguments (i.e. these are ignored in this context)
     */
    public static void main(String... args) {

        compareRussionaDivisionWithDivisionBySubtraction();
    }

    /**
     * Runs a set of test date with different division algorithms.
     */
    public static void compareRussionaDivisionWithDivisionBySubtraction() {

        System.out.println("create test data");
        List<TestCase> testCases = createTestdata();
        int testCount = testCases.size();
        System.out.println("created " + testCount + " tests");

        System.out.println("test multiplication by subtraction");
        long start1 = System.currentTimeMillis();
        float successRate1 = testRussianDivision(testCases);
        long end1 = System.currentTimeMillis();
        long duration1 = end1 - start1;
        System.out.println("duration " + duration1 + " ms with a success rate of " + (int) (successRate1 * 100) + "%");

        System.out.println("test long multiplication");
        long start2 = System.currentTimeMillis();
        float successRate2 = testDivisionBySubtraction(testCases);
        long end2 = System.currentTimeMillis();
        long duration2 = end2 - start2;
        System.out.println("duration " + duration2 + " ms with a success rate of " + (int) (successRate2 * 100) + "%");
    }

    /**
     * Tests the long division with the specified test cases.
     *
     * @param testCases
     *        all test cases
     *
     * @return a success rate
     */
    public static float testDivisionBySubtraction(List<TestCase> testCases) {

        Map<TestCase, Number> failedResults = new HashMap<>();
        Map<TestCase, Number> successfulResults = new HashMap<>();

        ProcessingDetails processingDetails = new ProcessingDetails(OperationIdentifiers.DIVIDE_NUMBERS_BY_SUBTRACTION);

        for (TestCase testCase : testCases) {

            Number actualResult;
            try {

                actualResult = testCase.operand1.divide(processingDetails, testCase.operand2);

                if (testCase.expectedResult.equals(actualResult)) {

                    successfulResults.put(testCase, actualResult);

                } else {

                    failedResults.put(testCase, actualResult);
                }

            } catch (Exception e) {

                failedResults.put(testCase, null);
            }
        }

        float maxTests = (float) testCases.size();
        float successfulTests = (float) successfulResults.size();
        float successRate = successfulTests / maxTests;

        return successRate;
    }

    /**
     * Tests the long division with the specified test cases.
     *
     * @param testCases
     *        all test cases
     *
     * @return a success rate
     */
    public static float testRussianDivision(List<TestCase> testCases) {

        Map<TestCase, Number> failedResults = new HashMap<>();
        Map<TestCase, Number> successfulResults = new HashMap<>();

        ProcessingDetails processingDetails = new ProcessingDetails(OperationIdentifiers.DIVIDE_NUMBERS_BY_SUBTRACTION);

        for (TestCase testCase : testCases) {

            Number actualResult;
            try {

                actualResult = testCase.operand1.divide(processingDetails, testCase.operand2);

                if (testCase.expectedResult.equals(actualResult)) {

                    successfulResults.put(testCase, actualResult);

                } else {

                    failedResults.put(testCase, actualResult);
                }

            } catch (Exception e) {

                failedResults.put(testCase, null);
            }
        }

        float maxTests = (float) testCases.size();
        float successfulTests = (float) successfulResults.size();
        float successRate = successfulTests / maxTests;

        return successRate;
    }

    /**
     * Creates a set of test cases.
     *
     * @return a set of test cases
     */
    public static List<TestCase> createTestdata() {

        List<TestCase> testCases = new ArrayList<>();

        for (int a = -100; a <= 100; a++) {

            for (int b = -100; b <= 100; b++) {

                if (b == 0) {

                    continue;
                }

                int result = a / b;
                TestCase testCase = new TestCase(result, b, a);

                testCases.add(testCase);
            }
        }

        return testCases;
    }

}
