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
import jmul.math.fractions.Fraction;
import static jmul.math.fractions.FractionHelper.createFraction;
import static jmul.math.numbers.Constants.BASE_MAX_LIMIT;
import static jmul.math.numbers.Constants.BASE_MIN_LIMIT;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createInfinity;
import static jmul.math.numbers.NumberHelper.createNegativeInfinity;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.operations.processing.ProcessingDetails;

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
public class ExponentiateNumberWithFractionTest {

    /**
     * A number.
     */
    private final Number number;

    /**
     * An exponent.
     */
    private final Fraction exponent;

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
    public ExponentiateNumberWithFractionTest(Number number, Fraction exponent, Number decimalPlaces,
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
     * Calculates the square root and checks the result.
     */
    @Test
    public void testExponentiation() {

        Number actualResult;
        if (decimalPlaces == null) {

            actualResult = number.exponentiate(exponent);

        } else {

            ProcessingDetails processingDetails =
                ProcessingDetails.setProcessingDetails(ProcessingDetails.DEFAULT_ALGORITHM, decimalPlaces,
                                                       ProcessingDetails.DEFAULT_ITERATION_DEPTH);

            actualResult = number.exponentiate(processingDetails, exponent);
        }

        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
    }

    /**
     * Calculates the square root and checks the result.
     */
    @Test
    public void testExponentiationVariant2() {

        Number actualResult;
        if (decimalPlaces == null) {

            actualResult = Math.exponentiate(number, exponent);

        } else {

            ProcessingDetails processingDetails =
                ProcessingDetails.setProcessingDetails(ProcessingDetails.DEFAULT_ALGORITHM, decimalPlaces,
                                                       ProcessingDetails.DEFAULT_ITERATION_DEPTH);

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

            parameters.add(new Object[] { createInfinity(base), createFraction(base, "0"), null,
                                          createNumber(base, "1") });
            parameters.add(new Object[] { createNegativeInfinity(base), createFraction(base, "0"), null,
                                          createNumber(base, "1") });

            parameters.add(new Object[] { createInfinity(base), createFraction(base, "1"), null,
                                          createInfinity(base) });
            parameters.add(new Object[] { createInfinity(base), createFraction(base, "-1"), null,
                                          createNumber(base, "0") });

            parameters.add(new Object[] { createNegativeInfinity(base), createFraction(base, "1"), null,
                                          createNegativeInfinity(base) });
            parameters.add(new Object[] { createNegativeInfinity(base), createFraction(base, "-1"), null,
                                          createNumber(base, "0") });

            parameters.add(new Object[] { createNumber(base, "0"), createFraction(base, "10"), null,
                                          createNumber(base, "0") });
            parameters.add(new Object[] { createNumber(base, "1"), createFraction(base, "10"), null,
                                          createNumber(base, "1") });
            parameters.add(new Object[] { createNumber(base, "1"), createFraction(base, "-10"), null,
                                          createNumber(base, "1") });
        }

        parameters.add(new Object[] { createNumber(10, "1"), createFraction(10, "0"), null, createNumber(10, "1") });
        parameters.add(new Object[] { createNumber(10, "2"), createFraction(10, "0"), null, createNumber(10, "1") });
        parameters.add(new Object[] { createNumber(10, "3"), createFraction(10, "0"), null, createNumber(10, "1") });
        parameters.add(new Object[] { createNumber(10, "4"), createFraction(10, "0"), null, createNumber(10, "1") });
        parameters.add(new Object[] { createNumber(10, "5"), createFraction(10, "0"), null, createNumber(10, "1") });
        parameters.add(new Object[] { createNumber(10, "6"), createFraction(10, "0"), null, createNumber(10, "1") });
        parameters.add(new Object[] { createNumber(10, "7"), createFraction(10, "0"), null, createNumber(10, "1") });
        parameters.add(new Object[] { createNumber(10, "8"), createFraction(10, "0"), null, createNumber(10, "1") });
        parameters.add(new Object[] { createNumber(10, "9"), createFraction(10, "0"), null, createNumber(10, "1") });
        parameters.add(new Object[] { createNumber(10, "10"), createFraction(10, "0"), null, createNumber(10, "1") });

        parameters.add(new Object[] { createNumber(10, "1"), createFraction(10, "1"), null, createNumber(10, "1") });
        parameters.add(new Object[] { createNumber(10, "2"), createFraction(10, "1"), null, createNumber(10, "2") });
        parameters.add(new Object[] { createNumber(10, "3"), createFraction(10, "1"), null, createNumber(10, "3") });
        parameters.add(new Object[] { createNumber(10, "4"), createFraction(10, "1"), null, createNumber(10, "4") });
        parameters.add(new Object[] { createNumber(10, "5"), createFraction(10, "1"), null, createNumber(10, "5") });
        parameters.add(new Object[] { createNumber(10, "6"), createFraction(10, "1"), null, createNumber(10, "6") });
        parameters.add(new Object[] { createNumber(10, "7"), createFraction(10, "1"), null, createNumber(10, "7") });
        parameters.add(new Object[] { createNumber(10, "8"), createFraction(10, "1"), null, createNumber(10, "8") });
        parameters.add(new Object[] { createNumber(10, "9"), createFraction(10, "1"), null, createNumber(10, "9") });
        parameters.add(new Object[] { createNumber(10, "10"), createFraction(10, "1"), null, createNumber(10, "10") });

        parameters.add(new Object[] { createNumber(10, "1"), createFraction(10, "-1"), null, createNumber(10, "1") });
        parameters.add(new Object[] { createNumber(10, "2"), createFraction(10, "-1"), null, createNumber(10, "0.5") });
        parameters.add(new Object[] { createNumber(10, "3"), createFraction(10, "-1"), null,
                                      createNumber(10, "0.3333333333") });
        parameters.add(new Object[] { createNumber(10, "4"), createFraction(10, "-1"), null,
                                      createNumber(10, "0.25") });
        parameters.add(new Object[] { createNumber(10, "5"), createFraction(10, "-1"), null, createNumber(10, "0.2") });
        parameters.add(new Object[] { createNumber(10, "6"), createFraction(10, "-1"), null,
                                      createNumber(10, "0.1666666666") });
        parameters.add(new Object[] { createNumber(10, "7"), createFraction(10, "-1"), null,
                                      createNumber(10, "0.1428571428") });
        parameters.add(new Object[] { createNumber(10, "8"), createFraction(10, "-1"), null,
                                      createNumber(10, "0.125") });
        parameters.add(new Object[] { createNumber(10, "9"), createFraction(10, "-1"), null,
                                      createNumber(10, "0.1111111111") });
        parameters.add(new Object[] { createNumber(10, "10"), createFraction(10, "-1"), null,
                                      createNumber(10, "0.1") });

        parameters.add(new Object[] { createNumber(10, "1"), createFraction(10, "2"), null, createNumber(10, "1") });
        parameters.add(new Object[] { createNumber(10, "2"), createFraction(10, "2"), null, createNumber(10, "4") });
        parameters.add(new Object[] { createNumber(10, "3"), createFraction(10, "2"), null, createNumber(10, "9") });
        parameters.add(new Object[] { createNumber(10, "4"), createFraction(10, "2"), null, createNumber(10, "16") });
        parameters.add(new Object[] { createNumber(10, "5"), createFraction(10, "2"), null, createNumber(10, "25") });
        parameters.add(new Object[] { createNumber(10, "6"), createFraction(10, "2"), null, createNumber(10, "36") });
        parameters.add(new Object[] { createNumber(10, "7"), createFraction(10, "2"), null, createNumber(10, "49") });
        parameters.add(new Object[] { createNumber(10, "8"), createFraction(10, "2"), null, createNumber(10, "64") });
        parameters.add(new Object[] { createNumber(10, "9"), createFraction(10, "2"), null, createNumber(10, "81") });
        parameters.add(new Object[] { createNumber(10, "10"), createFraction(10, "2"), null, createNumber(10, "100") });

        parameters.add(new Object[] { createNumber(10, "1"), createFraction(10, "-2"), null, createNumber(10, "1") });
        parameters.add(new Object[] { createNumber(10, "2"), createFraction(10, "-2"), null,
                                      createNumber(10, "0.25") });
        parameters.add(new Object[] { createNumber(10, "3"), createFraction(10, "-2"), null,
                                      createNumber(10, "0.111111111") });
        parameters.add(new Object[] { createNumber(10, "4"), createFraction(10, "-2"), null,
                                      createNumber(10, "0.0625") });
        parameters.add(new Object[] { createNumber(10, "5"), createFraction(10, "-2"), null,
                                      createNumber(10, "0.04") });
        parameters.add(new Object[] { createNumber(10, "6"), createFraction(10, "-2"), null,
                                      createNumber(10, "0.0277777777") });
        parameters.add(new Object[] { createNumber(10, "7"), createFraction(10, "-2"), null,
                                      createNumber(10, "0.0204081632") });
        parameters.add(new Object[] { createNumber(10, "8"), createFraction(10, "-2"), null,
                                      createNumber(10, "0.015625") });
        parameters.add(new Object[] { createNumber(10, "9"), createFraction(10, "-2"), null,
                                      createNumber(10, "0.012345679") });
        parameters.add(new Object[] { createNumber(10, "10"), createFraction(10, "-2"), null,
                                      createNumber(10, "0.01") });

        parameters.add(new Object[] { createNumber(10, "1"), createFraction(10, "3"), null, createNumber(10, "1") });
        parameters.add(new Object[] { createNumber(10, "2"), createFraction(10, "3"), null, createNumber(10, "8") });
        parameters.add(new Object[] { createNumber(10, "3"), createFraction(10, "3"), null, createNumber(10, "27") });
        parameters.add(new Object[] { createNumber(10, "4"), createFraction(10, "3"), null, createNumber(10, "64") });
        parameters.add(new Object[] { createNumber(10, "5"), createFraction(10, "3"), null, createNumber(10, "125") });
        parameters.add(new Object[] { createNumber(10, "6"), createFraction(10, "3"), null, createNumber(10, "216") });
        parameters.add(new Object[] { createNumber(10, "7"), createFraction(10, "3"), null, createNumber(10, "343") });
        parameters.add(new Object[] { createNumber(10, "8"), createFraction(10, "3"), null, createNumber(10, "512") });
        parameters.add(new Object[] { createNumber(10, "9"), createFraction(10, "3"), null, createNumber(10, "729") });
        parameters.add(new Object[] { createNumber(10, "10"), createFraction(10, "3"), null,
                                      createNumber(10, "1000") });

        parameters.add(new Object[] { createNumber(10, "1"), createFraction(10, "-3"), null, createNumber(10, "1") });
        parameters.add(new Object[] { createNumber(10, "2"), createFraction(10, "-3"), null,
                                      createNumber(10, "0.125") });
        parameters.add(new Object[] { createNumber(10, "3"), createFraction(10, "-3"), null,
                                      createNumber(10, "0.037037037") });
        parameters.add(new Object[] { createNumber(10, "4"), createFraction(10, "-3"), null,
                                      createNumber(10, "0.015625") });
        parameters.add(new Object[] { createNumber(10, "5"), createFraction(10, "-3"), null,
                                      createNumber(10, "0.008") });
        parameters.add(new Object[] { createNumber(10, "6"), createFraction(10, "-3"), null,
                                      createNumber(10, "0.0046296296") });
        parameters.add(new Object[] { createNumber(10, "7"), createFraction(10, "-3"), null,
                                      createNumber(10, "0.0029154518") });
        parameters.add(new Object[] { createNumber(10, "8"), createFraction(10, "-3"), null,
                                      createNumber(10, "0.001953125") });
        parameters.add(new Object[] { createNumber(10, "9"), createFraction(10, "-3"), null,
                                      createNumber(10, "0.0013717421") });
        parameters.add(new Object[] { createNumber(10, "10"), createFraction(10, "-3"), null,
                                      createNumber(10, "0.001") });

        // ---

        parameters.add(new Object[] { createNumber(10, "1"), createFraction(10, "1", "2"), null,
                                      createNumber(10, "1") });
        parameters.add(new Object[] { createNumber(10, "2"), createFraction(10, "1", "2"), null,
                                      createNumber(10, "1.4142135624") });
        parameters.add(new Object[] { createNumber(10, "3"), createFraction(10, "1", "2"), null,
                                      createNumber(10, "1.7320508075") });
        parameters.add(new Object[] { createNumber(10, "4"), createFraction(10, "1", "2"), null,
                                      createNumber(10, "2") });
        parameters.add(new Object[] { createNumber(10, "5"), createFraction(10, "1", "2"), null,
                                      createNumber(10, "2.2360679775") });
        parameters.add(new Object[] { createNumber(10, "6"), createFraction(10, "1", "2"), null,
                                      createNumber(10, "2.4494897428") });
        parameters.add(new Object[] { createNumber(10, "7"), createFraction(10, "1", "2"), null,
                                      createNumber(10, "2.645751311") });
        parameters.add(new Object[] { createNumber(10, "8"), createFraction(10, "1", "2"), null,
                                      createNumber(10, "2.8284271247") });
        parameters.add(new Object[] { createNumber(10, "9"), createFraction(10, "1", "2"), null,
                                      createNumber(10, "3") });
        parameters.add(new Object[] { createNumber(10, "10"), createFraction(10, "1", "2"), null,
                                      createNumber(10, "3.1622776601") });

        parameters.add(new Object[] { createNumber(10, "1"), createFraction(10, "-1", "2"), null,
                                      createNumber(10, "1") });
        parameters.add(new Object[] { createNumber(10, "2"), createFraction(10, "-1", "2"), null,
                                      createNumber(10, "0.7071067812") });
        parameters.add(new Object[] { createNumber(10, "3"), createFraction(10, "-1", "2"), null,
                                      createNumber(10, "0.5773502691") });
        parameters.add(new Object[] { createNumber(10, "4"), createFraction(10, "-1", "2"), null,
                                      createNumber(10, "0.5") });
        parameters.add(new Object[] { createNumber(10, "5"), createFraction(10, "-1", "2"), null,
                                      createNumber(10, "0.4472135956") });
        parameters.add(new Object[] { createNumber(10, "6"), createFraction(10, "-1", "2"), null,
                                      createNumber(10, "0.4082482904") });
        parameters.add(new Object[] { createNumber(10, "7"), createFraction(10, "-1", "2"), null,
                                      createNumber(10, "0.377964473") });
        parameters.add(new Object[] { createNumber(10, "8"), createFraction(10, "-1", "2"), null,
                                      createNumber(10, "0.3535533907") });
        parameters.add(new Object[] { createNumber(10, "9"), createFraction(10, "-1", "2"), null,
                                      createNumber(10, "0.3333333334") });
        parameters.add(new Object[] { createNumber(10, "10"), createFraction(10, "-1", "2"), null,
                                      createNumber(10, "0.3162277661") });

        parameters.add(new Object[] { createNumber(10, "1"), createFraction(10, "1", "3"), null,
                                      createNumber(10, "1") });
        parameters.add(new Object[] { createNumber(10, "2"), createFraction(10, "1", "3"), null,
                                      createNumber(10, "1.25992105") });
        parameters.add(new Object[] { createNumber(10, "3"), createFraction(10, "1", "3"), null,
                                      createNumber(10, "1.4422495704") });
        parameters.add(new Object[] { createNumber(10, "4"), createFraction(10, "1", "3"), null,
                                      createNumber(10, "1.587401052") });
        parameters.add(new Object[] { createNumber(10, "5"), createFraction(10, "1", "3"), null,
                                      createNumber(10, "1.7099759467") });
        parameters.add(new Object[] { createNumber(10, "6"), createFraction(10, "1", "3"), null,
                                      createNumber(10, "1.8171205929") });
        parameters.add(new Object[] { createNumber(10, "7"), createFraction(10, "1", "3"), null,
                                      createNumber(10, "1.9129311828") });
        parameters.add(new Object[] { createNumber(10, "8"), createFraction(10, "1", "3"), null,
                                      createNumber(10, "2.0000000001") });
        parameters.add(new Object[] { createNumber(10, "9"), createFraction(10, "1", "3"), null,
                                      createNumber(10, "2.0800838231") });
        parameters.add(new Object[] { createNumber(10, "10"), createFraction(10, "1", "3"), null,
                                      createNumber(10, "2.1544346901") });

        parameters.add(new Object[] { createNumber(10, "1"), createFraction(10, "-1", "3"), null,
                                      createNumber(10, "1") });
        parameters.add(new Object[] { createNumber(10, "2"), createFraction(10, "-1", "3"), null,
                                      createNumber(10, "0.7937005261") });
        parameters.add(new Object[] { createNumber(10, "3"), createFraction(10, "-1", "3"), null,
                                      createNumber(10, "0.6933612744") });
        parameters.add(new Object[] { createNumber(10, "4"), createFraction(10, "-1", "3"), null,
                                      createNumber(10, "0.6299605251") });
        parameters.add(new Object[] { createNumber(10, "5"), createFraction(10, "-1", "3"), null,
                                      createNumber(10, "0.5848035478") });
        parameters.add(new Object[] { createNumber(10, "6"), createFraction(10, "-1", "3"), null,
                                      createNumber(10, "0.5503212081") });
        parameters.add(new Object[] { createNumber(10, "7"), createFraction(10, "-1", "3"), null,
                                      createNumber(10, "0.5227579586") });
        parameters.add(new Object[] { createNumber(10, "8"), createFraction(10, "-1", "3"), null,
                                      createNumber(10, "0.5000000001") });
        parameters.add(new Object[] { createNumber(10, "9"), createFraction(10, "-1", "3"), null,
                                      createNumber(10, "0.4807498568") });
        parameters.add(new Object[] { createNumber(10, "10"), createFraction(10, "-1", "3"), null,
                                      createNumber(10, "0.4641588835") });

        parameters.add(new Object[] { createNumber(10, "1"), createFraction(10, "2", "3"), null,
                                      createNumber(10, "1") });
        parameters.add(new Object[] { createNumber(10, "2"), createFraction(10, "2", "3"), null,
                                      createNumber(10, "1.5874010522") });
        parameters.add(new Object[] { createNumber(10, "3"), createFraction(10, "2", "3"), null,
                                      createNumber(10, "2.0800838233") });
        parameters.add(new Object[] { createNumber(10, "4"), createFraction(10, "2", "3"), null,
                                      createNumber(10, "2.5198420998") });
        parameters.add(new Object[] { createNumber(10, "5"), createFraction(10, "2", "3"), null,
                                      createNumber(10, "2.9240177382") });
        parameters.add(new Object[] { createNumber(10, "6"), createFraction(10, "2", "3"), null,
                                      createNumber(10, "3.3019272491") });
        parameters.add(new Object[] { createNumber(10, "7"), createFraction(10, "2", "3"), null,
                                      createNumber(10, "3.6593057101") });
        parameters.add(new Object[] { createNumber(10, "8"), createFraction(10, "2", "3"), null,
                                      createNumber(10, "4.0000000004") });
        parameters.add(new Object[] { createNumber(10, "9"), createFraction(10, "2", "3"), null,
                                      createNumber(10, "4.3267487111") });
        parameters.add(new Object[] { createNumber(10, "10"), createFraction(10, "2", "3"), null,
                                      createNumber(10, "4.6415888339") });

        parameters.add(new Object[] { createNumber(10, "1"), createFraction(10, "-2", "3"), null,
                                      createNumber(10, "1") });
        parameters.add(new Object[] { createNumber(10, "2"), createFraction(10, "-2", "3"), null,
                                      createNumber(10, "0.6299605251") });
        parameters.add(new Object[] { createNumber(10, "3"), createFraction(10, "-2", "3"), null,
                                      createNumber(10, "0.4807498568") });
        parameters.add(new Object[] { createNumber(10, "4"), createFraction(10, "-2", "3"), null,
                                      createNumber(10, "0.3968502631") });
        parameters.add(new Object[] { createNumber(10, "5"), createFraction(10, "-2", "3"), null,
                                      createNumber(10, "0.3419951895") });
        parameters.add(new Object[] { createNumber(10, "6"), createFraction(10, "-2", "3"), null,
                                      createNumber(10, "0.302853432") });
        parameters.add(new Object[] { createNumber(10, "7"), createFraction(10, "-2", "3"), null,
                                      createNumber(10, "0.2732758832") });
        parameters.add(new Object[] { createNumber(10, "8"), createFraction(10, "-2", "3"), null,
                                      createNumber(10, "0.2500000001") });
        parameters.add(new Object[] { createNumber(10, "9"), createFraction(10, "-2", "3"), null,
                                      createNumber(10, "0.2311204248") });
        parameters.add(new Object[] { createNumber(10, "10"), createFraction(10, "-2", "3"), null,
                                      createNumber(10, "0.2154434691") });

        parameters.add(new Object[] { createNumber(10, "1"), createFraction(10, "4", "3"), null,
                                      createNumber(10, "1") });
        parameters.add(new Object[] { createNumber(10, "2"), createFraction(10, "4", "3"), null,
                                      createNumber(10, "2.5198421") });
        parameters.add(new Object[] { createNumber(10, "3"), createFraction(10, "4", "3"), null,
                                      createNumber(10, "4.3267487112") });
        parameters.add(new Object[] { createNumber(10, "4"), createFraction(10, "4", "3"), null,
                                      createNumber(10, "6.349604208") });
        parameters.add(new Object[] { createNumber(10, "5"), createFraction(10, "4", "3"), null,
                                      createNumber(10, "8.5498797335") });
        parameters.add(new Object[] { createNumber(10, "6"), createFraction(10, "4", "3"), null,
                                      createNumber(10, "10.9027235574") });
        parameters.add(new Object[] { createNumber(10, "7"), createFraction(10, "4", "3"), null,
                                      createNumber(10, "13.3905182796") });
        parameters.add(new Object[] { createNumber(10, "8"), createFraction(10, "4", "3"), null,
                                      createNumber(10, "16.0000000008") });
        parameters.add(new Object[] { createNumber(10, "9"), createFraction(10, "4", "3"), null,
                                      createNumber(10, "18.7207544079") });
        parameters.add(new Object[] { createNumber(10, "10"), createFraction(10, "4", "3"), null,
                                      createNumber(10, "21.544346901") });

        parameters.add(new Object[] { createNumber(10, "1"), createFraction(10, "-4", "3"), null,
                                      createNumber(10, "1") });
        parameters.add(new Object[] { createNumber(10, "2"), createFraction(10, "-4", "3"), null,
                                      createNumber(10, "0.396850263") });
        parameters.add(new Object[] { createNumber(10, "3"), createFraction(10, "-4", "3"), null,
                                      createNumber(10, "0.2311204247") });
        parameters.add(new Object[] { createNumber(10, "4"), createFraction(10, "-4", "3"), null,
                                      createNumber(10, "0.1574901312") });
        parameters.add(new Object[] { createNumber(10, "5"), createFraction(10, "-4", "3"), null,
                                      createNumber(10, "0.1169607095") });
        parameters.add(new Object[] { createNumber(10, "6"), createFraction(10, "-4", "3"), null,
                                      createNumber(10, "0.0917202013") });
        parameters.add(new Object[] { createNumber(10, "7"), createFraction(10, "-4", "3"), null,
                                      createNumber(10, "0.0746797083") });
        parameters.add(new Object[] { createNumber(10, "8"), createFraction(10, "-4", "3"), null,
                                      createNumber(10, "0.0625") });
        parameters.add(new Object[] { createNumber(10, "9"), createFraction(10, "-4", "3"), null,
                                      createNumber(10, "0.0534166507") });
        parameters.add(new Object[] { createNumber(10, "10"), createFraction(10, "-4", "3"), null,
                                      createNumber(10, "0.0464158883") });

        parameters.add(new Object[] { createNumber(10, "1"), createFraction(10, "5", "5"), null,
                                      createNumber(10, "1") });
        parameters.add(new Object[] { createNumber(10, "2"), createFraction(10, "5", "5"), null,
                                      createNumber(10, "2") });
        parameters.add(new Object[] { createNumber(10, "3"), createFraction(10, "5", "5"), null,
                                      createNumber(10, "3") });
        parameters.add(new Object[] { createNumber(10, "4"), createFraction(10, "5", "5"), null,
                                      createNumber(10, "4") });
        parameters.add(new Object[] { createNumber(10, "5"), createFraction(10, "5", "5"), null,
                                      createNumber(10, "5") });
        parameters.add(new Object[] { createNumber(10, "6"), createFraction(10, "5", "5"), null,
                                      createNumber(10, "6") });
        parameters.add(new Object[] { createNumber(10, "7"), createFraction(10, "5", "5"), null,
                                      createNumber(10, "7") });
        parameters.add(new Object[] { createNumber(10, "8"), createFraction(10, "5", "5"), null,
                                      createNumber(10, "8") });
        parameters.add(new Object[] { createNumber(10, "9"), createFraction(10, "5", "5"), null,
                                      createNumber(10, "9") });
        parameters.add(new Object[] { createNumber(10, "10"), createFraction(10, "5", "5"), null,
                                      createNumber(10, "10") });

        /* e = 2.71828182845904523536028747135266249775724709369995957496696762772407663035 */

        /* 2.71828182845904523536028747135266249775724709369995957496696762772407663035^0
           -> 1 */
        parameters.add(new Object[] { Math.e(10), createFraction(10, "0", "1"), null, createNumber(10, "1") });

        /* 2.71828182845904523536028747135266249775724709369995957496696762772407663035^(1/1)
           -> 2.71828182845904523536028747135266249775724709369995957496696762772407663035 */
        parameters.add(new Object[] { Math.e(10), createFraction(10, "1", "1"), null,
                                      createNumber(10, "2.7182818282") });

        /* 2.71828182845904523536028747135266249775724709369995957496696762772407663035^(1/2)
           -> 1.64872127070012814684865078781416357165377610071014801157507931164066102119 */
        parameters.add(new Object[] { Math.e(10), createFraction(10, "1", "2"), null,
                                      createNumber(10, "1.6487212706") });

        /* 2.71828182845904523536028747135266249775724709369995957496696762772407663035^(1/3)
           -> 1.39561242508608952862812531960258683759790651519940698261751670603173901564 */
        parameters.add(new Object[] { Math.e(10), createFraction(10, "1", "3"), null,
                                      createNumber(10, "1.3956124251") });

        /* 2.71828182845904523536028747135266249775724709369995957496696762772407663035^-0
           -> 1 */
        parameters.add(new Object[] { Math.e(10), createFraction(10, "-0"), null, createNumber(10, "1") });

        /* 2.71828182845904523536028747135266249775724709369995957496696762772407663035^-1
           -> 0.36787944117144232159552377016146086744581113103176783450783680169746149574 */
        parameters.add(new Object[] { Math.e(10), createFraction(10, "-1"), null, createNumber(10, "0.3678794412") });

        /* 2.71828182845904523536028747135266249775724709369995957496696762772407663035^-2
           -> 0.13533528323661269189399949497248440340763154590957588146815887265407337410 */
        parameters.add(new Object[] { Math.e(10), createFraction(10, "-2"), null, createNumber(10, "0.1353352832") });

        /* 2.71828182845904523536028747135266249775724709369995957496696762772407663035^-3
           -> 0.04978706836786394297934241565006177663169959218842321556762772760606066773 */
        parameters.add(new Object[] { Math.e(10), createFraction(10, "-3"), null, createNumber(10, "0.0497870683") });

        return parameters;
    }

}
