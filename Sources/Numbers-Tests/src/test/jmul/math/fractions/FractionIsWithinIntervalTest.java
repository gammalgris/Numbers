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

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests checking if fractions are within specified bounds (including bounds).
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class FractionIsWithinIntervalTest {

    /**
     * A fraction.
     */
    private final Fraction fraction;

    /**
     * A fraction (i.e. lower bound of an interval)
     */
    private final Fraction min;

    /**
     * A fraction  (i.e. upper bound of an interval)
     */
    private final Fraction max;

    /**
     * The expected result.
     */
    private final boolean expectedResult;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param fraction
     *        a fraction
     * @param min
     *        a fraction (i.e. lower bound of an interval)
     * @param max
     *        a fraction  (i.e. upper bound of an interval)
     * @param expectedResult
     *        the expected result
     */
    public FractionIsWithinIntervalTest(Fraction fraction, Fraction min, Fraction max, boolean expectedResult) {

        super();

        this.fraction = fraction;
        this.min = min;
        this.max = max;
        this.expectedResult = expectedResult;
    }

    /**
     * Returns a summary for this test case.
     *
     * @return a summary
     */
    @Override
    public String toString() {

        return String.format("[base:%d] %s =< [base:%d] %s =< [base:%d] %s -> %b", min.base(), min, fraction.base(),
                             fraction, max.base(), max, expectedResult);
    }

    /**
     * Tests if a number is within an interval.
     */
    @Test
    public void testIsWithinInterval() {

        boolean actualResult = fraction.isWithinInterval(min, max);

        assertEquals(toString(), expectedResult, actualResult);
    }

    /**
     * Tests if a number is within an interval.
     */
    @Test
    public void testIsWithinIntervalVariant2() {

        boolean actualResult = Math.isWithinInterval(min, fraction, max);

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

            parameters.add(new Object[] { createFraction(base, "0"), createFraction(base, "-1"),
                                          createFraction(base, "1"), true });
            parameters.add(new Object[] { createFraction(base, "-1"), createFraction(base, "0"),
                                          createFraction(base, "1"), false });
            parameters.add(new Object[] { createFraction(base, "1"), createFraction(base, "-1"),
                                          createFraction(base, "0"), false });

            parameters.add(new Object[] { createFraction(base, "11", "10"), createFraction(base, "10", "10"),
                                          createFraction(base, "111", "10"), true });

            parameters.add(new Object[] { createFraction(base, "1", "100", "10"), createFraction(base, "1", "11", "10"),
                                          createFraction(base, "11", "111", "10"), true });
        }

        return parameters;
    }

}
