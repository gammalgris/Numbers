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
 * This test suite compares two fractions.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class FractionLesserThanComparisonTest {

    /**
     * A fraction.
     */
    private final Fraction fraction1;

    /**
     * A fraction.
     */
    private final Fraction fraction2;

    /**
     * The expected comparison result.
     */
    private final boolean expectedResult;

    /**
     * Creates a test case according to the specified parameters.
     *
     * @param fraction1
     *        a fraction
     * @param fraction2
     *        a fraction
     * @param expectedResult
     *        the expected comparison result
     */
    public FractionLesserThanComparisonTest(Fraction fraction1, Fraction fraction2, boolean expectedResult) {

        super();

        this.fraction1 = fraction1;
        this.fraction2 = fraction2;
        this.expectedResult = expectedResult;
    }

    /**
     * Returns a summary of this test case (i.e. the operation with its operands)
     *
     * @return a summary
     */
    @Override
    public String toString() {

        return String.format("[base:%d] %s < %s", fraction1.base(), fraction1, fraction2);
    }

    /**
     * Tests comparing two numbers and checks the result.
     */
    @Test
    public void testComparison() {

        boolean actualResult = fraction1.isLesser(fraction2);

        assertEquals(toString(), expectedResult, actualResult);
    }

    /**
     * Tests comparing two numbers and checks the result.
     */
    @Test
    public void testComparisonVariant2() {

        boolean actualResult = Math.isLesser(fraction1, fraction2);

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

            parameters.add(new Object[] { createFraction(base, "1", "10"), createFraction(base, "1", "100"), false });
            parameters.add(new Object[] { createFraction(base, "1", "10"), createFraction(base, "1", "10"), false });
            parameters.add(new Object[] { createFraction(base, "1", "100"), createFraction(base, "1", "10"), true });

            parameters.add(new Object[] { createFraction(base, "10"), createFraction(base, "1"), false });
            parameters.add(new Object[] { createFraction(base, "1"), createFraction(base, "1"), false });
            parameters.add(new Object[] { createFraction(base, "1"), createFraction(base, "10"), true });

            parameters.add(new Object[] { createFraction(base, "10", "1", "10"), createFraction(base, "10", "1", "100"),
                                          false });
            parameters.add(new Object[] { createFraction(base, "10", "1", "10"), createFraction(base, "10", "1", "10"),
                                          false });
            parameters.add(new Object[] { createFraction(base, "10", "1", "100"), createFraction(base, "10", "1", "10"),
                                          true });

            parameters.add(new Object[] { createFraction(base, "1", "10"), createFraction(base, "1"), true });
            parameters.add(new Object[] { createFraction(base, "1", "1"), createFraction(base, "1"), false });
            parameters.add(new Object[] { createFraction(base, "11", "10"), createFraction(base, "1"), false });

            parameters.add(new Object[] { createFraction(base, "1", "10"), createFraction(base, "1", "1", "10"),
                                          true });
            parameters.add(new Object[] { createFraction(base, "1", "1"), createFraction(base, "1", "1", "10"), true });
        }

        parameters.add(new Object[] { createFraction(2, "11", "10"), createFraction(2, "1", "1", "10"), false });

        parameters.add(new Object[] { createFraction(10, "11", "10"), createFraction(10, "1", "1", "10"), false });

        return parameters;
    }

}
