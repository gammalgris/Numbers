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

import jmul.math.numbers.Number;
import jmul.math.numbers.NumberImpl;
import static jmul.math.numbers.Signs.NEGATIVE;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests the number comparison (i.e. the compareTo method).
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class NumberComparisonTest {

    /**
     * A number.
     */
    private final Number number1;

    /**
     * A number.
     */
    private final Number number2;

    /**
     * The result of the equals comparison.
     */
    private final int expectedResult;

    /**
     * Creates a test case according to the specified parameters.
     *
     * @param n1
     *        a number
     * @param n2
     *        a number
     * @param result
     *        the expected result
     */
    public NumberComparisonTest(Number n1, Number n2, int result) {

        number1 = n1;
        number2 = n2;
        expectedResult = result;
    }

    /**
     * Return the inverse of the sepcified number (i.e. 1->-1, -1->1 and 0->0).
     *
     * @param i
     *        an integer
     *
     * @return an integer
     */
    private static int inverse(int i) {

        switch (i) {
        case -1:
            return 1;
        case 0:
            return 0;
        case 1:
            return -1;
        default:
            throw new IllegalArgumentException();
        }
    }

    /**
     * Compares the specified numbers and ciompares the result with th expected result.
     *
     * @param n1
     *        a number
     * @param n2
     *        a number
     * @param result
     *        the expectedresult
     */
    private static void testComparison(Number n1, Number n2, int result) {

        String message =
            String.format("The equality test failed for the numbers \"%s\" and \"%s\")!", n1.toString(), n2.toString());

        int actualResult = n1.compareTo(n2);
        assertEquals(message, result, actualResult);
    }

    /**
     * Tests the comparison of two numbers (i.e. the numbers are compared as specified and
     * additionally in an the inverted order).
     */
    @Test
    public void testComparison() {

        testComparison(number1, number2, expectedResult);
        testComparison(number2, number1, inverse(expectedResult));
    }

    /**
     * Returns a matrix of test data and expected results.
     *
     * @return a matrix of test data and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { new NumberImpl(), new NumberImpl(), 0 });
        parameters.add(new Object[] { new NumberImpl(NEGATIVE), new NumberImpl(NEGATIVE), 0 });
        parameters.add(new Object[] { new NumberImpl(), new NumberImpl(NEGATIVE), 1 });

        parameters.add(new Object[] { new NumberImpl("0"), new NumberImpl("0"), 0 });
        parameters.add(new Object[] { new NumberImpl("-0"), new NumberImpl("0"), 0 });
        parameters.add(new Object[] { new NumberImpl("0"), new NumberImpl("-0"), 0 });

        parameters.add(new Object[] { new NumberImpl("1"), new NumberImpl("1"), 0 });
        parameters.add(new Object[] { new NumberImpl("-1"), new NumberImpl("1"), -1 });
        parameters.add(new Object[] { new NumberImpl("1"), new NumberImpl("-1"), 1 });

        parameters.add(new Object[] { new NumberImpl("123456789012345678901"), new NumberImpl("123456789012345678901"),
                                      0 });
        parameters.add(new Object[] { new NumberImpl("123456789012345678901"), new NumberImpl("-123456789012345678901"),
                                      1 });
        parameters.add(new Object[] { new NumberImpl("-123456789012345678901"), new NumberImpl("123456789012345678901"),
                                      -1 });

        parameters.add(new Object[] { new NumberImpl("09123456789012345678901"),
                                      new NumberImpl("123456789012345678901"), 1 });
        parameters.add(new Object[] { new NumberImpl("09123456789012345678901"),
                                      new NumberImpl("-123456789012345678901"), 1 });
        parameters.add(new Object[] { new NumberImpl("-09123456789012345678901"),
                                      new NumberImpl("123456789012345678901"), -1 });

        parameters.add(new Object[] { new NumberImpl("123456789012345678901"), new NumberImpl("123456789012345678902"),
                                      -1 });
        parameters.add(new Object[] { new NumberImpl("-123456789012345678902"),
                                      new NumberImpl("-123456789012345678901"), -1 });

        parameters.add(new Object[] { new NumberImpl("1.23456789012345678901"),
                                      new NumberImpl("1.23456789012345678902"), -1 });
        parameters.add(new Object[] { new NumberImpl("-1.23456789012345678902"),
                                      new NumberImpl("-1.23456789012345678901"), -1 });

        parameters.add(new Object[] { new NumberImpl("1.2345678901234567890123"),
                                      new NumberImpl("1.23456789012345678901"), 1 });
        parameters.add(new Object[] { new NumberImpl("-1.23456789012345678901"),
                                      new NumberImpl("-1.234567890123456789023"), 1 });

        parameters.add(new Object[] { new NumberImpl("-1.2345"), new NumberImpl("10.98765"), -1 });
        parameters.add(new Object[] { new NumberImpl("1.2345"), new NumberImpl("-10.98765"), 1 });
        parameters.add(new Object[] { new NumberImpl("1.2345"), new NumberImpl("10.98765"), -1 });

        parameters.add(new Object[] { new NumberImpl("12345678.9"), new NumberImpl("0.987654321"), 1 });
        parameters.add(new Object[] { new NumberImpl("-12345678.9"), new NumberImpl("0.987654321"), -1 });
        parameters.add(new Object[] { new NumberImpl("12345678.9"), new NumberImpl("-0.987654321"), 1 });

        parameters.add(new Object[] { new NumberImpl("1234567890.0123456789"), new NumberImpl("0.012345678987654321"),
                                      1 });
        parameters.add(new Object[] { new NumberImpl("-1234567890.0123456789"), new NumberImpl("0.012345678987654321"),
                                      -1 });
        parameters.add(new Object[] { new NumberImpl("1234567890.0123456789"), new NumberImpl("-0.012345678987654321"),
                                      1 });
        parameters.add(new Object[] { new NumberImpl("-1234567890.0123456789"), new NumberImpl("-0.012345678987654321"),
                                      -1 });
        parameters.add(new Object[] { new NumberImpl("1234567890.0123456789"), new NumberImpl("1234567890.0123456789"),
                                      0 });
        parameters.add(new Object[] { new NumberImpl("0.012345678987654321"), new NumberImpl("0.012345678987654321"),
                                      0 });

        return parameters;
    }

}
