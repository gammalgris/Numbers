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
import static jmul.math.numbers.Constants.BASE_MAX_LIMIT;
import static jmul.math.numbers.Constants.BASE_MIN_LIMIT;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suit checks if the multiples of numbers are correctly identified.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class IsMultipleTest {

    /**
     * A number.
     */
    private Number number1;

    /**
     * A number.
     */
    private Number number2;

    /**
     * The expected result.
     */
    private boolean expectedResult;

    /**
     * Creates a test case according to the specified parameters.
     *
     * @param number1
     *        a number
     * @param number2
     *        a number
     * @param expectedResult
     *        the expected result
     */
    public IsMultipleTest(Number number1, Number number2, boolean expectedResult) {

        super();

        this.number1 = number1;
        this.number2 = number2;
        this.expectedResult = expectedResult;
    }

    /**
     * Returns a summary of this test.
     *
     * @return a summary
     */
    @Override
    public String toString() {

        return String.format("[base:%d] %s is multiple of [base:%d] %s) -> %s", number2.base(), number2, number1.base(),
                             number1, expectedResult);
    }

    /**
     * Tests if the first number is a multiple of the second number and checks the result.
     */
    @Test
    public void testMultiple() {

        boolean actualResult = number1.isMultipleOf(number2);

        assertEquals(toString(), expectedResult, actualResult);
    }

    /**
     * Tests if the first number is a multiple of the second number and checks the result.
     */
    @Test
    public void testMultipleVariant2() {

        boolean actualResult = Math.isMultipleOf(number1, number2);

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

            parameters.add(new Object[] { createNumber(base, "10"), createNumber(base, "1"), true });
            parameters.add(new Object[] { createNumber(base, "1"), createNumber(base, "10"), false });
        }

        return parameters;
    }

}
