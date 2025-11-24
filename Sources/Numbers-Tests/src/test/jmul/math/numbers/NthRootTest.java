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
import java.util.Collection;

import jmul.math.Math;
import static jmul.math.numbers.Constants.BASE_MAX_LIMIT;
import static jmul.math.numbers.Constants.BASE_MIN_LIMIT;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createInfinity;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.operations.processing.ProcessingDetails;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * A testsuite for testing the nth root algorithm.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class NthRootTest {

    /**
     * A number.
     */
    private Number number;

    /**
     * A root.
     */
    private Number n;

    /**
     * (Optional) The number of decimal places retained after cutting the fraction part.
     */
    private Number decimalPlaces;

    /**
     * The expected result.
     */
    private Number expectedResult;

    /**
     * Creates a new test according to the specified parameters.
     *
     * @param number
     *        A number
     * @param decimalPlaces
     *        The number of decimal places retained after cutting the fraction part. If no value is specified use
     *        <code>null</code>.
     * @param expectedResult
     *        the expected result
     */
    public NthRootTest(Number number, Number n, Number decimalPlaces, Number expectedResult) {

        super();

        this.number = number;
        this.n = n;
        this.decimalPlaces = decimalPlaces;
        this.expectedResult = expectedResult;
    }

    /**
     * Returns a summary for this test case.
     *
     * @return a summary for this test case
     */
    @Override
    public String toString() {

        if (decimalPlaces == null) {

            return String.format("[base:%d] %s -> %s. root -> [base:%d] %s", number.base(), number, n,
                                 expectedResult.base(), expectedResult);
        } else {

            return String.format("[base:%d] %s (decimalPlaces: [base:%d] %s) -> %s. root -> [base:%d] %s",
                                 number.base(), number, decimalPlaces.base(), decimalPlaces, n, expectedResult.base(),
                                 expectedResult);
        }
    }

    /**
     * Tests calculating the nth root of a number and checks the result.
     */
    @Test
    public void testCalculation() {

        Number actualResult;
        if (decimalPlaces == null) {

            actualResult = number.root(n);

        } else {

            ProcessingDetails processingDetails =
                ProcessingDetails.setProcessingDetails(ProcessingDetails.DEFAULT_ALGORITHM, decimalPlaces,
                                                       ProcessingDetails.DEFAULT_ITERATION_DEPTH);

            actualResult = number.root(processingDetails, n);
        }

        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
    }

    /**
     * Tests calculating the nth root of a number and checks the result.
     */
    @Test
    public void testCalculationVariant2() {

        Number actualResult;
        if (decimalPlaces == null) {

            actualResult = Math.root(number, n);

        } else {

            ProcessingDetails processingDetails =
                ProcessingDetails.setProcessingDetails(ProcessingDetails.DEFAULT_ALGORITHM, decimalPlaces,
                                                       ProcessingDetails.DEFAULT_ITERATION_DEPTH);

            actualResult = Math.root(processingDetails, number, n);
        }

        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
    }

    /**
     * Returns a matrix of test data and expected results.
     *
     * @return a matrix of test data and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        for (int base = BASE_MIN_LIMIT; base <= BASE_MAX_LIMIT; base++) {

            parameters.add(new Object[] { createInfinity(base), createNumber(base, "1"), null, createInfinity(base) });
            parameters.add(new Object[] { createInfinity(base), createNumber(base, "10"), null, createInfinity(base) });
            parameters.add(new Object[] { createInfinity(base), createNumber(base, "11"), null, createInfinity(base) });

            parameters.add(new Object[] { createNumber(base, "0"), createNumber(base, "10"), null,
                                          createNumber(base, "0") });
            parameters.add(new Object[] { createNumber(base, "1"), createNumber(base, "10"), null,
                                          createNumber(base, "1") });
        }

        parameters.add(new Object[] { createNumber(10, "1"), createNumber(10, "1"), null, createNumber(10, "1") });
        parameters.add(new Object[] { createNumber(10, "1"), createNumber(10, "1"), createNumber(10, "20"),
                                      createNumber(10, "1") });

        parameters.add(new Object[] { createNumber(10, "2"), createNumber(10, "1"), null, createNumber(10, "2") });
        parameters.add(new Object[] { createNumber(10, "2"), createNumber(10, "1"), createNumber(10, "20"),
                                      createNumber(10, "2") });

        // A result with more digits is 1,4142135623730950488016887242097.
        parameters.add(new Object[] { createNumber(10, "2"), createNumber(10, "2"), null,
                                      createNumber(10, "1.4142135624") });
        parameters.add(new Object[] { createNumber(10, "2"), createNumber(10, "2"), createNumber(10, "20"),
                                      createNumber(10, "1.41421356237309504881") });

        // A result with more digits is 1.25992104989487316476721060727822835057025146470150798008197511215529967651
        parameters.add(new Object[] { createNumber(10, "2"), createNumber(10, "3"), null,
                                      createNumber(10, "1.25992105") });
        parameters.add(new Object[] { createNumber(10, "2"), createNumber(10, "3"), createNumber(10, "20"),
                                      createNumber(10, "1.25992104989487316477") });

        parameters.add(new Object[] { createNumber(10, "27"), createNumber(10, "3"), null,
                                      createNumber(10, "3.0000000813") });
        parameters.add(new Object[] { createNumber(10, "27"), createNumber(10, "3"), createNumber(10, "20"),
                                      createNumber(10, "3.00000008121020203131") });

        /* 2.71828182845904523536028747135266249775724709369995957496696762772407663035^(1/2)
           -> 1.64872127070012814684865078781416357165377610071014801157507931164066102119 */
        parameters.add(new Object[] { Math.e(10), createNumber(10, "2"), null, createNumber(10, "1.6487212707") });

        /* 2.71828182845904523536028747135266249775724709369995957496696762772407663035^(1/3)
           -> 1.39561242508608952862812531960258683759790651519940698261751670603173901564 */
        parameters.add(new Object[] { Math.e(10), createNumber(10, "3"), null, createNumber(10, "1.3956124251") });

        return parameters;
    }

}
