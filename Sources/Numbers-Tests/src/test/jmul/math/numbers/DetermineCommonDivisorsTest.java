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
import static jmul.math.collections.CollectionsHelper.createNumberSet;
import jmul.math.collections.Set;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests determining the common divisors of two numbers.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class DetermineCommonDivisorsTest {

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
    private final Set<Number> expectedResult;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param number1
     *        a number
     * @param number2
     *        a number
     * @param set
     *        the expüected result
     */
    public DetermineCommonDivisorsTest(Number number1, Number number2, Set<Number> set) {

        super();

        this.number1 = number1;
        this.number2 = number2;
        this.expectedResult = set;
    }

    /**
     * Returns a test summary.
     *
     * @return a test summary
     */
    @Override
    public String toString() {

        return String.format("[base:%d] %s & [base:%d] %s : %s", number1.base(), number1, number2.base(), number2,
                             expectedResult);
    }

    /**
     * Checks the divisors and the expected divisors.
     */
    @Test
    public void checkDivisors() {

        Set<Number> actualResult = number1.commonDivisors(number2);

        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
    }

    /**
     * Checks the divisors and the expected divisors.
     */
    @Test
    public void checkDivisorsVariant2() {

        Set<Number> actualResult = Math.commonDivisors(number1, number2);

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

        // 8 & 5 : { 1 }
        parameters.add(new Object[] { createNumber(2, "1000"), createNumber(2, "101"), createNumberSet(2, "1") });
        // 9 & 3 : { 1, 3 }
        parameters.add(new Object[] { createNumber(2, "1001"), createNumber(2, "110"), createNumberSet(2, "1", "11") });
        // 153 & 27 : { 1, 3, 9 }
        parameters.add(new Object[] { createNumber(2, "10011001"), createNumber(2, "11011"),
                                      createNumberSet(2, "1", "11", "1001") });

        // 8 & 5 : { 1 }
        parameters.add(new Object[] { createNumber(3, "22"), createNumber(3, "12"), createNumberSet(3, "1") });
        // 9 & 3 : { 1, 3 }
        parameters.add(new Object[] { createNumber(3, "100"), createNumber(3, "20"), createNumberSet(3, "1", "10") });
        // 153 & 27 : { 1, 3, 9 }
        parameters.add(new Object[] { createNumber(3, "12200"), createNumber(3, "1000"),
                                      createNumberSet(3, "1", "10", "100") });

        // 8 & 5 : { 1 }
        parameters.add(new Object[] { createNumber(4, "20"), createNumber(4, "11"), createNumberSet(4, "1") });
        // 9 & 3 : { 1, 3 }
        parameters.add(new Object[] { createNumber(4, "21"), createNumber(4, "12"), createNumberSet(4, "1", "3") });
        // 153 & 27 : { 1, 3, 9 }
        parameters.add(new Object[] { createNumber(4, "2121"), createNumber(4, "123"),
                                      createNumberSet(4, "1", "3", "21") });

        // 8 & 5 : { 1 }
        parameters.add(new Object[] { createNumber(10, "8"), createNumber(10, "5"), createNumberSet(10, "1") });
        // 9 & 3 : { 1, 3 }
        parameters.add(new Object[] { createNumber(10, "9"), createNumber(10, "3"), createNumberSet(10, "1", "3") });
        // 153 & 27 : { 1, 3, 9 }
        parameters.add(new Object[] { createNumber(10, "153"), createNumber(10, "27"),
                                      createNumberSet(10, "1", "3", "9") });

        // 854 & 397 : { 1 }
        parameters.add(new Object[] { createNumber(10, "854"), createNumber(10, "397"),
                                      createNumberSet(10, "1") });

        // 1286 & 566 : { 1, 2 }
        parameters.add(new Object[] { createNumber(10, "1286"), createNumber(10, "566"),
                                      createNumberSet(10, "1", "2") });

        return parameters;
    }

}
