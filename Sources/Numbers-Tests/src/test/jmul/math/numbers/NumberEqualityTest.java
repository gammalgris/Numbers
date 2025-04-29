/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2022  Kristian Kutin
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

import static jmul.math.numbers.Constants.BASE_MAX_LIMIT;
import static jmul.math.numbers.Constants.BASE_MIN_LIMIT;
import jmul.math.numbers.Number;
import jmul.math.numbers.NumberImpl;
import static jmul.math.signs.Signs.NEGATIVE;

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
public class NumberEqualityTest {

    /**
     * A number.
     */
    private final Number number1;

    /**
     * A number.
     */
    private final Number number2;

    /**
     * The expected result.
     */
    private final boolean expectedResult;

    /**
     * Creeates a new test case according to the specified parameters.
     *
     * @param number1
     *        a number
     * @param number2
     *        a number
     * @param expectedResult
     *        the expected comparison result
     */
    public NumberEqualityTest(Number number1, Number number2, boolean expectedResult) {

        super();

        this.number1 = number1;
        this.number2 = number2;
        this.expectedResult = expectedResult;
    }

    /**
     * Returns a description for the current test case.
     *
     * @return a test case description
     */
    @Override
    public String toString() {

        return String.format("%s equals %s;", number1, number2);
    }

    /**
     * Tests equality of two numbers.
     */
    @Test
    public void testEquality() {

        if (number1 != null) {

            boolean actualResult = number1.equals(number2);
            assertEquals(toString(), expectedResult, actualResult);
        }
    }

    /**
     * Tests equality of two numbers.
     */
    @Test
    public void testEqualityWithSwitchedOperands() {

        if (number2 != null) {

            boolean actualResult = number2.equals(number1);
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

            Number n = new NumberImpl(base, "1");
            parameters.add(new Object[] { n, n, true });

            parameters.add(new Object[] { new NumberImpl(base, "1"), null, false });
            parameters.add(new Object[] { null, new NumberImpl(base, "1"), false });

            parameters.add(new Object[] { new NumberImpl(base), new NumberImpl(base), true });
            parameters.add(new Object[] { new NumberImpl(base), new NumberImpl(base, NEGATIVE), false });
            parameters.add(new Object[] { new NumberImpl(base, NEGATIVE), new NumberImpl(base), false });
            parameters.add(new Object[] { new NumberImpl(base, NEGATIVE), new NumberImpl(base, NEGATIVE), true });

            parameters.add(new Object[] { new NumberImpl(base, "0"), new NumberImpl(base, "0"), true });
            parameters.add(new Object[] { new NumberImpl(base, "1"), new NumberImpl(base, "0"), false });
            parameters.add(new Object[] { new NumberImpl(base, "0"), new NumberImpl(base, "1"), false });
            parameters.add(new Object[] { new NumberImpl(base, "-1"), new NumberImpl(base, "0"), false });
            parameters.add(new Object[] { new NumberImpl(base, "0"), new NumberImpl(base, "-1"), false });

            parameters.add(new Object[] { new NumberImpl(base, "1111111111"), new NumberImpl(base, "1.111111111"),
                                          false });
            parameters.add(new Object[] { new NumberImpl(base, "1.111111111"), new NumberImpl(base, "1111111111"),
                                          false });
        }

        parameters.add(new Object[] { new NumberImpl(8, "11"), new NumberImpl(8), false });

        parameters.add(new Object[] { new NumberImpl(8, "3210765432107654321"),
                                      new NumberImpl(8, "3210765432107654321"), true });
        parameters.add(new Object[] { new NumberImpl(8, "43210765432107654321"),
                                      new NumberImpl(8, "3210765432107654321"), false });
        parameters.add(new Object[] { new NumberImpl(8, "3210765432107654321"),
                                      new NumberImpl(8, "43210765432107654321"), false });
        parameters.add(new Object[] { new NumberImpl(8, "3210765432107654321.0123456712345670123"),
                                      new NumberImpl(8, "3210765432107654321.0123456712345670123"), true });
        parameters.add(new Object[] { new NumberImpl(8, "3210765432107654321.01234567123456701234"),
                                      new NumberImpl(8, "3210765432107654321.0123456712345670123"), false });
        parameters.add(new Object[] { new NumberImpl(8, "3210765432107654321.0123456712345670123"),
                                      new NumberImpl(8, "3210765432107654321.01234567123456701234"), false });

        parameters.add(new Object[] { new NumberImpl(), new NumberImpl(), true });
        parameters.add(new Object[] { new NumberImpl(), new NumberImpl(), true });


        return parameters;
    }

}
