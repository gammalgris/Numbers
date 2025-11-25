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
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.operations.processing.ProcessingDetails;

import jmul.math.operations.repository.OperationIdentifiers;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suit tests exponentiating numbers.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class ExponentiateNumberWithLargeNumberTest {

    /**
     * A number.
     */
    private final Number number;

    /**
     * An exponent.
     */
    private final Number exponent;

    /**
     * The number of decimal places retained after cutting the fraction part
     */
    private final Number decimalPlaces;

    /**
     * The expected result.
     */
    private final Number expectedResult;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param number
     *        a number
     * @param exponent
     *        an exponent
     * @param decimalPlaces
     *        the number of decimal places retained after cutting the fraction part
     * @param expectedResult
     *        the expected result
     */
    public ExponentiateNumberWithLargeNumberTest(Number number, Number exponent, Number decimalPlaces,
                                                 Number expectedResult) {

        super();

        this.number = number;
        this.exponent = exponent;
        this.decimalPlaces = decimalPlaces;
        this.expectedResult = expectedResult;
    }

    /**
     * Returns a test summary.
     *
     * @return a test summary
     */
    @Override
    public String toString() {

        if (decimalPlaces == null) {

            return String.format("[%d] %s ^ [%d] %s -> [%d] %s default precision", number.base(), number,
                                 exponent.base(), exponent, expectedResult.base(), expectedResult);

        } else {

            return String.format("[%d] %s / [%d] %s -> [%d] %s precision [%d] %s", number.base(), number,
                                 exponent.base(), number, expectedResult.base(), expectedResult, decimalPlaces.base(),
                                 decimalPlaces);
        }
    }

    /**
     * Performs the division and checks the result.
     */
    @Test
    public void testExponentiation() {

        Number actualResult;
        if (decimalPlaces == null) {

            actualResult = number.exponentiate(exponent);

        } else {

            ProcessingDetails processingDetails =
                ProcessingDetails.setProcessingDetails(OperationIdentifiers.CONCURRENT_EXPONENTIATE_NUMBER_WITH_NUMBER_FUNCTION,
                                                       decimalPlaces, ProcessingDetails.DEFAULT_ITERATION_DEPTH);

            actualResult = number.exponentiate(processingDetails, exponent);
        }

        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
    }

    /**
     * Performs the division and checks the result.
     */
    @Test
    public void testExponentiationVariant2() {

        Number actualResult;
        if (decimalPlaces == null) {

            actualResult = Math.exponentiate(number, exponent);

        } else {

            ProcessingDetails processingDetails =
                ProcessingDetails.setProcessingDetails(OperationIdentifiers.CONCURRENT_EXPONENTIATE_NUMBER_WITH_NUMBER_FUNCTION,
                                                       decimalPlaces, ProcessingDetails.DEFAULT_ITERATION_DEPTH);

            actualResult = Math.exponentiate(processingDetails, number, exponent);
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

        parameters.add(new Object[] { createNumber(10, "10"), createNumber(10, "10000000"), null,
                                      createNumber(10, "0") });
        parameters.add(new Object[] { createNumber(10, "10"), createNumber(10, "-10000000"), null,
                                      createNumber(10, "0") });

        return parameters;
    }


}
