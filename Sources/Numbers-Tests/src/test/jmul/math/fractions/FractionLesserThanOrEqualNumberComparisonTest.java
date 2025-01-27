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
import static jmul.math.numbers.Constants.BASE_MAX_LIMIT;
import static jmul.math.numbers.Constants.BASE_MIN_LIMIT;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite compares a fraction with a number.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class FractionLesserThanOrEqualNumberComparisonTest {

    /**
     * A fraction.
     */
    private final Fraction fraction;

    /**
     * A number.
     */
    private final Number number;

    /**
     * The expected comparison result.
     */
    private final boolean expectedResult;

    /**
     * Creates a test case according to the specified parameters.
     *
     * @param fraction
     *        a fraction
     * @param number
     *        a number
     * @param expectedResult
     *        the expected comparison result
     */
    public FractionLesserThanOrEqualNumberComparisonTest(Fraction fraction, Number number, boolean expectedResult) {

        super();

        this.fraction = fraction;
        this.number = number;
        this.expectedResult = expectedResult;
    }

    /**
     * Returns a summary of this test case (i.e. the operation with its operands)
     *
     * @return a summary
     */
    @Override
    public String toString() {

        return String.format("[base:%d] %s <= %s", fraction.base(), fraction, number);
    }

    /**
     * Tests comparing a fraction and a number and checks the result.
     */
    @Test
    public void testComparison() {

        boolean actualResult = fraction.isLesserOrEqual(number);

        assertEquals(toString(), expectedResult, actualResult);
    }

    /**
     * Tests comparing a fraction and a number and checks the result.
     */
    @Test
    public void testComparisonVariant2() {

        boolean actualResult = Math.isLesserOrEqual(fraction, number);

        assertEquals(toString(), expectedResult, actualResult);
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

            parameters.add(new Object[] { createFraction(base, "1", "10"), createNumber(base, "1"), true });
            parameters.add(new Object[] { createFraction(base, "11", "10"), createNumber(base, "1"), false });
            parameters.add(new Object[] { createFraction(base, "1", "100"), createNumber(base, "1"), true });

            parameters.add(new Object[] { createFraction(base, "10"), createNumber(base, "1"), false });
            parameters.add(new Object[] { createFraction(base, "1"), createNumber(base, "1"), true });
            parameters.add(new Object[] { createFraction(base, "1"), createNumber(base, "10"), true });

            parameters.add(new Object[] { createFraction(base, "10", "1", "10"), createNumber(base, "10"), false });
            parameters.add(new Object[] { createFraction(base, "10", "1", "100"), createNumber(base, "100"), true });
        }

        parameters.add(new Object[] { createFraction(10, "11", "10"), createNumber(10, "1"), false });
        parameters.add(new Object[] { createFraction(10, "9", "10"), createNumber(10, "1"), true });

        return parameters;
    }

}
