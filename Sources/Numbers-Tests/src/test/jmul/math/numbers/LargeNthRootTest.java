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

import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.operations.processing.ProcessingDetails;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * A testsuite for testing the nth root algorithm.
 *
 * @author Kristian Kutin
 */
@Ignore
@UnitTest
@RunWith(Parameterized.class)
public class LargeNthRootTest {

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
     * (Optional) The iteration depth.
     */
    private Number iterations;

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
     * @param iterations
     *        the iteration depth
     * @param expectedResult
     *        the expected result
     */
    public LargeNthRootTest(Number number, Number n, Number decimalPlaces, Number iterations, Number expectedResult) {

        super();

        this.number = number;
        this.n = n;
        this.decimalPlaces = decimalPlaces;
        this.iterations = iterations;
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

        ProcessingDetails processingDetails =
            ProcessingDetails.setProcessingDetails(ProcessingDetails.DEFAULT_ALGORITHM, decimalPlaces, iterations);

        Number actualResult = number.root(processingDetails, n);

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

        //TODO results may not be correct - requires verification - iteration depths might not be enough
        parameters.add(new Object[] { createNumber(10, "5"), createNumber(10, "8765"), null, null,
                                      createNumber(10, "1.0001836378") });

        parameters.add(new Object[] { createNumber(10, "5"), createNumber(10, "8765"), null, createNumber(10, "10"),
                                      createNumber(10, "1.0001836378") });

        return parameters;
    }

}
