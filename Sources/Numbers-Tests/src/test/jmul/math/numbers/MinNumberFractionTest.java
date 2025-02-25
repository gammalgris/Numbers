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
import jmul.math.signs.Signs;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suit tests the min function with mixed parameters.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class MinNumberFractionTest {

    /**
     * The first operand.
     */
    private final Number operand1;

    /**
     * The second operand.
     */
    private final Fraction operand2;

    /**
     * The expected result.
     */
    private final Fraction expectedResult;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param operand1
     *        a number
     * @param operand2
     *        a fraction
     * @param expectedResult
     *        the expected result
     */
    public MinNumberFractionTest(Number operand1, Fraction operand2, Fraction expectedResult) {

        super();

        this.operand1 = operand1;
        this.operand2 = operand2;
        this.expectedResult = expectedResult;
    }

    /**
     * Returns a summary of this test.
     *
     * @return a summary
     */
    @Override
    public String toString() {

        String summary =
            String.format("[base:%d] min(%s, %s) -> %s", operand1.base(), operand1, operand2, expectedResult);
        return summary;
    }

    /**
     * Tests comparing two fractions and getting the greater fraction.
     */
    @Test
    public void testGettingMin() {

        Fraction actualResult = operand1.min(operand2);
        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
    }

    /**
     * Tests comparing two fractions and getting the greater fraction.
     */
    @Test
    public void testGettingMinVariant2() {

        Fraction actualResult = Math.min(operand1, operand2);
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

            parameters.add(new Object[] { createNumber(base, "0"), createFraction(base), createFraction(base, "0") });
            parameters.add(new Object[] { createNumber(Signs.NEGATIVE, base), createFraction(base),
                                          createFraction(Signs.NEGATIVE, base) });
            parameters.add(new Object[] { createNumber(base, "0"), createFraction(Signs.NEGATIVE, base),
                                          createFraction(Signs.NEGATIVE, base) });
            parameters.add(new Object[] { createNumber(base), createFraction(Signs.NEGATIVE, base),
                                          createFraction(Signs.NEGATIVE, base) });

            parameters.add(new Object[] { createNumber(base, "0"), createFraction(base, "1"),
                                          createFraction(base, "0") });
            parameters.add(new Object[] { createNumber(base, "1"), createFraction(base, "0"),
                                          createFraction(base, "0") });
            parameters.add(new Object[] { createNumber(base, "0"), createFraction(base, "-1"),
                                          createFraction(base, "-1") });
            parameters.add(new Object[] { createNumber(base, "-1"), createFraction(base, "0"),
                                          createFraction(base, "-1") });

            parameters.add(new Object[] { createNumber(base, "0"), createFraction(base, "1", "10"),
                                          createFraction(base, "0") });
            parameters.add(new Object[] { createNumber(base, "1.1"), createFraction(base, "1"),
                                          createFraction(base, "1") });

            parameters.add(new Object[] { createNumber(base, "0"), createFraction(base, "1", "1", "10"),
                                          createFraction(base, "0") });
        }

        return parameters;
    }

}
