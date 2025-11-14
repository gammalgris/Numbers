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

import static test.jmul.math.numbers.NumberCheckHelper.checkNumbersAreUniqueInstances;


/**
 * This test suite tests multiplying two numbers with various combinations of operands.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class MultiplyNumbersTest {

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
    private final Number expectedResult;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param number1
     *        a number
     * @param number2
     *        a number
     * @param expectedResult
     *        the expected result
     */
    public MultiplyNumbersTest(Number number1, Number number2, Number expectedResult) {

        super();

        this.number1 = number1;
        this.number2 = number2;
        this.expectedResult = expectedResult;
    }

    /**
     * Returns a string retpresentation for this test case.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return String.format("[base:%d] %s * [base:%d] %s = [base:%d] %s", number1.base(), number1, number2.base(),
                             number2, expectedResult.base(), expectedResult);
    }

    /**
     * Tests multiplying the operands and checks the result.
     */
    @Test
    public void testMultiplaction() {

        Number actualResult = number1.multiply(number2);

        checkNumbersAreUniqueInstances(number1, number2, actualResult);
        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
    }

    /**
     * Tests multiplying the operands and checks the result.
     */
    @Test
    public void testMultiplicationVariant2() {

        Number actualResult = Math.multiply(number1, number2);

        checkNumbersAreUniqueInstances(number1, number2, actualResult);
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

            parameters.add(createTestCase(base, "1", null, null));
            parameters.add(createTestCase(base, null, "1", null));

            parameters.add(createTestCase(base, "1", "1", "1"));
            parameters.add(createTestCase(base, "1", "0", "0"));
            parameters.add(createTestCase(base, "0", "1", "0"));
            parameters.add(createTestCase(base, "1", "-1", "-1"));
            parameters.add(createTestCase(base, "-1", "1", "-1"));
            parameters.add(createTestCase(base, "-1", "-1", "1"));
        }

        for (int base = BASE_MIN_LIMIT + 1; base <= BASE_MAX_LIMIT; base++) {

            parameters.add(createTestCase(base, "2", null, null));
            parameters.add(createTestCase(base, null, "1", null));

            parameters.add(createTestCase(base, "2", "1", "2"));
            parameters.add(createTestCase(base, "1", "2", "2"));
            parameters.add(createTestCase(base, "2", "0", "0"));
            parameters.add(createTestCase(base, "0", "2", "0"));
            parameters.add(createTestCase(base, "0", "1", "0"));
            parameters.add(createTestCase(base, "1", "0", "0"));
            parameters.add(createTestCase(base, "2", "-1", "-2"));
            parameters.add(createTestCase(base, "-1", "2", "-2"));
            parameters.add(createTestCase(base, "-2", "1", "-2"));
            parameters.add(createTestCase(base, "1", "-2", "-2"));
            parameters.add(createTestCase(base, "-2", "-1", "2"));
            parameters.add(createTestCase(base, "-1", "-2", "2"));
        }

        parameters.add(createTestCase(10, "11", "0", "0"));
        parameters.add(createTestCase(10, "0", "6", "0"));

        parameters.add(createTestCase(10, "11", "6", "66"));
        parameters.add(createTestCase(10, "11", "0.6", "6.6"));
        parameters.add(createTestCase(10, "11", "0.06", "0.66"));
        parameters.add(createTestCase(10, "11", "0.006", "0.066"));

        parameters.add(createTestCase(3, "0.220110201", "22", "21.020202122"));

        // multiplication table in decimal

        for (int a = -10; a <= 10; a++) {

            for (int b = -10; b <= 10; b++) {

                int result = a * b;
                parameters.add(createTestCase(10, "" + a, "" + b, "" + result));
            }
        }

        // multiplication table in ternary

        parameters.add(createTestCase(3, "1", "1", "1"));
        parameters.add(createTestCase(3, "1", "2", "2"));
        parameters.add(createTestCase(3, "1", "10", "10"));
        parameters.add(createTestCase(3, "1", "11", "11"));
        parameters.add(createTestCase(3, "1", "12", "12"));
        parameters.add(createTestCase(3, "1", "20", "20"));
        parameters.add(createTestCase(3, "1", "21", "21"));
        parameters.add(createTestCase(3, "1", "22", "22"));
        parameters.add(createTestCase(3, "1", "100", "100"));
        parameters.add(createTestCase(3, "1", "101", "101"));

        parameters.add(createTestCase(3, "2", "1", "2"));
        parameters.add(createTestCase(3, "2", "2", "11"));
        parameters.add(createTestCase(3, "2", "10", "20"));
        parameters.add(createTestCase(3, "2", "11", "22"));
        parameters.add(createTestCase(3, "2", "12", "101"));
        parameters.add(createTestCase(3, "2", "20", "110"));
        parameters.add(createTestCase(3, "2", "21", "112"));
        parameters.add(createTestCase(3, "2", "22", "121"));
        parameters.add(createTestCase(3, "2", "100", "200"));
        parameters.add(createTestCase(3, "2", "101", "202"));

        parameters.add(createTestCase(3, "10", "1", "10"));
        parameters.add(createTestCase(3, "10", "2", "20"));
        parameters.add(createTestCase(3, "10", "10", "100"));
        parameters.add(createTestCase(3, "10", "11", "110"));
        parameters.add(createTestCase(3, "10", "12", "120"));
        parameters.add(createTestCase(3, "10", "20", "200"));
        parameters.add(createTestCase(3, "10", "21", "210"));
        parameters.add(createTestCase(3, "10", "22", "220"));
        parameters.add(createTestCase(3, "10", "100", "1000"));
        parameters.add(createTestCase(3, "10", "101", "1010"));

        parameters.add(createTestCase(3, "11", "1", "11"));
        parameters.add(createTestCase(3, "11", "2", "22"));
        parameters.add(createTestCase(3, "11", "10", "110"));
        parameters.add(createTestCase(3, "11", "11", "121"));
        parameters.add(createTestCase(3, "11", "12", "202"));
        parameters.add(createTestCase(3, "11", "20", "220"));
        parameters.add(createTestCase(3, "11", "21", "1001"));
        parameters.add(createTestCase(3, "11", "22", "1012"));
        parameters.add(createTestCase(3, "11", "100", "1100"));
        parameters.add(createTestCase(3, "11", "101", "1111"));

        parameters.add(createTestCase(3, "12", "1", "12"));
        parameters.add(createTestCase(3, "12", "2", "101"));
        parameters.add(createTestCase(3, "12", "10", "120"));
        parameters.add(createTestCase(3, "12", "11", "202"));
        parameters.add(createTestCase(3, "12", "12", "221"));
        parameters.add(createTestCase(3, "12", "20", "1010"));
        parameters.add(createTestCase(3, "12", "21", "1022"));
        parameters.add(createTestCase(3, "12", "22", "1111"));
        parameters.add(createTestCase(3, "12", "100", "1200"));
        parameters.add(createTestCase(3, "12", "101", "1212"));

        parameters.add(createTestCase(3, "20", "1", "20"));
        parameters.add(createTestCase(3, "20", "2", "110"));
        parameters.add(createTestCase(3, "20", "10", "200"));
        parameters.add(createTestCase(3, "20", "11", "220"));
        parameters.add(createTestCase(3, "20", "12", "1010"));
        parameters.add(createTestCase(3, "20", "20", "1100"));
        parameters.add(createTestCase(3, "20", "21", "1120"));
        parameters.add(createTestCase(3, "20", "22", "1210"));
        parameters.add(createTestCase(3, "20", "100", "2000"));
        parameters.add(createTestCase(3, "20", "101", "2020"));

        parameters.add(createTestCase(3, "21", "1", "21"));
        parameters.add(createTestCase(3, "21", "2", "112"));
        parameters.add(createTestCase(3, "21", "10", "210"));
        parameters.add(createTestCase(3, "21", "11", "1001"));
        parameters.add(createTestCase(3, "21", "12", "1022"));
        parameters.add(createTestCase(3, "21", "20", "1120"));
        parameters.add(createTestCase(3, "21", "21", "1211"));
        parameters.add(createTestCase(3, "21", "22", "2002"));
        parameters.add(createTestCase(3, "21", "100", "2100"));
        parameters.add(createTestCase(3, "21", "101", "2121"));

        parameters.add(createTestCase(3, "22", "1", "22"));
        parameters.add(createTestCase(3, "22", "2", "121"));
        parameters.add(createTestCase(3, "22", "10", "220"));
        parameters.add(createTestCase(3, "22", "11", "1012"));
        parameters.add(createTestCase(3, "22", "12", "1111"));
        parameters.add(createTestCase(3, "22", "20", "1210"));
        parameters.add(createTestCase(3, "22", "21", "2002"));
        parameters.add(createTestCase(3, "22", "22", "2101"));
        parameters.add(createTestCase(3, "22", "100", "2200"));
        parameters.add(createTestCase(3, "22", "101", "2222"));

        parameters.add(createTestCase(3, "100", "1", "100"));
        parameters.add(createTestCase(3, "100", "2", "200"));
        parameters.add(createTestCase(3, "100", "10", "1000"));
        parameters.add(createTestCase(3, "100", "11", "1100"));
        parameters.add(createTestCase(3, "100", "12", "1200"));
        parameters.add(createTestCase(3, "100", "20", "2000"));
        parameters.add(createTestCase(3, "100", "21", "2100"));
        parameters.add(createTestCase(3, "100", "22", "2200"));
        parameters.add(createTestCase(3, "100", "100", "10000"));
        parameters.add(createTestCase(3, "100", "101", "10100"));

        parameters.add(createTestCase(3, "101", "1", "101"));
        parameters.add(createTestCase(3, "101", "2", "202"));
        parameters.add(createTestCase(3, "101", "10", "1010"));
        parameters.add(createTestCase(3, "101", "11", "1111"));
        parameters.add(createTestCase(3, "101", "12", "1212"));
        parameters.add(createTestCase(3, "101", "20", "2020"));
        parameters.add(createTestCase(3, "101", "21", "2121"));
        parameters.add(createTestCase(3, "101", "22", "2222"));
        parameters.add(createTestCase(3, "101", "100", "10100"));
        parameters.add(createTestCase(3, "101", "101", "10201"));

        parameters.add(createTestCase(3, "-1", "1", "-1"));
        parameters.add(createTestCase(3, "-1", "2", "-2"));
        parameters.add(createTestCase(3, "-1", "10", "-10"));
        parameters.add(createTestCase(3, "-1", "11", "-11"));
        parameters.add(createTestCase(3, "-1", "12", "-12"));
        parameters.add(createTestCase(3, "-1", "20", "-20"));
        parameters.add(createTestCase(3, "-1", "21", "-21"));
        parameters.add(createTestCase(3, "-1", "22", "-22"));
        parameters.add(createTestCase(3, "-1", "100", "-100"));
        parameters.add(createTestCase(3, "-1", "101", "-101"));

        parameters.add(createTestCase(3, "-2", "1", "-2"));
        parameters.add(createTestCase(3, "-2", "2", "-11"));
        parameters.add(createTestCase(3, "-2", "10", "-20"));
        parameters.add(createTestCase(3, "-2", "11", "-22"));
        parameters.add(createTestCase(3, "-2", "12", "-101"));
        parameters.add(createTestCase(3, "-2", "20", "-110"));
        parameters.add(createTestCase(3, "-2", "21", "-112"));
        parameters.add(createTestCase(3, "-2", "22", "-121"));
        parameters.add(createTestCase(3, "-2", "100", "-200"));
        parameters.add(createTestCase(3, "-2", "101", "-202"));

        parameters.add(createTestCase(3, "-10", "1", "-10"));
        parameters.add(createTestCase(3, "-10", "2", "-20"));
        parameters.add(createTestCase(3, "-10", "10", "-100"));
        parameters.add(createTestCase(3, "-10", "11", "-110"));
        parameters.add(createTestCase(3, "-10", "12", "-120"));
        parameters.add(createTestCase(3, "-10", "20", "-200"));
        parameters.add(createTestCase(3, "-10", "21", "-210"));
        parameters.add(createTestCase(3, "-10", "22", "-220"));
        parameters.add(createTestCase(3, "-10", "100", "-1000"));
        parameters.add(createTestCase(3, "-10", "101", "-1010"));

        parameters.add(createTestCase(3, "-11", "1", "-11"));
        parameters.add(createTestCase(3, "-11", "2", "-22"));
        parameters.add(createTestCase(3, "-11", "10", "-110"));
        parameters.add(createTestCase(3, "-11", "11", "-121"));
        parameters.add(createTestCase(3, "-11", "12", "-202"));
        parameters.add(createTestCase(3, "-11", "20", "-220"));
        parameters.add(createTestCase(3, "-11", "21", "-1001"));
        parameters.add(createTestCase(3, "-11", "22", "-1012"));
        parameters.add(createTestCase(3, "-11", "100", "-1100"));
        parameters.add(createTestCase(3, "-11", "101", "-1111"));

        parameters.add(createTestCase(3, "-12", "1", "-12"));
        parameters.add(createTestCase(3, "-12", "2", "-101"));
        parameters.add(createTestCase(3, "-12", "10", "-120"));
        parameters.add(createTestCase(3, "-12", "11", "-202"));
        parameters.add(createTestCase(3, "-12", "12", "-221"));
        parameters.add(createTestCase(3, "-12", "20", "-1010"));
        parameters.add(createTestCase(3, "-12", "21", "-1022"));
        parameters.add(createTestCase(3, "-12", "22", "-1111"));
        parameters.add(createTestCase(3, "-12", "100", "-1200"));
        parameters.add(createTestCase(3, "-12", "101", "-1212"));

        parameters.add(createTestCase(3, "-20", "1", "-20"));
        parameters.add(createTestCase(3, "-20", "2", "-110"));
        parameters.add(createTestCase(3, "-20", "10", "-200"));
        parameters.add(createTestCase(3, "-20", "11", "-220"));
        parameters.add(createTestCase(3, "-20", "12", "-1010"));
        parameters.add(createTestCase(3, "-20", "20", "-1100"));
        parameters.add(createTestCase(3, "-20", "21", "-1120"));
        parameters.add(createTestCase(3, "-20", "22", "-1210"));
        parameters.add(createTestCase(3, "-20", "100", "-2000"));
        parameters.add(createTestCase(3, "-20", "101", "-2020"));

        parameters.add(createTestCase(3, "-21", "1", "-21"));
        parameters.add(createTestCase(3, "-21", "2", "-112"));
        parameters.add(createTestCase(3, "-21", "10", "-210"));
        parameters.add(createTestCase(3, "-21", "11", "-1001"));
        parameters.add(createTestCase(3, "-21", "12", "-1022"));
        parameters.add(createTestCase(3, "-21", "20", "-1120"));
        parameters.add(createTestCase(3, "-21", "21", "-1211"));
        parameters.add(createTestCase(3, "-21", "22", "-2002"));
        parameters.add(createTestCase(3, "-21", "100", "-2100"));
        parameters.add(createTestCase(3, "-21", "101", "-2121"));

        parameters.add(createTestCase(3, "-22", "1", "-22"));
        parameters.add(createTestCase(3, "-22", "2", "-121"));
        parameters.add(createTestCase(3, "-22", "10", "-220"));
        parameters.add(createTestCase(3, "-22", "11", "-1012"));
        parameters.add(createTestCase(3, "-22", "12", "-1111"));
        parameters.add(createTestCase(3, "-22", "20", "-1210"));
        parameters.add(createTestCase(3, "-22", "21", "-2002"));
        parameters.add(createTestCase(3, "-22", "22", "-2101"));
        parameters.add(createTestCase(3, "-22", "100", "-2200"));
        parameters.add(createTestCase(3, "-22", "101", "-2222"));

        parameters.add(createTestCase(3, "-100", "1", "-100"));
        parameters.add(createTestCase(3, "-100", "2", "-200"));
        parameters.add(createTestCase(3, "-100", "10", "-1000"));
        parameters.add(createTestCase(3, "-100", "11", "-1100"));
        parameters.add(createTestCase(3, "-100", "12", "-1200"));
        parameters.add(createTestCase(3, "-100", "20", "-2000"));
        parameters.add(createTestCase(3, "-100", "21", "-2100"));
        parameters.add(createTestCase(3, "-100", "22", "-2200"));
        parameters.add(createTestCase(3, "-100", "100", "-10000"));
        parameters.add(createTestCase(3, "-100", "101", "-10100"));

        parameters.add(createTestCase(3, "-101", "1", "-101"));
        parameters.add(createTestCase(3, "-101", "2", "-202"));
        parameters.add(createTestCase(3, "-101", "10", "-1010"));
        parameters.add(createTestCase(3, "-101", "11", "-1111"));
        parameters.add(createTestCase(3, "-101", "12", "-1212"));
        parameters.add(createTestCase(3, "-101", "20", "-2020"));
        parameters.add(createTestCase(3, "-101", "21", "-2121"));
        parameters.add(createTestCase(3, "-101", "22", "-2222"));
        parameters.add(createTestCase(3, "-101", "100", "-10100"));
        parameters.add(createTestCase(3, "-101", "101", "-10201"));

        parameters.add(createTestCase(3, "1", "-1", "-1"));
        parameters.add(createTestCase(3, "1", "-2", "-2"));
        parameters.add(createTestCase(3, "1", "-10", "-10"));
        parameters.add(createTestCase(3, "1", "-11", "-11"));
        parameters.add(createTestCase(3, "1", "-12", "-12"));
        parameters.add(createTestCase(3, "1", "-20", "-20"));
        parameters.add(createTestCase(3, "1", "-21", "-21"));
        parameters.add(createTestCase(3, "1", "-22", "-22"));
        parameters.add(createTestCase(3, "1", "-100", "-100"));
        parameters.add(createTestCase(3, "1", "-101", "-101"));

        parameters.add(createTestCase(3, "2", "-1", "-2"));
        parameters.add(createTestCase(3, "2", "-2", "-11"));
        parameters.add(createTestCase(3, "2", "-10", "-20"));
        parameters.add(createTestCase(3, "2", "-11", "-22"));
        parameters.add(createTestCase(3, "2", "-12", "-101"));
        parameters.add(createTestCase(3, "2", "-20", "-110"));
        parameters.add(createTestCase(3, "2", "-21", "-112"));
        parameters.add(createTestCase(3, "2", "-22", "-121"));
        parameters.add(createTestCase(3, "2", "-100", "-200"));
        parameters.add(createTestCase(3, "2", "-101", "-202"));

        parameters.add(createTestCase(3, "10", "-1", "-10"));
        parameters.add(createTestCase(3, "10", "-2", "-20"));
        parameters.add(createTestCase(3, "10", "-10", "-100"));
        parameters.add(createTestCase(3, "10", "-11", "-110"));
        parameters.add(createTestCase(3, "10", "-12", "-120"));
        parameters.add(createTestCase(3, "10", "-20", "-200"));
        parameters.add(createTestCase(3, "10", "-21", "-210"));
        parameters.add(createTestCase(3, "10", "-22", "-220"));
        parameters.add(createTestCase(3, "10", "-100", "-1000"));
        parameters.add(createTestCase(3, "10", "-101", "-1010"));

        parameters.add(createTestCase(3, "11", "-1", "-11"));
        parameters.add(createTestCase(3, "11", "-2", "-22"));
        parameters.add(createTestCase(3, "11", "-10", "-110"));
        parameters.add(createTestCase(3, "11", "-11", "-121"));
        parameters.add(createTestCase(3, "11", "-12", "-202"));
        parameters.add(createTestCase(3, "11", "-20", "-220"));
        parameters.add(createTestCase(3, "11", "-21", "-1001"));
        parameters.add(createTestCase(3, "11", "-22", "-1012"));
        parameters.add(createTestCase(3, "11", "-100", "-1100"));
        parameters.add(createTestCase(3, "11", "-101", "-1111"));

        parameters.add(createTestCase(3, "12", "-1", "-12"));
        parameters.add(createTestCase(3, "12", "-2", "-101"));
        parameters.add(createTestCase(3, "12", "-10", "-120"));
        parameters.add(createTestCase(3, "12", "-11", "-202"));
        parameters.add(createTestCase(3, "12", "-12", "-221"));
        parameters.add(createTestCase(3, "12", "-20", "-1010"));
        parameters.add(createTestCase(3, "12", "-21", "-1022"));
        parameters.add(createTestCase(3, "12", "-22", "-1111"));
        parameters.add(createTestCase(3, "12", "-100", "-1200"));
        parameters.add(createTestCase(3, "12", "-101", "-1212"));

        parameters.add(createTestCase(3, "20", "-1", "-20"));
        parameters.add(createTestCase(3, "20", "-2", "-110"));
        parameters.add(createTestCase(3, "20", "-10", "-200"));
        parameters.add(createTestCase(3, "20", "-11", "-220"));
        parameters.add(createTestCase(3, "20", "-12", "-1010"));
        parameters.add(createTestCase(3, "20", "-20", "-1100"));
        parameters.add(createTestCase(3, "20", "-21", "-1120"));
        parameters.add(createTestCase(3, "20", "-22", "-1210"));
        parameters.add(createTestCase(3, "20", "-100", "-2000"));
        parameters.add(createTestCase(3, "20", "-101", "-2020"));

        parameters.add(createTestCase(3, "21", "-1", "-21"));
        parameters.add(createTestCase(3, "21", "-2", "-112"));
        parameters.add(createTestCase(3, "21", "-10", "-210"));
        parameters.add(createTestCase(3, "21", "-11", "-1001"));
        parameters.add(createTestCase(3, "21", "-12", "-1022"));
        parameters.add(createTestCase(3, "21", "-20", "-1120"));
        parameters.add(createTestCase(3, "21", "-21", "-1211"));
        parameters.add(createTestCase(3, "21", "-22", "-2002"));
        parameters.add(createTestCase(3, "21", "-100", "-2100"));
        parameters.add(createTestCase(3, "21", "-101", "-2121"));

        parameters.add(createTestCase(3, "22", "-1", "-22"));
        parameters.add(createTestCase(3, "22", "-2", "-121"));
        parameters.add(createTestCase(3, "22", "-10", "-220"));
        parameters.add(createTestCase(3, "22", "-11", "-1012"));
        parameters.add(createTestCase(3, "22", "-12", "-1111"));
        parameters.add(createTestCase(3, "22", "-20", "-1210"));
        parameters.add(createTestCase(3, "22", "-21", "-2002"));
        parameters.add(createTestCase(3, "22", "-22", "-2101"));
        parameters.add(createTestCase(3, "22", "-100", "-2200"));
        parameters.add(createTestCase(3, "22", "-101", "-2222"));

        parameters.add(createTestCase(3, "100", "-1", "-100"));
        parameters.add(createTestCase(3, "100", "-2", "-200"));
        parameters.add(createTestCase(3, "100", "-10", "-1000"));
        parameters.add(createTestCase(3, "100", "-11", "-1100"));
        parameters.add(createTestCase(3, "100", "-12", "-1200"));
        parameters.add(createTestCase(3, "100", "-20", "-2000"));
        parameters.add(createTestCase(3, "100", "-21", "-2100"));
        parameters.add(createTestCase(3, "100", "-22", "-2200"));
        parameters.add(createTestCase(3, "100", "-100", "-10000"));
        parameters.add(createTestCase(3, "100", "-101", "-10100"));

        parameters.add(createTestCase(3, "101", "-1", "-101"));
        parameters.add(createTestCase(3, "101", "-2", "-202"));
        parameters.add(createTestCase(3, "101", "-10", "-1010"));
        parameters.add(createTestCase(3, "101", "-11", "-1111"));
        parameters.add(createTestCase(3, "101", "-12", "-1212"));
        parameters.add(createTestCase(3, "101", "-20", "-2020"));
        parameters.add(createTestCase(3, "101", "-21", "-2121"));
        parameters.add(createTestCase(3, "101", "-22", "-2222"));
        parameters.add(createTestCase(3, "101", "-100", "-10100"));
        parameters.add(createTestCase(3, "101", "-101", "-10201"));

        parameters.add(createTestCase(3, "-1", "-1", "1"));
        parameters.add(createTestCase(3, "-1", "-2", "2"));
        parameters.add(createTestCase(3, "-1", "-10", "10"));
        parameters.add(createTestCase(3, "-1", "-11", "11"));
        parameters.add(createTestCase(3, "-1", "-12", "12"));
        parameters.add(createTestCase(3, "-1", "-20", "20"));
        parameters.add(createTestCase(3, "-1", "-21", "21"));
        parameters.add(createTestCase(3, "-1", "-22", "22"));
        parameters.add(createTestCase(3, "-1", "-100", "100"));
        parameters.add(createTestCase(3, "-1", "-101", "101"));

        parameters.add(createTestCase(3, "-2", "-1", "2"));
        parameters.add(createTestCase(3, "-2", "-2", "11"));
        parameters.add(createTestCase(3, "-2", "-10", "20"));
        parameters.add(createTestCase(3, "-2", "-11", "22"));
        parameters.add(createTestCase(3, "-2", "-12", "101"));
        parameters.add(createTestCase(3, "-2", "-20", "110"));
        parameters.add(createTestCase(3, "-2", "-21", "112"));
        parameters.add(createTestCase(3, "-2", "-22", "121"));
        parameters.add(createTestCase(3, "-2", "-100", "200"));
        parameters.add(createTestCase(3, "-2", "-101", "202"));

        parameters.add(createTestCase(3, "-10", "-1", "10"));
        parameters.add(createTestCase(3, "-10", "-2", "20"));
        parameters.add(createTestCase(3, "-10", "-10", "100"));
        parameters.add(createTestCase(3, "-10", "-11", "110"));
        parameters.add(createTestCase(3, "-10", "-12", "120"));
        parameters.add(createTestCase(3, "-10", "-20", "200"));
        parameters.add(createTestCase(3, "-10", "-21", "210"));
        parameters.add(createTestCase(3, "-10", "-22", "220"));
        parameters.add(createTestCase(3, "-10", "-100", "1000"));
        parameters.add(createTestCase(3, "-10", "-101", "1010"));

        parameters.add(createTestCase(3, "-11", "-1", "11"));
        parameters.add(createTestCase(3, "-11", "-2", "22"));
        parameters.add(createTestCase(3, "-11", "-10", "110"));
        parameters.add(createTestCase(3, "-11", "-11", "121"));
        parameters.add(createTestCase(3, "-11", "-12", "202"));
        parameters.add(createTestCase(3, "-11", "-20", "220"));
        parameters.add(createTestCase(3, "-11", "-21", "1001"));
        parameters.add(createTestCase(3, "-11", "-22", "1012"));
        parameters.add(createTestCase(3, "-11", "-100", "1100"));
        parameters.add(createTestCase(3, "-11", "-101", "1111"));

        parameters.add(createTestCase(3, "-12", "-1", "12"));
        parameters.add(createTestCase(3, "-12", "-2", "101"));
        parameters.add(createTestCase(3, "-12", "-10", "120"));
        parameters.add(createTestCase(3, "-12", "-11", "202"));
        parameters.add(createTestCase(3, "-12", "-12", "221"));
        parameters.add(createTestCase(3, "-12", "-20", "1010"));
        parameters.add(createTestCase(3, "-12", "-21", "1022"));
        parameters.add(createTestCase(3, "-12", "-22", "1111"));
        parameters.add(createTestCase(3, "-12", "-100", "1200"));
        parameters.add(createTestCase(3, "-12", "-101", "1212"));

        parameters.add(createTestCase(3, "-20", "-1", "20"));
        parameters.add(createTestCase(3, "-20", "-2", "110"));
        parameters.add(createTestCase(3, "-20", "-10", "200"));
        parameters.add(createTestCase(3, "-20", "-11", "220"));
        parameters.add(createTestCase(3, "-20", "-12", "1010"));
        parameters.add(createTestCase(3, "-20", "-20", "1100"));
        parameters.add(createTestCase(3, "-20", "-21", "1120"));
        parameters.add(createTestCase(3, "-20", "-22", "1210"));
        parameters.add(createTestCase(3, "-20", "-100", "2000"));
        parameters.add(createTestCase(3, "-20", "-101", "2020"));

        parameters.add(createTestCase(3, "-21", "-1", "21"));
        parameters.add(createTestCase(3, "-21", "-2", "112"));
        parameters.add(createTestCase(3, "-21", "-10", "210"));
        parameters.add(createTestCase(3, "-21", "-11", "1001"));
        parameters.add(createTestCase(3, "-21", "-12", "1022"));
        parameters.add(createTestCase(3, "-21", "-20", "1120"));
        parameters.add(createTestCase(3, "-21", "-21", "1211"));
        parameters.add(createTestCase(3, "-21", "-22", "2002"));
        parameters.add(createTestCase(3, "-21", "-100", "2100"));
        parameters.add(createTestCase(3, "-21", "-101", "2121"));

        parameters.add(createTestCase(3, "-22", "-1", "22"));
        parameters.add(createTestCase(3, "-22", "-2", "121"));
        parameters.add(createTestCase(3, "-22", "-10", "220"));
        parameters.add(createTestCase(3, "-22", "-11", "1012"));
        parameters.add(createTestCase(3, "-22", "-12", "1111"));
        parameters.add(createTestCase(3, "-22", "-20", "1210"));
        parameters.add(createTestCase(3, "-22", "-21", "2002"));
        parameters.add(createTestCase(3, "-22", "-22", "2101"));
        parameters.add(createTestCase(3, "-22", "-100", "2200"));
        parameters.add(createTestCase(3, "-22", "-101", "2222"));

        parameters.add(createTestCase(3, "-100", "-1", "100"));
        parameters.add(createTestCase(3, "-100", "-2", "200"));
        parameters.add(createTestCase(3, "-100", "-10", "1000"));
        parameters.add(createTestCase(3, "-100", "-11", "1100"));
        parameters.add(createTestCase(3, "-100", "-12", "1200"));
        parameters.add(createTestCase(3, "-100", "-20", "2000"));
        parameters.add(createTestCase(3, "-100", "-21", "2100"));
        parameters.add(createTestCase(3, "-100", "-22", "2200"));
        parameters.add(createTestCase(3, "-100", "-100", "10000"));
        parameters.add(createTestCase(3, "-100", "-101", "10100"));

        parameters.add(createTestCase(3, "-101", "-1", "101"));
        parameters.add(createTestCase(3, "-101", "-2", "202"));
        parameters.add(createTestCase(3, "-101", "-10", "1010"));
        parameters.add(createTestCase(3, "-101", "-11", "1111"));
        parameters.add(createTestCase(3, "-101", "-12", "1212"));
        parameters.add(createTestCase(3, "-101", "-20", "2020"));
        parameters.add(createTestCase(3, "-101", "-21", "2121"));
        parameters.add(createTestCase(3, "-101", "-22", "2222"));
        parameters.add(createTestCase(3, "-101", "-100", "10100"));
        parameters.add(createTestCase(3, "-101", "-101", "10201"));

        return parameters;
    }

    /**
     * Creates a test case according to the specified parameters.
     *
     * @param base
     *        a number base
     * @param numberString1
     *        a number string
     * @param numberString2
     *        a number string
     * @param resultString
     *        a number string
     *
     * @return an object array
     */
    private static Object[] createTestCase(int base, String numberString1, String numberString2, String resultString) {

        return new Object[] {
               createNumber(base, numberString1), createNumber(base, numberString2), createNumber(base, resultString) };
    }

}
