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
import jmul.math.signs.Signs;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests multiplying two fractions with various combinations of operands.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class MultiplyFractionsTest {

    /**
     * The first operand.
     */
    private Fraction operand1;

    /**
     * The second operand.
     */
    private Fraction operand2;

    /**
     * The expected result.
     */
    private Fraction expectedResult;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param operand1
     *        a fraction
     * @param operand2
     *        a fraction
     * @param expectedResult
     *        a fraction
     */
    public MultiplyFractionsTest(Fraction operand1, Fraction operand2, Fraction expectedResult) {

        super();

        this.operand1 = operand1;
        this.operand2 = operand2;
        this.expectedResult = expectedResult;
    }

    /**
     * Returns a test summary.
     *
     * @return a test summary
     */
    @Override
    public String toString() {

        return String.format("[base:%d]: %s * %s = %s", operand1.base(), operand1, operand2, expectedResult);
    }

    /**
     * Tests multiplying the operands and checks the result.
     */
    @Test
    public void testMultiplaction() {

        Fraction actualResult = operand1.multiply(operand2);

        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
    }

    /**
     * Tests multiplying the operands and checks the result.
     */
    @Test
    public void testMultiplicationVariant2() {

        Fraction actualResult = Math.multiply(operand1, operand2);

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

            parameters.add(new Object[] { createFraction(base), createFraction(base, "1"), createFraction(base) });
            parameters.add(new Object[] { createFraction(base, "1"), createFraction(base), createFraction(base) });
            parameters.add(new Object[] { createFraction(Signs.NEGATIVE, base), createFraction(base, "1"),
                                          createFraction(Signs.NEGATIVE, base) });
            parameters.add(new Object[] { createFraction(base, "1"), createFraction(Signs.NEGATIVE, base),
                                          createFraction(Signs.NEGATIVE, base) });

            parameters.add(new Object[] { createFraction(base, "0"), createFraction(base, "10"),
                                          createFraction(base, "0") });
            parameters.add(new Object[] { createFraction(base, "10"), createFraction(base, "0"),
                                          createFraction(base, "0") });

            parameters.add(new Object[] { createFraction(base, "1"), createFraction(base, "10"),
                                          createFraction(base, "10") });
            parameters.add(new Object[] { createFraction(base, "10"), createFraction(base, "1"),
                                          createFraction(base, "10") });
            parameters.add(new Object[] { createFraction(base, "-1"), createFraction(base, "10"),
                                          createFraction(base, "-10") });
            parameters.add(new Object[] { createFraction(base, "-10"), createFraction(base, "1"),
                                          createFraction(base, "-10") });
        }

        parameters.add(new Object[] { createFraction(10, "5"), createFraction(10, "9"), createFraction(10, "45") });
        parameters.add(new Object[] { createFraction(10, "-5"), createFraction(10, "9"), createFraction(10, "-45") });

        parameters.add(new Object[] { createFraction(10, "5"), createFraction(10, "9", "3"),
                                      createFraction(10, "45", "3") });
        parameters.add(new Object[] { createFraction(10, "-5"), createFraction(10, "9", "3"),
                                      createFraction(10, "-45", "3") });

        parameters.add(new Object[] { createFraction(10, "5"), createFraction(10, "2", "9", "3"),
                                      createFraction(10, "75", "3") });
        parameters.add(new Object[] { createFraction(10, "-5"), createFraction(10, "2", "9", "3"),
                                      createFraction(10, "-75", "3") });

        parameters.add(new Object[] { createFraction(10, "5", "2"), createFraction(10, "2", "9", "3"),
                                      createFraction(10, "75", "6") });
        parameters.add(new Object[] { createFraction(10, "-5", "2"), createFraction(10, "2", "9", "3"),
                                      createFraction(10, "-75", "6") });

        parameters.add(new Object[] { createFraction(10, "5", "3"), createFraction(10, "2", "9", "3"),
                                      createFraction(10, "75", "9") });
        parameters.add(new Object[] { createFraction(10, "-5", "3"), createFraction(10, "2", "9", "3"),
                                      createFraction(10, "-75", "9") });

        parameters.add(new Object[] { createFraction(10, "1", "5", "2"), createFraction(10, "2", "9", "3"),
                                      createFraction(10, "105", "6") });
        parameters.add(new Object[] { createFraction(10, "1", "-5", "2"), createFraction(10, "2", "9", "3"),
                                      createFraction(10, "-105", "6") });

        parameters.add(new Object[] { createFraction(10, "5", "2"), createFraction(10, "9", "3"),
                                      createFraction(10, "45", "6") });
        parameters.add(new Object[] { createFraction(10, "-5", "2"), createFraction(10, "9", "3"),
                                      createFraction(10, "-45", "6") });
        parameters.add(new Object[] { createFraction(10, "5", "2"), createFraction(10, "-9", "3"),
                                      createFraction(10, "-45", "6") });
        parameters.add(new Object[] { createFraction(10, "-5", "2"), createFraction(10, "-9", "3"),
                                      createFraction(10, "45", "6") });

        return parameters;
    }

}
