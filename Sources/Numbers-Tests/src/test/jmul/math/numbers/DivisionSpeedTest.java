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
import java.util.List;

import jmul.math.operations.processing.ProcessingDetails;
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
        //List<TestCase> testCases = createTestdata();
        List<TestCase> testCases = createTestdata2();
        int testCount = testCases.size();
        System.out.println("created " + testCount + " tests");

        System.out.println("test division by subtraction");
        long start1 = System.currentTimeMillis();
        testRussianDivision(testCases);
        long end1 = System.currentTimeMillis();
        long duration1 = end1 - start1;
        System.out.println("duration " + duration1 + " ms.");

        /*System.out.println("test russian division");
        long start2 = System.currentTimeMillis();
        testDivisionBySubtraction(testCases);
        long end2 = System.currentTimeMillis();
        long duration2 = end2 - start2;
        System.out.println("duration " + duration2 + " ms");*/

        System.out.println("test long division");
        long start3 = System.currentTimeMillis();
        testDivisionBySubtraction(testCases);
        long end3 = System.currentTimeMillis();
        long duration3 = end3 - start3;
        System.out.println("duration " + duration3 + " ms");
    }

    /**
     * Tests the division by subtraction with the specified test cases.
     *
     * @param testCases
     *        all test cases
     */
    public static void testDivisionBySubtraction(List<TestCase> testCases) {

        ProcessingDetails processingDetails =
            ProcessingDetails.setProcessingDetails(OperationIdentifiers.DIVIDE_NUMBERS_BY_SUBTRACTION,
                                                   ProcessingDetails.DEFAULT_PRECISION,
                                                   ProcessingDetails.DEFAULT_ITERATION_DEPTH);

        int max = testCases.size();

        for (int index = 0; index < max; index++) {

            try {

                TestCase testCase = testCases.get(index);
                testCase.operand1.divide(processingDetails, testCase.operand2);

            } catch (Exception e) {

                // ignore
            }

            if (index % 200 == 0) {

                System.out.print(".");
            }
        }

        System.out.println();
    }

    /**
     * Tests the russian division with the specified test cases.
     *
     * @param testCases
     *        all test cases
     */
    public static void testRussianDivision(List<TestCase> testCases) {

        ProcessingDetails processingDetails =
            ProcessingDetails.setProcessingDetails(OperationIdentifiers.RUSSIAN_DIVISION_FUNCTION,
                                                   ProcessingDetails.DEFAULT_PRECISION,
                                                   ProcessingDetails.DEFAULT_ITERATION_DEPTH);

        int max = testCases.size();

        for (int index = 0; index < max; index++) {

            try {

                TestCase testCase = testCases.get(index);
                testCase.operand1.divide(processingDetails, testCase.operand2);

            } catch (Exception e) {

                // ignore
            }

            if (index % 200 == 0) {

                System.out.print(".");
            }
        }

        System.out.println();
    }

    /**
     * Tests the long division with the specified test cases.
     *
     * @param testCases
     *        all test cases
     */
    public static void testLongDivision(List<TestCase> testCases) {

        ProcessingDetails processingDetails =
            ProcessingDetails.setProcessingDetails(OperationIdentifiers.LONG_DIVISION,
                                                   ProcessingDetails.DEFAULT_PRECISION,
                                                   ProcessingDetails.DEFAULT_ITERATION_DEPTH);

        int max = testCases.size();

        for (int index = 0; index < max; index++) {

            try {

                TestCase testCase = testCases.get(index);
                testCase.operand1.divide(processingDetails, testCase.operand2);

            } catch (Exception e) {

                // ignore
            }

            if (index % 200 == 0) {

                System.out.print(".");
            }
        }

        System.out.println();
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

                int result = a * b;
                TestCase testCase = new TestCase(result, b, a);

                testCases.add(testCase);
            }
        }

        return testCases;
    }

    /**
     * Creates a set of test cases.
     *
     * @return a set of test cases
     */
    public static List<TestCase> createTestdata2() {

        List<TestCase> testCases = new ArrayList<>();

        for (int a = -100; a <= 100; a++) {

            for (int b = -100; b <= 100; b++) {

                if (b == 0) {

                    continue;
                }

                int c = a * a * a;
                int result = c * b;
                TestCase testCase = new TestCase(result, b, c);

                testCases.add(testCase);
            }
        }

        /*for (int a = -100; a <= 100; a++) {

            for (int b = -100; b <= 100; b++) {

                if (b == 0) {

                    continue;
                }

                int c = a * a * a;
                int result = c * b;
                TestCase testCase = new TestCase(result, b, c);

                testCases.add(testCase);
            }
        }*/

        return testCases;
    }

}
