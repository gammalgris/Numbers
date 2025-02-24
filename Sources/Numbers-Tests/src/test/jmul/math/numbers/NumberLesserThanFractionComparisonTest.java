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
import static jmul.math.numbers.NumberHelper.createNumber;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite compares a number with a fraction.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class NumberLesserThanFractionComparisonTest {

    /**
     * A number.
     */
    private final Number number;

    /**
     * A fraction.
     */
    private final Fraction fraction;

    /**
     * The expected comparison result.
     */
    private final boolean expectedResult;

    /**
     * Creates a test case according to the specified parameters.
     *
     * @param number
     *        a number
     * @param fraction
     *        a fraction
     * @param expectedResult
     *        the expected comparison result
     */
    public NumberLesserThanFractionComparisonTest(Number number, Fraction fraction, boolean expectedResult) {

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

        return String.format("[base:%d] %s < %s", number.base(), number, fraction);
    }

    /**
     * Tests comparing a fraction and a number and checks the result.
     */
    @Test
    public void testComparison() {

        boolean actualResult = number.isLesser(fraction);

        assertEquals(toString(), expectedResult, actualResult);
    }

    /**
     * Tests comparing a fraction and a number and checks the result.
     */
    @Test
    public void testComparisonVariant2() {

        boolean actualResult = Math.isLesser(number, fraction);

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

            parameters.add(new Object[] { createNumber(base, "1"), createFraction(base, "1", "10"), false });
            parameters.add(new Object[] { createNumber(base, "1"), createFraction(base, "11", "10"), true });
            parameters.add(new Object[] { createNumber(base, "1"), createFraction(base, "1", "100"), false });

            parameters.add(new Object[] { createNumber(base, "1"), createFraction(base, "10"), true });
            parameters.add(new Object[] { createNumber(base, "1"), createFraction(base, "1"), false });
            parameters.add(new Object[] { createNumber(base, "10"), createFraction(base, "1"), false });

            parameters.add(new Object[] { createNumber(base, "10"), createFraction(base, "10", "1", "10"), true });
            parameters.add(new Object[] { createNumber(base, "100"), createFraction(base, "10", "1", "100"), false });

            parameters.add(new Object[] { createNumber(base, "0.1"), createFraction(base, "0"), false });
            parameters.add(new Object[] { createNumber(base, "0.1"), createFraction(base, "1", "10"), false });
        }

        parameters.add(new Object[] { createNumber(10, "1"), createFraction(10, "11", "10"), true });
        parameters.add(new Object[] { createNumber(10, "1"), createFraction(10, "9", "10"), false });

        return parameters;
    }

}
