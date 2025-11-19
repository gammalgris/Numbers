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

package test.jmul.math.functions;


import jmul.math.functions.Function;

import jmul.math.functions.FunctionHelper;

import jmul.test.classification.ManualTest;

import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.operations.processing.ProcessingDetails;

import org.junit.Ignore;


/**
 * This test suite tests the calculation speed for sigmoid functions.
 *
 * @author Kristian Kutin
 */
@Ignore
@ManualTest
public class SigmoidSpeedTest {

    /**
     * Invokes a sigmoid function repeatedly.
     *
     * @param args
     *        command line arguments are not evaluated
     */
    public static void main(String... args) {

        Function function = FunctionHelper.createSigmoidFunction(10);
        Number x = createNumber(10, "54321");

        long start = System.currentTimeMillis();

        calculate(function, x, 1000);

        long end = System.currentTimeMillis();
        long delta = end - start;

        String info = String.format("%d milliseconds", delta);
        System.out.println(info);
    }

    /**
     * The specified function is invoked with the specified input value. The calculation is repeated.
     *
     * @param function
     *        a function
     * @param x
     *        an input value
     * @param repetitions
     * 
     *        the number of repetitions
     */
    private static void calculate(Function function, Number x, int repetitions) {

        ProcessingDetails processingDetails =
            ProcessingDetails.setProcessingDetails(ProcessingDetails.DEFAULT_ALGORITHM, createNumber(10, "2"),
                                                   ProcessingDetails.DEFAULT_ITERATION_DEPTH);

        for (int a = 0; a < repetitions; a++) {

            function.calculate(processingDetails, x);

            if (a % 50 == 0) {

                System.out.print(".");
            }
        }

        System.out.println();
    }

}
