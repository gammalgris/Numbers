/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2024  Kristian Kutin
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

import static jmul.math.Math.compare;
import jmul.math.fractions.Fraction;
import static jmul.math.fractions.FractionHelper.createFraction;
import jmul.math.numbers.Constants;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * Test comparing two fractions.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class FractionComparisonTest {

    /**
     * A fraction.
     */
    private final Fraction fraction1;

    /**
     * A fraction.
     */
    private final Fraction fraction2;

    /**
     * The expected result.
     */
    private final int expectedComparisonResult;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param fraction1
     *        a fraction
     * @param fraction2
     *        a fraction
     * @param expectedComparisonResult
     *        the expected result
     */
    public FractionComparisonTest(Fraction fraction1, Fraction fraction2, int expectedComparisonResult) {

        super();

        this.fraction1 = fraction1;
        this.fraction2 = fraction2;
        this.expectedComparisonResult = expectedComparisonResult;
    }

    /**
     * Test comparing two fractions.
     */
    @Test
    public void testCompare() {

        int actualComparisonResult = fraction1.compareTo(fraction2);

        String message = String.format("Compare %s with %s;", fraction1, fraction2);
        assertEquals(message, expectedComparisonResult, actualComparisonResult);
    }

    /**
     * Test comparing two fractions.
     */
    @Test
    public void testCompare2() {

        int actualComparisonResult = compare(fraction1, fraction2);

        String message = String.format("Compare %s with %s;", fraction1, fraction2);
        assertEquals(message, expectedComparisonResult, actualComparisonResult);
    }

    /**
     * Returns a matrix of test data and expected results.
     *
     * @return a matrix of test data and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        for (int base = Constants.BASE_MIN_LIMIT; base <= Constants.BASE_MAX_LIMIT; base++) {

            parameters.add(new Object[] { createFraction(base, null), createFraction(base, null), 0 });
            parameters.add(new Object[] { createFraction(base, "0"), createFraction(base, null), -1 });
            parameters.add(new Object[] { createFraction(base, null), createFraction(base, "0"), 1 });

            parameters.add(new Object[] { createFraction(base, "1"), createFraction(base, "1"), 0 });
            parameters.add(new Object[] { createFraction(base, "0"), createFraction(base, "1"), -1 });
            parameters.add(new Object[] { createFraction(base, "1"), createFraction(base, "0"), 1 });

            parameters.add(new Object[] { createFraction(base, "11"), createFraction(base, "10"), 1 });
            parameters.add(new Object[] { createFraction(base, "10"), createFraction(base, "11"), -1 });

            parameters.add(new Object[] { createFraction(base, "11", "10"), createFraction(base, "10"), -1 });
            parameters.add(new Object[] { createFraction(base, "10"), createFraction(base, "11", "10"), 1 });

            parameters.add(new Object[] { createFraction(base, "11", "101"), createFraction(base, "10", "110"), 1 });
            parameters.add(new Object[] { createFraction(base, "10", "110"), createFraction(base, "11", "101"), -1 });

            parameters.add(new Object[] { createFraction(base, "11", "10", "11"), createFraction(base, "10"), 1 });
            parameters.add(new Object[] { createFraction(base, "10"), createFraction(base, "11", "10", "11"), -1 });

            parameters.add(new Object[] { createFraction(base, "11", "101", "11"), createFraction(base, "10", "110"),
                                          1 });
            parameters.add(new Object[] { createFraction(base, "10", "110"), createFraction(base, "11", "101", "11"),
                                          -1 });

            parameters.add(new Object[] { createFraction(base, "11", "101", "11"),
                                          createFraction(base, "10", "101", "11"), 1 });
            parameters.add(new Object[] { createFraction(base, "10", "101", "11"),
                                          createFraction(base, "11", "101", "11"), -1 });
            parameters.add(new Object[] { createFraction(base, "11", "101", "11"),
                                          createFraction(base, "11", "101", "11"), 0 });
        }

        parameters.add(new Object[] { createFraction(2, "1", "101", "11"), createFraction(2, "10", "10", "11"), 0 });

        parameters.add(new Object[] { createFraction(10, "1", "5", "3"), createFraction(10, "2", "2", "3"), 0 });

        return parameters;
    }

}
