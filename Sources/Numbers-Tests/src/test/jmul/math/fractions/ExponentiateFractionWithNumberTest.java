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

package test.jmul.math.fractions;


import java.util.ArrayList;
import java.util.Collection;

import jmul.math.Math;
import jmul.math.fractions.Fraction;
import static jmul.math.fractions.FractionHelper.createFraction;
import static jmul.math.fractions.FractionHelper.createInfinity;
import static jmul.math.numbers.Constants.BASE_MAX_LIMIT;
import static jmul.math.numbers.Constants.BASE_MIN_LIMIT;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.signs.Signs;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suit tests exponentiating fractions.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class ExponentiateFractionWithNumberTest {

    /**
     * A fraction.
     */
    private final Fraction fraction;

    /**
     * An exponent.
     */
    private final Number exponent;

    /**
     * The expected result.
     */
    private final Fraction expectedResult;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param fraction
     *        a fraction
     * @param exponent
     *        an exponent
     * @param expectedResult
     *        expected result
     */
    public ExponentiateFractionWithNumberTest(Fraction fraction, Number exponent, Fraction expectedResult) {

        super();

        this.fraction = fraction;
        this.exponent = exponent;
        this.expectedResult = expectedResult;
    }

    /**
     * Returns a test summary.
     *
     * @return a test summary
     */
    @Override
    public String toString() {

        return String.format("[%d] %s ^ [%d] %s -> [%d] %s", fraction.base(), fraction, exponent.base(), exponent,
                             expectedResult.base(), expectedResult);
    }

    /**
     * Performs the division and checks the result.
     */
    @Test
    public void testExponentiation() {

        Fraction actualResult = fraction.exponentiate(exponent);

        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
    }

    /**
     * Performs the division and checks the result.
     */
    @Test
    public void testExponentiationVariant2() {

        Fraction actualResult = Math.exponentiate(fraction, exponent);

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

            parameters.add(new Object[] { createInfinity(base), createNumber(base, "0"), createFraction(base, "1") });
            parameters.add(new Object[] { createInfinity(base, Signs.NEGATIVE), createNumber(base, "0"),
                                          createFraction(base, "1") });

            parameters.add(new Object[] { createInfinity(base), createNumber(base, "1"), createInfinity(base) });
            parameters.add(new Object[] { createInfinity(base), createNumber(base, "-1"), createFraction(base, "0") });

            parameters.add(new Object[] { createInfinity(base, Signs.NEGATIVE), createNumber(base, "1"),
                                          createInfinity(base, Signs.NEGATIVE) });
            parameters.add(new Object[] { createInfinity(base, Signs.NEGATIVE), createNumber(base, "-1"),
                                          createFraction(base, "0") });

            parameters.add(new Object[] { createFraction(base, "0"), createNumber(base, "10"),
                                          createFraction(base, "0") });
            parameters.add(new Object[] { createFraction(base, "1"), createNumber(base, "10"),
                                          createFraction(base, "1") });
            parameters.add(new Object[] { createFraction(base, "1"), createNumber(base, "-10"),
                                          createFraction(base, "1", "1") });
        }

        parameters.add(new Object[] { createFraction(10, "0"), createNumber(10, "0"), createFraction(10, "1") });
        parameters.add(new Object[] { createFraction(10, "1"), createNumber(10, "0"), createFraction(10, "1") });
        parameters.add(new Object[] { createFraction(10, "2"), createNumber(10, "0"), createFraction(10, "1") });
        parameters.add(new Object[] { createFraction(10, "3"), createNumber(10, "0"), createFraction(10, "1") });
        parameters.add(new Object[] { createFraction(10, "4"), createNumber(10, "0"), createFraction(10, "1") });
        parameters.add(new Object[] { createFraction(10, "5"), createNumber(10, "0"), createFraction(10, "1") });
        parameters.add(new Object[] { createFraction(10, "6"), createNumber(10, "0"), createFraction(10, "1") });
        parameters.add(new Object[] { createFraction(10, "7"), createNumber(10, "0"), createFraction(10, "1") });
        parameters.add(new Object[] { createFraction(10, "8"), createNumber(10, "0"), createFraction(10, "1") });
        parameters.add(new Object[] { createFraction(10, "9"), createNumber(10, "0"), createFraction(10, "1") });
        parameters.add(new Object[] { createFraction(10, "10"), createNumber(10, "0"), createFraction(10, "1") });

        parameters.add(new Object[] { createFraction(10, "0"), createNumber(10, "1"), createFraction(10, "0") });
        parameters.add(new Object[] { createFraction(10, "1"), createNumber(10, "1"), createFraction(10, "1") });
        parameters.add(new Object[] { createFraction(10, "2"), createNumber(10, "1"), createFraction(10, "2") });
        parameters.add(new Object[] { createFraction(10, "3"), createNumber(10, "1"), createFraction(10, "3") });
        parameters.add(new Object[] { createFraction(10, "4"), createNumber(10, "1"), createFraction(10, "4") });
        parameters.add(new Object[] { createFraction(10, "5"), createNumber(10, "1"), createFraction(10, "5") });
        parameters.add(new Object[] { createFraction(10, "6"), createNumber(10, "1"), createFraction(10, "6") });
        parameters.add(new Object[] { createFraction(10, "7"), createNumber(10, "1"), createFraction(10, "7") });
        parameters.add(new Object[] { createFraction(10, "8"), createNumber(10, "1"), createFraction(10, "8") });
        parameters.add(new Object[] { createFraction(10, "9"), createNumber(10, "1"), createFraction(10, "9") });
        parameters.add(new Object[] { createFraction(10, "10"), createNumber(10, "1"), createFraction(10, "10") });

        parameters.add(new Object[] { createFraction(10, "0"), createNumber(10, "2"), createFraction(10, "0") });
        parameters.add(new Object[] { createFraction(10, "1"), createNumber(10, "2"), createFraction(10, "1") });
        parameters.add(new Object[] { createFraction(10, "2"), createNumber(10, "2"), createFraction(10, "4") });
        parameters.add(new Object[] { createFraction(10, "3"), createNumber(10, "2"), createFraction(10, "9") });
        parameters.add(new Object[] { createFraction(10, "4"), createNumber(10, "2"), createFraction(10, "16") });
        parameters.add(new Object[] { createFraction(10, "5"), createNumber(10, "2"), createFraction(10, "25") });
        parameters.add(new Object[] { createFraction(10, "6"), createNumber(10, "2"), createFraction(10, "36") });
        parameters.add(new Object[] { createFraction(10, "7"), createNumber(10, "2"), createFraction(10, "49") });
        parameters.add(new Object[] { createFraction(10, "8"), createNumber(10, "2"), createFraction(10, "64") });
        parameters.add(new Object[] { createFraction(10, "9"), createNumber(10, "2"), createFraction(10, "81") });
        parameters.add(new Object[] { createFraction(10, "10"), createNumber(10, "2"), createFraction(10, "100") });

        parameters.add(new Object[] { createFraction(10, "0"), createNumber(10, "3"), createFraction(10, "0") });
        parameters.add(new Object[] { createFraction(10, "1"), createNumber(10, "3"), createFraction(10, "1") });
        parameters.add(new Object[] { createFraction(10, "2"), createNumber(10, "3"), createFraction(10, "8") });
        parameters.add(new Object[] { createFraction(10, "3"), createNumber(10, "3"), createFraction(10, "27") });
        parameters.add(new Object[] { createFraction(10, "4"), createNumber(10, "3"), createFraction(10, "64") });
        parameters.add(new Object[] { createFraction(10, "5"), createNumber(10, "3"), createFraction(10, "125") });
        parameters.add(new Object[] { createFraction(10, "6"), createNumber(10, "3"), createFraction(10, "216") });
        parameters.add(new Object[] { createFraction(10, "7"), createNumber(10, "3"), createFraction(10, "343") });
        parameters.add(new Object[] { createFraction(10, "8"), createNumber(10, "3"), createFraction(10, "512") });
        parameters.add(new Object[] { createFraction(10, "9"), createNumber(10, "3"), createFraction(10, "729") });
        parameters.add(new Object[] { createFraction(10, "10"), createNumber(10, "3"), createFraction(10, "1000") });

        parameters.add(new Object[] { createFraction(10, "0"), createNumber(10, "-1"), createFraction(10, "1", "0") });
        parameters.add(new Object[] { createFraction(10, "1"), createNumber(10, "-1"), createFraction(10, "1", "1") });
        parameters.add(new Object[] { createFraction(10, "2"), createNumber(10, "-1"), createFraction(10, "1", "2") });
        parameters.add(new Object[] { createFraction(10, "3"), createNumber(10, "-1"), createFraction(10, "1", "3") });
        parameters.add(new Object[] { createFraction(10, "4"), createNumber(10, "-1"), createFraction(10, "1", "4") });
        parameters.add(new Object[] { createFraction(10, "5"), createNumber(10, "-1"), createFraction(10, "1", "5") });
        parameters.add(new Object[] { createFraction(10, "6"), createNumber(10, "-1"), createFraction(10, "1", "6") });
        parameters.add(new Object[] { createFraction(10, "7"), createNumber(10, "-1"), createFraction(10, "1", "7") });
        parameters.add(new Object[] { createFraction(10, "8"), createNumber(10, "-1"), createFraction(10, "1", "8") });
        parameters.add(new Object[] { createFraction(10, "9"), createNumber(10, "-1"), createFraction(10, "1", "9") });
        parameters.add(new Object[] { createFraction(10, "10"), createNumber(10, "-1"),
                                      createFraction(10, "1", "10") });

        parameters.add(new Object[] { createFraction(10, "0"), createNumber(10, "-2"), createFraction(10, "1", "0") });
        parameters.add(new Object[] { createFraction(10, "1"), createNumber(10, "-2"), createFraction(10, "1", "1") });
        parameters.add(new Object[] { createFraction(10, "2"), createNumber(10, "-2"), createFraction(10, "1", "4") });
        parameters.add(new Object[] { createFraction(10, "3"), createNumber(10, "-2"), createFraction(10, "1", "9") });
        parameters.add(new Object[] { createFraction(10, "4"), createNumber(10, "-2"), createFraction(10, "1", "16") });
        parameters.add(new Object[] { createFraction(10, "5"), createNumber(10, "-2"), createFraction(10, "1", "25") });
        parameters.add(new Object[] { createFraction(10, "6"), createNumber(10, "-2"), createFraction(10, "1", "36") });
        parameters.add(new Object[] { createFraction(10, "7"), createNumber(10, "-2"), createFraction(10, "1", "49") });
        parameters.add(new Object[] { createFraction(10, "8"), createNumber(10, "-2"), createFraction(10, "1", "64") });
        parameters.add(new Object[] { createFraction(10, "9"), createNumber(10, "-2"), createFraction(10, "1", "81") });
        parameters.add(new Object[] { createFraction(10, "10"), createNumber(10, "-2"),
                                      createFraction(10, "1", "100") });

        parameters.add(new Object[] { createFraction(10, "0"), createNumber(10, "-3"), createFraction(10, "1", "0") });
        parameters.add(new Object[] { createFraction(10, "1"), createNumber(10, "-3"), createFraction(10, "1", "1") });
        parameters.add(new Object[] { createFraction(10, "2"), createNumber(10, "-3"), createFraction(10, "1", "8") });
        parameters.add(new Object[] { createFraction(10, "3"), createNumber(10, "-3"), createFraction(10, "1", "27") });
        parameters.add(new Object[] { createFraction(10, "4"), createNumber(10, "-3"), createFraction(10, "1", "64") });
        parameters.add(new Object[] { createFraction(10, "5"), createNumber(10, "-3"),
                                      createFraction(10, "1", "125") });
        parameters.add(new Object[] { createFraction(10, "6"), createNumber(10, "-3"),
                                      createFraction(10, "1", "216") });
        parameters.add(new Object[] { createFraction(10, "7"), createNumber(10, "-3"),
                                      createFraction(10, "1", "343") });
        parameters.add(new Object[] { createFraction(10, "8"), createNumber(10, "-3"),
                                      createFraction(10, "1", "512") });
        parameters.add(new Object[] { createFraction(10, "9"), createNumber(10, "-3"),
                                      createFraction(10, "1", "729") });
        parameters.add(new Object[] { createFraction(10, "10"), createNumber(10, "-3"),
                                      createFraction(10, "1", "1000") });

        // ---

        parameters.add(new Object[] { createFraction(10, "0", "3"), createNumber(10, "1"), createFraction(10, "0") });
        parameters.add(new Object[] { createFraction(10, "1", "3"), createNumber(10, "1"),
                                      createFraction(10, "1", "3") });
        parameters.add(new Object[] { createFraction(10, "2", "3"), createNumber(10, "1"),
                                      createFraction(10, "2", "3") });
        parameters.add(new Object[] { createFraction(10, "3", "3"), createNumber(10, "1"),
                                      createFraction(10, "3", "3") });
        parameters.add(new Object[] { createFraction(10, "4", "3"), createNumber(10, "1"),
                                      createFraction(10, "4", "3") });
        parameters.add(new Object[] { createFraction(10, "5", "3"), createNumber(10, "1"),
                                      createFraction(10, "5", "3") });
        parameters.add(new Object[] { createFraction(10, "6", "3"), createNumber(10, "1"),
                                      createFraction(10, "6", "3") });
        parameters.add(new Object[] { createFraction(10, "7", "3"), createNumber(10, "1"),
                                      createFraction(10, "7", "3") });
        parameters.add(new Object[] { createFraction(10, "8", "3"), createNumber(10, "1"),
                                      createFraction(10, "8", "3") });
        parameters.add(new Object[] { createFraction(10, "9", "3"), createNumber(10, "1"),
                                      createFraction(10, "9", "3") });
        parameters.add(new Object[] { createFraction(10, "10", "3"), createNumber(10, "1"),
                                      createFraction(10, "10", "3") });

        parameters.add(new Object[] { createFraction(10, "0", "3"), createNumber(10, "2"), createFraction(10, "0") });
        parameters.add(new Object[] { createFraction(10, "1", "3"), createNumber(10, "2"),
                                      createFraction(10, "1", "9") });
        parameters.add(new Object[] { createFraction(10, "2", "3"), createNumber(10, "2"),
                                      createFraction(10, "4", "9") });
        parameters.add(new Object[] { createFraction(10, "3", "3"), createNumber(10, "2"),
                                      createFraction(10, "3", "3") });
        parameters.add(new Object[] { createFraction(10, "4", "3"), createNumber(10, "2"),
                                      createFraction(10, "16", "9") });
        parameters.add(new Object[] { createFraction(10, "5", "3"), createNumber(10, "2"),
                                      createFraction(10, "25", "9") });
        parameters.add(new Object[] { createFraction(10, "6", "3"), createNumber(10, "2"),
                                      createFraction(10, "36", "9") });
        parameters.add(new Object[] { createFraction(10, "7", "3"), createNumber(10, "2"),
                                      createFraction(10, "49", "9") });
        parameters.add(new Object[] { createFraction(10, "8", "3"), createNumber(10, "2"),
                                      createFraction(10, "64", "9") });
        parameters.add(new Object[] { createFraction(10, "9", "3"), createNumber(10, "2"),
                                      createFraction(10, "81", "9") });
        parameters.add(new Object[] { createFraction(10, "10", "3"), createNumber(10, "2"),
                                      createFraction(10, "100", "9") });

        parameters.add(new Object[] { createFraction(10, "0", "3"), createNumber(10, "3"), createFraction(10, "0") });
        parameters.add(new Object[] { createFraction(10, "1", "3"), createNumber(10, "3"),
                                      createFraction(10, "1", "27") });
        parameters.add(new Object[] { createFraction(10, "2", "3"), createNumber(10, "3"),
                                      createFraction(10, "8", "27") });
        parameters.add(new Object[] { createFraction(10, "3", "3"), createNumber(10, "3"),
                                      createFraction(10, "3", "3") });
        parameters.add(new Object[] { createFraction(10, "4", "3"), createNumber(10, "3"),
                                      createFraction(10, "64", "27") });
        parameters.add(new Object[] { createFraction(10, "5", "3"), createNumber(10, "3"),
                                      createFraction(10, "125", "27") });
        parameters.add(new Object[] { createFraction(10, "6", "3"), createNumber(10, "3"),
                                      createFraction(10, "216", "27") });
        parameters.add(new Object[] { createFraction(10, "7", "3"), createNumber(10, "3"),
                                      createFraction(10, "343", "27") });
        parameters.add(new Object[] { createFraction(10, "8", "3"), createNumber(10, "3"),
                                      createFraction(10, "512", "27") });
        parameters.add(new Object[] { createFraction(10, "9", "3"), createNumber(10, "3"),
                                      createFraction(10, "729", "27") });
        parameters.add(new Object[] { createFraction(10, "10", "3"), createNumber(10, "3"),
                                      createFraction(10, "1000", "27") });

        parameters.add(new Object[] { createFraction(10, "0", "3"), createNumber(10, "-1"),
                                      createFraction(10, "1", "0") });
        parameters.add(new Object[] { createFraction(10, "1", "3"), createNumber(10, "-1"),
                                      createFraction(10, "3", "1") });
        parameters.add(new Object[] { createFraction(10, "2", "3"), createNumber(10, "-1"),
                                      createFraction(10, "3", "2") });
        parameters.add(new Object[] { createFraction(10, "3", "3"), createNumber(10, "-1"),
                                      createFraction(10, "3", "3") });
        parameters.add(new Object[] { createFraction(10, "4", "3"), createNumber(10, "-1"),
                                      createFraction(10, "3", "4") });
        parameters.add(new Object[] { createFraction(10, "5", "3"), createNumber(10, "-1"),
                                      createFraction(10, "3", "5") });
        parameters.add(new Object[] { createFraction(10, "6", "3"), createNumber(10, "-1"),
                                      createFraction(10, "3", "6") });
        parameters.add(new Object[] { createFraction(10, "7", "3"), createNumber(10, "-1"),
                                      createFraction(10, "3", "7") });
        parameters.add(new Object[] { createFraction(10, "8", "3"), createNumber(10, "-1"),
                                      createFraction(10, "3", "8") });
        parameters.add(new Object[] { createFraction(10, "9", "3"), createNumber(10, "-1"),
                                      createFraction(10, "3", "9") });
        parameters.add(new Object[] { createFraction(10, "10", "3"), createNumber(10, "-1"),
                                      createFraction(10, "3", "10") });

        parameters.add(new Object[] { createFraction(10, "0", "3"), createNumber(10, "-2"),
                                      createFraction(10, "1", "0") });
        parameters.add(new Object[] { createFraction(10, "1", "3"), createNumber(10, "-2"), createFraction(10, "9") });
        parameters.add(new Object[] { createFraction(10, "2", "3"), createNumber(10, "-2"),
                                      createFraction(10, "9", "4") });
        parameters.add(new Object[] { createFraction(10, "3", "3"), createNumber(10, "-2"),
                                      createFraction(10, "3", "3") });
        parameters.add(new Object[] { createFraction(10, "4", "3"), createNumber(10, "-2"),
                                      createFraction(10, "9", "16") });
        parameters.add(new Object[] { createFraction(10, "5", "3"), createNumber(10, "-2"),
                                      createFraction(10, "9", "25") });
        parameters.add(new Object[] { createFraction(10, "6", "3"), createNumber(10, "-2"),
                                      createFraction(10, "9", "36") });
        parameters.add(new Object[] { createFraction(10, "7", "3"), createNumber(10, "-2"),
                                      createFraction(10, "9", "49") });
        parameters.add(new Object[] { createFraction(10, "8", "3"), createNumber(10, "-2"),
                                      createFraction(10, "9", "64") });
        parameters.add(new Object[] { createFraction(10, "9", "3"), createNumber(10, "-2"),
                                      createFraction(10, "9", "81") });
        parameters.add(new Object[] { createFraction(10, "10", "3"), createNumber(10, "-2"),
                                      createFraction(10, "9", "100") });

        parameters.add(new Object[] { createFraction(10, "0", "3"), createNumber(10, "-3"),
                                      createFraction(10, "1", "0") });
        parameters.add(new Object[] { createFraction(10, "1", "3"), createNumber(10, "-3"), createFraction(10, "27") });
        parameters.add(new Object[] { createFraction(10, "2", "3"), createNumber(10, "-3"),
                                      createFraction(10, "27", "8") });
        parameters.add(new Object[] { createFraction(10, "3", "3"), createNumber(10, "-3"),
                                      createFraction(10, "3", "3") });
        parameters.add(new Object[] { createFraction(10, "4", "3"), createNumber(10, "-3"),
                                      createFraction(10, "27", "64") });
        parameters.add(new Object[] { createFraction(10, "5", "3"), createNumber(10, "-3"),
                                      createFraction(10, "27", "125") });
        parameters.add(new Object[] { createFraction(10, "6", "3"), createNumber(10, "-3"),
                                      createFraction(10, "27", "216") });
        parameters.add(new Object[] { createFraction(10, "7", "3"), createNumber(10, "-3"),
                                      createFraction(10, "27", "343") });
        parameters.add(new Object[] { createFraction(10, "8", "3"), createNumber(10, "-3"),
                                      createFraction(10, "27", "512") });
        parameters.add(new Object[] { createFraction(10, "9", "3"), createNumber(10, "-3"),
                                      createFraction(10, "27", "729") });
        parameters.add(new Object[] { createFraction(10, "10", "3"), createNumber(10, "-3"),
                                      createFraction(10, "27", "1000") });

        parameters.add(new Object[] { createFraction(10, "1", "10", "3"), createNumber(10, "3"),
                                      createFraction(10, "2197", "27") });

        parameters.add(new Object[] { createFraction(10, "1", "10", "3"), createNumber(10, "-3"),
                                      createFraction(10, "27", "2197") });

        return parameters;
    }

}
