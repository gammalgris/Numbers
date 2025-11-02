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
import static jmul.math.collections.CollectionsHelper.createNumberSequence;
import jmul.math.collections.Sequence;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests determining the common prime factors of two numbers.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class DetermineCommonPrimeFactorsTest {

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
    private final Sequence<Number> expectedResult;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param number1
     *        a number
     * @param number2
     *        a number
     * @param set
     *        the expected result
     */
    public DetermineCommonPrimeFactorsTest(Number number1, Number number2, Sequence<Number> set) {

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

        Sequence<Number> actualResult = number1.commonPrimeFactors(number2);

        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
    }

    /**
     * Checks the divisors and the expected divisors.
     */
    @Test
    public void checkDivisorsVariant2() {

        Sequence<Number> actualResult = Math.commonPrimeFactors(number1, number2);

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

        // 17 & 19 : { 17 } & { 19 } : { }
        parameters.add(new Object[] { createNumber(10, "17"), createNumber(10, "19"), createNumberSequence(10) });
        // 9 & 3 : { 3, 3 } & { 3 } : { 3 }
        parameters.add(new Object[] { createNumber(10, "9"), createNumber(10, "3"), createNumberSequence(10, "3") });
        // 9 & 6 : { 3, 3 } & { 2, 3 } : { 3 }
        parameters.add(new Object[] { createNumber(10, "9"), createNumber(10, "6"), createNumberSequence(10, "3") });
        // 5134 & 1020 : { 2, 17, 151 } & { 2, 2, 3, 5, 17 } : { 2, 17 }
        parameters.add(new Object[] { createNumber(10, "5134"), createNumber(10, "1020"),
                                      createNumberSequence(10, "2", "17") });

        return parameters;
    }

}
