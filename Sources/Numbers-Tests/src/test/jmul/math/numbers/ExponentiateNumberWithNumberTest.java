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

import static jmul.math.numbers.NumberHelper.createNumber;
import static jmul.math.numbers.NumberHelper.createInfinity;
import static jmul.math.numbers.NumberHelper.createNegativeInfinity;
import jmul.math.operations.ProcessingDetails;
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
public class ExponentiateNumberWithNumberTest {

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
    public ExponentiateNumberWithNumberTest(Number number, Number exponent, Number decimalPlaces,
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
    public void testDivision() {

        Number actualResult;
        if (decimalPlaces == null) {

            actualResult = number.exponentiate(exponent);

        } else {

            ProcessingDetails processingDetails =
                new ProcessingDetails(OperationIdentifiers.EXPONENTIATE_NUMBER_WITH_NUMBER_FUNCTION, decimalPlaces);
            actualResult = number.exponentiate(processingDetails, exponent);
        }

        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
    }

    /**
     * Performs the division and checks the result.
     */
    @Test
    public void testDivisionVariant2() {

        Number actualResult;
        if (decimalPlaces == null) {

            actualResult = Math.exponentiate(number, exponent);

        } else {

            ProcessingDetails processingDetails =
                new ProcessingDetails(OperationIdentifiers.EXPONENTIATE_NUMBER_WITH_NUMBER_FUNCTION, decimalPlaces);
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

        for (int base = BASE_MIN_LIMIT; base <= BASE_MAX_LIMIT; base++) {

            parameters.add(new Object[] { createInfinity(base), createNumber(base, "0"), null,
                                          createNumber(base, "1") });
            parameters.add(new Object[] { createNegativeInfinity(base), createNumber(base, "0"), null,
                                          createNumber(base, "1") });

            parameters.add(new Object[] { createInfinity(base), createNumber(base, "1"), null, createInfinity(base) });
            parameters.add(new Object[] { createInfinity(base), createNumber(base, "-1"), null,
                                          createNumber(base, "0") });

            parameters.add(new Object[] { createNegativeInfinity(base), createNumber(base, "1"), null,
                                          createNegativeInfinity(base) });
            parameters.add(new Object[] { createNegativeInfinity(base), createNumber(base, "-1"), null,
                                          createNumber(base, "0") });

            parameters.add(new Object[] { createNumber(base, "0"), createNumber(base, "10"), null,
                                          createNumber(base, "0") });
            parameters.add(new Object[] { createNumber(base, "1"), createNumber(base, "10"), null,
                                          createNumber(base, "1") });
            parameters.add(new Object[] { createNumber(base, "1"), createNumber(base, "-10"), null,
                                          createNumber(base, "1") });
        }

        parameters.add(new Object[] { createNumber(10, "1"), createNumber(10, "0"), null, createNumber(10, "1") });
        parameters.add(new Object[] { createNumber(10, "2"), createNumber(10, "0"), null, createNumber(10, "1") });
        parameters.add(new Object[] { createNumber(10, "3"), createNumber(10, "0"), null, createNumber(10, "1") });
        parameters.add(new Object[] { createNumber(10, "4"), createNumber(10, "0"), null, createNumber(10, "1") });
        parameters.add(new Object[] { createNumber(10, "5"), createNumber(10, "0"), null, createNumber(10, "1") });
        parameters.add(new Object[] { createNumber(10, "6"), createNumber(10, "0"), null, createNumber(10, "1") });
        parameters.add(new Object[] { createNumber(10, "7"), createNumber(10, "0"), null, createNumber(10, "1") });
        parameters.add(new Object[] { createNumber(10, "8"), createNumber(10, "0"), null, createNumber(10, "1") });
        parameters.add(new Object[] { createNumber(10, "9"), createNumber(10, "0"), null, createNumber(10, "1") });
        parameters.add(new Object[] { createNumber(10, "10"), createNumber(10, "0"), null, createNumber(10, "1") });

        parameters.add(new Object[] { createNumber(10, "1"), createNumber(10, "1"), null, createNumber(10, "1") });
        parameters.add(new Object[] { createNumber(10, "2"), createNumber(10, "1"), null, createNumber(10, "2") });
        parameters.add(new Object[] { createNumber(10, "3"), createNumber(10, "1"), null, createNumber(10, "3") });
        parameters.add(new Object[] { createNumber(10, "4"), createNumber(10, "1"), null, createNumber(10, "4") });
        parameters.add(new Object[] { createNumber(10, "5"), createNumber(10, "1"), null, createNumber(10, "5") });
        parameters.add(new Object[] { createNumber(10, "6"), createNumber(10, "1"), null, createNumber(10, "6") });
        parameters.add(new Object[] { createNumber(10, "7"), createNumber(10, "1"), null, createNumber(10, "7") });
        parameters.add(new Object[] { createNumber(10, "8"), createNumber(10, "1"), null, createNumber(10, "8") });
        parameters.add(new Object[] { createNumber(10, "9"), createNumber(10, "1"), null, createNumber(10, "9") });
        parameters.add(new Object[] { createNumber(10, "10"), createNumber(10, "1"), null, createNumber(10, "10") });

        parameters.add(new Object[] { createNumber(10, "1"), createNumber(10, "-1"), null, createNumber(10, "1") });
        parameters.add(new Object[] { createNumber(10, "2"), createNumber(10, "-1"), null, createNumber(10, "0.5") });
        parameters.add(new Object[] { createNumber(10, "3"), createNumber(10, "-1"), null,
                                      createNumber(10, "0.3333333333") });
        parameters.add(new Object[] { createNumber(10, "4"), createNumber(10, "-1"), null, createNumber(10, "0.25") });
        parameters.add(new Object[] { createNumber(10, "5"), createNumber(10, "-1"), null, createNumber(10, "0.2") });
        parameters.add(new Object[] { createNumber(10, "6"), createNumber(10, "-1"), null,
                                      createNumber(10, "0.1666666666") });
        parameters.add(new Object[] { createNumber(10, "7"), createNumber(10, "-1"), null,
                                      createNumber(10, "0.1428571428") });
        parameters.add(new Object[] { createNumber(10, "8"), createNumber(10, "-1"), null, createNumber(10, "0.125") });
        parameters.add(new Object[] { createNumber(10, "9"), createNumber(10, "-1"), null,
                                      createNumber(10, "0.1111111111") });
        parameters.add(new Object[] { createNumber(10, "10"), createNumber(10, "-1"), null, createNumber(10, "0.1") });

        parameters.add(new Object[] { createNumber(10, "1"), createNumber(10, "2"), null, createNumber(10, "1") });
        parameters.add(new Object[] { createNumber(10, "2"), createNumber(10, "2"), null, createNumber(10, "4") });
        parameters.add(new Object[] { createNumber(10, "3"), createNumber(10, "2"), null, createNumber(10, "9") });
        parameters.add(new Object[] { createNumber(10, "4"), createNumber(10, "2"), null, createNumber(10, "16") });
        parameters.add(new Object[] { createNumber(10, "5"), createNumber(10, "2"), null, createNumber(10, "25") });
        parameters.add(new Object[] { createNumber(10, "6"), createNumber(10, "2"), null, createNumber(10, "36") });
        parameters.add(new Object[] { createNumber(10, "7"), createNumber(10, "2"), null, createNumber(10, "49") });
        parameters.add(new Object[] { createNumber(10, "8"), createNumber(10, "2"), null, createNumber(10, "64") });
        parameters.add(new Object[] { createNumber(10, "9"), createNumber(10, "2"), null, createNumber(10, "81") });
        parameters.add(new Object[] { createNumber(10, "10"), createNumber(10, "2"), null, createNumber(10, "100") });

        parameters.add(new Object[] { createNumber(10, "1"), createNumber(10, "-2"), null, createNumber(10, "1") });
        parameters.add(new Object[] { createNumber(10, "2"), createNumber(10, "-2"), null, createNumber(10, "0.25") });
        parameters.add(new Object[] { createNumber(10, "3"), createNumber(10, "-2"), null,
                                      createNumber(10, "0.1111111111") });
        parameters.add(new Object[] { createNumber(10, "4"), createNumber(10, "-2"), null,
                                      createNumber(10, "0.0625") });
        parameters.add(new Object[] { createNumber(10, "5"), createNumber(10, "-2"), null, createNumber(10, "0.04") });
        parameters.add(new Object[] { createNumber(10, "6"), createNumber(10, "-2"), null,
                                      createNumber(10, "0.0277777777") });
        parameters.add(new Object[] { createNumber(10, "7"), createNumber(10, "-2"), null,
                                      createNumber(10, "0.0204081632") });
        parameters.add(new Object[] { createNumber(10, "8"), createNumber(10, "-2"), null,
                                      createNumber(10, "0.015625") });
        parameters.add(new Object[] { createNumber(10, "9"), createNumber(10, "-2"), null,
                                      createNumber(10, "0.012345679") });
        parameters.add(new Object[] { createNumber(10, "10"), createNumber(10, "-2"), null, createNumber(10, "0.01") });

        parameters.add(new Object[] { createNumber(10, "1"), createNumber(10, "3"), null, createNumber(10, "1") });
        parameters.add(new Object[] { createNumber(10, "2"), createNumber(10, "3"), null, createNumber(10, "8") });
        parameters.add(new Object[] { createNumber(10, "3"), createNumber(10, "3"), null, createNumber(10, "27") });
        parameters.add(new Object[] { createNumber(10, "4"), createNumber(10, "3"), null, createNumber(10, "64") });
        parameters.add(new Object[] { createNumber(10, "5"), createNumber(10, "3"), null, createNumber(10, "125") });
        parameters.add(new Object[] { createNumber(10, "6"), createNumber(10, "3"), null, createNumber(10, "216") });
        parameters.add(new Object[] { createNumber(10, "7"), createNumber(10, "3"), null, createNumber(10, "343") });
        parameters.add(new Object[] { createNumber(10, "8"), createNumber(10, "3"), null, createNumber(10, "512") });
        parameters.add(new Object[] { createNumber(10, "9"), createNumber(10, "3"), null, createNumber(10, "729") });
        parameters.add(new Object[] { createNumber(10, "10"), createNumber(10, "3"), null, createNumber(10, "1000") });

        parameters.add(new Object[] { createNumber(10, "1"), createNumber(10, "-3"), null, createNumber(10, "1") });
        parameters.add(new Object[] { createNumber(10, "2"), createNumber(10, "-3"), null, createNumber(10, "0.125") });
        parameters.add(new Object[] { createNumber(10, "3"), createNumber(10, "-3"), null,
                                      createNumber(10, "0.037037037") });
        parameters.add(new Object[] { createNumber(10, "4"), createNumber(10, "-3"), null,
                                      createNumber(10, "0.015625") });
        parameters.add(new Object[] { createNumber(10, "5"), createNumber(10, "-3"), null, createNumber(10, "0.008") });
        parameters.add(new Object[] { createNumber(10, "6"), createNumber(10, "-3"), null,
                                      createNumber(10, "0.0046296296") });
        parameters.add(new Object[] { createNumber(10, "7"), createNumber(10, "-3"), null,
                                      createNumber(10, "0.0029154518") });
        parameters.add(new Object[] { createNumber(10, "8"), createNumber(10, "-3"), null,
                                      createNumber(10, "0.001953125") });
        parameters.add(new Object[] { createNumber(10, "9"), createNumber(10, "-3"), null,
                                      createNumber(10, "0.0013717421") });
        parameters.add(new Object[] { createNumber(10, "10"), createNumber(10, "-3"), null,
                                      createNumber(10, "0.001") });

        return parameters;
    }

}
