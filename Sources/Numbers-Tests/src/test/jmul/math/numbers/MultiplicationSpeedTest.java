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
import jmul.math.operations.processing.ProcessingDetails;
import jmul.math.operations.repository.OperationIdentifiers;

import jmul.test.classification.ManualTest;

import org.junit.Ignore;


/**
 * This test suite compares the speed of different multiplication algorithms.
 *
 * @author Kristian Kutin
 */
@Ignore
@ManualTest
public class MultiplicationSpeedTest {

    /**
     * The main method.
     *
     * @param args
     *        command line arguments (i.e. these are ignored in this context)
     */
    public static void main(String... args) {

        compareLongMultiplicationWithMultiplicationByAddition();
    }

    /**
     * Runs a set of test date with different multiplication algorithms.
     */
    public static void compareLongMultiplicationWithMultiplicationByAddition() {

        System.out.println("create test data");
        List<TestCase> testCases = createTestdata();
        int testCount = testCases.size();
        System.out.println("created " + testCount + " tests");

        System.out.println("test multiplication by addition");
        long start1 = System.currentTimeMillis();
        float successRate1 = testMultiplicationByAddition(testCases);
        long end1 = System.currentTimeMillis();
        long duration1 = end1 - start1;
        System.out.println("duration " + duration1 + " ms with a success rate of " + (int) (successRate1 * 100) + "%");

        System.out.println("test long multiplication");
        long start2 = System.currentTimeMillis();
        float successRate2 = testLongMultiplication(testCases);
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
    public static float testMultiplicationByAddition(List<TestCase> testCases) {

        Map<TestCase, Number> failedResults = new HashMap<>();
        Map<TestCase, Number> successfulResults = new HashMap<>();

        for (TestCase testCase : testCases) {

            Number actualResult;
            try {

                ProcessingDetails processingDetails =
                    ProcessingDetails.setProcessingDetails(OperationIdentifiers.MULTIPLY_NUMBERS_BY_ADDITION_FUNCTION,
                                                           ProcessingDetails.DEFAULT_PRECISION,
                                                           ProcessingDetails.DEFAULT_ITERATION_DEPTH);

                actualResult = testCase.operand1.multiply(processingDetails, testCase.operand2);

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
    public static float testLongMultiplication(List<TestCase> testCases) {

        Map<TestCase, Number> failedResults = new HashMap<>();
        Map<TestCase, Number> successfulResults = new HashMap<>();

        for (TestCase testCase : testCases) {

            Number actualResult;
            try {

                ProcessingDetails processingDetails =
                    ProcessingDetails.setProcessingDetails(OperationIdentifiers.LONG_MULTIPLICATION_FUNCTION,
                                                           ProcessingDetails.DEFAULT_PRECISION,
                                                           ProcessingDetails.DEFAULT_ITERATION_DEPTH);

                actualResult = testCase.operand1.multiply(processingDetails, testCase.operand2);

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

                int result = a * b;
                TestCase testCase = new TestCase(a, b, result);

                testCases.add(testCase);
            }
        }

        return testCases;
    }

}


