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
import static jmul.math.fractions.FractionHelper.DONT_CLONE;
import static jmul.math.fractions.FractionHelper.createFraction;
import static jmul.math.numbers.Constants.BASE_MAX_LIMIT;
import static jmul.math.numbers.Constants.BASE_MIN_LIMIT;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createInfinity;
import static jmul.math.numbers.NumberHelper.createNegativeInfinity;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.signs.Signs;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests dividing a fraction by a number.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class DivideFractionByNumberTest {

    /**
     * The dividend.
     */
    private final Fraction dividend;

    /**
     * The divisor,
     */
    private final Number divisor;

    /**
     * The expected result.
     */
    private final Fraction expectedResult;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param dividend
     *        the dividend
     * @param divisor
     *        the divisor
     * @param expectedResult
     *        the expected result
     */
    public DivideFractionByNumberTest(Fraction dividend, Number divisor, Fraction expectedResult) {

        super();

        this.dividend = dividend;
        this.divisor = divisor;
        this.expectedResult = expectedResult;
    }

    /**
     * Returns a summary of this test case (i.e. the operation with its operands)
     *
     * @return a summary
     */
    @Override
    public String toString() {

        return String.format("[base:%d] %s / %s = %s", dividend.base(), dividend, divisor, expectedResult);
    }

    /**
     * Performs a division and checks the result.
     */
    @Test
    public void testDivision() {

        Fraction actualResult = dividend.divide(divisor);

        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
    }

    /**
     * Performs a division and checks the result.
     */
    @Test
    public void testDivisionVariant2() {

        Fraction actualResult = Math.divide(dividend, divisor);

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

            parameters.add(new Object[] { createFraction(base), createNumber(base, "1"), createFraction(base) });
            parameters.add(new Object[] { createFraction(Signs.NEGATIVE, base), createNumber(base, "1"),
                                          createFraction(Signs.NEGATIVE, base) });

            parameters.add(new Object[] { createFraction(base, "1"), createInfinity(base),
                                          createFraction(base, "1", null) });
            parameters.add(new Object[] { createFraction(base, "1"), createNegativeInfinity(base),
                                          createFraction(DONT_CLONE, createNumber(base, "-1"), createInfinity(base)) });

            parameters.add(new Object[] { createFraction(base, "1"), createNumber(base, "1"),
                                          createFraction(base, "1", "1") });
            parameters.add(new Object[] { createFraction(base, "-1"), createNumber(base, "1"),
                                          createFraction(base, "-1", "1") });
            parameters.add(new Object[] { createFraction(base, "1"), createNumber(base, "-1"),
                                          createFraction(base, "-1", "1") });
            parameters.add(new Object[] { createFraction(base, "-1"), createNumber(base, "-1"),
                                          createFraction(base, "1", "1") });

            parameters.add(new Object[] { createFraction(base, "0"), createNumber(base, "1"),
                                          createFraction(base, "0") });
            parameters.add(new Object[] { createFraction(base, "0"), createNumber(base, "-1"),
                                          createFraction(base, "0") });

            parameters.add(new Object[] { createFraction(base, "10"), createNumber(base, "1"),
                                          createFraction(base, "10") });
            parameters.add(new Object[] { createFraction(base, "-10"), createNumber(base, "1"),
                                          createFraction(base, "-10") });
            parameters.add(new Object[] { createFraction(base, "10"), createNumber(base, "-1"),
                                          createFraction(base, "-10") });
            parameters.add(new Object[] { createFraction(base, "-10"), createNumber(base, "-1"),
                                          createFraction(base, "10") });

            parameters.add(new Object[] { createFraction(base, "1"), createNumber(base, "10"),
                                          createFraction(base, "1", "10") });
            parameters.add(new Object[] { createFraction(base, "-1"), createNumber(base, "10"),
                                          createFraction(base, "-1", "10") });
            parameters.add(new Object[] { createFraction(base, "1"), createNumber(base, "-10"),
                                          createFraction(base, "-1", "10") });
            parameters.add(new Object[] { createFraction(base, "-1"), createNumber(base, "-10"),
                                          createFraction(base, "1", "10") });
        }

        parameters.add(new Object[] { createFraction(10, "5", "2"), createNumber(10, "4"),
                                      createFraction(10, "5", "8") });
        parameters.add(new Object[] { createFraction(10, "-5", "2"), createNumber(10, "4"),
                                      createFraction(10, "-5", "8") });
        parameters.add(new Object[] { createFraction(10, "5", "2"), createNumber(10, "-4"),
                                      createFraction(10, "-5", "8") });
        parameters.add(new Object[] { createFraction(10, "-5", "2"), createNumber(10, "-4"),
                                      createFraction(10, "5", "8") });

        return parameters;
    }

}
