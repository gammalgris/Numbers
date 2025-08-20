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

import static jmul.math.fractions.FractionHelper.createFraction;
import static jmul.math.numbers.Constants.BASE_MAX_LIMIT;
import static jmul.math.numbers.Constants.BASE_MIN_LIMIT;
import static jmul.math.numbers.NumberHelper.createInfinity;
import static jmul.math.numbers.NumberHelper.createNegativeInfinity;
import static jmul.math.numbers.NumberHelper.createNumber;
import static jmul.math.numbers.creation.CreationParameters.DONT_CLONE;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite contains tests for testing the equals method.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class FractionNumberEqualityTest {

    /**
     * A number or fraction.
     */
    private final Object operand1;

    /**
     * A number or fraction.
     */
    private final Object operand2;

    /**
     * The expected result.
     */
    private final boolean expectedResult;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param operand1
     *        a number or fraction
     * @param operand2
     *        a number or fraction
     * @param expectedResult
     *        the expected comparison result
     */
    public FractionNumberEqualityTest(Object operand1, Object operand2, boolean expectedResult) {

        super();

        this.operand1 = operand1;
        this.operand2 = operand2;
        this.expectedResult = expectedResult;
    }

    /**
     * Returns a description for the current test case.
     *
     * @return a test case description
     */
    @Override
    public String toString() {

        return String.format("%s equals %s;", operand1, operand2);
    }

    /**
     * Tests equality of two numbers.
     */
    @Test
    public void testEquality() {

        if (operand1 != null) {

            boolean actualResult = operand1.equals(operand2);
            assertEquals(toString(), expectedResult, actualResult);
        }
    }

    /**
     * Tests equality of two numbers.
     */
    @Test
    public void testEqualityWithSwitchedOperands() {

        if (operand2 != null) {

            boolean actualResult = operand2.equals(operand1);
            assertEquals(toString(), expectedResult, actualResult);
        }
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

            parameters.add(new Object[] { createFraction(DONT_CLONE, createInfinity(base)), createInfinity(base),
                                          true });
            parameters.add(new Object[] { createFraction(DONT_CLONE, createNegativeInfinity(base)),
                                          createInfinity(base), false });
            parameters.add(new Object[] { createFraction(DONT_CLONE, createNegativeInfinity(base)),
                                          createInfinity(base), false });
            parameters.add(new Object[] { createFraction(DONT_CLONE, createNegativeInfinity(base)),
                                          createNegativeInfinity(base), true });

            parameters.add(new Object[] { createFraction(base, "0"), createNumber(base, "0"), true });
            parameters.add(new Object[] { createFraction(base, "0"), createNumber(base, "1"), false });
            parameters.add(new Object[] { createFraction(base, "1"), createNumber(base, "0"), false });
            parameters.add(new Object[] { createFraction(base, "0"), createNumber(base, "-1"), false });
            parameters.add(new Object[] { createFraction(base, "-1"), createNumber(base, "0"), false });

            parameters.add(new Object[] { createFraction(base, "11", "1"), createNumber(base, "11"), true });
            parameters.add(new Object[] { createFraction(base, "11", "1"), createNumber(base, "10"), false });
            parameters.add(new Object[] { createFraction(base, "11", "1"), createNumber(base, "100"), false });

            parameters.add(new Object[] { createFraction(base, "1", "10", "1"), createNumber(base, "11"), true });
            parameters.add(new Object[] { createFraction(base, "1", "10", "1"), createNumber(base, "10"), false });
            parameters.add(new Object[] { createFraction(base, "1", "10", "1"), createNumber(base, "100"), false });

            parameters.add(new Object[] { createFraction(base, "0"), createNumber(base, "0.1"), false });
            parameters.add(new Object[] { createFraction(base, "1", "10"), createNumber(base, "0.1"), true });
        }

        return parameters;
    }

}
