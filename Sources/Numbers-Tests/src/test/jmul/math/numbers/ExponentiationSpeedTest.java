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


import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.numbers.Number;
import jmul.math.operations.processing.ProcessingDetails;

import jmul.math.operations.repository.OperationIdentifiers;

import jmul.test.classification.ManualTest;


/**
 * A manual test to test calculation speed of various exponentiation algorithms.
 *
 * @author Kristian Kutin
 */
@ManualTest
public class ExponentiationSpeedTest {

    /**
     * The main method (i.e. starts the test)
     *
     * @param args
     *        command line arguments are ignored
     */
    public static void main(String... args) {

        testSingleExponentiationWithSmallNumbers();
        testSingleExponentiationWithMediumNumbers();
        testSingleExponentiationWithLargeNumbers();

        testConcurrentSingleExponentiationWithSmallNumbers();
        testConcurrentSingleExponentiationWithMediumNumbers();
        testConcurrentSingleExponentiationWithLargeNumbers();
    }

    private static void testSingleExponentiationWithSmallNumbers() {

        Number n = createNumber(10, "10");
        Number exponent = createNumber(10, "10");

        ProcessingDetails processingDetails =
            ProcessingDetails.setProcessingDetails(ProcessingDetails.DEFAULT_ALGORITHM,
                                                   ProcessingDetails.DEFAULT_PRECISION,
                                                   ProcessingDetails.DEFAULT_ITERATION_DEPTH);

        long start = System.currentTimeMillis();

        n.exponentiate(processingDetails, exponent);

        long end = System.currentTimeMillis();
        long delta = end - start;

        System.out.println("duration " + delta + " ms");
    }

    private static void testSingleExponentiationWithMediumNumbers() {

        Number n = createNumber(10, "123.123456789");
        Number exponent = createNumber(10, "1000");

        ProcessingDetails processingDetails =
            ProcessingDetails.setProcessingDetails(ProcessingDetails.DEFAULT_ALGORITHM,
                                                   ProcessingDetails.DEFAULT_PRECISION,
                                                   ProcessingDetails.DEFAULT_ITERATION_DEPTH);

        long start = System.currentTimeMillis();

        n.exponentiate(processingDetails, exponent);

        long end = System.currentTimeMillis();
        long delta = end - start;

        System.out.println("duration " + delta + " ms");
    }

    private static void testSingleExponentiationWithLargeNumbers() {

        Number n = createNumber(10, "123456.123456789");
        Number exponent = createNumber(10, "1000");

        ProcessingDetails processingDetails =
            ProcessingDetails.setProcessingDetails(ProcessingDetails.DEFAULT_ALGORITHM, createNumber(10, "5"),
                                                   ProcessingDetails.DEFAULT_ITERATION_DEPTH);

        long start = System.currentTimeMillis();

        n.exponentiate(processingDetails, exponent);

        long end = System.currentTimeMillis();
        long delta = end - start;

        System.out.println("duration " + delta + " ms");
    }

    private static void testConcurrentSingleExponentiationWithSmallNumbers() {

        Number n = createNumber(10, "10");
        Number exponent = createNumber(10, "10");

        ProcessingDetails processingDetails =
            ProcessingDetails.setProcessingDetails(OperationIdentifiers.CONCURRENT_EXPONENTIATE_NUMBER_WITH_NUMBER_FUNCTION,
                                                   ProcessingDetails.DEFAULT_PRECISION,
                                                   ProcessingDetails.DEFAULT_ITERATION_DEPTH);

        long start = System.currentTimeMillis();

        n.exponentiate(processingDetails, exponent);

        long end = System.currentTimeMillis();
        long delta = end - start;

        System.out.println("duration " + delta + " ms");
    }

    private static void testConcurrentSingleExponentiationWithMediumNumbers() {

        Number n = createNumber(10, "123.123456789");
        Number exponent = createNumber(10, "1000");

        ProcessingDetails processingDetails =
            ProcessingDetails.setProcessingDetails(OperationIdentifiers.CONCURRENT_EXPONENTIATE_NUMBER_WITH_NUMBER_FUNCTION,
                                                   ProcessingDetails.DEFAULT_PRECISION,
                                                   ProcessingDetails.DEFAULT_ITERATION_DEPTH);

        long start = System.currentTimeMillis();

        n.exponentiate(processingDetails, exponent);

        long end = System.currentTimeMillis();
        long delta = end - start;

        System.out.println("duration " + delta + " ms");
    }

    private static void testConcurrentSingleExponentiationWithLargeNumbers() {

        Number n = createNumber(10, "123456.123456789");
        Number exponent = createNumber(10, "10000");

        ProcessingDetails processingDetails =
            ProcessingDetails.setProcessingDetails(OperationIdentifiers.CONCURRENT_EXPONENTIATE_NUMBER_WITH_NUMBER_FUNCTION,
                                                   createNumber(10, "5"), ProcessingDetails.DEFAULT_ITERATION_DEPTH);

        long start = System.currentTimeMillis();

        n.exponentiate(processingDetails, exponent);

        long end = System.currentTimeMillis();
        long delta = end - start;

        System.out.println("duration " + delta + " ms");
    }

}
